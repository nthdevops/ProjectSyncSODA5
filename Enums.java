enum Direction{
	UP(0, -1),
	DOWN(0, 1),
	RIGHT(1, 0),
	LEFT(-1, 0),
	UP_RIGHT(1, -1),
	UP_LEFT(-1, -1),
	DOWN_RIGHT(1, 1),
	DOWN_LEFT(-1, 1),
	NONE(-99, -99);

	private PositionPair posPar = new PositionPair();

	Direction(int x, int y){
		this.posPar.x = x;
		this.posPar.y = y;
	}

	public PositionPair getPair(){
		return this.posPar;
	}
}

public class Enums{
	public static Direction get(int x, int y){
		for(Direction dir : Direction.values()){
			if(dir.getPair().x == x && dir.getPair().y == y)
				return dir;
		}
		return Direction.NONE;
	}
}