package blackjackGA;

import java.util.Random;

public class BlackJackGA {
	static int populationSize = 845;
	static int decksInPlay = 130;//casino level blackjack uses 6 decks
	static int gamesSimulated = 0;
	static int gamesPerGeneration = 417;
	static int generation = 0;
	static int maxGeneration = 10000;
	static Player bestFromGen = new Player();
	static Player bestAllTime = new Player();
	//static boolean verbose =false;

	
	public Player[] createNewGeneration(Player[] population)
	{
		Player[] nextGen = new Player[populationSize];
		Player parentA;
		Player parentB;
		
		for(int i = 0; i < population.length; i++)
		{
		//start tournament
		Random random = new Random();
		Player contestantA = population[random.nextInt(populationSize)];
		Player contestantB = population[random.nextInt(populationSize)];
		Player contestantC = population[random.nextInt(populationSize)];
		Player contestantD = population[random.nextInt(populationSize)];
			
			if(contestantA.fitness > contestantB.fitness)
			{
				parentA = contestantA;
			}
			else
			{
				parentA = contestantB;
			}
			
			if(contestantC.fitness > contestantD.fitness)
			{
				parentB = contestantC;
			}
			else
			{
				parentB = contestantD;
			}
		//winners of tournament crossover
		nextGen[i] = parentA.crossover(parentB);
		//child mutates self
		nextGen[i].mutate();
		//System.out.println("PLAYER " + i + ": \n"+ nextGen[i].toString());
		}//end for loop. population full
		return nextGen;
	}

	public static void main(String[] args) {
		
		BlackJackGA GA = new BlackJackGA();
		//int generation = 0;
		//int gamesSimulated = 0;
		
		// TODO Auto-generated method stub
		
		//create population
		Player[] population = new Player[populationSize];
		
		//initialize population
		for(int i = 0; i < populationSize; i++)//for each player
		{
			population[i] = new Player();
			
			//System.out.println("PLAYER" + i + ": \n"+ population[i].toString());
		}
		
		//create Dealer
		Dealer dealer = new Dealer();
		
		while(generation <= maxGeneration)
			{
			System.out.println("\nSTART OF GENERATION:" + generation + "\n");
			gamesSimulated = 0;
			
			while (gamesSimulated < gamesPerGeneration)
			{
				gamesSimulated++;
				System.out.println("\nGAME "+ gamesSimulated +" HAS STARTED\n");
				// Create game Deck
				Deck gameDeck = new Deck();
				//Fill deck with cards
				for(int i =0; i< decksInPlay; i++) 
				{
					gameDeck.createDeck();
				}
				//randomize deck order
				gameDeck.shuffle();
				
				//Check deck created successfully
				//System.out.println(gameDeck);
				
				//clear decks at start of new game
				dealer.deck.clear();
				for(int i = 0; i < populationSize; i++)//for each player
				{
					population[i].playerDeck.clear();
				}
				
				for(int i = 0; i < populationSize; i++)//for each player
				{
					//each player in pop gets the 2 same cards. testing decision making in same test against different individuals
					//population[i].playerDeck.addCard(gameDeck.getCard(0));
					//population[i].playerDeck.addCard(gameDeck.getCard(1));
					
					//each player in pop draws unique cards from universal game deck
					population[i].playerDeck.draw(gameDeck);
					population[i].playerDeck.draw(gameDeck);
					population[i].calcHandValue();
					
					System.out.println("Player " + i +" Intial Deck: " + population[i].playerDeck.toString());
					System.out.println("Current Player Hand Value: "+ population[i].handValue + "\n");
				}
				//remove cards that players received
				//gameDeck.removeCard(0);
				//gameDeck.removeCard(1);
				
				//dealer draws 2
				dealer.deck.draw(gameDeck);
				dealer.deck.draw(gameDeck);
				System.out.println("Dealer Inital Deck: " + dealer.deck.toString());
				dealer.handValue = dealer.calcHandValue();
				System.out.println("Current Dealer Hand Value: " + dealer.handValue );
				
				
				for(int i = 0; i < populationSize; i++)//for each player
				{
					//loop through player decision array
					for(int decisionArrayIndex = 0; decisionArrayIndex < population[i].choicesForHand.length ; decisionArrayIndex++)
					{
						//if hit (1) give new card
						if (population[i].calcHandValue() < 21 && population[i].playerChoice(decisionArrayIndex) == 1)
						{
							System.out.println("\nPLAYER " + i +  " DRAWS\n");
							population[i].playerDeck.draw(gameDeck);
							System.out.println("New Player " + i + " Deck: " + population[i].playerDeck.toString()+ "\n");
							population[i].calcHandValue();
							System.out.println("New Player "+ i +" Hand Value: "+ population[i].handValue );
						
						}
						else //if stand break from loop. go to next player
						{
							decisionArrayIndex = population[i].choicesForHand.length;
							break;
						}
					}
					
					//player calculates final hand value
					population[i].handValue = population[i].calcHandValue();
					System.out.println("\nPlayer "+ i +" Final Hand Value: "+ population[i].handValue );
				}
				
				//dealer finalizes hand
				dealer.handValue = dealer.calcHandValue();
				//by ruler dealer must draw if less than 17
				while(dealer.handValue < 17)
				{
					System.out.println("\nDEALER DRAWS\n");
					dealer.deck.draw(gameDeck);
					dealer.calcHandValue();
					System.out.println("New Dealer Deck: " + dealer.deck.toString());
					System.out.println("Dealer Hand Value: " + dealer.handValue );
				}
				System.out.println("\nDealer Final Deck: " + dealer.deck.toString());
				dealer.calcHandValue();
				System.out.println("Dealer Final Hand Value: " + dealer.handValue );
				//determine winner
				for(int i = 0; i < populationSize; i++)//for each player
				{
					if(dealer.handValue > 21 && population[i].handValue <= 21)//dealer hand bust and player hand under. player win
					{
						population[i].gamesWon+=1;
						population[i].pureWins+=1;
						System.out.println("\nPlayer "+ i +  " Won");
						System.out.println("\nIndividual " + i + " games won: "+ population[i].gamesWon);
						System.out.println("Individual " + i + " fitness: "+ population[i].fitness);
					}	
					else if(dealer.handValue <= 21 && population[i].handValue <= 21)//both dealer hand and player hand under or at 21
					{
						if(population[i].handValue > dealer.handValue)//if player hand higher player win
						{
							population[i].gamesWon+=1;
							population[i].pureWins+=1;
							System.out.println("\nPlayer "+ i +  " Won");
							System.out.println("\nIndividual " + i + " games won: "+ population[i].gamesWon);
							System.out.println("Individual " + i + " fitness: "+ population[i].fitness);
						}
						else if(dealer.handValue == population[i].handValue)//if equal tie
						{
							population[i].gamesWon+=0.25;
							System.out.println("\nPlayer "+ i + " Tied");
							System.out.println("\nIndividual " + i + " games won: "+ population[i].gamesWon);
							System.out.println("Individual " + i + " fitness: "+ population[i].fitness);
						}
						else//else lose
						{
							System.out.println("\nPlayer "+ i +" Lost");
							System.out.println("\nIndividual " + i + " games won: "+ population[i].gamesWon);
							System.out.println("Individual " + i + " fitness: "+ population[i].fitness);
						}
					}
					else//else lose
					{
						System.out.println("\nPlayer "+ i +"  Lost");
						System.out.println("\nIndividual " + i + " games won: "+ population[i].gamesWon);
						System.out.println("Individual " + i + " fitness: "+ population[i].fitness);
					}
		
				}
				//Game End
				System.out.println("\nGAME "+ gamesSimulated +" HAS ENDED\n");
				//calculate each player fitness
				for(int i = 0; i < populationSize; i++)//for each player
				{	
					population[i].fitness = (population[i].gamesWon / gamesSimulated);
					population[i].pureWinPercent = (population[i].pureWins / gamesSimulated);
					//System.out.println("\nEND GAME: Individual " + i + "pure win percentage (no award for ties): "+ (population[i].pureWinPercent * 100) + "%");
					//System.out.println("\nEND GAME: Individual " + i + " fitness "+ population[i].fitness);
				}
			}//end while
			System.out.println("\nEND OF GENERATION: " + generation);
			System.out.println("--------------------------------------------------------------------------------------------");
			
			//find best individual
			int bestIndex = 0;
			double currentBestFitness = 0;
			
			for(int i = 0; i < populationSize; i++)//for each player
			{		
				if(population[i].fitness > currentBestFitness)
				{
					bestIndex = i;
					currentBestFitness = population[i].fitness;
				}
			}
			
			//store print best individual from generation and all time
		
			// copy stats of best individual
			
			//copy decision matrix to new instance "bestFromGen"
			for(int firstCard = 0; firstCard < population[bestIndex].rankLength ; firstCard++)
			{
				for(int secondCard = 0;  secondCard < population[bestIndex].rankLength ; secondCard++)
				{
					for(int decisionArrayIndex = 0; decisionArrayIndex <  population[bestIndex].decisionArrayLength; decisionArrayIndex++)
					{
						 bestFromGen.matrixKey[firstCard][secondCard][decisionArrayIndex] =  population[bestIndex].matrixKey[firstCard][secondCard][decisionArrayIndex];
						 if(bestFromGen.fitness > bestAllTime.fitness)
						 {
							 bestAllTime.matrixKey[firstCard][secondCard][decisionArrayIndex] =  population[bestIndex].matrixKey[firstCard][secondCard][decisionArrayIndex];
						 }
						 
					}
				}
			}
			
			//copy fitness value
			 bestFromGen.fitness =  population[bestIndex].fitness;
			 bestFromGen.pureWins = population[bestIndex].pureWins;
			 
			 if(bestFromGen.fitness > bestAllTime.fitness)
			 {
				 bestAllTime.fitness =  population[bestIndex].fitness;
				 bestAllTime.pureWins = population[bestIndex].pureWins;
			 }
			 
			 /*
			 if(bestFromGen.fitness > bestAllTime.fitness)
			 {
				//copy decision matrix to bestAllTime
					for(int firstCard = 0; firstCard < population[bestIndex].rankLength ; firstCard++)
					{
						for(int secondCard = 0;  secondCard < population[bestIndex].rankLength ; secondCard++)
						{
							for(int decisionArrayIndex = 0; decisionArrayIndex <  population[bestIndex].decisionArrayLength; decisionArrayIndex++)
							{
								 bestAllTime.matrixKey[firstCard][secondCard][decisionArrayIndex] =  population[bestIndex].matrixKey[firstCard][secondCard][decisionArrayIndex];
							}
						}
					}
					
					bestAllTime.fitness =  population[bestIndex].fitness;
			 }
			 */
			 System.out.print("\nBest in Gen: \n"+ bestFromGen.toStringBest() + "\nPure Win Percentage (no award for ties) : " + ((bestFromGen.pureWins / gamesSimulated)*100) + "%");
			 System.out.print("\n\nBest all Time: \n"+ bestAllTime.toStringBest() + "\nPure Win Percentage (no award for ties) : " + ((bestAllTime.pureWins / gamesSimulated)*100) + "%");
			
			population = GA.createNewGeneration(population);
			generation++;
		}//end while. total generations
	}

}
