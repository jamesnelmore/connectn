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
		playGame(GameMode.MEDIUM).showInstructions();
	}

	public static ConnectNFrame playGame(GameMode gameMode) {
		HumanPlayer humanPlayer = new HumanPlayer('x');
		ComputerPlayer computerPlayer = new ComputerPlayer('o');

		return new ConnectNFrame(new ConnectNGame(humanPlayer, computerPlayer, gameMode));
	}
}
