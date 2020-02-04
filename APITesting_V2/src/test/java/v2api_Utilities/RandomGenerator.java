/**
 * 
 */
package v2api_Utilities;

import org.testng.annotations.Test;

/**
 * @author ayush
 *
 */
public class RandomGenerator {
	@Test(priority = 1)
	public static String getRandomMobileNumber()	{
		/*Random rand = new Random();
		int random=rand.nextInt(999999999);*/
		long timeSeed = System.nanoTime(); // to get the current date time value

        double randSeed = Math.random() * 1000; // random number generation

        long midSeed = (long) (timeSeed * randSeed); // mixing up the time and
                                                        // rand number.

                                                        // variable timeSeed
                                                        // will be unique


                                                       // variable rand will 
                                                       // ensure no relation 
                                                      // between the numbers

        String s = midSeed + "";
        String subStr = s.substring(0, 9);

        int finalSeed = Integer.parseInt(subStr);    // integer value
		String mobileNum="9"+finalSeed;
		System.out.println("Generated mobile = "+mobileNum);
		return mobileNum;
	}

	

}
