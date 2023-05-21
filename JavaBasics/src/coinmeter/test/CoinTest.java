package coinmeter.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.BeforeClass;
import org.junit.Test;

import coinmeter.Coin;
import coinmeter.CoinMeter;
import gradescope.grader.GradedTest;
import ledger.LoggedTest;

public class CoinTest extends LoggedTest 
{
	private CoinMeter uut = new CoinMeter();

	@Test
    @GradedTest(name="Test classify()", max_score=6)
	public void testClasify()
	{
		assertEquals("Coin did not match (see test case 1)", Coin.PENNY, uut.classifyCoin(2.5, 0.75));
		assertEquals("Coin did not match (see test case 2)", Coin.NICKEL, uut.classifyCoin(5.0, 0.835));
		assertEquals("Coin did not match (see test case 3)", Coin.DIME, uut.classifyCoin(2.268, 0.705));
		assertEquals("Coin did not match (see test case 4)", Coin.QUARTER, uut.classifyCoin(5.67, 0.955));
		assertEquals("Coin did not match (see test case 5)", Coin.UNKNOWN, uut.classifyCoin(5.67, 0.75));
		assertEquals("Coin did not match (see test case 6)", Coin.NICKEL, uut.classifyCoin(5.1, 0.83));
		assertEquals("Coin did not match (see test case 7)", Coin.QUARTER, uut.classifyCoin(5.7, 0.95));
		assertEquals("Coin did not match (see test case 8)", Coin.UNKNOWN, uut.classifyCoin(2.4, 0.705));
	}
	
	private double computeLimit(double base, double modifier, boolean proper)
	{
	    if (proper) return base + modifier * base;
	    else return base + modifier;
	}

	private void testPartial(String name, Coin coin)
	{
		try
		{
			testCoin(name, coin, true);
		} catch (Throwable e)
		{
			testCoin(name, coin, false);
		}
	}
	
	@Test
    @GradedTest(name="Does your Penny logic partially work?", max_score=3)
	public void testPennyPartial()
	{
		testPartial("isAPenny", Coin.PENNY);
	}

	@Test
    @GradedTest(name="Does your Nickel logic partially work?", max_score=3)
	public void testNickelPartial()
	{
		testPartial("isANickel", Coin.NICKEL);
	}

	@Test
    @GradedTest(name="Does your Dime logic partially work?", max_score=3)
	public void testDimePartial()
	{
		testPartial("isADime", Coin.DIME);
	}

	@Test
    @GradedTest(name="Does your Dime logic partially work?", max_score=3)
	public void testQuarterPartial()
	{
		testPartial("isAQuarter", Coin.QUARTER);
	}

	@Test
    @GradedTest(name="Test isAPenny()", max_score=3)
	public void testPennyProper()
	{
		testCoin("isAPenny", Coin.PENNY, true);
	}

	@Test
    @GradedTest(name="Test isANickel()", max_score=3)
	public void testNickelProper()
	{
		testCoin("isANickel", Coin.NICKEL, true);
	}

	@Test
    @GradedTest(name="Test isADime()", max_score=3)
	public void testDimeProper()
	{
		testCoin("isADime", Coin.DIME, true);
	}

	@Test
    @GradedTest(name="Test isAQuarter()", max_score=3)
	public void testQuarterProper()
	{
		testCoin("isAQuarter", Coin.QUARTER, true);
	}
	
	private void testCoin(String name, Coin type, boolean proper) 
	{
		try
		{
			Method isA = uut.getClass().getMethod(name, double.class, double.class);
			double weight   = computeLimit(type.getWeight(), CoinMeter.WEIGHT_THRESHOLD, proper);
			double diameter = type.getDiameter();
			assertTrue("Should have positively identified for (" + weight + ", " + diameter + ")",
					   (Boolean) isA.invoke(uut, weight, diameter));
			weight += 0.01;
			assertFalse("Should have rejected (" + weight + ", " + diameter + ")",
					    (Boolean) isA.invoke(uut, weight, diameter));
			weight = computeLimit(type.getWeight(), -CoinMeter.WEIGHT_THRESHOLD, proper);
			assertTrue("Should have positively identified for (" + weight + ", " + diameter + ")",
					   (Boolean) isA.invoke(uut, weight, diameter)); 
			weight -= 0.01;
			assertFalse("Should have rejected (" + weight + ", " + diameter + ")",
					    (Boolean) isA.invoke(uut, weight, diameter));
	
			weight   = type.getWeight();
			diameter = computeLimit(type.getDiameter(), CoinMeter.DIAMETER_THRESHOLD, proper);
			assertTrue("Should have positively identified for (" + weight + ", " + diameter + ")",
					   (Boolean) isA.invoke(uut, weight, diameter));
			diameter += 0.01;
			assertFalse("Should have rejected (" + weight + ", " + diameter + ")",
					    (Boolean) isA.invoke(uut, weight, diameter));
			diameter = computeLimit(type.getDiameter(), -CoinMeter.DIAMETER_THRESHOLD, proper);
			assertTrue("Should have positively identified for (" + weight + ", " + diameter + ")",
					   (Boolean) isA.invoke(uut, weight, diameter)); 
			diameter -= 0.01;
			assertFalse("Should have rejected (" + weight + ", " + diameter + ")",
					    (Boolean) isA.invoke(uut, weight, diameter));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e)
		{
			System.err.println("Something went wrong with the test");
			e.printStackTrace();
		}
	}

	private static final String CODE_FILE= "src/coinmeter/CoinMeter";
	@BeforeClass
	public static void grabCode()
	{
		LoggedTest.grabCode(CODE_FILE);
	}
}