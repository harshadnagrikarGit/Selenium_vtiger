package commonutils;

import java.util.Random;

public class JavaUtil {

	public int getRandomNumber() {
		
		Random random = new Random();
		int ran	= random.nextInt(500);
		return ran;
	}
}
