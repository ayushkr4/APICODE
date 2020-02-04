package v2api_Utilities;

import java.util.Random;

public class RandomStringGenerator {
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String generateRandomString(int RANDOM_STRING_LENGTH){
	StringBuffer randStr = new StringBuffer();
	for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	int number = getRandomNumber();
	char ch = CHAR_LIST.charAt(number);
	randStr.append(ch);
	}
	return randStr.toString().toUpperCase();
}
	
	private static int getRandomNumber() {
	int randomInt = 0;
	Random randomGenerator = new Random();
	randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	if (randomInt - 1 == -1) {
	return randomInt;
	} else {
	return randomInt - 1;
	}
	}

}