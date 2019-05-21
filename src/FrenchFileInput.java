import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*phillip pham
 * 
 * sheetNumbers:
 * 0:phrases
 * 1:matching
 * 2:fill in the blank questions
 * 3:fill in the blank answers
 * 
 * IMMPORTANT
 * csv files must have a comma at the end of each line
 * with the exception of the last line
 * 
 */

//inputs phrases and conjugations from csv file
public class FrenchFileInput {

	private final String[] SHEET_NAMES = {"French Questions - Phrases.csv", 
			"French Questions - Matching.csv", "French Questions - Fill in the Blank Questions.csv",
	"French Questions - Fill in the Blank Answers.csv"};


	public ArrayList<Phrase> phrases = new ArrayList<Phrase>();
	
	public ArrayList<Phrase> recieveQuestions(){

		//sheet Phrases
		String[] in = new String[5];//number of answers to each phrase
//		ArrayList<Phrase> phrases = new ArrayList<Phrase>();

		try{

			Scanner input = new Scanner(new File(SHEET_NAMES[0])).useDelimiter(",");

			input.nextLine();//jump to next line and skip headers

			while(input.hasNextLine()){

				for(int i=0;i<in.length;i++)//answers
					in[i] = input.next();

				phrases.add(new Phrase(in));//add new phrase

			}//while hasNextLine

//			input.close();

		}catch(FileNotFoundException error){

			System.err.println("File not found - check the file name");

		}

//		for(int i=0;i<phrases.size();i++)
//			System.out.println(phrases.get(i).toString());

		return phrases;//return arraylist of phrases

	}

	public String[] recieveMatches(){

		ArrayList<String> match = new ArrayList<String>();


		try{

			Scanner input = new Scanner(new File(SHEET_NAMES[1])).useDelimiter(",");

			input.nextLine();//jump to next line and skip headers

			while(input.hasNextLine()){

				match.add(input.next());//add strings

			}//while hasNextLine

			input.close();

		}catch(FileNotFoundException error){

			System.err.println("File not found - check the file name");

		}

		//convert match to array
		String[] arr = new String[match.size()];
		for(int i=0;i<arr.length;i++)
			arr[i] = match.get(i);

		return arr;

	}

	public String[] receiveFillQuestions(){

		ArrayList<String> question = new ArrayList<String>();

		int count = 0;

		try {

			Scanner input = new Scanner(new File(SHEET_NAMES[2])).useDelimiter(",");

			input.nextLine();//jump to next line and skip headers

			while(input.hasNextLine()){

				question.add(input.next());

			}//while hasNextLine

			input.close();

		}catch(FileNotFoundException error){

			System.err.println("File not found - check the file name");

		}

		//convert match to array
		String[] arr = new String[question.size()];
		for(int i=0;i<arr.length;i++)
			arr[i] = question.get(i);

		return arr;

	}

	public String[] receiveFillBlanks() {

		String[] answer = new String[5];

		int count = 0;

		try {

			Scanner input = new Scanner(new File(SHEET_NAMES[3])).useDelimiter(",");

			input.nextLine();//jump to next line and skip headers

			while(input.hasNextLine()){

				answer[count] = input.next();
				count++;

			}

			input.close();

		} catch(FileNotFoundException error){

			System.err.println("File not found - check the file name");

		}

		return answer;

	}

}//end class
