package validation.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.junit.BeforeClass;
import org.junit.Test;

import gradescope.grader.GradedTest;
import ledger.LoggedTest;
import validation.ValidationHelper;

/**
 * @author Tony
 */
public class ValidationTest extends LoggedTest
{
	@Test		
    @GradedTest(name="Test getRequiredString()", max_score=5)
    public void testRequired()
    {
		final String PROMPT = "Say anything: ";
		final String ERROR  = "You did not say anything";
		String expected = "anything";
		String expectedPrompt = PROMPT + ERROR + PROMPT + ERROR + PROMPT + ERROR + PROMPT;
		ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBuffer);
		ByteArrayInputStream inBuffer = new ByteArrayInputStream(("\n  \n\t\n"+expected+"\n").getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inBuffer));
		ValidationHelper uut = new ValidationHelper(out, in);
		
		try
		{
			String actual = uut.getRequiredString(PROMPT, ERROR);
			assertEquals("	", expected, actual);
			assertEquals("Expected 	error not displayed", expectedPrompt, outBuffer.toString());
		} catch (Exception e)
		{
			e.printStackTrace();
			fail("An exception thrown (check the output for the stack trace)" + e.getLocalizedMessage());
		}

    }

	@Test		
    @GradedTest(name="Test getIntegerInput()", max_score=5)
    public void testInteger()
    {
		final String PROMPT = "	 ";
		final String ERROR  = "Not an integer";
		int expected = 13;
		String expectedPrompt = PROMPT + ERROR + PROMPT + ERROR + PROMPT + ERROR + PROMPT;
		ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBuffer);
		ByteArrayInputStream inBuffer = new ByteArrayInputStream(("\nthirteen\n13.0\n"+expected+"\n").getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inBuffer));
		ValidationHelper uut = new ValidationHelper(out, in);
		
		try
		{
			int actual = uut.getIntegerInput(PROMPT, ERROR);
			assertEquals("Returned value did not match input", expected, actual);
			assertEquals("Expected error not displayed", expectedPrompt, outBuffer.toString());
		} catch (Exception e)
		{
			e.printStackTrace();
			fail("An exception thrown (check the output for the stack trace)" + e.getLocalizedMessage());
		}
    }

//	@Test		
//    @GradedTest(name="Test getDoubleInput()", max_score=5)
//    public void testDouble()
//    {
//		final String PROMPT = "Enter Pi to 5 digits: ";
//		final String ERROR  = "Not a double";
//		double expected = 3.14159;
//		String expectedPrompt = PROMPT + ERROR + PROMPT + ERROR + PROMPT;
//		ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
//		PrintStream out = new PrintStream(outBuffer);
//		ByteArrayInputStream inBuffer = new ByteArrayInputStream(("\npi\n"+expected+"\n").getBytes());
//		BufferedReader in = new BufferedReader(new InputStreamReader(inBuffer));
//		ValidationHelper uut = new ValidationHelper(out, in);
//		
//		try
//		{
//			double actual = uut.getDoubleInput(PROMPT, ERROR);
//			assertEquals("Returned value did not match input", expected, actual, 0.000001);
//			assertEquals("Expected error not displayed", expectedPrompt, outBuffer.toString());
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//			fail("An exception thrown (check the output for the stack trace)" + e.getLocalizedMessage());
//		}
//    }

	@Test		
    @GradedTest(name="Test getPositiveInteger()", max_score=5)
    public void testPositive()
    {
		final String PROMPT = "How many points have you earned: ";
		final String ERROR  = "Positive integer please";
		int expected = 1000;
		String expectedPrompt = PROMPT + ERROR + PROMPT + ERROR + PROMPT;
		ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBuffer);
		ByteArrayInputStream inBuffer = new ByteArrayInputStream(("-1\n\n"+expected+"\n").getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inBuffer));
		ValidationHelper uut = new ValidationHelper(out, in);
		
		try
		{
			int actual = uut.getPositiveInteger(PROMPT, ERROR);
			assertEquals("Returned value did not match input", expected, actual);
			assertEquals("Expected error not displayed", expectedPrompt, outBuffer.toString());
		} catch (Exception e)
		{
			e.printStackTrace();
			fail("An exception thrown (check the output for the stack trace)" + e.getLocalizedMessage());
		}
    }

	@Test		
    @GradedTest(name="Test getMinimumIntegerInput()", max_score=5)
    public void testMin()
    {
		final String PROMPT = "Enter the temperature in C: ";
		final String ERROR  = "Must be above absoute zero";
		final int MIN = -273;
		int expected = -273;
		String expectedPrompt = PROMPT + ERROR + PROMPT + ERROR + PROMPT;
		ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBuffer);
		ByteArrayInputStream inBuffer = new ByteArrayInputStream(("-274\n5.5\n"+expected+"\n").getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inBuffer));
		ValidationHelper uut = new ValidationHelper(out, in);
		
		try
		{
			int actual = uut.getMinimumIntegerInput(PROMPT, ERROR, MIN);
			assertEquals("Returned value did not match input", expected, actual);
			assertEquals("Expected error not displayed", expectedPrompt, outBuffer.toString());
		} catch (Exception e)
		{
			e.printStackTrace();
			fail("An exception thrown (check the output for the stack trace)" + e.getLocalizedMessage());
		}
    }

	@Test		
    @GradedTest(name="Test getSeveralIntegers()", max_score=5)
    public void testSeveral()
    {
		final String PROMPT = "Pushups on day ";
		final String ERROR  = "Must be an integer";
		int[] expected = {25, 30, 25};
		String expectedPrompt = PROMPT + PROMPT + ERROR + PROMPT + ERROR + PROMPT + PROMPT;
		ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBuffer);
		ByteArrayInputStream inBuffer = new ByteArrayInputStream(
				(expected[0]+"\n\nd\n"+expected[1]+"\n"+expected[2]+"\n").getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inBuffer));
		ValidationHelper uut = new ValidationHelper(out, in);
		
		try
		{
			int[] actual = uut.getSeveralIntegers(PROMPT, ERROR, expected.length);
			assertArrayEquals("Returned value did not match input", expected, actual);
			assertEquals("Expected error not displayed", expectedPrompt, outBuffer.toString());
		} catch (Exception e)
		{
			e.printStackTrace();
			fail("An exception thrown (check the output for the stack trace)" + e.getLocalizedMessage());
		}
    }

	private static final String CODE_FILE= "src/validation/ValidationHelper";
	@BeforeClass
	public static void grabCode()
	{
		LoggedTest.grabCode(CODE_FILE);
	}
}