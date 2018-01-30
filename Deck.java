package poker;

public class Deck {

	private Card[] cards;
	//Initializes a deck of 52 cards. Every 13 cards has a different suit
	public Deck() {
		cards = new Card[52];
		int counter = 0;
		while(counter < 52){
			for(int suit = 0; suit<4; suit++){
				for(int i = 1; i <= 13; i++){
					cards[counter] = new Card(i, suit);
					counter++;
				}
			}		
		}
	}
	//Copy Constructor
	public Deck(Deck other) {
		cards = new Card[other.getNumCards()];
		for(int i = 0; i < cards.length; i++){
			cards[i] = other.getCardAt(i);
		}
	}
	//This method returns the card at the stated position
	public Card getCardAt(int position) {
		int value = cards[position].getValue();
		int suit = cards[position].getSuit();
		return new Card(value,suit);
	}
	//Returns the number of cards in the deck
	public int getNumCards() {
		return cards.length;
	}
	/*The shuffle method first divides the cards into two decks, the top deck
	 * and the bottom deck. It then places the first card of the top deck and the 
	 * second card of the bottom deck into a new deck. The instance variable cards
	 * then refers to this "new Deck" and is therefore shuffled. */
	public void shuffle() {
		Card[] topHalf;
		Card[] bottomHalf;
		Card[] newCardDeck = new Card[cards.length];
		if(cards.length %2 == 0){
			topHalf = new Card[cards.length/2];
			bottomHalf = new Card[cards.length/2];
		}else{
			topHalf = new Card[(cards.length+1)/2];
			bottomHalf = new Card[cards.length/2];
		}
		int index;
		for(index = 0; index<topHalf.length; index++){
			topHalf[index]=cards[index];
		}
		index = topHalf.length;
		for(int place = 0; place < bottomHalf.length; place++){
			bottomHalf[place]=cards[index+place];
		}
		int x = 0;
		int y = 0;
		for(int newPlace = 0; newPlace<newCardDeck.length; newPlace++){
			if(newPlace%2==0){
				newCardDeck[newPlace]=topHalf[x];
				x++;
			}else{
				newCardDeck[newPlace] = bottomHalf[y];
				y++;
			}
		}
		cards = newCardDeck;
	}
	/*The cut method divides the deck in two depending on the position 
	 * indicated by the user. Both decks are divided into two new decks,
	 * and then the bottom deck is placed on top and the top deck is placed
	 * on the bottom of a new deck of cards. The instance variable cards
	 * then refers to this "new Deck" and is therefore cut.*/
	public void cut(int position) {
		int topHalf;
		int mask = 0;
		Card[] topTemp = new Card[position];
		Card[] bottomTemp = new Card[cards.length-position];
		Card[] finalTemp = new Card[cards.length];

		for( topHalf = 0; topHalf<position;topHalf++){
			topTemp[topHalf] = cards[topHalf];
		}
		int x = position;
		for(int bottomHalf = 0; bottomHalf<bottomTemp.length; bottomHalf++){
			bottomTemp[bottomHalf] = cards[x];
			x++;
		}
		for(int counter = 0; counter < finalTemp.length; counter++){
			if(counter < bottomTemp.length){
				finalTemp[counter] = bottomTemp[counter];
			}else{
				finalTemp[counter] = topTemp[mask];
				mask++;
			}
		}
		cards = finalTemp;
	}
	/*The deal method deals out the indicated number of cards. The amount of 
	 * cards in the original deck decreases by the number of cards dealt. The
	 * cards dealt are returned. */
	public Card[] deal(int numCards) {
		Card[] smaller = new Card[cards.length - numCards];
		Card[] hand = new Card[numCards];
		int x = 0;
		for(int i = 0; i<cards.length; i++){
			if(i<numCards){
				hand[i] = cards[i];
			}else{
				smaller[x] = cards[i];
				x++;
			}
		}
		cards = smaller;
		return hand;

	}

}
