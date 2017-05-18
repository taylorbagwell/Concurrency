package com.taylorb;

public class Runner {
	public static void main(String[] args) {
		int contestants;
		if (args.length > 0) {
			contestants = Integer.parseInt(args[0]);
		}

		else {
			contestants = 10;
		}

		MusicalChairGame newGame = new MusicalChairGame(contestants);
		newGame.startGame();
	}
}