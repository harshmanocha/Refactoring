package com.directi.training.codesmells.smelly.chess;

import com.directi.training.codesmells.smelly.Color;
import com.directi.training.codesmells.smelly.Position;
import com.directi.training.codesmells.smelly.pieces.*;

import java.util.Scanner;

public class GameEngine
{
    private static final Scanner scanner = new Scanner(System.in);
    private final ChessBoard _chessBoard;
    private Player _player1, _player2;
    private Player _currentPlayer;

    public GameEngine(Player player1, Player player2)
    {
        _chessBoard = new ChessBoard();
        _player1 = player1;
        _player2 = player2;
        resetBoard();
    }

    public void initGame()
    {
        if (_currentPlayer == null || _player1.getColor() == Color.BLACK) {
            _currentPlayer = _player1;
            _player1.setColor(Color.WHITE);
            _player2.setColor(Color.BLACK);
        } else {
            _currentPlayer = _player2;
            _player1.setColor(Color.BLACK);
            _player2.setColor(Color.WHITE);
        }
        System.out.println("\nGame initialized");
        System.out.println("Player " + _player1.getName() + " has Color " + _player1.getColor());
        System.out.println("Player " + _player2.getName() + " has Color " + _player2.getColor());
        System.out.println("");
        resetBoard();
        System.out.println(_chessBoard);
    }

    public void startGame()
    {
        while (true) {
            System.out.println("Next move is of " + _currentPlayer.getName()
                               + " [" + _currentPlayer.getColor() + "]");
            System.out.print("Enter position (row col) of piece to move: ");
            Position from = inputPosition();
            System.out.print("Enter destination position: ");
            Position to = inputPosition();
            Move move = new Move(from, to);
            if (isValidMove(move)) {
                makeMove(move);
            } else {
                System.out.println("Invalid move!");
            }
        }
    }

    private Position inputPosition()
    {
        int row = scanner.nextInt() - 1;
        int col = scanner.nextInt() - 1;
        return new Position(row, col);
    }

    public void resetBoard()
    {
        for (int column = 0; column < 8; column++) {
            if (column == 0) {
                _chessBoard.getBoard()[7][column].setPiece(new LeftRook(Color.WHITE));
            } else if (column == 1) {
                _chessBoard.getBoard()[7][column].setPiece(new LeftKnight(Color.WHITE));
            } else if (column == 2) {
                _chessBoard.getBoard()[7][column].setPiece(new LeftBishop(Color.WHITE));
            } else if (column == 3) {
                _chessBoard.getBoard()[7][column].setPiece(new King(Color.WHITE));
            } else if (column == 4) {
                _chessBoard.getBoard()[7][column].setPiece(new Queen(Color.WHITE));
            } else if (column == 5) {
                _chessBoard.getBoard()[7][column].setPiece(new RightBishop(Color.WHITE));
            } else if (column == 6) {
                _chessBoard.getBoard()[7][column].setPiece(new RightKnight(Color.WHITE));
            } else if (column == 7) {
                _chessBoard.getBoard()[7][column].setPiece(new RightRook(Color.WHITE));
            }
        }
        for (int column = 0; column < 8; column++) {
            _chessBoard.getBoard()[6][column].setPiece(new Pawn(Color.WHITE));
        }

        for (int column = 0; column < 8; column++) {
            if (column == 0) {
                _chessBoard.getBoard()[0][column].setPiece(new LeftRook(Color.BLACK));
            } else if (column == 1) {
                _chessBoard.getBoard()[0][column].setPiece(new LeftKnight(Color.BLACK));
            } else if (column == 2) {
                _chessBoard.getBoard()[0][column].setPiece(new LeftBishop(Color.BLACK));
            } else if (column == 3) {
                _chessBoard.getBoard()[0][column].setPiece(new King(Color.BLACK));
            } else if (column == 4) {
                _chessBoard.getBoard()[0][column].setPiece(new Queen(Color.BLACK));
            } else if (column == 5) {
                _chessBoard.getBoard()[0][column].setPiece(new RightBishop(Color.BLACK));
            } else if (column == 6) {
                _chessBoard.getBoard()[0][column].setPiece(new RightKnight(Color.BLACK));
            } else if (column == 7) {
                _chessBoard.getBoard()[0][column].setPiece(new RightRook(Color.BLACK));
            }
        }
        for (int column = 0; column < 8; column++) {
            _chessBoard.getBoard()[1][column].setPiece(new Pawn(Color.BLACK));
        }

        _chessBoard._kingDead = false;
    }

    private void endGame()
    {
        System.out.println("Game Ended");
        Player winner = _currentPlayer;
        winner.increase();
        System.out.println("WINNER - " + winner + "\n\n");
    }

    private Player getOtherPlayer()
    {
        return _player1 == _currentPlayer ? _player2 : _player1;
    }

    public void makeMove(Move move)
    {
        _chessBoard.movePiece(move.getFrom().getRow(), move.getFrom().getColumn(), move.getTo().getRow(),
                move.getTo().getColumn());
        System.out.println("");
        System.out.println(_chessBoard);
        if (_chessBoard.isKingDead()) {
            endGame();
            initGame();
        } else {
            _currentPlayer = getOtherPlayer();
        }
    }

    public boolean isValidMove(Move move)
    {
        return isPlayerMovingItsOwnColoredPiece(move.getFrom())
               && _chessBoard.isValidMove(move.getFrom().getRow(), move.getFrom().getColumn(),
            move.getTo().getRow(), move.getTo().getColumn());
    }

    private boolean isPlayerMovingItsOwnColoredPiece(Position from) {
        return !_chessBoard.isEmpty(from)
               && _chessBoard.getPiece(from).getColor() == _currentPlayer.getColor();
    }

    public ChessBoard getChessBoard()
    {
        return _chessBoard;
    }
}
