import java.util.Random;

public class Aleatorio{
	private static Random gerador = new Random();

	public static int get(int min, int max){
		max++;
		return gerador.nextInt(max - min)+min;
	}
}