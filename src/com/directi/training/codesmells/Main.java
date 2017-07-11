package com.directi.training.codesmells;

import com.directi.training.codesmells.smelly.chess.GameEngine;
import com.directi.training.codesmells.smelly.chess.Player;

import java.util.Scanner;

public class Main
{
    private static final Scanner scanner = new Scanner(System.in);
    private static GameEngine gameEngine;

    public static void main(String[] args)
    {
        initGameEngine();
        gameEngine.initGame();
        gameEngine.startGame();
    }

    private static void initGameEngine()
    {
        System.out.print("Enter Player 1 Name: ");
        Player player1 = new Player(scanner.nextLine());
        System.out.print("Enter Player 2 Name: ");
        Player player2 = new Player(scanner.nextLine());
        gameEngine = new GameEngine(player1, player2);
    }
}
