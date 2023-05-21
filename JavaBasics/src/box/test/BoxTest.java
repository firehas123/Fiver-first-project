package box.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import box.BoxDrawing;
import gradescope.grader.GradedTest;
import ledger.LoggedTest;

/**
 * @author Tony
 */
public class BoxTest extends LoggedTest
{
	@Test		
    @GradedTest(name="Test drawFilledBox()", max_score=5)
    public void testFilled() 
    {
		BoxDrawing uut = new BoxDrawing();
		
		String actual = uut.drawFilledBox(5, 5);
		assertEquals("The drawings do not match, check everything including white space and new lines (size = 5, 5)",
				     BoxDrawing.EXAMPLE_SOLID_BOX, actual);
    }

	@Test		
    @GradedTest(name="Test drawEmptyBox()", max_score=5)
    public void testEmpty() 
    {
		BoxDrawing uut = new BoxDrawing();
		
		String actual = uut.drawEmptyBox(5, 5);
		assertEquals("The drawings do not match, check everything including white space and new lines (size = 5, 5)",
				     BoxDrawing.EXAMPLE_EMPTY_BOX, actual);
    }

	@Test		
    @GradedTest(name="Test drawRightTriangle()", max_score=5)
    public void testRightTriangle() 
    {
		BoxDrawing uut = new BoxDrawing();
		
		String actual = uut.drawRightTriangle(5);
		assertEquals("The drawings do not match, check everything including white space and new lines (size = 5)",
				     BoxDrawing.EXAMPLE_RIGHT_TRIANGLE, actual);
    }

	@Test		
    @GradedTest(name="Test drawFlippedRightTriangle()", max_score=5)
    public void testFlippedRightTriangle() 
    {
		BoxDrawing uut = new BoxDrawing();
		String actual = uut.drawFlippedRightTriangle(5);
		assertEquals("The drawings do not match, check everything including white space and new lines (size = 5)",
			     BoxDrawing.EXAMPLE_FLIPPED_RIGHT_TRIANGLE, actual);
    }

	@Test		
    @GradedTest(name="Test drawMirroredRightTriangle()", max_score=5)
    public void testMirroredRightTriangle() 
    {
		BoxDrawing uut = new BoxDrawing();
		
		String actual = uut.drawMirroredRightTriangle(5);
		assertEquals("The drawings do not match, check everything including white space and new lines (size = 5)",
			     BoxDrawing.EXAMPLE_MIRRORED_RIGHT_TRIANGLE, actual);
    }

	@Test		
    @GradedTest(name="Test drawIsosoleseTriangle()", max_score=5)
    public void testIsosoleseTriangle() 
    {
		BoxDrawing uut = new BoxDrawing();
		String actual = uut.drawIsosoleseTriangle(5);
		assertEquals("The drawings do not match, check everything including white space and new lines (size = 5)",
			     BoxDrawing.EXAMPLE_ISOSOLESE_TRIANGLE, actual);
    }

	private static final String CODE_FILE= "src/box/BoxDrawing";
	@BeforeClass
	public static void grabCode()
	{
		LoggedTest.grabCode(CODE_FILE);
	}
}