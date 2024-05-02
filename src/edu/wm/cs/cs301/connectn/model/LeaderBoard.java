/**
 * Manages serialization and leaderboard updating logic. Follows the singleton design pattern.
 */
package edu.wm.cs.cs301.connectn.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.File;

public class LeaderBoard implements Serializable {
	private static final long serialVersionUID = 3994190589222848591L;
	static final String filePath = "resources/leaderboard.ser";
	private static LeaderBoard instance;
	private int[] highScores;
	private String[] names;
	
	private LeaderBoard() {
		highScores  = new int[] {0,0,0};
		names = new String[] {"", "", ""};
	}
	
	public static LeaderBoard getLeaderBoard() {
		if (instance == null) {
			FileInputStream fileIn;
			
//			Check if file exists
//			If exists, load
//			Else return new class
			
			try {
				fileIn = new FileInputStream(LeaderBoard.filePath);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				
				LeaderBoard.instance = (LeaderBoard) objectIn.readObject();
				
				objectIn.close();
				fileIn.close();
				
			} catch (FileNotFoundException e) {
				LeaderBoard.instance = new LeaderBoard();
			}
			catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
		}
		return instance;
	}
	
	private void saveBoard() {
		try {
			FileOutputStream fileOut = new FileOutputStream(LeaderBoard.filePath);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(LeaderBoard.getLeaderBoard());
			
			objectOut.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			try {
				File file = new File(LeaderBoard.filePath);
				new File("resources").mkdirs();
				file.createNewFile();
				this.saveBoard();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateScore(GameMode gameMode, int candidateScore, String candidateName) {
		int candidateIndex = 3;
		switch (gameMode) {
		case SMALL:
			candidateIndex = 0;
			break;
		case MEDIUM:
			candidateIndex = 1;
			break;
		case LARGE:
			candidateIndex = 2;
			break;
		}
		
		if(candidateScore < this.highScores[candidateIndex] | this.highScores[candidateIndex] == 0) {
			int previousScore = this.highScores[candidateIndex];
			System.out.println("New High Score!");
			if (previousScore != 0) {
				System.out.println("Beat previous score of " + previousScore);
			}
			this.highScores[candidateIndex] = candidateScore;
			this.names[candidateIndex] = candidateName;
		}
		
		this.saveBoard();
	}
	
	public int[] getHighScore() {
		return highScores;
	}
	
	public String[] getNames() {
		return names;
	}
}
