package tips.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import gradescope.grader.GradedTest;
import ledger.LoggedTest;
import tips.TipCalculator;

/**
 * @author Tony
 */
public class TestTips extends LoggedTest
{
	@Test		
    @GradedTest(name="Test calculator()", max_score=5)
    public void testCalculator() 
    {
		TipCalculator uut = new TipCalculator();
		assertEquals("Sent 20.00, 0.15, false", 3.0, uut.computeTip(20.00, 0.15, false), 0.01);
		assertEquals("Sent 100.00, 0.001, false", 0.1, uut.computeTip(100.00, 0.001, false), 0.01);
		assertEquals("Sent 10.00, 1, false", 10.0, uut.computeTip(10.00, 1, false), 0.01);
    }	

	@Test		
    @GradedTest(name="Test calculator() with roundup", max_score=5)
    public void testRoundup() 
    {
		TipCalculator uut = new TipCalculator();
		assertEquals("Sent 10.14, .15, true", 1.85, uut.computeTip(10.15, .15, true), 0.01);
		assertEquals("Sent 100.01, 0.001, true", 0.99, uut.computeTip(100.01, 0.001, true), 0.01);
		assertEquals("Sent 20.01, 0.15, true", 3.99, uut.computeTip(20.01, 0.15, true), 0.01);
    }
	
	private void testCommandLine(String[] args, String expected)
	{
		PrintStream original = System.out;
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		PrintStream redirectedOut = new PrintStream(buffer);
		System.setOut(redirectedOut);
		TipCalculator.not_main(args);
		System.setOut(original);
		String actual = buffer.toString();
		assertEquals("For the arguments " + Arrays.toString(args), expected, actual);
	}

	@Test		
    @GradedTest(name="Test basic command line", max_score=5)
	public void testBasicCommandLine()
	{
		String args[] = {"-price", "10.00", "-percent", "25"};
		String expected = "For $10.00 at a tip of 25.0%\n" + "Tip: $2.50\n" + "Total: $12.50\n";
		testCommandLine(args, expected);
	}

	@Test		
    @GradedTest(name="Test round up command line", max_score=5)
	public void testRoundupCommandLine()
	{
		String args[] = {"-price", "10.00", "-percent", "25", "roundup"};
		String expected = "For $10.00 at a tip of 25.0% rounded up\n" + "Tip: $3.00\n" + "Total: $13.00\n";
		testCommandLine(args, expected);
	}

	@Test		
    @GradedTest(name="Test tricky command line", max_score=10)
	public void testTrickyCommandLine()
	{
		String args[] = {"-percent", "25", "roundup", "-price", "10.00"};
		String expected = "For $10.00 at a tip of 25.0% rounded up\n" + "Tip: $3.00\n" + "Total: $13.00\n";
		testCommandLine(args, expected);
	}

	private static final String CODE_FILE= "src/tips/TipCalculator";
	@BeforeClass
	public static void grabCode()
	{
		LoggedTest.grabCode(CODE_FILE);
	}
}