package impersonator.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import gradescope.grader.GradedTest;
import impersonator.CelebrityImpersonator;
import ledger.LoggedTest;

/**
 * @author Tony
 */
public class TestImpersonator extends LoggedTest
{
    String quote = "Four score and seven years ago";

	private void test(String name, String expected)
	{
		CelebrityImpersonator uut = new CelebrityImpersonator();
		assertEquals("Sent : \"" + quote + "\"", expected, uut.generateQuote(quote, name));
	}
	
	@Test		
    @GradedTest(name="Test Canadian", max_score=5)
    public void testCanadian() 
    {
		test("Canadian", "Four score and seven years ago, eh?");
    }

	@Test		
    @GradedTest(name="Test Valley Girl", max_score=5)
    public void testValley() 
    {
		test("Valley Girl", "like Four like score like and like seven like years like ago");
    }
	
	@Test		
    @GradedTest(name="Test Shatner", max_score=5)
    public void testShatner() 
    {
		test("Shatner", "Four...\nscore...\nand...\nseven...\nyears...\nago...\n");
    }

	@Test		
    @GradedTest(name="Test Pirate", max_score=5)
    public void testPirate() 
    {
		test("Pirate", "FouRRRr scoRRRre and seven yeaRRRrs ago");
    }

	@Test		
    @GradedTest(name="Test Zatanna", max_score=5)
    public void testZatanna() 
    {
		test("Zatanna", "ruoF erocs dna neves sraey oga");
    }

	@Test		
    @GradedTest(name="Test Yoda", max_score=10)
    public void testYoda() 
    {
		test("Yoda", "score Four seven and ago years");
    }

	private static final String CODE_FILE= "src/impersonator/CelebrityImpersonator";
	@BeforeClass
	public static void grabCode()
	{
		LoggedTest.grabCode(CODE_FILE);
	}
}