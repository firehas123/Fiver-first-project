package spelling.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import gradescope.grader.GradedTest;
import ledger.LoggedTest;
import spelling.AudioHelper;
import spelling.ManualSpellingBee;
import spelling.SpellingBeeHelper;
import spelling.SpellingWord;
import spelling.TestResults;

/**
 * @author Tony
 */
public class SpellingTest extends LoggedTest
{
	@Test		
    @GradedTest(name="Test loadWords()", max_score=5)
    public void testLoad() 
    {
		try 
		{
			SpellingBeeHelper uut = new SpellingBeeHelper();
			List<SpellingWord> words = uut.loadWords(ManualSpellingBee.SPELLING_WORDS_FILE);
			assertEquals("Number of words does not match", 6, words.size());

			SpellingWord word = new SpellingWord("cat", "sounds/Cat.wav");
			assertTrue("Expected " + word, words.contains(word));
			
			word = new SpellingWord("curmudgeon", "sounds/Curmudgeon.wav");
			assertTrue("Expected " + word, words.contains(word));
		} catch (IOException e) 
		{
			e.printStackTrace();
			fail("Exception occurred: " + e.getLocalizedMessage());
		}
    }

	/**
	 * A helper to add missed spelling words 
	 * @param uut
	 * @return
	 * @throws IOException
	 */
	private TestResults addAttempts(SpellingBeeHelper uut) throws IOException
	{
		Random r = new Random();
		TestResults results = new TestResults();
		for (int words = 0; words < r.nextInt(10) + 5; words++)
		{
			SpellingWord word = new SpellingWord("Word" + words, "");
			results.totalWords++;
			boolean mistake = false;
			for (int mistakes = 0; mistakes < r.nextInt(4); mistakes++)
			{
				mistake = true;
				long duration = r.nextInt(2000) + 1000;
				uut.recordAttempt(word, "wrong"+mistakes, duration);
				results.totalDuration += duration;
				results.incorrectAttempts++;
			}
			if(! mistake || r.nextDouble() > .17)
			{
				long duration = r.nextInt(2000) + 1000;
				uut.recordAttempt(word, word.getWord(), duration);
				results.totalDuration += duration;
				results.correct++;
			}
		}
		return results;
	}
	
	@Test		
    @GradedTest(name="Test addAttempts() and gradeTest()", max_score=10)
    public void testReporting() 
    {
		// Clear the old file
		try 
		{ 
			new FileOutputStream(SpellingBeeHelper.ATTEMPT_FILE, false).close(); 
		} catch (IOException e) 
		{
			fail("Could not clear the attempts file so no test conducted");
		}
		
		try
		{
			SpellingBeeHelper uut = new SpellingBeeHelper();
			TestResults expected = addAttempts(uut);
			TestResults actual   = uut.gradeTest(SpellingBeeHelper.ATTEMPT_FILE);
			assertEquals("The grading did not match", expected, actual);
		} catch (Exception e)
		{
			fail("Error in test " + e.getLocalizedMessage());
		}
    }

    private void testUI(List<SpellingWord> words, String inputs, String expected) 
    {
		SpellingBeeHelper uut = new SpellingBeeHelper();
		AudioHelper.testing = true;
		
		try
		{
			ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
			PrintStream out = new PrintStream(outBuffer);
			
			byte[] data = inputs.getBytes();
			ByteArrayInputStream inBuffer = new ByteArrayInputStream(data);
			BufferedReader in = new BufferedReader(new InputStreamReader(inBuffer));
			
			uut.promptTestTaker(words, in, out);
			String actual   = outBuffer.toString();
			assertEquals(expected, actual);
			
		} catch(Exception e)
		{
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
    }

	@Test		
    @GradedTest(name="Test Happy Path promptTestTaker()", max_score=10)
    public void testHappy() 
    {
		List<SpellingWord> words = new ArrayList<SpellingWord>();
		words.add(new SpellingWord("cat", "sounds/Cat.wav"));
		words.add(new SpellingWord("inheritance", "sounds/Inheritance.wav"));
	
		String inputs = "cat\ninheritance\n";
		String expected = SpellingBeeHelper.PROMPT + 
				          SpellingBeeHelper.PROMPT + 
				          SpellingBeeHelper.COMPLETED + System.lineSeparator();
		testUI(words, inputs, expected);
    }

	@Test		
    @GradedTest(name="Test Empty promptTestTaker()", max_score=3)
    public void testEmpty() 
    {
		List<SpellingWord> words = new ArrayList<SpellingWord>();
		words.add(new SpellingWord("cat", "sounds/Cat.wav"));
	
		String inputs = "\ncat\n";
		String expected = SpellingBeeHelper.PROMPT + 
						  SpellingBeeHelper.NO_INPUT_RESPONSE + System.lineSeparator() +
				          SpellingBeeHelper.PROMPT + 
				          SpellingBeeHelper.COMPLETED + System.lineSeparator();
		testUI(words, inputs, expected);
    }
	
	@Test		
    @GradedTest(name="Test Mispelling promptTestTaker()", max_score=3)
    public void testMispell() 
    {
		List<SpellingWord> words = new ArrayList<SpellingWord>();
		words.add(new SpellingWord("cat", "sounds/Cat.wav"));
	
		String inputs = "kat\n\ncat\n";
		String expected = SpellingBeeHelper.PROMPT + 
						  SpellingBeeHelper.INCORRECT + System.lineSeparator() +
				          SpellingBeeHelper.PROMPT + 
				          SpellingBeeHelper.COMPLETED + System.lineSeparator();
		testUI(words, inputs, expected);
    }

	@Test		
    @GradedTest(name="Test Skip Word promptTestTaker()", max_score=3)
    public void testSkip() 
    {
		List<SpellingWord> words = new ArrayList<SpellingWord>();
		words.add(new SpellingWord("cat", "sounds/Cat.wav"));
	
		String inputs = "kat\nn\n";
		String expected = SpellingBeeHelper.PROMPT + 
						  SpellingBeeHelper.INCORRECT + System.lineSeparator() +
				          SpellingBeeHelper.COMPLETED + System.lineSeparator();
		testUI(words, inputs, expected);
    }

	private static final String CODE_FILE= "src/spelling/SpellingBeeHelper";
	@BeforeClass
	public static void grabCode()
	{
		LoggedTest.grabCode(CODE_FILE);
	}
}