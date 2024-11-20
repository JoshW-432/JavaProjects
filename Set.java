import java.util.ArrayList;

public class Set {
	//class-wide variables
	private ArrayList<String> englishWords;
	private ArrayList<String> spanishWords;
	
	//constructor
	public Set(ArrayList<String> englishWords, ArrayList<String> spanishWords) {
		this.englishWords = englishWords;
		this.spanishWords = spanishWords;
	}
	
	// gets the word in english (EN)
	public String getEN(int index) {
		return spanishWords.get(index);
	}
	
	// gets the word in spanish (ES)
	public String getES(int index) {
		return englishWords.get(index);
	}
	
	// gets the "card" (EN & ES word)
	public String getCard(int index) {
		return englishWords.get(index) + " " + spanishWords.get(index);
	}
	
	public int length() {
		return englishWords.size();
	}
}
