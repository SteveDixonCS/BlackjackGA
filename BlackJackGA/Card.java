package blackjackGA;

//code derived from https://youtu.be/xLhgqPUHoVs

public class Card {
	//create instance variables for card object
	private Suit suit;
	private Rank rank;
	
	//Card constructor
	public Card(Suit suit, Rank rank)
	{
		this.suit = suit;
		this.rank = rank;
		
	}
	
	//Print card rank and suit
	public String toString()
	{
		return this.rank.toString() + " of " + this.suit.toString() + "S";
	}
	
	//return card rank
	public Rank getRank()
	{
		return this.rank;
	}
}
