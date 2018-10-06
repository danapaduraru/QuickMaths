package quickMaths;

import exercises.Exercises;
import userFile.UserFile;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;


public class QuickMaths {

	static boolean wants_to_play = false;
	static boolean two_options = false;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		ClearConsole();
		System.out.println(" QUICK MATHS\n");
	    System.out.println(" Welcome! This game will test how fast you add, substract and multiply numbers.");
	    System.out.println(" You will practice with random numbers and we'll tell you the time in which you solved all the exercises.\n");

		Scanner sc = new Scanner(System.in);
		
		File file = new File("D:\\PROJECTS\\Java\\QuickMaths\\user-file.txt");						
		Scanner sc2 = new Scanner(file);
		UserFile.ReadFromFile(sc2);
		sc2.close();
		
		System.out.println();
		System.out.println(" Welcome, "+ UserFile.Name + "!");
		System.out.println(" GamesCount: " + UserFile.GamesCount);
		System.out.println(" AvgTime: " + UserFile.TotalAvgTime);
	    System.out.println();
	    System.out.println(" 1 - Play");
	    System.out.println(" 2 - Change nickname");
	    System.out.println(" 3 - Reset score");
	    System.out.println(" 4 - Exit game\n");
	    
	    int userOption;
	    String input = "";
	    
	    while(true) 
	    {
			input = sc.nextLine();
			if(isOption(input)==true) {
				userOption=Integer.parseInt(input);
				break;
			}
	    }
	    
	    switch(userOption)
	    {
	    case 1:
	    	wants_to_play = true;
			// start game directly
			GameRounds(sc);
			break;
	    case 2:
	    	// CHANGE NAME OPTION
	    	System.out.println(" Enter your new nickname:");
	    	//sc.nextLine();
	    	String new_name = sc.nextLine();
	    	UserFile.Name = new_name;			
	    	UserFile.WriteToFile();
	    	System.out.println(" Changes saved!");
	    	StartGame(sc);
	    	break;
	    case 3:
	    	// RESET SCORE OPTION
	    	UserFile.GamesCount = 0;
	    	UserFile.TotalAvgTime = 0.000;
	    	UserFile.WriteToFile();
	    	System.out.println();
	    	System.out.println(" Changes saved!");
	    	StartGame(sc);
	    	break;
	    case 4:
	    	System.out.println(" You exited the game.\n");
	    	break;
	    default: break;
	    }
		sc.close();
	}
	
	public static void StartGame(Scanner sc) throws IOException, InterruptedException {
		
		// Start game after choosing one of the extra options(change name/reset score)
	    two_options = true;
		System.out.println(" 1 - Start!");
		System.out.println(" 2 - Exit");
		    
		int userOption;
		String input = "";
		while(true) {
			input = sc.nextLine();
			if(isOption(input)==true) {
				userOption=Integer.parseInt(input);
				break;
			}
		}
	    
		if(userOption==1)
	    	GameRounds(sc);
	    else
	    	System.out.println(" You exited the game.\n");
	    two_options = false;
	}
	
	public static void GameRounds(Scanner sc) throws IOException, InterruptedException {
	
	    System.out.println(" First round is: ADDITION");
	    Sleep();
		int round = 1;
		
		// ROUND 1
		Go();
    	Exercises.CreateExerciseSeries(sc, round);
    	Sleep();
		
		// ROUND 2
		System.out.println(" Next round: SUBTRACTION");
		Sleep(); Sleep();
		Go();
		round++;
    	Exercises.CreateExerciseSeries(sc, round);
    	
		// ROUND 3
		System.out.println(" Next round: MULTIPLICATION");
		Sleep(); Sleep();
		Go();
    	round++;
    	Exercises.CreateExerciseSeries(sc, round);
    	
    	Finish(sc);
	}
	
	public static void Finish(Scanner sc) throws IOException, InterruptedException {

		UserFile.GamesCount++;
    	double avgTime = Exercises.GetAverageTime();
    	
		System.out.println();
		System.out.println(" Congratulations! You finished all the exercises.\n");
    	System.out.println(" Your total time was: " + avgTime + " seconds.\n");
		System.out.println(" Games: " + UserFile.GamesCount);
    	System.out.println(" Past Average Time: " + UserFile.TotalAvgTime + " seconds");
    	
    	UserFile.TotalAvgTime = UserFile.GetNewAverageTime(avgTime);
    	
		System.out.println(" New  Average Time: " + UserFile.TotalAvgTime + " seconds\n");
		
		UserFile.WriteToFile();
    	PlayAgain(sc);
	}
	
	public static void PlayAgain(Scanner sc) throws IOException, InterruptedException {
		
		two_options = true;
		
		System.out.println(" Want to play again?");
		System.out.println(" 1 - yasss!!");
		System.out.println(" 2 - exit :(");
		
		int userOption;
	    String input = "";
	    while(true) {
			input = sc.nextLine();
			if(isOption(input)==true) {
				userOption=Integer.parseInt(input);
				break;
			}
	    }
		if(userOption==1)
		{
			Exercises.avgTime = 0;
			GameRounds(sc);
		}
		else
		{
			System.out.println(" You exited the game.\n");
		}
		
		two_options = false;
	}
	
	public static boolean isOption(String s) {
		
		int option=0;
		try {
			 option = Integer.parseInt(s);
			 if(two_options == false)
			 {	 if(option<1 || option>4)
			 	 {
			 		 System.out.println(" Please choose between the given options.");
			 		 return false;
			 	 }
			 }
			 else
			 {
				 if(option!=1 && option!=2)
				 {
					 System.out.println(" You need to choose between 1 or 2.");
					 return false;
				 }
			 }
		}catch(NumberFormatException e) {
			System.out.println(" Please choose between the given options.");
		    return false;
		}
		return true;
	}
	
	public static void Go() throws InterruptedException, IOException {
		
		ClearConsole();
	    System.out.println(3);
	    Sleep();
	    
	    ClearConsole();
	    System.out.println(2);
	    Sleep();
	    
	    ClearConsole();
	    System.out.println(1);
	    Sleep();
	    
	    ClearConsole();
	    System.out.println("GO!");
	    Sleep();
	}
	
	public static void Sleep() throws InterruptedException {
		
		// sleeps one second
		TimeUnit.SECONDS.sleep(1);
	}
	
	public static void ClearConsole() throws InterruptedException, IOException {
		
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
}