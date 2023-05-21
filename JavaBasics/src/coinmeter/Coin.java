package coinmeter;

public enum Coin
{
	PENNY(2.5, 0.75), NICKEL(5.0, 0.835), DIME(2.268, 0.705), QUARTER(5.67, 0.955), UNKNOWN(0, 0);

	private double weight;
	private double diameter;
	
	private Coin(double weight, double diameter)
	{
		this.weight   = weight;
		this.diameter = diameter;
	}

	public double getWeight()
	{
		return weight;
	}

	public double getDiameter()
	{
		return diameter;
	}
}
