package edu.wm.cs.cs301.connectn;

import edu.wm.cs.cs301.connectn.model.ConnectNGame;
import edu.wm.cs.cs301.connectn.model.GameMode;
import edu.wm.cs.cs301.connectn.model.players.ComputerPlayer;
import edu.wm.cs.cs301.connectn.model.players.HumanPlayer;
import edu.wm.cs.cs301.connectn.model.players.Player;
import edu.wm.cs.cs301.connectn.view.ConnectNFrame;

public class ConnectN {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		playGame(GameMode.MEDIUM);
	}

	public static void playGame(GameMode gameMode) {
		Player humanPlayer = new HumanPlayer('x');
		Player computerPlayer = new ComputerPlayer('o');

		new ConnectNFrame(new ConnectNGame(humanPlayer, computerPlayer, gameMode));
	}
}
