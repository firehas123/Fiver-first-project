package story;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ledger.LoggedTest;

/**
 * @author <Your Name Here>
 */
public class DynamicStory extends LoggedTest
{
	private static final String CODE_FILE= "src/story/DynamicStory";

	public static void main(String[] args) throws IOException
	{
		LoggedTest.grabCode(CODE_FILE); // This logs your code in the ledger.  If you take this out the ledger will not fill!
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Once upon a time you participated in a story by telling me your name:");
		String input = in.readLine();
		System.out.println("Hi " + input);

	}	
}