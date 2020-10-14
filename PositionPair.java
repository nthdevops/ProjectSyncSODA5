public class PositionPair{
	public int x;
	public int y;

	public boolean equal(int x, int y){
		if(this.x == x && this.y == y)
			return true;
		return false;
	}
}