package blackjackGA;

import java.util.ArrayList;
import java.util.Random;

//code derived from https://youtu.be/xLhgqPUHoVs
public class Deck {
	

	
	// create an arrayList of card objects
	public ArrayList<Card> deck = new ArrayList<>();
	
	public void clear()
	{
		deck.clear();
	}
	
	//Initialize ArrayList
	public Deck()
	{
		this.deck = new ArrayList<Card>();
	}
	
	//Fill ArrayList "deck" with every combination of rank and suit cards
	public void createDeck() 
	{
		
			for(Suit cardSuit : Suit.values())
			{
				for(Rank cardRank : Rank.values())
				{
					this.deck.add(new Card(cardSuit, cardRank));
				}
			}
		
	}
	
	//shuffle deck
	public void shuffle()
	{
		//create temp deck
		ArrayList<Card> tempDeck = new ArrayList<Card>();
		Random random  = new Random();
		int randomCardIndex = 0;
		int orginalSize = this.deck.size();
		for (int i = 0; i < orginalSize; i++)
		{
			//generate random index
			randomCardIndex = random.nextInt(this.deck.size());
			//add card at index to temp deck
			tempDeck.add(this.deck.get(randomCardIndex));
			//remove card arr index from orginal deck
			this.deck.remove(randomCardIndex);
		}
		//set orginal deck to temp deck
		this.deck = tempDeck;
	}
	
	//print deck
	public String toString()
	{
		String deckString = "";
		int i = 0;
		for (Card aCard : this.deck)
		{
			deckString += "\n" + i + "-"+ aCard.toString();
			i++;
		}
		return deckString;
	}
	
	public void removeCard(int index)
	{
		this.deck.remove(index);
	}
	
	public Card getCard(int index)
	{
		return this.deck.get(index);
	}
	public void addCard(Card newCard)
	{
		
		this.deck.add(newCard);
	}
	//Draw from deck
	public void draw(Deck source)
	{
		//get card for top of source deck and add to deck that called the draw method
		this.deck.add(source.getCard(0));
		//remove card from source deck
		source.removeCard(0);
	}
	public void sort()
	{
		ArrayList<Card> tempDeck = new ArrayList<Card>();
		
		while(this.deck.size() != 0)//loop through deck
		{
			int currentIndex = 0;
			int lowestValueIndex = 0;//start from beginning of deck each time
			
			for(int compareIndex = currentIndex+1; compareIndex < this.deck.size(); compareIndex++)//compare rest of deck
			{
				if(this.getCard(currentIndex).getRank().compareTo(this.getCard(compareIndex).getRank())  > 0)//if current card greater than compared card
				{
					lowestValueIndex = compareIndex;
				}
			}
			tempDeck.add(this.deck.get(lowestValueIndex ));
			this.removeCard(lowestValueIndex);
		}
		this.deck = tempDeck;

	}
	/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deck == null) ? 0 : deck.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
		{
			if(this == obj)//if self
			{
				return true;
			}
			if(obj == null)//if compare to empty
			{
				return false;
			}
			if(obj instanceof Deck)
			{
				Deck test = (Deck)obj;
				int orginalSize = this.deck.size();
				if(test.deck.size() != this.deck.size())//check decks same size
				{
					return false;
				}
				boolean sameCardsInDeck = true;
				this.sort();
				test.sort();
				for(int i = 0; i< orginalSize; i++)
				{
					if(this.getCard(i).getRank() != test.getCard(i).getRank())
					{
						sameCardsInDeck = false;
						break;
					}
				}
				return sameCardsInDeck;
			}
			return false;
		}//end equals
	*/
}
