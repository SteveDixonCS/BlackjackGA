package hw5;

import java.util.Random;
import java.lang.Math;

public class IndividualQ1 {

	public double x,y,fitness;
	Random RNG = new Random();;
//START PROJECT CONTROL VALUES---------------------------------------------------------------------------
	int fitnessFormulaOption = 2;//0 for default, 1 for 3x function, 2 for ackley's function
	
	//VARIABLES FOR ACKLEY'S FUNCTION
	public int dimension = 30;
	public double[] ackleyArray = new double[dimension];
//END PROJECT CONTROL VALUES---------------------------------------------------------------------------
	public double sigma1 = 0;
	public double sigma2 = 0;
	public double oneOverN = 1/dimension;
	//END VARIABLES FOR ACKLEY'S FUNCTION
	IndividualQ1()
	{
		
		int xOffset = RNG.nextInt(100) - 60;//create x range of -60 to 39
		int yOffset = RNG.nextInt(100) - 30;//create 7 range of -30 to 69
		//nextDouble() Generates a value between 0.0 and 1.0
		//add this value to x and y offsets respectively
		this.x = RNG.nextDouble() + xOffset;
		this.y = RNG.nextDouble() +  yOffset;
		
		if(fitnessFormulaOption == 1)
		{
			this.fitness = (Math.abs(x) + Math.abs(y)) * (1 + Math.abs(Math.sin(3 * Math.abs(x) * Math.PI)) + Math.abs(Math.sin(3 * Math.abs(y) * Math.PI)));
		}
		else if(fitnessFormulaOption == 2)
		{
			
			//Initialize ackleyArray
			for(int i = 0; i < dimension;i++)
			{
				ackleyArray[i]= ((RNG.nextInt(60) - 30) + RNG.nextDouble());//create x range of -30 to 29 and add decimal value with nextDouble
			}
			//calculate Sigma values
			for(int i =0; i < dimension; i++)
			{
				sigma1 += Math.pow(ackleyArray[i], 2);
				sigma2 += Math.cos(2 * Math.PI * ackleyArray[i]);
			}
			
			fitness = -20.0 * Math.exp(-0.2 * Math.sqrt(sigma1/dimension)) - Math.exp(sigma2/dimension) + 20.0 + Math.exp(1);
		}
		else
			this.fitness = (Math.abs(x) + Math.abs(y)) * (1 + Math.abs(Math.sin(Math.abs(x) * Math.PI)) + Math.abs(Math.sin(Math.abs(y) * Math.PI)));
	}
}
