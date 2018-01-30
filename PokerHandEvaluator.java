package poker;

import java.util.Arrays;

public class PokerHandEvaluator {
	/*Return true if there is a hand where two cards have the same value. This 
	 * method cycles through the array and finds 1 matching pair. */
	public static boolean hasPair(Card[] cards) {
		int counter = 0;
		for(int i = 0; i < cards.length; i++){
			for(int j = i+1; j< cards.length; j++){
				if(cards[i].getValue() == cards[j].getValue()){
					counter++;
				}
			}
		}
		if(counter>=1){
			return true;
		}
		return false;
	}
	/*Returns true if you can find two distinct pairs of matching values. This
	 * method cycles through the array of cards and checks to see if there
	 * are 2 distinct pairs of matching cards.*/
	public static boolean hasTwoPair(Card[] cards) {
		int counter = 0;
		int value = 0;
		for(int i = 0; i< cards.length; i++){
			for(int j = i+1; j< cards.length; j++){
				if(cards[i].getValue() == cards[j].getValue() 
						&& value !=cards[j].getValue()){
					counter++;
					value = cards[j].getValue();
				}
			}
		}
		if(counter>=2){
			return true;
		}
		return false;
	}
	/*Returns true if your hand has three cards with matching values. This 
	 * method cycles through the array of cards and checks to see if there are
	 * 3 cards with the same value.*/
	public static boolean hasThreeOfAKind(Card[] cards) {
		int counter = 0;
		for(int i = 0; i< cards.length; i++){
			for(int j = i+1; j< cards.length; j++){
				if(cards[i].getValue() == cards[j].getValue()){
					counter++;
				}
			}
		}
		if(counter>=3){
			return true;
		}
		return false;
	}
	/*Returns true if there are 5 cards with consecutive values. This method
	 * carefully cycles through the array and checks to see if there is a 
	 * value in the array that immediately follows the previous number. Also,
	 * this method states that if an Ace is in your hand then it is either the
	 * beginning or the end of the hand.*/
	public static boolean hasStraight(Card [] cards) {
		int smallNum = 14;
		int counter = 0;
		for (int i = 0; i<cards.length; i++){
			if (cards[i].getValue() < smallNum){
				smallNum = cards[i].getValue(); 
			}
		}
		for (int i = 0; i<cards.length; i++){
			if (cards[i].getValue() == smallNum+1){
				counter++;
			}
		}
		if (counter == 0 && smallNum == 1){
			smallNum = 10;
		}
		for (int x = 0; x<cards.length; x++){
			if (cards[x].getValue() == smallNum){
				counter++;
			}
		}
		if(counter!=0){
			counter = 1;
			for (int x = 0; x<cards.length; x++){
				if (cards[x].getValue() == smallNum+1){
					counter++;
				}
			}
			if(counter!=1){
				counter = 2;
				for (int x = 0; x<cards.length; x++){
					if (cards[x].getValue() == (smallNum+2)){
						counter++;
					}
				}
				if(counter!=2){
					counter = 3;

					for (int x = 0; x<cards.length; x++){
						if (cards[x].getValue() == smallNum+3){
							counter++;
						}
					}
					if(counter!=3){
						counter = 4;

						if((smallNum+4) == 14){
							smallNum = 1;
						}else{
							smallNum = smallNum+4;
						}
						for (int x = 0; x<cards.length; x++){
							if (cards[x].getValue() == smallNum){
								counter++;
							}
						}
						if(counter!=4){
							return true;
						}else{
							return false;
						}
					}else{
						return false;
					}
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	/*Returns true if five cards of the same suit. This method cycles through 
	 * the array and checks if all the cards in your hand has the same suit*/
	public static boolean hasFlush(Card[] cards) {
		int counter = 0;
		for(int i = 1; i< cards.length; i++){
			if(cards[0].getSuit() == cards[i].getSuit()){
				counter++;
			}
		}
		if(counter==4){
			return true;
		}
		return false;
	}
	/*This method returns true if a pair of cards share the same value, and the
	 *other three cards share a second value. */
	public static boolean hasFullHouse(Card[] cards) {
		int counter = 0;
		for(int i = 0; i< cards.length; i++){
			for(int j = i+1; j< cards.length; j++){
				if(cards[i].getValue() == cards[j].getValue()){
					counter++;
				}
			}
		}
		if(counter==4){
			return true;
		}
		return false;
	}
	/*Similar to three of a kind, this method returns true if there are 
	 * four matching values in a hand. */
	public static boolean hasFourOfAKind(Card[] cards) {
		int counter = 0;
		for(int i = 0; i< cards.length; i++){
			for(int j = i+1; j< cards.length; j++){
				if(cards[i].getValue() == cards[j].getValue()){
					counter++;
				}
			}
		}
		if(counter==6){
			return true;
		}
		return false;
	}
	//If there is a straight as well as a flush this method will return true.
	public static boolean hasStraightFlush(Card[] cards) {
		if(hasStraight(cards) == true && hasFlush(cards)==true){
			return true;
		}
		return false;
	}
}

