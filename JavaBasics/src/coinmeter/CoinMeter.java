package coinmeter;

public class CoinMeter
{
	public static final double WEIGHT_THRESHOLD   = 0.03;
	public static final double DIAMETER_THRESHOLD = 0.01;
	
	public boolean isAPenny(double weight, double diameter)
	{
		return false;
	}

	public boolean isANickel(double weight, double diameter)
	{
		return false;
	}

	public boolean isADime(double weight, double diameter)
	{
		return false;
	}

	public boolean isAQuarter(double weight, double diameter)
	{
		return false;
	}

	public Coin classifyCoin(double weight, double diameter)
	{
		return Coin.UNKNOWN;
	}
}
