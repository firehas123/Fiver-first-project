package strings.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import gradescope.grader.GradedTest;
import ledger.LoggedTest;
import strings.BasicTextManipulations;

/**
 * @author Tony
 */
public class StringTest extends LoggedTest
{
	@Test		
    @GradedTest(name="Test reverse()", max_score=5)
    public void testReverse() 
    {
        BasicTextManipulations uut = new BasicTextManipulations();
		
		assertEquals("Sent: \"123456789\"", "987654321", uut.reverse("123456789"));
		assertEquals("Sent: \"Tony Lowe\"", "ewoL ynoT", uut.reverse("Tony Lowe"));
		assertEquals("Sent: \"racecar\"", "racecar", uut.reverse("racecar"));
		assertEquals("Sent: \"\"", "", uut.reverse(""));
    }
	
	@Test		
    @GradedTest(name="Test count(char)", max_score=5)
    public void testCountChar() 
    {
        BasicTextManipulations uut = new BasicTextManipulations();
        
		assertEquals("Sent: \"Tony Lowe\", 'o'", 2, uut.count("Tony Lowe", 'o'));
		assertEquals("Sent: \"Tony Lowe\", ' '", 1, uut.count("Tony Lowe", ' '));
		assertEquals("Sent: \"Tony Lowe\", 'z'", 0, uut.count("Tony Lowe", 'z'));
		assertEquals("Sent: \"racecar\", 'r'", 2, uut.count("racecar", 'r'));
		assertEquals("Sent: \"\", ' '", 0, uut.count("", ' '));
		
    }	

	@Test		
    @GradedTest(name="Test onlyAlpha()", max_score=5)
    public void testOnlyAlpha() 
    {
        BasicTextManipulations uut = new BasicTextManipulations();

        assertEquals("Sent: \"Tony Lowe\"", "TonyLowe", uut.onlyAlpha("Tony Lowe"));
		assertEquals("Sent: \"I'm excited!\"", "Imexcited", uut.onlyAlpha("I'm excited!"));
		assertEquals("Sent: \"&*(&#$*\"", "", uut.onlyAlpha("&*(&#$*"));
		assertEquals("Sent: \"\"", "", uut.onlyAlpha(""));
       
    }       

	@Test		
    @GradedTest(name="Test count(String)", max_score=5)
    public void testCountString() 
    {
        BasicTextManipulations uut = new BasicTextManipulations();
        
		assertEquals("Sent: \"Tony Lowe\", \"o\"", 2, uut.count("Tony Lowe", "o"));
		assertEquals("Sent: \"Tony Lowe\", \"Tony\"", 1, uut.count("Tony Lowe", "Tony"));
		assertEquals("Sent: \"Mississippi\", \"ss\"", 2, uut.count("Mississippi", "ss"));
		assertEquals("Sent: \"\", ' '", 0, uut.count("", " "));
    }	

	@Test		
    @GradedTest(name="Test markDoubles()", max_score=10)
    public void testMarkDoubles() 
    {
        BasicTextManipulations uut = new BasicTextManipulations();

        assertEquals("Sent: \"Mississippi\"", "Mis2sis2sip2pi", uut.markDoubles("Mississippi"));
        assertEquals("Sent: \"aaaa\"", "a2a2a2a", uut.markDoubles("aaaa"));
        assertEquals("Sent: \"\"", "", uut.markDoubles(""));
    }
	
	@Test		
    @GradedTest(name="Test isPalindrome()", max_score=5)
    public void testPalindrome() 
    {
        BasicTextManipulations uut = new BasicTextManipulations();

        assertTrue("Sent: \"racecar\"", uut.isPalindrome("racecar"));
        assertTrue("Sent: \"madam\"", uut.isPalindrome("madam"));
        assertFalse("Sent: \"Madam\"", uut.isPalindrome("Madam"));
        assertTrue("Sent: \"\"", uut.isPalindrome(""));
    }
	
	@Test		
    @GradedTest(name="Test swapCase()", max_score=5)
    public void testSwapCase() 
    {
        BasicTextManipulations uut = new BasicTextManipulations();

        assertEquals("Sent: \"Tony Lowe\"", "tONY lOWE", uut.swapCase("Tony Lowe"));
        assertEquals("Sent: \"123!@#$%\"", "!@#$%", uut.swapCase("123!@#$%"));
        assertEquals("Sent: \"aaAA\"", "AAaa", uut.swapCase("aaAA"));
        assertEquals("Sent: \"\"", "", uut.swapCase(""));
    }

	@Test		
    @GradedTest(name="Test countNs()", max_score=10)
    public void testCountNs() 
    {
        BasicTextManipulations uut = new BasicTextManipulations();

        assertEquals("Sent: \"Tony Lowe\"", 1, uut.countNs("Tony Lowe"));
        assertEquals("Sent: \"n\\nn\"", 2, uut.countNs("n\nn"));
        assertEquals("Sent: \"nn\\nnn\\n\"", 4, uut.countNs("nn\nnn\n"));
        assertEquals("Sent: \"\"", 0, uut.countNs(""));
    }

	private static final String CODE_FILE= "src/strings/BasicTextManipulations";
	@BeforeClass
	public static void grabCode()
	{
		LoggedTest.grabCode(CODE_FILE);
	}
}