package spelling;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author <Your Name Here>
 */
public class SpellingBeeHelper 
{
	public static final String PROMPT = "Your Answer>> ";
	public static final String NO_INPUT_RESPONSE = "Please at least guess at the spelling";
	public static final String INCORRECT = "That was not correct, hit enter to try again, or n to move on";
	public static final String SKIP_RESPONSE = "n";
	public static final String COMPLETED = "You have completed the test";
	
	public static final String ATTEMPT_FILE = "spellingTest.txt";

	/**
	 * Load the spelling words from the specified file
	 * @param filename - the name of the file
	 * @return a list of SpellingWord objects
	 * @throws IOException
	 */
    public List<SpellingWord> loadWords(String filename) throws IOException
    {
    	List<SpellingWord> words = new ArrayList<SpellingWord>();
    	return words;
    }
	
    /**
     * Prompt the test taker through the test as described in the design
     * @param words - the spelling words for the test
     * @param in - The input from the user
     * @param out - The output to the user
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
	public void promptTestTaker(List<SpellingWord> words, BufferedReader in, PrintStream out) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
    	PromptWords:
    	for(SpellingWord word : words)
    	{
	    	while (true)
	        {
	    		AudioHelper.play(word.getAudioFile());
	    		long start = System.currentTimeMillis();
	    		
	    		String answer = "";
	    		recordAttempt(word, answer, System.currentTimeMillis() - start);

	    		break PromptWords; // Need to jump out of the loop till you write logic!
	        }
    	}
    }

	/**
	 * Record a spelling word attempt by the user in their file.
	 * @param word - the attempted word
	 * @param answer - their provided answer
	 * @param duration - the amount of time they took from the time the word was presented
	 *                   to the time they entered their answer in milliseconds
	 * @throws IOException
	 */
	public void recordAttempt(SpellingWord word, String answer, long duration) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileOutputStream(ATTEMPT_FILE, true));
		out.close();
	}
    
	/**
	 * Open the provided file and evaluate the answers to the test
	 * @param filename the test file
	 * @return a filled TestResults object
	 * @throws IOException
	 */
	public TestResults gradeTest(String filename) throws IOException
	{
		TestResults results = new TestResults();
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String line = in.readLine();
        System.out.println(line); // This print is just so you can see it... no other purpose in the logic
        in.close();
        return results;
	}
}