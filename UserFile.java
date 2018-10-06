package userFile;

import java.util.*;
import java.io.*;

public class UserFile {
	
	public static String Name = "";
	public static int GamesCount = 0;
	public static double TotalAvgTime = 0.000;
	
	public static double GetNewAverageTime(double avgTime) {
		
		// m = [(n*tm) + t] / (n+1)
		double newAvgTime = (double)((double)(GamesCount * TotalAvgTime) + avgTime)/(GamesCount+1);
		newAvgTime = (double)Math.round(newAvgTime * 1000d) / 1000d;
		return newAvgTime;
	}
	
	public static void ReadFromFile (Scanner sc) {
		
		String input = "";
		String name = null;
		int gamescount = 0;
		double avgtime = 0.000;
		
		input = sc.nextLine();
		name =  (String)input;

		input = sc.nextLine();
		gamescount = Integer.parseInt(input);
			
		input = sc.nextLine();
		avgtime = Double.parseDouble(input);
				
		// my pathfile: D:\PROJECTS\Java\QuickMaths\ user-dana.txt
		Name = name;
		GamesCount = gamescount;
		TotalAvgTime = avgtime;
	}
	
	public static void WriteToFile () throws IOException {
		
	    BufferedWriter writer = null;
	    File user_file = new File("D:/PROJECTS/Java/QuickMaths/user-file.txt");
	    writer = new BufferedWriter(new FileWriter(user_file));
	    
	    writer.write(Name);
	    writer.newLine();
	    String GamesCountToString = Integer.toString(GamesCount);
	    writer.write(GamesCountToString);
	    writer.newLine();
	    String AvgTimeToString = Double.toString(TotalAvgTime);
	    writer.write(AvgTimeToString);
	    writer.close();        
	}
}
