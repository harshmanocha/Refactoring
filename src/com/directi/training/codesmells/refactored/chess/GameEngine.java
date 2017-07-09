package com.directi.training.codesmells.refactored.chess;

public class GameEngine {
    private final ChessBoard _chessBoard;
    private Player _player1, _player2;
    private Player _currentPlayer;

    public GameEngine(Player player1, Player player2) {
        _chessBoard = new ChessBoard();
        _player1 = player1;
        _player2 = player2;
    }

    public void initGame() {
        if (_currentPlayer == null || _player1.getCurrentColor() == Color.BLACK) {
            _player1.setCurrentColor(Color.WHITE);
            _player2.setCurrentColor(Color.BLACK);
        } else {
            _player1.setCurrentColor(Color.BLACK);
            _player2.setCurrentColor(Color.WHITE);
        }
        System.out.println("Game initialized");
        System.out.println("Player " + _player1.getName() + " has Color " + _player1.getCurrentColor());
        System.out.println("Player " + _player2.getName() + " has Color " + _player2.getCurrentColor());
        System.out.println("");
        _chessBoard.resetBoard(); //Fixes Feature Envy Code Smell
        System.out.println();
    }

    private void endGame() {
        System.out.println("Game Ended");
        Player winner = _currentPlayer;
        winner.incrementGamesWon();
        System.out.println("WINNER - " + winner + "\n\n");
        initGame();
    }

    private Player getOtherPlayer() {
        return _player1 == _currentPlayer ? _player2 : _player1;
    }

    //Fixed Lazy-class Code Smell by removing Move class
    public boolean makeMove(Position from, Position to) {
        if (!isValidMove(from, to)) {
            System.out.println("Invalid Move");
            return false;
        }
        if (_chessBoard.movePiece(from, to)) {
            System.out.println("Piece moved for Player : " + _currentPlayer);
            System.out.println(_chessBoard);
            if (_chessBoard.isKingDead()) {
                endGame();
                return true;
            }
            _currentPlayer = getOtherPlayer();
            return true;
        }
        return false;
    }

    private boolean isValidMove(Position from, Position to) {
        return !(!_chessBoard.isEmpty(from) && _chessBoard.getPiece(from).getColor() != _currentPlayer.getCurrentColor())
                && _chessBoard.isValidMove(from, to);
    }

}
