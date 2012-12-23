public class Player {
	//sets up the varables used in the constructor
	String name; 
	int handValue;
	//following code contructs a player object with the properties name and handValue.
	public Player(String name, int handValue) {
		this.handValue = handValue;
		this.name = name;
	}
	//this sets the value of the hand
	public void setHandValue(int handValue) {
		this.handValue = handValue;
	}
	//when called this returns the value of the hand
	public int getHandValue() {
		return handValue;
	}
	//this sets the name of the player
	public void setName(String name) {
		this.name = name;
	}
	//when called this returns the name of the player
	public String getName() {
		this.name = name;
		return name;
	}	
}