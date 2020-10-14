public class Position{
	PositionPair pos = new PositionPair();
	public int maxX;
	public int maxY;

	public Position(int maxX, int maxY){
		this.maxX = maxX-1;
		this.maxY = maxY-1;
	}

	public int x(){
		return this.pos.x;
	}

	public int y(){
		return this.pos.y;
	}

	public PositionPair posPar(){
		return this.pos;
	}

	public boolean setPosition(int x, int y){
		boolean setDone = this.isValid(x, y);
		if(setDone){
			pos.x = x;
			pos.y = y;
		}
		return setDone;
	}

	public boolean setPosition(PositionPair posPar){
		return this.setPosition(posPar.x, posPar.y);
	}

	public boolean isValid(int x, int y){
		if(x <= this.maxX && x >= 0 && y <= this.maxY && y >= 0){
			return true;
		}
		return false;
	}

	public boolean isValid(PositionPair posPar){
		return this.isValid(posPar.x, posPar.y);
	}

	public Direction getNewDirection(){
		int moveX = getRandomMove(pos.x, maxX, false);
		int moveY = getRandomMove(pos.y, maxY, false);
		if(moveX == 0 && moveY == 0){
			if(Aleatorio.get(0, 1) == 0)
				moveX = getRandomMove(pos.x, maxX, true);
			else
				moveY = getRandomMove(pos.y, maxY, true);
		}
		return Enums.get(moveX, moveY);
	}

	public int getRandomMove(int posVal, int max, boolean noZero){
		int returnVal;
		if(posVal == 0){
			returnVal = Aleatorio.get(0,1);
			if(noZero && returnVal == 0)
				returnVal = 1;
		}else if(posVal == max){
			returnVal = Aleatorio.get(-1, 0);
			if(noZero && returnVal == 0)
				returnVal = -1;
		}else{
			returnVal = Aleatorio.get(-1, 1);
			if(noZero && returnVal == 0){
				if(Aleatorio.get(0,1) == 0)
					returnVal = -1;
				else
					returnVal = 1;
			}
		}

		return returnVal;
	}

	public Direction reversa(int x, int y){
		PositionPair newPos = new PositionPair();
		if(x != this.pos.x)
			newPos.x = (x - this.pos.x)*-1;
		else
			newPos.x = 0;
		if(y != this.pos.y)
			newPos.y = (y - this.pos.y)*-1;
		else
			newPos.y = 0;
		return Enums.get(newPos.x, newPos.y);
	}

	public Direction reversa(PositionPair posPar){
		return this.reversa(posPar.x, posPar.y);
	}

	public PositionPair getRandomPosition(){
		PositionPair newRandom = new PositionPair();
		newRandom.x = Aleatorio.get(0, this.maxX);
		newRandom.y = Aleatorio.get(0, this.maxY);

		return newRandom;
	}

	public PositionPair posByDirection(Direction dir){
		PositionPair newPos = new PositionPair();
		PositionPair sumDirection = dir.getPair();
		newPos.x = pos.x + sumDirection.x;
		newPos.y = pos.y + sumDirection.y;

		return newPos;
	}
}