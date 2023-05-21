package clock.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import clock.Time;
import gradescope.grader.GradedTest;
import ledger.LoggedTest;

public class ClockTest extends LoggedTest 
{
	
	private Time tick(Time uut)
	{
		uut.advanceOneSecond();
		return uut;
	}

	@Test		
    @GradedTest(name="Test advanceOneSecond()", max_score=5)
    public void testTick() 
    {
    	assertEquals(new Time(0, 0, 1),  tick(new Time(0, 0, 0)));
    	assertEquals(new Time(0, 0, 59), tick(new Time(0, 0, 58)));
    	assertEquals(new Time(0, 1, 0),  tick(new Time(0, 0, 59)));
    	assertEquals(new Time(0, 59, 0), tick(new Time(0, 58, 59)));
    	assertEquals(new Time(1, 0, 0),  tick(new Time(0, 59, 59)));
    	assertEquals(new Time(0, 0, 0),  tick(new Time(23, 59, 59)));
    	
    	// Continuity
    	Time t = new Time(0, 0, 0);
    	assertEquals(new Time(0, 0, 1),  tick(t));
    	assertEquals(new Time(0, 0, 2),  tick(t));
    }
	
	@Test		
    @GradedTest(name="Test compareTo()", max_score=5)
    public void testCompare() 
    {
		Time t1 = new Time(0, 0, 4);
		assertEquals(0, t1.compareTo(t1));
		assertEquals(0, t1.compareTo(new Time(0, 0, 4)));
		
		Time t2 = new Time(0, 0, 5);
		assertEquals(-1, t1.compareTo(t2));
		assertEquals(1, t2.compareTo(t1));
		
		assertEquals(1, new Time(2, 2, 2).compareTo(new Time(1, 2, 2)));
		assertEquals(1, new Time(2, 2, 2).compareTo(new Time(2, 1, 2)));
		assertEquals(1, new Time(2, 2, 2).compareTo(new Time(2, 2, 1)));

		assertEquals(-1, new Time(1, 2, 2).compareTo(new Time(2, 2, 2)));
		assertEquals(-1, new Time(2, 1, 2).compareTo(new Time(2, 2, 2)));
		assertEquals(-1, new Time(2, 2, 1).compareTo(new Time(2, 2, 2)));
    }

	private Time add(Time t1, Time t2)
	{
		t1.add(t2);
		return t1;
	}
	
	@Test		
    @GradedTest(name="Test add()", max_score=5)
    public void testAdd() 
    {
		assertEquals(new Time(3, 3, 3),    add(new Time(1, 1, 1), new Time(2, 2, 2)));
		assertEquals(new Time(0, 1, 0),    add(new Time(0, 0, 59), new Time(0, 0, 1)));
		assertEquals(new Time(0, 59, 1),   add(new Time(0, 59, 0), new Time(0, 0, 1)));
		assertEquals(new Time(1, 0, 0),    add(new Time(0, 59, 59), new Time(0, 0, 1)));
		assertEquals(new Time(0, 0, 0),    add(new Time(23, 0, 0), new Time(1, 0, 0)));
		assertEquals(new Time(0, 0, 0),    add(new Time(23, 59, 0), new Time(0, 1, 0)));
		assertEquals(new Time(23, 59, 58), add(new Time(23, 59, 59), new Time(23, 59, 59)));
    }	

	private Time subtract(Time t1, Time t2)
	{
		t1.subtract(t2);
		return t1;
	}
	
	@Test		
    @GradedTest(name="Test subtract()", max_score=5)
    public void testSubtract() 
    {
		assertEquals(new Time(1, 1, 1),    subtract(new Time(2, 2, 2), new Time(1, 1, 1)));
		assertEquals(new Time(0, 0, 59),    subtract(new Time(0, 1, 0), new Time(0, 0, 1)));
		assertEquals(new Time(0, 59, 0),    subtract(new Time(1, 0, 0), new Time(0, 1, 0)));
		assertEquals(new Time(0, 59, 59),    subtract(new Time(1, 0, 0), new Time(0, 0, 1)));
		assertEquals(new Time(0, 0, 0),    subtract(new Time(1, 1, 1), new Time(1, 1, 1)));
		assertEquals(new Time(1, 0, 59),    subtract(new Time(1, 1, 1), new Time(0, 0, 2)));
		assertEquals(new Time(0, 58, 59),    subtract(new Time(1, 1, 1), new Time(0, 2, 2)));
		assertEquals(new Time(22, 58, 59),    subtract(new Time(1, 1, 1), new Time(2, 2, 2)));
    }

	private static final String CODE_FILE= "src/clock/Time";
	@BeforeClass
	public static void grabCode()
	{
		LoggedTest.grabCode(CODE_FILE);
	}
}