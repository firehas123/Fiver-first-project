package validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import ledger.LoggedTest;

/**
 * @author Tony
 * Use this class to run your validation tests by hand rather than the automated tester
 * ----You do not need to edit or change this class at all----
 */
public class ByHandTester extends LoggedTest
{
	public static void main(String[] args) throws IOException
	{
		try {
		LoggedTest.grabCode(CODE_FILE); // This line logs your attempt in the ledger.
		ValidationHelper uut = new ValidationHelper(System.out, new BufferedReader(new InputStreamReader(System.in)));
		String favoriteColor = uut.getRequiredString("What is your favorite color: ",
                                                     "Input is required");
		System.out.println("I love " + favoriteColor);
		
		int luckyNumber = uut.getIntegerInput("Your lucky number: ", 
				                              "You must provide an integer value");
		System.out.println("I hope the lottery includes " + luckyNumber);
		
		int age = uut.getPositiveInteger("Your age: ",
		                                 "You must provide a positive integer value");
		System.out.println("You look good for " + age);
		
		int randomNumInput = uut.getNumber("Enter a number: ",
		                               "Invalid input you must provide an integer value");
		System.out.println("Nice number " + randomNumInput);
		
		int year = uut.getMinimumIntegerInput("Birth year: ",
		                                      "Your integer must be greater than " + 1900, 
		                                      1900);
		System.out.println("Are you sure that you are " + age + "if you were born in " + year);
		
		int[] lottery = uut.getSeveralIntegers("Pick a number ", 
				                               "An integer is required", 
				                               3); //3 is here the number values to take input from the user
		System.out.println("Your lottery picks are: " + Arrays.toString(lottery));
		}catch(Exception e) {
		e.printStackTrace();
		}	
	}

	private static final String CODE_FILE= "src/validation/ValidationHelper";
}
