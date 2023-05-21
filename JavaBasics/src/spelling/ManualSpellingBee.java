package spelling;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ledger.LoggedTest;

/**
 * @author Tony
 * This class allows you to run the spelling app directly rather than via the tester
 * ----You do not need to edit or change this class at all----
 */
public class ManualSpellingBee extends LoggedTest
{
	public static final String SPELLING_WORDS_FILE = "spellingWords.txt";
	private static final String CODE_FILE= "src/spelling/SpellingBeeHelper";
	
    public static void main(String[] args) throws Exception
    {
		LoggedTest.grabCode(CODE_FILE); // This logs your code in the ledger.  If you take this out the ledger will not fill!
        SpellingBeeHelper sbh = new SpellingBeeHelper();
        
    	sbh.promptTestTaker(sbh.loadWords(SPELLING_WORDS_FILE), 
    			            new BufferedReader(new InputStreamReader(System.in)), 
    			            System.out);
    }    
}
