package com.wyldersong.saltengine;

import com.wyldersong.saltengine.examples.examplegame.ExampleGame;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println("Starting example game...");

		ExampleGame game = new ExampleGame();

		game.run();
	}
}