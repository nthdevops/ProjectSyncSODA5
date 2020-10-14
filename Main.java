public class Main {
	public static void main(String[] args){
		int maxX = 50;
		int maxY = 50;
		//Mínimo e máximo de players
		int minP = 20;
		int maxP = 30;

		int numPlayers = Aleatorio.get(minP, maxP);
		Painel painel = new Painel(maxX, maxY);
		Player[] players = new Player[numPlayers];

		for(Player p : players){
			p = new Player(painel, maxX, maxY);
			p.start();
		}
	}
}