import java.util.ArrayList;  
import java.util.HashMap;
import java.util.Scanner;

/**
 * B0544120.游佳容 
 * 先輸入及儲存這場遊戲設定為幾副牌
 * 再來設定Deck的3個for loop
 * 接著為card這個class(methods, 15 points)
 */
public class HW2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("input N (deck of cards):");
		String testn = sc.nextLine();

		int nDeck = Integer.parseInt(testn);
		Deck deck = new Deck(nDeck);
		deck.printDeck();

		if (isAllCardsCorrect(deck.getAllCards(), nDeck)) {
			System.out.println("Well done!");
		} else {
			System.out.println("Error, please check your sourse code");
		}
	}

	private static boolean isAllCardsCorrect(ArrayList<Card> allCards, int nDeck) {
		boolean isCorrect = true;
		HashMap<String, Integer> checkHash = new HashMap<String, Integer>();
		for (Card card : allCards) {
			int suit = card.getSuit();
			int rank = card.getRank();
			if (suit > 4 || suit < 1 || rank > 13 || rank < 1) {
				isCorrect = false;
				break;
			}
			if (checkHash.containsKey(suit + "," + rank)) {
				checkHash.put(suit + "," + rank, checkHash.get(suit + "," + rank) + 1);
			} else {
				checkHash.put(suit + "," + rank, 1);
			}

		}
		if (checkHash.keySet().size() == 52) {
			for (int value : checkHash.values()) {
				if (value != nDeck) {
					isCorrect = false;
					break;
				}
			}
		} else {
			isCorrect = false;
		}
		return isCorrect;
	}

}

/**
 * Description: 三個for loop 從第一副牌開始進入，再來為第一種花色接著從A~K
 * 結束後再回到第二種花色一樣A~K，依序下去。
 */
class Deck {
	private ArrayList<Card> cards;

	public Deck(int nDeck) {
		cards = new ArrayList<Card>();
		for (int n = 1; n <= nDeck; n++) {
			for (int s = 1; s <= 4; s++) {
				for (int r = 1; r <= 13; r++) {
					Card card = new Card(s, r);
					cards.add(card);
				}
			}
		}
	}

	public void printDeck() {
		for (int n = 0; n < cards.size(); n++) {
			cards.get(n).printCard();
		}
	}

	public ArrayList<Card> getAllCards() {
		return cards;
	}
}

/**
 * Description: 列出花色及數字陣列，並回傳輸出。
 */
class Card {
	private int suit;
	private int rank;

	public Card(int s, int r) {
		suit = s;
		rank = r;
	}

	private String[] suitname = { "Clubs", "Diamonds", "Hearts", "Spades" };
	private String[] rankname = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	public void printCard() {
		System.out.println(suitname[suit - 1] + "," + rankname[rank - 1]);
	}

	public int getSuit() {
		return suit;
	}

	public int getRank() {
		return rank;
	}
}
