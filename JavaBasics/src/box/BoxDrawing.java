package box;

/**
 * @author <Your Name Here>
 */
public class BoxDrawing 
{
	public static final String EXAMPLE_SOLID_BOX = "*****\n*****\n*****\n*****\n*****\n";
	public String drawFilledBox(int height, int width)
	{
		return EXAMPLE_SOLID_BOX;
	}

	public static final String EXAMPLE_EMPTY_BOX = "*****\n*   *\n*   *\n*   *\n*****\n";
	public String drawEmptyBox(int height, int width)
	{
	    return EXAMPLE_EMPTY_BOX;
	}

	public static final String EXAMPLE_RIGHT_TRIANGLE = "*\n**\n***\n****\n*****\n";
	public String drawRightTriangle(int length)
	{
		return EXAMPLE_RIGHT_TRIANGLE;
	}

	public static final String EXAMPLE_FLIPPED_RIGHT_TRIANGLE = "*****\n****\n***\n**\n*\n";
	public String drawFlippedRightTriangle(int length)
	{
		return EXAMPLE_FLIPPED_RIGHT_TRIANGLE;
	}

	public static final String EXAMPLE_MIRRORED_RIGHT_TRIANGLE = "    *\n   **\n  ***\n ****\n*****\n";
	public String drawMirroredRightTriangle(int length)
	{
		return EXAMPLE_MIRRORED_RIGHT_TRIANGLE;
	}

	public static final String EXAMPLE_ISOSOLESE_TRIANGLE = "    *\n   ***\n  *****\n *******\n*********\n";
	public String drawIsosoleseTriangle(int length)
	{
		return EXAMPLE_ISOSOLESE_TRIANGLE;
	}
}