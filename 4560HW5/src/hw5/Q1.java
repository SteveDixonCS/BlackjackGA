package hw5;
import java.util.Random;

public class Q1 {
//START PROJECT CONTROL VALUES---------------------------------------------------------------------------
	public static int globalFitCalc = 0;
	public static final int maxFitCalc =  200000;
	//turn verbose
	public static boolean verbose = true;
	//0 for default, 1 for 3x function, 2 for ackley's function
	public static int fitnessFormulaOption = 2;
	public int popSize = 400;
	int mutationRate = 5;//mutate at a rate 1 out of muationRate variable
//END PROJECT CONTROL VALUES---------------------------------------------------------------------------
	static Random RNG = new Random();
	public IndividualQ1[] population;
	
	
	Q1()
	{
	 population = new IndividualQ1[popSize];
	 for(int i = 0; i < popSize; i++)
	 {
		 population[i] = new IndividualQ1();
		 globalFitCalc +=1;
		 if(verbose && fitnessFormulaOption == 2 )
		 {
			 System.out.println("Individual " + globalFitCalc + " Has Been Created. Genetics below");
			 for(int j =0; j<population[i].ackleyArray.length; j ++)
			 {
				 System.out.println(population[i].ackleyArray[j]+ " ");
			 }
			 System.out.println("Fitness: " + population[i].fitness);
			 System.out.println("");
		 }
		 if(verbose && fitnessFormulaOption != 2 )
		 {
			System.out.println("Individual "+ globalFitCalc + " Has Been Created");
			System.out.println("X: " + population[i].x);
			System.out.println("Y: "  + population[i]+ "\n");
			System.out.println("Individual"+ globalFitCalc + " Fitness: " + population[i].fitness);
		 }
	 }
	}
	
	IndividualQ1 tournament(IndividualQ1 contestantA, IndividualQ1 contestantB)
	{
		double tournamentFitnessA = (contestantA.fitness * RNG.nextInt(3));//nextInt used as a multiplier so weaker (in this case higher value) fitness individuals can have a chance to be parents.
		double tournamentFitnessB = (contestantB.fitness * RNG.nextInt(3));
		//if A has a lower (in this case better) fitness the select it for parenthood
		if(tournamentFitnessA < tournamentFitnessB)
		{
			return contestantA;
		}
		else if(tournamentFitnessA == tournamentFitnessB)
		{
			if(contestantA.fitness < contestantB.fitness)
			{
				return contestantA;
			}
			else
			{
				return contestantB;
			}
		}
		else
		{
			return contestantB;
		}
	}
	
	IndividualQ1 crossover(IndividualQ1 parent1, IndividualQ1 parent2)
	{
		IndividualQ1 child = new IndividualQ1();
		//create a child with x and y values the average of parents
		if(fitnessFormulaOption != 2)
		{
			child.x = (parent1.x + parent2.x)/2;
			child.y = (parent1.y + parent2.y)/2;
		}
		else
		{
			for(int i =0; i < child.ackleyArray.length; i++)
			{
				child.ackleyArray[i] = (parent1.ackleyArray[i]  + parent2.ackleyArray[i])/2;
			}
		}
		//calculate child fitness
		child.fitness = calculateFitness(child);
		 if(verbose && fitnessFormulaOption == 2 )
		 {
			 System.out.println("Individual " + globalFitCalc + " Has Been Created. Genetics below");
			 for(int j =0; j < child.ackleyArray.length; j ++)
			 {
				 System.out.println(child.ackleyArray[j]+ " ");
			 }
			 System.out.println("Fitness: " + child.fitness);
			 System.out.println("");
		 }
		globalFitCalc +=1;
		
		return child;
	}
	
	IndividualQ1 mutate(IndividualQ1 individual)
	{
		if(fitnessFormulaOption != 2)
		{
			int xShift = RNG.nextInt(100 + 1) - 60;//shift up to 60 left and 40 right
			int yShift = RNG.nextInt(100 + 1) - 30;//shift up to 30 left and 70 right
			// check that values are in range before setting the shift
			if((individual.x + xShift) >= -60 && (individual.x + xShift)<=40)
			{
				individual.x = individual.x + xShift;
			}
			if((individual.y+ yShift) >= -30 && (individual.y + yShift) <=70)
			{
				individual.x = individual.x + yShift;
			}
		}
		else 
		{
			//change one random array index value
			int i = RNG.nextInt(individual.ackleyArray.length);
			individual.ackleyArray[i]= (RNG.nextInt(60) - 30)+ RNG.nextDouble();
		}
		//recalculate fitness
		individual.fitness = calculateFitness(individual);
		//globalFitCalc +=1;
		return individual;
	}
	
	double calculateFitness(IndividualQ1 individual)
	{
		double fitness;
		
		if(fitnessFormulaOption == 1)
		{
			fitness = (Math.abs(individual.x) + Math.abs(individual.y)) * (1 + Math.abs(Math.sin(3 * Math.abs(individual.x) * Math.PI)) + Math.abs(Math.sin(3 * Math.abs(individual.y) * Math.PI)));
		}
		else if(fitnessFormulaOption == 2)
		{
			//calculate Sigma values
			for(int i =0; i < individual.dimension; i++)
			{
				individual.sigma1 += Math.pow(individual.ackleyArray[i], 2);
				individual.sigma2 += (Math.cos(2*Math.PI*individual.ackleyArray[i]));
			}
			
			fitness = -20.0 * Math.exp(-0.2 * Math.sqrt(individual.sigma1/individual.dimension)) - Math.exp(individual.sigma2/individual.dimension) + 20.0 + Math.exp(1);
		}
		else
			fitness = (Math.abs(individual.x) + Math.abs(individual.y)) * (1 + Math.abs(Math.sin(Math.abs(individual.x) * Math.PI)) + Math.abs(Math.sin(Math.abs(individual.y) * Math.PI)));
		
		return fitness;
	}
	
	public static void main(String[] args)
	{ 	
		Q1 SteadyStateGA = new Q1();
		IndividualQ1 parent1;
		IndividualQ1 parent2;
		IndividualQ1 child;
		IndividualQ1 newEntry;
		
		
		while (globalFitCalc < maxFitCalc)
		{
			//select parents
			int index1 = RNG.nextInt(20);
			int index2 = RNG.nextInt(20);
			//if same index re-roll until different
			while(index1 == index2)
			{
				index2 = RNG.nextInt(20);
			}
			//have tournament for parent 1
			parent1 = SteadyStateGA.tournament(SteadyStateGA.population[index1], SteadyStateGA.population[index2]);
			if(verbose)
			{
				if(fitnessFormulaOption != 2)
				{
					System.out.println("Parent1 X:" + parent1.x);
					System.out.println("Parent1 Y:" + parent1.y);
				}
				System.out.println("Parent1 Fitness:" + parent1.fitness +"\n");
			}
			
			//get second parent
			index1 = RNG.nextInt(20);
			index2 = RNG.nextInt(20);
			
			while(index1 == index2)
			{
				index1 = RNG.nextInt(20);
			}
			parent2 = SteadyStateGA.tournament(SteadyStateGA.population[index1], SteadyStateGA.population[index2]);
			if(verbose)
			{
				if(fitnessFormulaOption != 2)
				{
					System.out.println("Parent2 X:" + parent2.x);
					System.out.println("Parent2 Y:" + parent2.y);
				}
				System.out.println("Parent2 Fitness:" + parent2.fitness +"\n");
			}
			
			if(verbose)
			{
			System.out.println("Parents Have Been Selected\n");
			}
			
			if(parent1.x != parent2.x && parent1.y != parent2.y)//if not the same individual
			{
				//create child
				child = SteadyStateGA.crossover(parent1, parent2);
				//System.out.println("A Child Has Been Created");
				if(verbose)
				{
					System.out.println("Individual "+ globalFitCalc + " Has Been Created");
					
					if(fitnessFormulaOption != 2)
					{
						System.out.println("X: " + child.x);
						System.out.println("Y: "  + child.y + "\n");
					}
				}
			
				//DETERMINE MUTATION
				boolean mutate = RNG.nextInt(SteadyStateGA.mutationRate) == 0; //one in 40 chance to mutate
				if(mutate)
				{
					SteadyStateGA.mutate(child);
					if(verbose)
					{
						System.out.println("Individual"+ globalFitCalc + " Mutated");
						if(fitnessFormulaOption != 2)
						{
							System.out.println("X: " + child.x);
							System.out.println("Y: "  + child.y + "\n");
						}
					}
				}
			
				//determine where to place child in population
				int indexWeakest = 0;
				//first find weakest individual in population
				for(int i = 0; i < SteadyStateGA.popSize; i++)
				{
					if(SteadyStateGA.population[i].fitness > SteadyStateGA.population[indexWeakest].fitness)
					{
						indexWeakest = i;
					}
				}
				//place child in tournament with weakest individual
				newEntry = SteadyStateGA.tournament(SteadyStateGA.population[indexWeakest], child);
				//Place winner in index of weakest
				SteadyStateGA.population[indexWeakest] = newEntry; 
				if(verbose)
				{
					if(fitnessFormulaOption != 2)
					{
						System.out.println("New Entry X: "+ newEntry.x);
						System.out.println("New Entry Y: "+ newEntry.y);
					}
					System.out.println("New Entry Fitness: "+ newEntry.fitness+ "\n");
				}
			}//end if
		}//end while
		
		//print Best individual of all time
		int indexStrongest = 0;
		for(int i = 0; i < SteadyStateGA.popSize; i++)
		{
			if(SteadyStateGA.population[i].fitness < SteadyStateGA.population[indexStrongest].fitness)
			{
				indexStrongest = i;
			}
		}
		System.out.println("The Best Individual Of "+ globalFitCalc +" Fitness Calculations: ");
		if(fitnessFormulaOption != 2)
		{
			System.out.println("X: " + SteadyStateGA.population[indexStrongest].x);
			System.out.println("Y: " + SteadyStateGA.population[indexStrongest].y);
		}
		else
		{
			 System.out.println("Genetics below:");
			 for(int j =0; j<SteadyStateGA.population[indexStrongest].ackleyArray.length; j ++)
			 {
				 System.out.println(SteadyStateGA.population[indexStrongest].ackleyArray[j]+ " ");
			 }
		}
		System.out.println("\nFitness "+ SteadyStateGA.population[indexStrongest].fitness);
	}
			
}

