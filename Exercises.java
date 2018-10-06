package exercises;

import quickMaths.QuickMaths;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Exercises {
	
	public static double avgTime = 0.000;
	
	public static double GetAverageTime() {
		
		// saves only the first 3 decimals
		avgTime = (double)Math.round(avgTime * 1000d) / 1000d;
		return avgTime;
	}
	
	public static int GetExerciseResult(int round) {
		
		Random rand = new Random();
		int first_nr =  rand.nextInt(1000) + 1;
		int second_nr = rand.nextInt(1000) + 1;
		int result = first_nr + second_nr;
		
		switch(round) {
		case 1: 
			// ADDITION
			System.out.println(first_nr + " + " + second_nr + " = ?");
			return result;
		case 2: 
			// SUBTRACTION
			if(first_nr < second_nr)
			{
				// swap
				int tmp = first_nr;
				first_nr = second_nr;
				second_nr = tmp;
			}
			result = first_nr - second_nr;
			System.out.println(first_nr + " - " + second_nr + " = ?");
			return result;
		case 3: 
			// MULTIPLICATION
			// make numbers smaller
			first_nr =  rand.nextInt(100) + 1;
			second_nr = rand.nextInt(100) + 1;
			result = first_nr * second_nr;
			System.out.println(first_nr + " * " + second_nr + " = ?");
			return result;
		default: 
			return 0;
		}
	}
	
	public static void CreateExerciseSeries(Scanner sc, int current_round) throws InterruptedException, IOException {
		
		QuickMaths.ClearConsole();
		
		// Number of exercises for each round
	    int exercise_count = 1;
	    int result = GetExerciseResult(current_round);
		final long startTime = System.currentTimeMillis();
		int userResult;
		String input = "";
		
	    while(exercise_count > 0)
		{
		    while(true) {
				input = sc.nextLine();
				if(isNumber(input)==true) 
				{
					userResult=Integer.parseInt(input);
					if(userResult!=result)
						System.out.println(" Try again!");
					else
						break;
				}
		    }
			if(userResult == result) {
				exercise_count--;
			    System.out.println();
				if(exercise_count!=0) 
				{
					result = GetExerciseResult(current_round);
				}
			}
		}
	    
	    final long endTime = System.currentTimeMillis();
	    double exerciseTime = (endTime - startTime)/1000.0;
	    avgTime += exerciseTime;

	    System.out.println(" Round is over. Your time is " + exerciseTime + " seconds. Congrats!");
	}
	
	public static boolean isNumber(String s) {
		try {
			 if(s.equals("") || s.equals(null)) {	
				 System.out.println(" It has to be a number!");
				 return false;
			 }
			 Integer.parseInt(s);
		}catch(NumberFormatException e) {
			System.out.println(" It has to be a number!");
		    return false;
		}
		return true;
	}
}
