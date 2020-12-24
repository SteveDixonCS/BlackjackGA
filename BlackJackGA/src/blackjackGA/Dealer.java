package blackjackGA;

public class Dealer {
	 Deck deck = new Deck();
	 public int handValue = 0;
	 
		public int calcHandValue()
		{
			int handValue = 0;
			for (int i = 0; i < deck.deck.size(); i++)//loop through hand
			{
				if(deck.getCard(i).getRank().ordinal() == 0)//if ace
				{
					handValue += 11;
					//System.out.println("Rank Detected Dealer: " + deck.getCard(i).getRank() + " current hand value: " + handValue);
				
				}
				else if(deck.getCard(i).getRank() == Rank.KING || deck.getCard(i).getRank() == Rank.QUEEN  || deck.getCard(i).getRank() == Rank.JACK || deck.getCard(i).getRank() == Rank.TEN)// if K,Q,J,10
				{
					handValue += 10;
					//System.out.println("Rank Detected Dealer: " + deck.getCard(i).getRank() + " current hand value: " + handValue);
				}
				else if(deck.getCard(i).getRank() == Rank.TWO)
				{
					handValue += 2;
					//System.out.println("Rank Detected Dealer: " + deck.getCard(i).getRank() + " current hand value: " + handValue);
				}
				else if(deck.getCard(i).getRank() == Rank.THREE)
				{
					handValue += 3;
					//System.out.println("Rank Detected Dealer: " + deck.getCard(i).getRank() + " current hand value: " + handValue);
				}
				else if(deck.getCard(i).getRank() == Rank.FOUR)
				{
					handValue += 4;
					//System.out.println("Rank Detected Dealer: " + deck.getCard(i).getRank() + " current hand value: " + handValue);
				}
				else if(deck.getCard(i).getRank() == Rank.FIVE)
				{
					handValue += 5;
					//System.out.println("Rank Detected Dealer: " + deck.getCard(i).getRank() + " current hand value: " + handValue);
				}
				else if(deck.getCard(i).getRank() == Rank.SIX)
				{
					handValue += 6;
					//System.out.println("Rank Detected Dealer: " + deck.getCard(i).getRank() + " current hand value: " + handValue);
				}
				else if(deck.getCard(i).getRank() == Rank.SEVEN)
				{
					handValue += 7;
					//System.out.println("Rank Detected Dealer: " + deck.getCard(i).getRank() + " current hand value: " + handValue);
				}
				else if(deck.getCard(i).getRank() == Rank.EIGHT)
				{
					handValue += 8;
					//System.out.println("Rank Detected Dealer: " + deck.getCard(i).getRank() + " current hand value: " + handValue);
				}
				else 
				{
					handValue += 9;
				}
				
			}
			if (handValue > 21)//check for possible ace value reductions where originally valued at 11
			{
				for(int i = 0; i < deck.deck.size(); i++)//loop through hand
				{
					if(deck.getCard(i).getRank() == Rank.ACE)//if ace
					{
						handValue -=10;
						//System.out.println("Rank Detected Dealer: " + deck.getCard(i).getRank() + " current hand value: " + handValue);
					}
				}
			}
			this.handValue = handValue;
			return handValue;
		}
}
