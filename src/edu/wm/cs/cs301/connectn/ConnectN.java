package edu.wm.cs.cs301.connectn;

import edu.wm.cs.cs301.connectn.model.ConnectNGame;
import edu.wm.cs.cs301.connectn.model.GameMode;
import edu.wm.cs.cs301.connectn.view.ConnectNFrame;

public class ConnectN {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		playGame(GameMode.MEDIUM);
	}

	public static void playGame(GameMode gameMode) {
		new ConnectNFrame(new ConnectNGame(gameMode));
	}
}
