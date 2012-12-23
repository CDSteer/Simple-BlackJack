import java.util.Random;
class Card {
	//sets up the varables used in the constructor
	String suite;
	int value; 
	String cardName;
	boolean used;
	
	//following code contructs a card object with the properties suite, value and used. The name is determied from the value
	public Card(String suite, int value, boolean used) {
			this.suite = suite;
			this.value = value;
			this.cardName = this.setCardName(value);
			this.used = used;
	}
	//this sets the suite of the card
	public void setSuite(String suite) {
		this.suite = suite;
	}
	//when called this returns the value of the card
	public String getSuite() {
		this.suite = suite;
		return suite;
	}
	
	//this sets the value of the card, the main use for this retuning the value of 11,12,13 to 10 after the name is set
	public void setValue(int value) {
		this.value = value;
	}
	//when called this returns the value of the card
	public int getValue() {
		this.value = value;
		return value;
	}
	
	/*setCardName method runs the value though a loop in order to return and store a name this method mainly helps with named 	 *cards like ace, jack, queen and king.
	 */
	public String setCardName(int value) {
		switch (value) {
			case 1:
				cardName = "ace";
				break;
			case 2:
				cardName = "two";
				break;
			case 3:
				cardName = "three";
				break;
			case 4:
				cardName = "four";
				break;
			case 5:
				cardName = "five";
				break;
			case 6:
				cardName = "six";
				break;
			case 7:
				cardName = "seven";
				break;
			case 8:
				cardName = "eight";
				break;
			case 9:
				cardName = "nine";
				break;
			case 10:
				cardName = "ten";
				break;
			case 11:
				cardName = "jack";
				this.setValue(10);
				break;
			case 12:
				cardName = "queen";
				this.setValue(10);
				break;
			case 13:
				cardName = "king";
				this.setValue(10);
				break;
		}
		return cardName;
	}
	//when called this returns the name of the card
	public String getCardName() {
		this.cardName = cardName;
		return cardName;
	}
	//this sets whether the card is in use
	public void setUsed(boolean used) {
		this.used = used;
	}
	//this retuns whether the card is in use
	public boolean getUsed() {
		this.used = used;
		return used;
	}	
}