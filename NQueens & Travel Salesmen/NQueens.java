package hw2;

import java.util.Random;

public class NQueens {
//START PROJECT CONTROL VALUES---------------------------------------------------------------------------
	/* Use these values to manipulate:
	 * the N size of the board (nControl), 
	 * the size of each generation (popControl)
	 * the termination value if no solution is found after X-many fitness functions/individuals created (popTerminationValue */
	public static int nControl = 4;
	public static int popControl = 10;
	public static int popTerminationValue = 100;
//END PROJECT CONTROL VALUES---------------------------------------------------------------------------
private individualNQ currentBest;
public int globalMinCollisions = 2147483647;


private int maxInt = 2147483647;
public int sizeBoard;// size of board in 1-dimension
public int sizePop = 100;//size of GA population
public individualNQ[] population;
public int individualsCreated = 0;
public Random RNG = new Random();
public final int tournamentSize = 2;


//START CONSTRUCTORS//

//NQueens default constructor
NQueens()
{
	this.sizeBoard = 4;
	this.sizePop = 20;
}

NQueens(int board)
{
	this.sizePop = 20;
	if (board >= 4 && board <= maxInt)//make sure int value x is within range
	{
	this.sizeBoard= board;
	}//end if
	else//exit program if invaild input is given
	{
		System.out.print("ERROR: Invaild size declared for NQueens."
				+ " Size must be between 4 and 2147483647 (inclusive)");
		System.exit(0);
	}//end else
	
}

NQueens(int board, int pop)
{
	if (pop >=  0 && pop <= maxInt )//make sure size of population int value x is within range
	{
	this.sizePop= pop;
	}//end if
	else//exit program if invaild input is given
	{
		System.out.print("ERROR: Invaild population size declared for NQueens."
				+ " Size must be between 0 and 2147483647 (inclusive)");
		System.exit(0);
	}
	
	if (board >= 4 && board <= maxInt)//make sure size of board int value x is within range
	{
	this.sizeBoard= board;
	}//end if
	
	else//exit program if invaild input is given
	{
		System.out.print("ERROR: Invaild board size declared for NQueens."
				+ " Size must be between 4 and 2147483647 (inclusive)");
		System.exit(0);
	}//end else
	
}//END CONSTRUCTORS//


public int dupCheck(individualNQ A)//check for duplicate queen positions in DNA
{
	int duplicates = 0;
	
	// ALGORITHM for dupCheck
	for(int i = 0; i < A.DNA.length; i++)
	{
		for(int j = i; j < A.DNA.length; j++)
		{
			if(A.DNA[i].getXPos() == A.DNA[j].getXPos() && A.DNA[i].getYPos() == A.DNA[j].getYPos() && i != j) //check x and y position same AND not self
			{
				duplicates++;
			}
		}
	}
	return duplicates;
}
public void setBest(individualNQ champion)
{
	for(int i = 0; i < champion.DNA.length; i++)
	{
		
		this.currentBest.DNA[i].xPos = champion.DNA[i].xPos;
		this.currentBest.DNA[i].yPos = champion.DNA[i].yPos;
	}
}
public individualNQ[] createPop()
{
	population = new individualNQ[sizePop];
	
	for(int i = 0; i < this.sizePop; i++)
	{
		//create new individual for population array
		this.population[i] = new individualNQ(sizeBoard);
		
		//continue to create a new individual of index position until the new individual created contains no duplicate queen positions
		//System.out.println("Checking for duplicates (above while): "+ dupCheck(this.population[i]));
		int dupCount = dupCheck(this.population[i]);
		
		///*
		while(dupCount != 0)
		{
			this.population[i] = new individualNQ(this.sizeBoard);
			dupCount = dupCheck(this.population[i]);
			//System.out.println("DUP (in while): " + dupCheck(this.population[i]));
		}
		//*/
		individualsCreated++;
		//set first individual to best
		
		if(individualsCreated == 1)
		{
			this.currentBest = population[0];
			
			
		}
		System.out.println("Individual " +   individualsCreated + " created ");
		System.out.println("collisions for individual:" + (i+1) + " is: " + population[i].collisions);
		
		//save best individual
		if(population[i].collisions < this.currentBest.collisions)
		{
			for(int j = 0; j < population[i].DNA.length; j++)
			{
				
				this.currentBest.DNA[j].xPos = population[i].DNA[j].xPos;
				this.currentBest.DNA[j].yPos =population[i].DNA[j].yPos;
			}
			
			this.currentBest.collisions= population[i].collisions;
		}
		
		
		for(int j = 0; j < population[i].DNA.length ; j++)
		{
			System.out.println("Queen"+ j + ": X position: " + population[i].DNA[j].getXPos() + ": Y position: " + population[i].DNA[j].getYPos());
		}
		//System.out.println("dups after while: " + dupCheck(this.population[i]));
		
		if( i == sizePop-1)
		{
			System.out.println("END OF GENERATION\n");
		}
		System.out.println();
		
		if(individualsCreated == sizePop )
		{
			break;
		}
		/*
		
		if(this.population[i].getFitness() == maxInt)
		{
			//System.out.println("individuals created: " + individualsCreated);
			
			System.exit(0);
		}
		*/
	}
	return this.population;
}

public int getIndividualsCreated()
{
	return individualsCreated;
}


public individualNQ[] nextGeneration(individualNQ[] population)
{	
	individualNQ parent1;
	individualNQ parent2;
	
	
	individualNQ[] newPop = new individualNQ[population.length];
	for(int k = 0; k+2 <= population.length; k+=2)
		{
		//random select x parents for parent 1 tournament
		individualNQ contestant1 = population[RNG.nextInt(population.length)];
		individualNQ contestant2 = population[RNG.nextInt(population.length)];
		
		//start tournament 1 (chance of parenthood proportional to fitness)
		//roll a 0,1, or 2 multiplier to fitness function.
		if( (contestant1.getFitness() * RNG.nextInt(3)) > (contestant2.getFitness() * RNG.nextInt(3)) )
		{
			parent1 = contestant1;
		}
		else if( (contestant1.getFitness() * RNG.nextInt(3)) == (contestant2.getFitness() * RNG.nextInt(3)) )
		{
			if(contestant1.getFitness() > contestant2.getFitness())
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
		
	
		//random select x parents for parent 2 tournament
		individualNQ contestant3 = population[RNG.nextInt(population.length)];
		individualNQ contestant4 = population[RNG.nextInt(population.length)];
		
		//start tournament 2 (chance of parenthood proportional to fitness)
		if((contestant3.getFitness() * RNG.nextInt(3)) > (contestant4.getFitness() * RNG.nextInt(3)))
				{
					parent2 = contestant3;
				}
				else if((contestant3.getFitness() * RNG.nextInt(3)) == (contestant4.getFitness() * RNG.nextInt(3)))
				{
					if(contestant3.getFitness() > contestant4.getFitness())
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
					parent2 = contestant2;
				}
		
		//loop through parents DNA index and swap if rand = 1, move on if rand = 0 
		for(int i = 0; i < parent1.DNA.length && i < parent2.DNA.length; i++)
		{
			if(RNG.nextInt(2) == 1)
			{
				int tempX = parent1.DNA[i].getXPos();
				int tempY = parent1.DNA[i].getYPos();
				parent1.DNA[i].setXPos(parent2.DNA[i].getXPos());
				parent1.DNA[i].setYPos(parent2.DNA[i].getYPos());
				parent2.DNA[i].setXPos(tempX);
				parent2.DNA[i].setXPos(tempY);
			}
		}
		
		
		
		
		
			//System.out.println("DUP (in while): " + dupCheck(this.population[i]));
		//*/
		
		
		// ALGORITHM for dupCheck
		int dupCount = dupCheck(parent1);
		while(dupCount != 0)
		{
		for(int i = 0; i < parent1.DNA.length; i++)
		{
			for(int j = i; j < parent1.DNA.length; j++)
			{
				
					if(parent1.DNA[i].xPos == parent1.DNA[j].xPos && parent1.DNA[i].yPos == parent1.DNA[j].yPos && i != j) //check x and y position same AND not self
					{
					
					//randomly select x or y value to change
					int mutationIndex = RNG.nextInt(4);
					if(mutationIndex == 0)
						parent1.DNA[i].xPos = RNG.nextInt(parent1.DNA.length);
					if(mutationIndex == 1)
						parent1.DNA[j].xPos = RNG.nextInt(parent1.DNA.length); 
					if(mutationIndex == 2)
						parent1.DNA[i].yPos = RNG.nextInt(parent1.DNA.length);
					if(mutationIndex == 3)
						parent1.DNA[j].yPos = RNG.nextInt(parent1.DNA.length); 
					
					//dupCount = dupCheck(parent1);
					}	
					dupCount = dupCheck(parent1);
				}
			}
		}
		
		dupCount = dupCheck(parent2);
		
		
		while(dupCount != 0)
		{
			for(int i = 0; i < parent2.DNA.length; i++)
			{
			for(int j = i; j < parent2.DNA.length; j++)
			{
					if(parent2.DNA[i].xPos == parent2.DNA[j].xPos && parent2.DNA[i].yPos == parent2.DNA[j].yPos && i != j) //check x and y position same AND not self
					{				
							
					//randomly select x or y value to change
					int mutationIndex = RNG.nextInt(4);
					if(mutationIndex == 0)
						parent2.DNA[i].xPos = RNG.nextInt(parent2.DNA.length);
					if(mutationIndex == 1)
						parent2.DNA[j].xPos = RNG.nextInt(parent2.DNA.length); 
					if(mutationIndex == 2)
						parent2.DNA[i].yPos = RNG.nextInt(parent2.DNA.length);
					if(mutationIndex == 3)
						parent2.DNA[j].yPos = RNG.nextInt(parent2.DNA.length); 
					
					dupCount = dupCheck(parent2);
					}			
				}
			}
		}
				
		//two children created enter new population
		//System.out.println("K: " + k );
		//System.out.println("pop length " + population.length);
		newPop[k] = parent1;
		
		
		if(newPop[k].collisions < currentBest.collisions)
		{
			
			for(int i = 0; i < newPop[k].DNA.length; i++)
			{
				
				this.currentBest.DNA[i].xPos = newPop[k].DNA[i].xPos;
				this.currentBest.DNA[i].yPos = newPop[k].DNA[i].yPos;
			}
			this.currentBest.collisions= newPop[k].collisions;
			//this.currentBest = newPop[k];
			//this.currentBest.collisions= newPop[k].collisions;
			//setBest(newPop[k]);
			//currentBest.collisions= newPop[k].collisions;
		}
		
		individualsCreated++;
		
		System.out.println("Individual: " + (k + 1) + " CREATED");
		//print collisions
		int collisionsChild1= newPop[k].numCollisions(newPop[k],sizeBoard);
		
		if(collisionsChild1 != 0)
		{
			newPop[k].fitness = 1/collisionsChild1;
			System.out.println("collisions for individual: "+ (k+1) + " is " + collisionsChild1);
		}
		else
		{
			
			//System.out.println("Solution Found:");
			System.out.println("collisions for individual: "+ (k+1) + " is " + collisionsChild1);
			System.exit(0);
		}
		//print data
		for(int i = 0; i < newPop[k].DNA.length; i++)
		{
			System.out.println("Queen "+ i + ": X position: " + newPop[k].DNA[i].xPos + ": Y position: " +  newPop[k].DNA[i].yPos);
		}
		System.out.println();

		
		newPop[k+1] = parent2;
		
		if(newPop[k+1].collisions < currentBest.collisions)
		{
			for(int i = 0; i < newPop[k+1].DNA.length; i++)
			{
				
				this.currentBest.DNA[i].xPos = newPop[k+1].DNA[i].xPos;
				this.currentBest.DNA[i].yPos = newPop[k+1].DNA[i].yPos;
			}
			this.currentBest.collisions= newPop[k+1].collisions;
			//currentBest = newPop[k+1];
			//currentBest.collisions= newPop[k+1].collisions;
			//setBest(newPop[k+1]);
			//currentBest.collisions= newPop[k+1].collisions;
		}
		//increment individual counter
		individualsCreated++;
		System.out.println("Individual: " + (k + 2) + " CREATED");
		//System.out.println("collisions: " + newPop[k+1].collisions);
		//print collisions
		int collisionsChild2= newPop[k+1].numCollisions(newPop[k+1],sizeBoard);
		
		if(collisionsChild2 != 0)
			
		{
			newPop[k+1].fitness = 1/collisionsChild2;
			System.out.println("collisions for individual: " + (k+2) + " is " + collisionsChild2);
		}
		else
		{
			
			//System.out.println("Solution Found:");
			System.out.println("collisions for individual: " + (k+2) + " is " + collisionsChild2);
			System.exit(0);
		}
		//print data
		for(int i = 0; i < newPop[k].DNA.length; i++)
		{
			System.out.println("Queen "+ i + ": X position: " + newPop[k].DNA[i].xPos + ": Y position: " +  newPop[k].DNA[i].yPos);
		}
		System.out.println();
		
		
		if(k + 3 == population.length)//added to handle odd population sizes
		{
			newPop[k+2] = population[k+2];
			individualsCreated++;
			
			if(newPop[k+2].collisions < currentBest.collisions)
			{
				//this.currentBest = newPop[k+2];
				for(int i = 0; i < newPop[k+2].DNA.length; i++)
				{
					
					this.currentBest.DNA[i].xPos = newPop[k+2].DNA[i].xPos;
					this.currentBest.DNA[i].yPos = newPop[k+2].DNA[i].yPos;
				}
				this.currentBest.collisions= newPop[k+2].collisions;
			}
			
			System.out.println("Individual: " + (k + 3) + " CREATED");
			//print collisions
			int collisionsChild3= newPop[k+2].numCollisions(newPop[k+2],sizeBoard);
			
			if(collisionsChild3 != 0)
			{
				newPop[k+1].fitness = 1/collisionsChild3;
				System.out.println("collisions for individual: " + (k+3) + " is " + collisionsChild3);
			}
			else
			{
				
				//System.out.println("Solution Found:");
				System.out.println("collisions for individual: " + (k+3) + " is " + collisionsChild3);
				System.exit(0);
			}
			//print data
			for(int i = 0; i < newPop[k+2].DNA.length; i++)
			{
				System.out.println("Queen "+ i + ": X position: " + newPop[k+2].DNA[i].xPos + ": Y position: " +  newPop[k+2].DNA[i].yPos);
			}
			System.out.println();
			
		}
	}
	
	this.population = newPop;
	
	//Print Data
	/*
	for(int j = 0 ; j < population[0].DNA.length; j++)
	{
		for(int i = 0; i < population[0].DNA.length; i++)
		{
			System.out.println("Q"+ i + ": X position: " + population[j].DNA[i].xPos + ": Y position: " +  newPop[j].DNA[i].yPos);
		}
		System.out.println("Individual: " + (j+1) + " CREATED");
	}
	*/
	
	
	//return new population

	System.out.println("END OF GENERATION\n");
	return newPop;
}
/*
public boolean checkForSolution()
{
	for(int i = 0; i < sizePop; i++)
	{
		if(population[i].getFitness() == maxInt)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
*/

	public static void main(String[] args)
	{ 
		//boolean solutionFound;
		
		NQueens search = new NQueens(nControl, popControl);
		search.population = search.createPop();
		int count = search.getIndividualsCreated();
		while (count < popTerminationValue)
		{
			search.population = search.nextGeneration(search.population);
			count = search.getIndividualsCreated();
			System.out.println("Individuals created: " + count +"\n");
		}
		System.out.println("\n BEST SOLUTION: \n");
		System.out.println("NUMBER OF COLLISIONS: " + search.currentBest.collisions);
		for(int i = 0; i < search.currentBest.DNA.length; i++)
		{
			System.out.println("Queen "+ i + ": X position: " + search.currentBest.DNA[i].xPos + ": Y position: " + search.currentBest.DNA[i].yPos);
		}	
		
	}
}

