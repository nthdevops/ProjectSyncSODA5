public class Player extends Thread{
	Painel pnl;
	Position pos;
	Direction direcao = Direction.NONE;
	long lastMove;
	final long WAIT_MOVE = 500;

	public Player(Painel painel, int maxX, int maxY){
		this.pnl = painel;
		pos = new Position(maxX, maxY);
		while(!pos.setPosition(pos.getRandomPosition())){
			continue;
		}
		pnl.usePosition(pos.posPar());
		direcao = pos.getNewDirection();
		lastMove = System.currentTimeMillis();
	}

	public boolean move(){
		if(System.currentTimeMillis() - lastMove < WAIT_MOVE)
			return false;
		PositionPair newPos = pos.posByDirection(direcao);
		if(pos.isValid(newPos)){
			if(!pnl.usePosition(newPos)){
				this.dormirRandom();
				direcao = pos.reversa(newPos);
				return false;
			}else{
				pnl.clearPosition(pos.posPar());
				pos.setPosition(newPos);
			}
		}else{
			direcao = pos.getNewDirection();
			return false;
		}
		lastMove = System.currentTimeMillis();
		return true;
	}

	public void dormirRandom(){
		this.dormir(Aleatorio.get(3, 5)*1000);
	}

	public void dormir(long tempo){
		try{
			Thread.sleep(tempo);
		}catch(Exception e){}
	}

	@Override
	public void run() {
		while(true){
			this.move();
		}
	}
}