package tips;

/**
 * @author <Your Name Here>
 */
public class TipCalculator 
{
	/**
	 * Compute the tip based on the provided amount and percentage.  If the roundUp flag is set,
	 * the tip should round the total up to the next full dollar amount.
	 * @param amount - total of the purchase
	 * @param percentage - the percentage tip (e.g., .2 = 20%, 1 = 100%)
	 * @param roundUp - if true, add to the tip so the amount + tip is an even dollar amount
	 * @return
	 */
    public double computeTip(double billTotal, double percentage, boolean roundUp)
    {
        return 0;
    }

    /**
     * Run a command line version of the tip calculator.  It does NOT prompt the user.  The user must call using 
     * the following arguments:
     * <REQUIRED>
     *   -price xx.xx => the -price flag followed by a price 
     *   -percent xx.x => the -percent flag followed by a percent tip (e.g., 15.5 would be a 15.5% tip)
     * <OPTIONAL>
     *   roundup => the flag indicating to add an extra amount to the tip to round up to the next dollar value
     * For example, 
     *   java tips.TipCalculator -price 10.00 -percent 25 
     * or
     *   java tips.TipCalculator -price 10.00 -percent 25 roundup
     * @param args
     */
    public static void not_main(String[] args) // This is so when you hit the run button it runs your test, it is just main in real Java programs
    {
        boolean roundUp = false;
        double price    = 0;
        double percent  = 0;

        // Fill the values above from the command line parameters (args) so the rest of this
        // call works.
         
        TipCalculator tc = new TipCalculator();
        double tip = tc.computeTip(price, percent, roundUp);
        double total = price + tip;
        System.out.printf("For $%.2f at a tip of %.1f%%%s\nTip: $%.2f\nTotal: $%.2f\n", 
                          price, percent*100, (roundUp? " rounded up": ""), tip, total);
    }
}