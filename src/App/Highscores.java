package App;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Highscores {	
	private Scanner s = null;
	private String scores="";
	private BufferedWriter bw = null;
	
	Highscores(String name,int score){
		String oldFileName = "highscores.txt";
		String tmpFileName = "highscores_temp.txt";
		try {
			s = new Scanner(new BufferedReader(new FileReader("highscores.txt")));
			s.useDelimiter(";");
			bw = new BufferedWriter(new FileWriter(tmpFileName));
			String line_before = null;
			do{
				String line;
				String name_temp = s.next();
				int score_temp = s.nextInt();
				// reorganize highscore
				if(line_before != null){
					line = line_before;
					line_before = name_temp + ";"+ score_temp + ";";
				}else{
					if(score > score_temp){
						line = name + ";"+ score + ";";
						line_before = name_temp + ";"+ score_temp + ";";
					}else{
						line = name_temp + ";"+ score_temp + ";";
					}
				}				
				bw.write(line);
			}while(s.hasNext());			
		} catch (Exception e) {			
			e.printStackTrace();
			return;
		}finally {	          
          try {
             if(bw != null)
                bw.close();
          } catch (IOException e) {
             //
          }
       }
		s.close();
		// Once everything is complete, delete old file..
		File oldFile = new File(oldFileName);
		//System.out.println(oldFile.getAbsolutePath());
		oldFile.delete();
		
		// And rename tmp file's name to old file name
		File newFile = new File(tmpFileName);
		//System.out.println(newFile.getAbsolutePath());
		newFile.renameTo(oldFile);
	}
	public String allScores(){
		try {
			s = new Scanner(new BufferedReader(new FileReader("highscores.txt")));
			s.useDelimiter(";");
			do{
				scores += "Nome: "+s.next()+" ";
				scores += "Score: "+s.next()+"\n";
			}while(s.hasNext());			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		return scores;
	}
}
