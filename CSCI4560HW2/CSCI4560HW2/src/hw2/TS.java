package hw2;
import java.util.Random;

public class TS {
//START PROJECT CONTROL VALUES---------------------------------------------------------------------------
		/* Use these values to manipulate:
		 * the size of each generation (popControl)
		 * the termination value if no solution is found after X-many fitness functions/individuals created (popTerminationValue */
		public static int popControl = 10;
		public static int popTerminationValue = 20;
//END PROJECT CONTROL VALUES---------------------------------------------------------------------------
	public int individualsCreated = 0;
	individualTS[] population;
	Random RNG = new Random();

	TS()
	{
		population = new individualTS[popControl];
		
		//initialize population
		for(int i = 0; i < popControl; i++)
		{
			population[i] = new individualTS();
			double fitness = population[i].fitness(population[i]);
			individualsCreated++;
			System.out.println("Indiviual " + individualsCreated + " created. It's fitness is: " + fitness);
		}
	}
	
	//Create Next Generation
	individualTS[] createNextGen(individualTS[] oldGen)
	{
		individualTS parent1;
		individualTS parent2;
		//create uninitialized child population
		individualTS[] newGen = new individualTS[popControl];
		 
		for(int i = 0; i < popControl; i++)
		{
			//random select x parents for parent 1 tournament
			individualTS contestant1 = oldGen[RNG.nextInt(popControl)];
			individualTS contestant2 = oldGen[RNG.nextInt(popControl)];
			
			//start tournament 1 (chance of parenthood proportional to fitness)
			//roll a 0,1, or 2 multiplier to fitness function.
			if( (contestant1.fitness(contestant1) * RNG.nextInt(3)) > (contestant2.fitness(contestant2) * RNG.nextInt(3)) )
			{
				parent1 = contestant1;
			}
			
			else if( (contestant1.fitness(contestant1) * RNG.nextInt(3)) == (contestant2.fitness(contestant2) * RNG.nextInt(3)) )
			{
				if(contestant1.fitness(contestant1) > contestant2.fitness(contestant2))
				{
					parent1 = contestant1;
				}
				else
				{
					parent1 = contestant2;
				}
			}
			else
			{
				parent1 = contestant2;
			}
			
			//random select x parents for parent 1 tournament
			individualTS contestant3 = oldGen[RNG.nextInt(popControl)];
			individualTS contestant4 = oldGen[RNG.nextInt(popControl)];
			
			//start tournament 1 (chance of parenthood proportional to fitness)
			//roll a 0,1, or 2 multiplier to fitness function.
			if( (contestant3.fitness(contestant3) * RNG.nextInt(3)) > (contestant4.fitness(contestant4) * RNG.nextInt(3)) )
			{
				parent2 = contestant1;
			}
			
			else if( (contestant3.fitness(contestant3) * RNG.nextInt(3)) == (contestant4.fitness(contestant4) * RNG.nextInt(3)) )
			{
				if(contestant3.fitness(contestant3) > contestant2.fitness(contestant4))
				{
					parent2 = contestant3;
				}
				else
				{
					parent2 = contestant4;
				}
			}
			else
			{
				parent2 = contestant4;
			}
			System.out.println("Parents selected");
			newGen[i] = orderCrossover(parent1,parent2);
		}
		return newGen;
	}
	
	individualTS orderCrossover(individualTS parent1, individualTS parent2)
	{ 
		System.out.println("in order cross over");
		individualTS child = new individualTS();
		
		int setValue1 = RNG.nextInt(child.DNA.length);
		int setValue2 =  RNG.nextInt(child.DNA.length);
		
		//put start and stop indexes for set in order.
		//value 2 is lower
		if(setValue1 > setValue2)
		{
			//System.out.println("in order cross over parent 1a");
			//System.out.println("Crossover range" + setValue2 + " - " + (setValue1-1));
			for(int i = setValue2; i < setValue1; i++)
			{
				child.DNA[i] = parent1.DNA[i];
				//System.out.println("Child Created: " + i);
				//individualsCreated++;
			}
			//check values to copy from parent 2
			
			//int parent2Index = setValue1;
			int stop = child.DNA.length;
			//from set end to dna end
			for(int i = setValue1; i < stop; i++)
			{
				//System.out.println("in order cross over parent 2a");
				//System.out.println("i" + i);
				//check for match in subset
				for(int j = setValue2; j < setValue1; j++ )
				{
					//System.out.println("j" + j);
					//if matching in parent1 subset
					if((parent1.DNA[j].x == parent2.DNA[i].x) && (parent1.DNA[j].y == parent2.DNA[i].y))
					{
						if(i+1 < child.DNA.length)//if space increment i 
						{
							i++;
							break;
						}
						else
						{	
							i = 0;
							stop  = setValue2;
							break;
						}
					}
					else
					{
						child.DNA[i] = parent2.DNA[i];
						//System.out.println("Child Created: " + i);
						//individualsCreated++;
					}
				}

				//from dna start to set start
				/*for(i = 0; i < setValue2; i++)
				{
					System.out.println("i" + i);
					//check for match in subset
					for(int j = setValue2; j < setValue1; j++ )
					{ 
						System.out.println("j" + j);
						//if matching in parent1 subset
						if((parent1.DNA[j].x == parent2.DNA[i].x) && (parent1.DNA[j].y == parent2.DNA[i].y))
						{
							if(i+1 < child.DNA.length)//if space increment i 
							{
								i++;
								break;
							}
							else
							{
								break;
							}
						}
						else
						{
							child.DNA[i] = parent2.DNA[i];
							//System.out.println("Child Created: " + i);
							//individualsCreated++;
						}
					}
				}*/
			}
		}
		else
		{
			int temp;
			temp = setValue1;
			setValue1 = setValue2;
			setValue2 = temp;
			
			//System.out.println("in order cross over parent 1b");
			//System.out.println("Crossover range" + setValue2 + " - " + (setValue2-1));
			for(int i = setValue1; i < setValue2; i++)
			{
				child.DNA[i] = parent1.DNA[i];
				//System.out.println("Child Created: " + i);
				//individualsCreated++;
			}
			//check values to copy from parent 2
			
			//int parent2Index = setValue1;
			
			//from set end to dna end
			int stop = child.DNA.length;
			//from set end to dna end
			for(int i = setValue1; i < stop; i++)
			{
				//System.out.println("in order cross over parent 2a");
				//System.out.println("i" + i);
				//check for match in subset
				for(int j = setValue2; j < setValue1; j++ )
				{
					//System.out.println("j" + j);
					//if matching in parent1 subset
					if((parent1.DNA[j].x == parent2.DNA[i].x) && (parent1.DNA[j].y == parent2.DNA[i].y))
					{
						if(i+1 < child.DNA.length)//if space increment i 
						{
							i++;
							break;
						}
						else
						{	
							i = 0;
							stop  = setValue2;
							break;
						}
					}
					else
					{
						child.DNA[i] = parent2.DNA[i];
						//System.out.println("Child Created: " + i);
						//individualsCreated++;
					}
				}

				//from dna start to set start
				/*for(i = 0; i < setValue2; i++)
				{
					System.out.println("i" + i);
					//check for match in subset
					for(int j = setValue2; j < setValue1; j++ )
					{ 
						System.out.println("j" + j);
						//if matching in parent1 subset
						if((parent1.DNA[j].x == parent2.DNA[i].x) && (parent1.DNA[j].y == parent2.DNA[i].y))
						{
							if(i+1 < child.DNA.length)//if space increment i 
							{
								i++;
								break;
							}
							else
							{
								break;
							}
						}
						else
						{
							child.DNA[i] = parent2.DNA[i];
							//System.out.println("Child Created: " + i);
							//individualsCreated++;
						}
					}
				}*/
			}
		}
			
		
		for(int i = 0; i < popControl; i++)
		{
			double fitness = population[i].fitness(population[i]);
			System.out.println("Indiviual" + individualsCreated + "Created. It's fitness is: " + fitness);
			individualsCreated++;
		}
		return child;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TS generation = new TS();
		while (generation.individualsCreated < popTerminationValue)
		{
			generation.population = generation.createNextGen(generation.population);
		}
	
	}

}
