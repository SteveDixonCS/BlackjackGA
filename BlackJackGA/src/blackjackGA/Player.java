package blackjackGA;

import java.util.*;

public class Player {
	public Deck playerDeck;
	public double fitness;
	public double pureWins;
	public double gamesWon = 0;
	public double pureWinPercent = 0;
	public int handValue = 0;
	public int mutationFactor = 507;
	//public HashMap<Card[][], int[]> DecisionMatrix = new HashMap();

	int rankLength = 13;
	int decisionArrayLength = 5;
	int [][][] matrixKey = new int[rankLength][rankLength][decisionArrayLength];
	int[] choicesForHand = new int[decisionArrayLength];
	
	Random random  = new Random();
	
	
	Player()
	{
		//initialize values
		playerDeck = new Deck();
		fitness = 0;
		pureWins = 0;
		
		for(int firstCard = 0; firstCard < rankLength ; firstCard++)
		{
			for(int secondCard = 0;  secondCard < rankLength ; secondCard++)
			{
				for(int decisionArrayIndex = 0; decisionArrayIndex < decisionArrayLength; decisionArrayIndex++)
				{
					matrixKey[firstCard][secondCard][decisionArrayIndex] = random.nextInt(2);//pick 0 or 1
				}
			}
		}
		
	}

	public String toString()
	{

		return playerDeck.deck.toString();
		/*
		String playerString = "";
		
		for(Rank cardRank : Rank.values())
		{
			
			for(Rank cardRank2 : Rank.values())
			{
				playerString = playerString + cardRank + "("+ cardRank.ordinal() + ")," + cardRank2+ "("+ cardRank2.ordinal()  + "): ";
				for(int decisionArrayIndex = 0; decisionArrayIndex < decisionArrayLength; decisionArrayIndex++)
				{
					playerString = playerString + matrixKey[cardRank.ordinal()][cardRank2.ordinal()][decisionArrayIndex] + "  ";//pick 0 or 1
				}
				playerString += "\n";
			}
		}
		return playerString;
		*/
	}
	
	public String toStringBest()
	{
		String playerString = "";
		
		for(Rank cardRank : Rank.values())
		{
			
			for(Rank cardRank2 : Rank.values())
			{
				playerString = playerString + cardRank + "("+ cardRank.ordinal() + ")," + cardRank2+ "("+ cardRank2.ordinal()  + "): ";
				for(int decisionArrayIndex = 0; decisionArrayIndex < decisionArrayLength; decisionArrayIndex++)
				{
					playerString = playerString + matrixKey[cardRank.ordinal()][cardRank2.ordinal()][decisionArrayIndex] + "  ";//pick 0 or 1
				}
				playerString += "\n";
			}
		}
		playerString += "\nfitness: " + fitness;
		
		return playerString;
	}
	
	public String printPlayerDeck()
	{
		return this.playerDeck.toString();
	}
	
	//to return player choice lookup value in decisionArrayIndex and return. 0 = stand, 1 = hit
	public int playerChoice(int decisionArrayIndex)
	{
		int firstDimension = 0;
		int secondDimension = 0;
		
		switch(playerDeck.getCard(0).getRank()) 
		{
		case ACE:
			break;
		case TWO:
			firstDimension = 1;
			break;
		case THREE:
			firstDimension = 2;
			break;
		case FOUR:
			firstDimension = 3;
			break;
		case FIVE:
			firstDimension = 4;
			break;
		case SIX:
			firstDimension = 5;
			break;
		case SEVEN:
			firstDimension = 6;
			break;
		case EIGHT:
			firstDimension = 7;
			break;
		case NINE:
			firstDimension = 8;
			break;
		case TEN:
			firstDimension = 9;
			break;
		case JACK:
			firstDimension = 10;
			break;
		case QUEEN:
			firstDimension = 11;
			break;
		case KING:
			firstDimension = 12;
			break;
		}//end switch
		switch(playerDeck.getCard(1).getRank()) 
		{
		case ACE:
			break;
		case TWO:
			firstDimension = 1;
			break;
		case THREE:
			firstDimension = 2;
			break;
		case FOUR:
			firstDimension = 3;
			break;
		case FIVE:
			firstDimension = 4;
			break;
		case SIX:
			firstDimension = 5;
			break;
		case SEVEN:
			firstDimension = 6;
			break;
		case EIGHT:
			firstDimension = 7;
			break;
		case NINE:
			firstDimension = 8;
			break;
		case TEN:
			firstDimension = 9;
			break;
		case JACK:
			firstDimension = 10;
			break;
		case QUEEN:
			firstDimension = 11;
			break;
		case KING:
			firstDimension = 12;
			break;
		}//end switch
		return matrixKey[firstDimension][secondDimension][decisionArrayIndex];
	}
	
	public int calcHandValue()
	{
		int handValue = 0;
		
		for (int i = 0; i < playerDeck.deck.size(); i++)//loop through hand
		{
			if(playerDeck.getCard(i).getRank() == Rank.ACE)//if ace
			{
				handValue += 11;
				//System.out.println("Rank Detected Player: " + playerDeck.getCard(i).getRank() + " current hand value: " + handValue);
			
			}
			else if(playerDeck.getCard(i).getRank() == Rank.KING || playerDeck.getCard(i).getRank() == Rank.QUEEN || playerDeck.getCard(i).getRank() == Rank.JACK || playerDeck.getCard(i).getRank() == Rank.TEN )// if face K,Q,J
			{
				handValue += 10;
				//System.out.println("Rank Detected Player: " + playerDeck.getCard(i).getRank() + " current hand value: " + handValue);
			}
			else if(playerDeck.getCard(i).getRank() == Rank.TWO)
			{
				handValue += 2;
				//System.out.println("Rank Detected Player: " + playerDeck.getCard(i).getRank() + " current hand value: " + handValue);
			
			}
			else if(playerDeck.getCard(i).getRank() == Rank.THREE)
			{
				handValue += 3;
				//System.out.println("Rank Detected Player: " + playerDeck.getCard(i).getRank() + " current hand value: " + handValue);
			
			}
			else if(playerDeck.getCard(i).getRank() == Rank.FOUR)
			{
				handValue += 4;
				//System.out.println("Rank Detected Player: " + playerDeck.getCard(i).getRank() + " current hand value: " + handValue);
			
			}
			else if(playerDeck.getCard(i).getRank() == Rank.FIVE)
			{
				handValue += 5;
				//System.out.println("Rank Detected Player: " + playerDeck.getCard(i).getRank() + " current hand value: " + handValue);
			
			}
			else if(playerDeck.getCard(i).getRank() == Rank.SIX)
			{
				handValue += 6;
				//System.out.println("Rank Detected Player: " + playerDeck.getCard(i).getRank() + " current hand value: " + handValue);
			
			}
			else if(playerDeck.getCard(i).getRank() == Rank.SEVEN)
			{
				handValue += 7;
				//System.out.println("Rank Detected Player: " + playerDeck.getCard(i).getRank() + " current hand value: " + handValue);
			
			}
			else if(playerDeck.getCard(i).getRank() == Rank.EIGHT)
			{
				handValue += 8;
				//System.out.println("Rank Detected Player: " + playerDeck.getCard(i).getRank() + " current hand value: " + handValue);
			
			}
			else
			{
				handValue += 9;
				//System.out.println("Rank Detected Player: " + playerDeck.getCard(i).getRank() + " current hand value: " + handValue);
			}
		}
		if (handValue > 21)//check for possible ace value reductions where originally valued at 11
		{
			for(int i = 0; i < playerDeck.deck.size(); i++)//loop through hand
			{
				if(playerDeck.getCard(i).getRank()== Rank.ACE)//if ace
				{
					handValue -=10;
					//System.out.println("Rank Detected Player: " + playerDeck.getCard(i).getRank() + " current hand value: " + handValue);
				}
			}
		}
		this.handValue = handValue;
		return handValue;
	}
	
	public Player crossover(Player parentB)
	{
		Player offspring = new Player();
		
		for(int firstCard = 0; firstCard < rankLength ; firstCard++)
		{
			for(int secondCard = 0;  secondCard < rankLength ; secondCard++)
			{
				for(int decisionArrayIndex = 0; decisionArrayIndex < decisionArrayLength; decisionArrayIndex++)
				{
					int geneSelector = random.nextInt(2);
					
					if(geneSelector == 0)//take from first parent
					{
						offspring.matrixKey[firstCard][secondCard][decisionArrayIndex] = this.matrixKey[firstCard][secondCard][decisionArrayIndex];
					}
					else//take from second parent
					{
						offspring.matrixKey[firstCard][secondCard][decisionArrayIndex] = parentB.matrixKey[firstCard][secondCard][decisionArrayIndex];
					}
				}
			}
		}
		return offspring;
	}//end crossover
	
	public void mutate()
	{
		for(int firstCard = 0; firstCard < rankLength ; firstCard++)
		{
			for(int secondCard = 0;  secondCard < rankLength ; secondCard++)
			{
				for(int decisionArrayIndex = 0; decisionArrayIndex < decisionArrayLength; decisionArrayIndex++)
				{
					int geneMutate = random.nextInt(mutationFactor);
					
					if(geneMutate== 0)//if mutation activated. bit flip
					{
						if(this.matrixKey[firstCard][secondCard][decisionArrayIndex] == 0)//if zero, switch to 1
						{
							this.matrixKey[firstCard][secondCard][decisionArrayIndex] = 1;
						}
						else//else, switch to zero
						{
							this.matrixKey[firstCard][secondCard][decisionArrayIndex] = 0;
						}
					}

				}
			}
		}
	}
}


