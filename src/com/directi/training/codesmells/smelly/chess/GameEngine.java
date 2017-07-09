package com.directi.training.codesmells.smelly.chess;

import com.directi.training.codesmells.smelly.pieces.*;

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
        resetBoard();
        System.out.println(_chessBoard);
    }

    public void resetBoard() {
        for (int column = 0; column < 8; column++) {
            if (column == 0 || column == 7) {
                _chessBoard.getBoard()[7][column].setPiece(new Rook(Color.WHITE));
            } else if (column == 1 || column == 6) {
                _chessBoard.getBoard()[7][column].setPiece(new Knight(Color.WHITE));
            } else if (column == 2 || column == 5) {
                _chessBoard.getBoard()[7][column].setPiece(new Bishop(Color.WHITE));
            } else if (column == 3) {
                _chessBoard.getBoard()[7][column].setPiece(new King(Color.WHITE));
            } else if (column == 4) {
                _chessBoard.getBoard()[7][column].setPiece(new Queen(Color.WHITE));
            }
        }
        for (int column = 0; column < 8; column++)
            _chessBoard.getBoard()[6][column].setPiece(new Pawn(Color.WHITE));

        for (int column = 0; column < 8; column++) {
            if (column == 0 || column == 7) {
                _chessBoard.getBoard()[0][column].setPiece(new Rook(Color.BLACK));
            } else if (column == 1 || column == 6) {
                _chessBoard.getBoard()[0][column].setPiece(new Knight(Color.BLACK));
            } else if (column == 2 || column == 5) {
                _chessBoard.getBoard()[0][column].setPiece(new Bishop(Color.BLACK));
            } else if (column == 3) {
                _chessBoard.getBoard()[0][column].setPiece(new King(Color.BLACK));
            } else if (column == 4) {
                _chessBoard.getBoard()[0][column].setPiece(new Queen(Color.BLACK));
            }
        }
        for (int column = 0; column < 8; column++)
            _chessBoard.getBoard()[1][column].setPiece(new Pawn(Color.BLACK));

        _chessBoard._isKingDead = false;
    }

    private void endGame() {
        System.out.println("Game Ended");
        Player winner = _currentPlayer;
        winner.increase();
        System.out.println("WINNER - " + winner + "\n\n");
        initGame();
    }

    private Player getOtherPlayer() {
        return _player1 == _currentPlayer ? _player2 : _player1;
    }

    public boolean makeMove(Position from, Position to) {
        if (!isValidMove(from, to)) {
            System.out.println("Invalid Move");
            return false;
        }
        if (_chessBoard.movePiece(from.getRow(), from.getColumn(), to.getRow(), to.getColumn())) {
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
                && _chessBoard.isValidMove(from.getRow(), from.getColumn(), to.getRow(), to.getColumn());
    }

}
