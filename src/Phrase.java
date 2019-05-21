import java.util.Arrays;


public class Phrase {

	private String phrase;
	private String[] ansPhr = new String[3];
	private int diffPh;
	//[0] = correct, [1,2] = incorrect
	
	//creating new phrase
	public Phrase(String []an){
		
//		System.out.println(Arrays.toString(an));
		
		phrase = an[0];
		
		for(int i=0;i<ansPhr.length;i++)
			ansPhr[i]=an[i+1];
		
		if(an[4] == "Easy")
			diffPh = 0;
		else if(an[4] == "Medium")
			diffPh = 1;
		else if(an[4] == "Hard")
			diffPh = 2;
		
//		System.out.println(this);
	}

	//getters and setters
	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	
	public String[] getAnsPhr() {
		return ansPhr;
	}

	public void setAnsPhr(String[] ansPhr) {
		this.ansPhr = ansPhr;
	}

	public int getDiffPh() {
		return diffPh;
	}

	public void setDiffPh(int diffPh) {
		this.diffPh = diffPh;
	}
	//end getters and setters

	@Override
	public String toString() {
		return "Phrase [phrase=" + phrase + ", ansPhr=" + Arrays.toString(ansPhr) + ", diffPh=" + diffPh + "]";
	}
	

	
	
}
