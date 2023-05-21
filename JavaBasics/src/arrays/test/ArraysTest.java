package arrays.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import arrays.BasicArrayManipulations;
import gradescope.grader.GradedTest;
import ledger.LoggedTest;

/**
 * @author Tony
 */
public class ArraysTest extends LoggedTest
{
    int[] basic = {2, 5, 10, 15};
    int[] negative = {0, -5, -10};
    int[] mixed = {-5, -1, 0, 1, 5};
    int[] empty = {};
    int[] multiple = {0, 5, 2, 3, 5, 2, -5, 5};

	@Test		
    @GradedTest(name="Test total()", max_score=5)
    public void testTotal() 
    {
        BasicArrayManipulations uut = new BasicArrayManipulations();
		
        assertEquals("Sent " + Arrays.toString(basic), 32, uut.total(basic));
        assertEquals("Sent " + Arrays.toString(negative), -15, uut.total(negative));
        assertEquals("Sent " + Arrays.toString(mixed), 0, uut.total(mixed));
        assertEquals("Sent " + Arrays.toString(empty), 0, uut.total(empty));
    }
	
	@Test		
    @GradedTest(name="Test mean()", max_score=5)
    public void testMean() 
    {
        BasicArrayManipulations uut = new BasicArrayManipulations();
		
        assertEquals("Sent " + Arrays.toString(basic), 8.0, uut.mean(basic), 0.05);
        assertEquals("Sent " + Arrays.toString(negative), -5.0, uut.mean(negative), 0.05);
        assertEquals("Sent " + Arrays.toString(mixed), 0.0, uut.mean(mixed), 0.05);
        assertEquals("Sent " + Arrays.toString(empty), 0.0, uut.mean(empty), 0.05);
    }
	
	@Test		
    @GradedTest(name="Test count()", max_score=5)
    public void testCount() 
    {
        BasicArrayManipulations uut = new BasicArrayManipulations();
		
        assertEquals("Sent " + Arrays.toString(basic), 1, uut.count(basic, 10));
        assertEquals("Sent " + Arrays.toString(negative), 0, uut.count(negative, 42));
        assertEquals("Sent " + Arrays.toString(multiple), 3, uut.count(multiple, 5));
        assertEquals("Sent " + Arrays.toString(empty), 0, uut.count(empty, 0));
    }

	@Test		
    @GradedTest(name="Test median()", max_score=5)
    public void testMedian() 
    {
        BasicArrayManipulations uut = new BasicArrayManipulations();
		
        assertEquals("Sent " + Arrays.toString(basic), 7.5, uut.median(basic), 0.05);
        assertEquals("Sent " + Arrays.toString(negative), -5.0, uut.median(negative), 0.05);
        assertEquals("Sent " + Arrays.toString(multiple), 2.5, uut.median(multiple), 0.05);
        assertEquals("Sent " + Arrays.toString(mixed), 0.0, uut.median(mixed), 0.05);
        assertEquals("Sent " + Arrays.toString(empty), 0.0, uut.median(empty), 0.05);
    }

	@Test		
    @GradedTest(name="Test largest()", max_score=5)
    public void testLargest() 
    {
        BasicArrayManipulations uut = new BasicArrayManipulations();
		
        assertEquals("Sent " + Arrays.toString(basic), 15, uut.largest(basic));
        assertEquals("Sent " + Arrays.toString(negative), 0, uut.largest(negative));
        assertEquals("Sent " + Arrays.toString(multiple), 5, uut.largest(multiple));
        assertEquals("Sent " + Arrays.toString(mixed), 5, uut.largest(mixed));
        assertEquals("Sent " + Arrays.toString(empty), 0, uut.largest(empty));
    }
	
	@Test		
    @GradedTest(name="Test smallest()", max_score=5)
    public void testSmallest() 
    {
        BasicArrayManipulations uut = new BasicArrayManipulations();
		
        assertEquals("Sent " + Arrays.toString(basic), 2, uut.smallest(basic));
        assertEquals("Sent " + Arrays.toString(negative), -10, uut.smallest(negative));
        assertEquals("Sent " + Arrays.toString(multiple), -5, uut.smallest(multiple));
        assertEquals("Sent " + Arrays.toString(mixed), -5, uut.smallest(mixed));
        assertEquals("Sent " + Arrays.toString(empty), 0, uut.smallest(empty));
    }

	@Test		
    @GradedTest(name="Test tenTimes()", max_score=10)
    public void testTenTimes() 
    {
        BasicArrayManipulations uut = new BasicArrayManipulations();
        int[] positive    = {1, 10, 100};
        int[] negativeTen = {-1, -10, -100};
		
        assertTrue("Sent " + Arrays.toString(positive), uut.tenTimes(positive));
        assertTrue("Sent " + Arrays.toString(negativeTen), uut.tenTimes(negativeTen));
        assertFalse("Sent " + Arrays.toString(negative), uut.tenTimes(negative));
        assertFalse("Sent " + Arrays.toString(mixed), uut.tenTimes(mixed));
        assertFalse("Sent " + Arrays.toString(empty), uut.tenTimes(empty));
    }	

	private static final String CODE_FILE= "src/arrays/BasicArrayManipulations";
	@BeforeClass
	public static void grabCode()
	{
		LoggedTest.grabCode(CODE_FILE);
	}
}