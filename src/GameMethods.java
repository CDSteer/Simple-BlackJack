import java.util.Random;
import java.util.Scanner;


public class GameMethods{
	//dinfine unitlites from the impoted java methods used thoughout the following code to get input and random numbers
	Random randNum = new Random();
	Scanner in = new Scanner(System.in);
	
	/*creates objects from classes in other files, and sets up an array for the cards with 53 memory as to avoid confuion element 0 is 	 *not used
	 */
	private Player user;
	private Player dealer;
	private Card card[] = new Card[53];

	//makeDeck creates an array of playing cards using a for loop for each suite
	public void makeDeck(){
		for(int i = 1; i <= 13; i++){
			card[i] = new Card("Hearts", i, false);
		}
		for(int i = 14, n = 1; i <= 26 && n <= 13; i++, n++){
			card[i] = new Card("Diamonds", n, false);
		}
		for(int i = 27, n = 1; i <= 39 && n <= 13; i++, n++){
			card[i] = new Card("Spades", n, false);
		}
		for(int i = 40, n = 1; i <= 52 && n <= 13; i++, n++){
			card[i] = new Card("Clubs", n, false);
		}
	}
		
	//this method checks if the user is ready to play and also acts as a way of teaching the way input in communicated
	public void startGame(){
		int input = 0; 
		input = this.inputAsk("welcomeQ");		
		switch (input) {
			case 1:
				//when 1 in chosen program begins a game of blackjack
				this.startBlackjack();
			case 2:
				//if 2 is chosen then the prgram ends with a good bye message
				System.out.println("Good bye :( Come play anther time.");
				System.exit(0);
			default:
				//the default message prints if the 1 or 2 in entered and the user is promted they did not follow instuctions
				System.out.println("Good bye :( Come play when you can follow intructions.");
				System.exit(0);
		}
	}
	
	//startBlackjack sets up the game contructing objects for the user, dealer and cards. 
	public void startBlackjack(){
		System.out.println("Lets begin!");
		//user is asked to name their player
		String name = this.textInputAsk();
		/*objects are created with the properties for each the user and the dealer(computer), then the deck of cards in 		 *generated in to an array of objects*/
		user = new Player(name, 0);
		dealer = new Player("Dealer", 0);
		this.makeDeck();
		//user take their turn
		this.userTurn();
	}
	//restartBlackjack resets objects properties where needed ready for to night
	public void restartBlackjack(){
		System.out.println("Lets go again!");
		user.setHandValue(0);
		dealer.setHandValue(0);
		//remake the deck of card so trues are set to false
		this.makeDeck();
		//user take their turn
		this.userTurn();
	}
	
	public void userTurn(){
		//First card is delt with out asking
		int newCardValue = this.newCard();
		user.setHandValue(user.getHandValue() + newCardValue);
		System.out.println(user.getName() + "'s hand value: " + user.getHandValue());
		//the while loop uses the contCheck method so make sure the hand value is not greater than 21
		while (this.contCheck(user.getHandValue()) == true) {
			//and while this is true the user can continue their turn
			int input = 0;
			//they are then asked if they want to twist(be delt a new card) or stick(keep their hand and let the dealer go)
			input = this.inputAsk("gameQ");
			switch (input) {
				case 1:
					//input "1" will deal the user a new card
					newCardValue = this.newCard();
					user.setHandValue(user.getHandValue() + newCardValue);
					System.out.println(user.getName() + "'s hand value: " + user.getHandValue());
					break;
				case 2:
					//input "2" ends the player turn and the dealer take theirs
					System.out.println("Dealers Turn.");
					//dealer takes their turn
					this.dealerTurn();
					break;
				default:
					System.out.println("Good bye :( Come play when you can follow intruction.");
					System.exit(0);
			}
		}
	}
	
	public void endGame(){
		//win check prformed
		this.winCheck();
		int input = this.inputAsk("againQ");
		switch (input) {
			case 1:
				//when 1 in chosen program begins a new game of blackjack
				this.restartBlackjack();
				break;
			case 2:
				//if 2 is chosen then the prgram ends with a good bye message
				System.out.println("Good bye :( Come play anther time.");
				System.exit(0);
		}
	}
	
	public int newCard(){
		//this gets a random value within the range of the array of cards
		int arrayValue = randNum.nextInt(53-1)+1;
		//this checks if the card is it use before its added to the players hand
		while (card[arrayValue].used == true) {
			arrayValue = randNum.nextInt(53-1)+1;
		}
		//get the properties of the card to display to the user
		String suite = card[arrayValue].suite;
		int value = card[arrayValue].value;
		String cardName = card[arrayValue].cardName;
		//set card to used
		card[arrayValue].setUsed(true);
		System.out.println("A " + cardName + " of " + suite + " was dealt");
		//return the value to add to the value of the players hand
		return value;
	}
	//this method is used make sure the users hand in under 21 in order to continue asking if they want to twist or stick
	public boolean contCheck(int plrHand){
		boolean check = false;
		if (plrHand <= 21) {
			return check = true;
		} else if (plrHand > 21) {
			//if the hand is greater than 21 the method for the dealers turn is run
			System.out.println("Bust!!! Computer's Turn");
			this.dealerTurn();
	 	}
		return check = false;
	}
	//this method runs input questions that retun intergers
	public int inputAsk(String type){
		int input = 0;
		//this is the welcome message for asking to start the game
		if (type.equals("welcomeQ")) {
			System.out.println("Hello and Welcome to Blackjack! \nReady to play enter: \n1 for yes \n2 for no");
			input = in.nextInt();
		//this asks if the player wants to stick or twist
		}else if (type.equals("gameQ")) {
			System.out.println("Enter: \n1 for Twist  \n2 to Stick");
			input = in.nextInt();
		//this asks if the player wants to play again
		}else if (type.equals("againQ")) {
			System.out.println("Play Again: \n1 for Yes  \n2 for to No");
			input = in.nextInt();
		}
		return input;
	}
	//this method runs input questions that retun strings
	public String textInputAsk(){
		//this runs to get any input and interpret it as a String for the players name
		System.out.println("Player Name:");
		String name = in.next();
		return name;
	}
	
	public void dealerTurn(){
		// this dealer will contunue to play untill its hand is greater than 15
		while (dealer.getHandValue() < 15) {
			int newCardValue = this.newCard();
			dealer.setHandValue(dealer.getHandValue() + newCardValue);
			System.out.println(dealer.getName() + "'s hand value: " + dealer.getHandValue());
		}
		//win check and play again it proformed
		this.endGame();
	}
	//winCheck uses an if statment to determind the winner from the two values of each hand
	public void winCheck(){
		//if both hands are greater than 21 then there are no winners
		if (user.getHandValue() > 21 && dealer.getHandValue() > 21) {
			System.out.println("No Winners :(");
		//if the first if is untrue then it checks if only the users hand is greater than 21 as then the dealer will win
		}else if(user.getHandValue() > 21) {
			System.out.println(dealer.getName()+" Wins!");
		//if the first two ifs are untrue then it checks if only the dealers hand is greater than 21 as then the user will win
		}else if (dealer.getHandValue() > 21) {
			System.out.println(user.getName()+" Wins!");
		//then the if the hands are equal its a tie
		}else if (dealer.getHandValue() == user.getHandValue()) {
			System.out.println("Its a Tie!");
		//if they are both under 21 then it checks for the largest hand as the program will know both are under 21 
		}else if (dealer.getHandValue() > user.getHandValue()){
			System.out.println(dealer.getName()+" Wins!");
		}else if (dealer.getHandValue() < user.getHandValue()){
			System.out.println(user.getName()+" Wins!");
		}
	}
}