import java.util.Random;
import java.util.Scanner;


public class DiceRoll {

	public static void main(String[] args) 

		
		    {
		        Random dice = new Random();
		        Scanner input = new Scanner(System.in);
		        int numberOfFaces;
		        int randomValue;
		        System.out.println("Enter number of faces: ");
		        numberOfFaces = input.nextInt();
		        randomValue = dice.nextInt(numberOfFaces) + 1;
		        String s=String.valueOf(randomValue);
		        System.out.println("The random number after rolling the dice is " + s + ".");
		    }
		}
		
	




