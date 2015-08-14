package oodesign;

import java.util.ArrayList;

public class Deck<T extends Card> {
	protected ArrayList<T> cards = new ArrayList<T>();
	private int dealtIndex = 0;
	
	public void shuffle(){}
	
	public int remainingCards(){
		return cards.size() - dealtIndex;
	}
	
	public void dealHand(int number){}
	
}
