import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.concurrent.Semaphore;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Painel extends JFrame {

	private static final long serialVersionUID = 1234567L;

	private int DIM_X;
	private int DIM_Y;

	public static final Color BACKGROUND_CLEAR = Color.lightGray;
	public static final Color BACKGROUND_USED = Color.blue;

	public Semaphore[][] posicoesDisponiveis;

	private JPanel[][] squares;
	private JPanel mainPanel;
	
		public Painel(int DIM_X, int DIM_Y) {
      setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			this.DIM_X = DIM_X;
			this.DIM_Y = DIM_Y;

			posicoesDisponiveis = new Semaphore[DIM_X][DIM_Y];
			squares = new JPanel[DIM_X][DIM_Y];
			mainPanel = new JPanel(new GridLayout(DIM_X, DIM_Y));

      this.init();
      
      this.pack();
      this.setVisible(true);
		}

	private void init() {
		Container c = getContentPane();

		for (int y = 0; y < DIM_Y; y++) {
			for (int x = 0; x < DIM_X; x++) {
				posicoesDisponiveis[x][y] = new Semaphore(1);
				squares[x][y] = new JPanel();
				squares[x][y].setPreferredSize(new Dimension(12,12));
				this.squares[x][y].setBackground(BACKGROUND_CLEAR);
				squares[x][y].setBorder(BorderFactory.createLineBorder(Color.white));
				mainPanel.add(squares[x][y]);
			}
		}
		c.add(mainPanel);
	}

	public boolean clearPosition(int x, int y){
		if(this.permits(x, y) == 0){
			this.liberar(x, y);
			this.squares[x][y].setBackground(BACKGROUND_CLEAR);
			return true;
		}
		return false;
	}

	public boolean clearPosition(PositionPair posPar){
		return clearPosition(posPar.x, posPar.y);
	}

	public boolean usePosition(int x, int y){
		if(this.adquirir(x, y)){
			this.squares[x][y].setBackground(BACKGROUND_USED);
			return true;
		}
		return false;
	}

	public boolean usePosition(PositionPair posPar){
		return this.usePosition(posPar.x, posPar.y);
	}

	public Semaphore semaforo(int x, int y){
		return this.posicoesDisponiveis[x][y];
	}

	public boolean adquirir(int x, int y){
		return this.semaforo(x, y).tryAcquire();
	}

	public void liberar(int x, int y){
		this.semaforo(x, y).release();
	}

	private int permits(int x, int y){
		return this.semaforo(x, y).availablePermits();
	}

	public boolean livre(int x, int y){
		if(this.permits(x, y) == 1)
			return true;
		return false;
	}

	public boolean livre(PositionPair posPar){
		return this.livre(posPar.x, posPar.y);
	}
}