package hw2;

import java.util.Random;


public class individualNQ {
	public queen[] DNA;//array of queens for genotype of individual
	public int collisions;//calculate the number of queen collisions of a N-Queen individual.=
	public double fitness;
	private int maxInt = 2147483647;
	Random RNG = new Random();
	
	individualNQ (int Nsize)// initalizing an individual with N queens
	{

		DNA = new queen[Nsize];
		
		//create queen array that will represent DNA
		for (int i = 0; i < Nsize; i++)
		{
			//creates queen with random x and y position
			this.DNA[i] = new queen(RNG.nextInt(Nsize),RNG.nextInt(Nsize));
			//System.out.println("Q"+ i + ": X position: " + this.DNA[i].getXPos() + ": Y position: " + this.DNA[i].getYPos());
		}
		//System.out.println("Individual Created ");
		//determine number of collisions
		this.collisions = 0;
			//check horizontally
		///*
			for (int x = 0; x < Nsize; x++)
			{
				for (int y = x; y < Nsize; y++)
				{
					if (x != y)//a queen compared to itself is not a collision
					{
						if (DNA[x].getYPos() == DNA[y].getYPos())
							collisions++;
					}
				}
			}
			//check vertically
			for (int x = 0; x < Nsize; x++)
			{
				for (int y = x; y < Nsize; y++)
				{
					if (x != y)//a queen compared to itself is not a collision
					{
						if (DNA[x].getXPos() == DNA[y].getXPos())
							collisions++;
					}
				}
			}
		//CHECK DIAGONALS
			//check down left
			
			for(int DNAIndex = 0; DNAIndex < Nsize; DNAIndex++)
			{
				int x = DNA[DNAIndex].getXPos();
				int y = DNA[DNAIndex].getYPos();
				
				while(x < Nsize && y >= 0)
				{
					x++;
					y--;
					
					for(int compareIndex = DNAIndex + 1; compareIndex < Nsize; compareIndex++)
					{
						if(this.DNA[compareIndex].getXPos() == x && this.DNA[compareIndex].getYPos() == y) 
						{
							collisions++;
						}
					}
						
				}
			}
			//check down right
			for(int DNAIndex = 0; DNAIndex < this.DNA.length; DNAIndex++)
			{
				int x = this.DNA[DNAIndex].getXPos();
				int y = this.DNA[DNAIndex].getYPos();
				
				while(x < Nsize && y < Nsize)
				{
					x++;
					y++;
					
					for(int compareIndex = DNAIndex + 1; compareIndex < Nsize; compareIndex++)
					{
						if(this.DNA[compareIndex].getXPos() == x && this.DNA[compareIndex].getYPos() == y) 
						{
							collisions++;
						}
					}
						
				}
			}
			//check up left
			for(int DNAIndex = 0; DNAIndex < this.DNA.length; DNAIndex++)
			{
				int x = this.DNA[DNAIndex].getXPos();
				int y = this.DNA[DNAIndex].getYPos();
				
				while(x >= 0 && y >=0)
				{
					x--;
					y--;
					
					for(int compareIndex = DNAIndex + 1; compareIndex < Nsize; compareIndex++)
					{
						if(this.DNA[compareIndex].getXPos() == x && this.DNA[compareIndex].getYPos() == y) 
						{
							collisions++;
						}
					}
						
				}
			}
			//check up right
			for(int DNAIndex = 0; DNAIndex < this.DNA.length; DNAIndex++)
			{
				int x = this.DNA[DNAIndex].getXPos();
				int y = this.DNA[DNAIndex].getYPos();
				
				while(x >= 0 && y <= Nsize)
				{
					x--;
					y++;
					
					for(int compareIndex = DNAIndex + 1; compareIndex < Nsize; compareIndex++)
					{
						if(this.DNA[compareIndex].getXPos() == x && this.DNA[compareIndex].getYPos() == y) 
						{
							collisions++;
						}
					}
						
				}
			}
			//*/
			//END COLLISION CHECKS
			//System.out.println("collisions: " + collisions);
			
			if(collisions != 0)
			{
				this.fitness = 1/collisions;
			}
			else
			{
				System.out.println("ALERT! ALERT! ALERT!---------------------");
				System.out.println("Solution Found:");
				for(int i = 0; i < Nsize; i++)
				{
				System.out.println("Queen: " + i + " x: "+ this.DNA[i].getXPos() + " y: "+ this.DNA[i].getYPos());
				}
				this.fitness = maxInt;
				System.out.println();
				System.exit(0);
			}
			
	}//END CONSTRUCTOR
	public int numCollisions(individualNQ individual, int Nsize)
	{
	DNA = new queen[Nsize];
		
		//create queen array that will represent DNA
		for (int i = 0; i < Nsize; i++)
		{
			//creates queen with random x and y position
			this.DNA[i] = new queen(RNG.nextInt(Nsize),RNG.nextInt(Nsize));
			//System.out.println("Q"+ i + ": X position: " + this.DNA[i].getXPos() + ": Y position: " + this.DNA[i].getYPos());
		}
		//System.out.println("Individual Created ");
		//determine number of collisions
		this.collisions = 0;
			//check horizontally
		///*
			for (int x = 0; x < Nsize; x++)
			{
				for (int y = x; y < Nsize; y++)
				{
					if (x != y)//a queen compared to itself is not a collision
					{
						if (DNA[x].getYPos() == DNA[y].getYPos())
							collisions++;
					}
				}
			}
			//check vertically
			for (int x = 0; x < Nsize; x++)
			{
				for (int y = x; y < Nsize; y++)
				{
					if (x != y)//a queen compared to itself is not a collision
					{
						if (DNA[x].getXPos() == DNA[y].getXPos())
							collisions++;
					}
				}
			}
		//CHECK DIAGONALS
			//check down left
			
			for(int DNAIndex = 0; DNAIndex < Nsize; DNAIndex++)
			{
				int x = DNA[DNAIndex].getXPos();
				int y = DNA[DNAIndex].getYPos();
				
				while(x < Nsize && y >= 0)
				{
					x++;
					y--;
					
					for(int compareIndex = DNAIndex + 1; compareIndex < Nsize; compareIndex++)
					{
						if(this.DNA[compareIndex].getXPos() == x && this.DNA[compareIndex].getYPos() == y) 
						{
							collisions++;
						}
					}
						
				}
			}
			//check down right
			for(int DNAIndex = 0; DNAIndex < this.DNA.length; DNAIndex++)
			{
				int x = this.DNA[DNAIndex].getXPos();
				int y = this.DNA[DNAIndex].getYPos();
				
				while(x < Nsize && y < Nsize)
				{
					x++;
					y++;
					
					for(int compareIndex = DNAIndex + 1; compareIndex < Nsize; compareIndex++)
					{
						if(this.DNA[compareIndex].getXPos() == x && this.DNA[compareIndex].getYPos() == y) 
						{
							collisions++;
						}
					}
						
				}
			}
			//check up left
			for(int DNAIndex = 0; DNAIndex < this.DNA.length; DNAIndex++)
			{
				int x = this.DNA[DNAIndex].getXPos();
				int y = this.DNA[DNAIndex].getYPos();
				
				while(x >= 0 && y >=0)
				{
					x--;
					y--;
					
					for(int compareIndex = DNAIndex + 1; compareIndex < Nsize; compareIndex++)
					{
						if(this.DNA[compareIndex].getXPos() == x && this.DNA[compareIndex].getYPos() == y) 
						{
							collisions++;
						}
					}
						
				}
			}
			//check up right
			for(int DNAIndex = 0; DNAIndex < this.DNA.length; DNAIndex++)
			{
				int x = this.DNA[DNAIndex].getXPos();
				int y = this.DNA[DNAIndex].getYPos();
				
				while(x >= 0 && y <= Nsize)
				{
					x--;
					y++;
					
					for(int compareIndex = DNAIndex + 1; compareIndex < Nsize; compareIndex++)
					{
						if(this.DNA[compareIndex].getXPos() == x && this.DNA[compareIndex].getYPos() == y) 
						{
							collisions++;
						}
					}
						
				}
			}
			//*/
			//END COLLISION CHECKS
			//System.out.println("collisions: " + collisions);
			
			if(collisions != 0)
			{
				this.fitness = 1/collisions;
				return collisions;
			}
			else
			{
				System.out.println("Solution Found:");
				
				for(int i = 0; i < Nsize; i++)
				{
				System.out.println("Queen: " + i + " x: "+ this.DNA[i].getXPos() + " y: "+ this.DNA[i].getYPos());
				}
			
				this.fitness = maxInt;
				return 0;
				//System.exit(0);
			}
		
	}
	
	
	
	
	
	public double getFitness()
	{
		return this.fitness;
	}
	
}
