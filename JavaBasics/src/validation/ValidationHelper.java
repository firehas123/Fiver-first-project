package validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class ValidationHelper
{
	private PrintStream out;
	private BufferedReader in;

	/**
	 * Prompt the user until they provide a non-empty that is also not whitespace
	 * @param prompt - the message to show the user prompting for input
	 * @param error - the error message to show the user when incorrect
	 * @return the user's non-blank String
	 * @throws IOException
	 */
	public String getRequiredString(String prompt, String error) throws Exception
	{
		out.print(prompt);
		String input = in.readLine();
		 if (!input.matches("[a-zA-Z]+")) {
             throw new Exception("Invalid input must enter alphabetic characters only");
         }
		return input;
	}

	/**
	 * Prompt the user until they provide valid integer value 
	 * @param prompt - the message to show the user prompting for input
	 * @param error - the error message to show the user when incorrect
	 * @return the user's integer value
	 * @throws IOException
	 */
	public int getIntegerInput(String prompt, String error) throws Exception
	{
		out.print(prompt);
		String input = in.readLine();
		if (input.contains(".") || input.contains(",")) {
            throw new Exception("Invalid input no decimal values allowed");
        }
		return Integer.parseInt(input);
	}

//	/**
//	 * Prompt the user until they provide valid positive value
//	 * @param prompt - the message to show the user prompting for input
//	 * @param error - the error message to show the user when incorrect
//	 * @return
//	 * @throws IOException
//	 */
//	public double getDoubleInput(String prompt, String error) throws IOException
//	{
//		out.print(prompt);
//		String input = in.readLine();
//		return Double.parseDouble(input);
//	}

	/**
	 * Prompt the user until they provide valid floating point number
	 * @param prompt - the message to show the user prompting for input
	 * @param error - the error message to show the user when incorrect
	 * @return the user's positive integer
	 * @throws IOException
	 */
	public int getPositiveInteger(String prompt, String error) throws Exception
	{
		out.print(prompt);
		String input = in.readLine();
		if(Integer.parseInt(input)<=0) {
			throw new Exception("Invalid input number should be not negative");
		}
		return Integer.parseInt(input);
	}
	
	/**
	 * Prompt the user until they provide an integer greater than or equal to the minimum
	 * @param prompt - the message to show the user prompting for input
	 * @param error - the error message to show the user when incorrect
	 * @param min - the minimum value acceptable
	 * @return the user's selected integer at or above the minimum
	 * @throws IOException
	 */
	public int getMinimumIntegerInput(String prompt, String error, int min) throws Exception
	{
		int minVal = 1900;
		out.print(prompt);
		String input = in.readLine();
		if(Integer.parseInt(input)<minVal) {
			throw new Exception(error);
		}
		return Integer.parseInt(input);
	}
	
	/**
	 * Prompt the user until they provide a predetermined number of integer values
     *  e.g.,  if you passed in "Test Score " this function would prompt
     *      Test Score 1:
     *      Test Score 2:
     *      Test Score ....(up to the count)
	 * @param prompt - the message to show the user prompting for input
	 * @param error - the error message to show the user when incorrect
	 * @param count - the number of required integers from the user
	 * @return an array equal to the size of count filled with the user's integers 
	 * @throws IOException
	 */
	public int[] getSeveralIntegers(String prompt, String error, int count) throws Exception
	{
		int [] values = new int[count];
		int outerLoop = 0;
		out.print(prompt);
		System.out.println("Enter "+count+" values\n");
		while(outerLoop<=count) {
			values[outerLoop] = getNumber("Enter a number: ",
                "Invalid input you must provide an integer value");
			
		outerLoop++;
		}
		
		return values;
	}

	public ValidationHelper(PrintStream out, BufferedReader in)
	{
		this.out = out;
		this.in = in;
	}
	
	/**
	 * 
	 * @param string
	 * @param string2
	 * @return integer value unless it's not a number will throw exception
	 */
	public int getNumber(String prompt, String error) throws Exception{
		out.print(prompt);
		
		String input = in.readLine();
		if (!input.matches("\\d+")) {
		    throw new Exception(error);
		}
		return Integer.parseInt(input);
	}
}