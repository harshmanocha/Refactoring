package tests.com.directi.training.codesmells.chess.chess;

import com.directi.training.codesmells.smelly.Position;
import com.directi.training.codesmells.smelly.chess.ChessBoard;
import com.directi.training.codesmells.smelly.chess.GameEngine;
import com.directi.training.codesmells.smelly.chess.Move;
import com.directi.training.codesmells.smelly.chess.Player;
import com.directi.training.codesmells.smelly.pieces.Pawn;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ChessGameTest
{

    private GameEngine _gameEngine;

    @Before
    public void initGame()
    {
        Player player1 = new Player("A");
        Player player2 = new Player("B");
        _gameEngine = new GameEngine(player1, player2);
        _gameEngine.initGame();
    }

    @Test
    public void testNoMovementOfPieceForFirstPlayer()
    {
        ChessBoard initialChessBoard = _gameEngine.getChessBoard();
        Position fromPosition = new Position(1, 0);
        Position toPosition = new Position(1, 0);
        _gameEngine.makeMove(new Move(fromPosition, toPosition));
        assertEquals(_gameEngine.getChessBoard(), initialChessBoard);
    }

    @Test
    public void testNoMovementOfPieceForSecondPlayer()
    {
        ChessBoard initialChessBoard = _gameEngine.getChessBoard();
        Position fromPosition = new Position(7, 0);
        Position toPosition = new Position(7, 0);
        _gameEngine.makeMove(new Move(fromPosition, toPosition));
        assertEquals(_gameEngine.getChessBoard(), initialChessBoard);
    }

    @Test
    public void testValidMoveOfPawnForFirstPlayer()
    {
        Position fromPosition = new Position(1, 0);
        Position toPosition = new Position(2, 0);
        _gameEngine.makeMove(new Move(fromPosition, toPosition));
        ChessBoard chessBoard = _gameEngine.getChessBoard();
        assertTrue(chessBoard.getPiece(new Position(1, 0)) == null);
        assertTrue(chessBoard.getPiece(new Position(2, 0)) instanceof Pawn);
    }

    @Test
    public void testValidMoveOfPawnForSecondPlayer()
    {
        Position fromPosition = new Position(6, 0);
        Position toPosition = new Position(5, 0);
        _gameEngine.makeMove(new Move(fromPosition, toPosition));
        ChessBoard chessBoard = _gameEngine.getChessBoard();
        assertTrue(chessBoard.getPiece(new Position(6, 0)) == null);
        assertTrue(chessBoard.getPiece(new Position(5, 0)) instanceof Pawn);
    }

    @Test
    public void testInvalidMoveOfPieceForFirstPlayer()
    {
        ChessBoard initialChessBoard = _gameEngine.getChessBoard();
        Position fromPosition = new Position(1, 0);
        Position toPosition = new Position(2, 1);
        _gameEngine.makeMove(new Move(fromPosition, toPosition));
        assertEquals(_gameEngine.getChessBoard(), initialChessBoard);
    }

    @Test
    public void testInvalidMoveOfPieceForSecondPlayer()
    {
        ChessBoard initialChessBoard = _gameEngine.getChessBoard();
        Position fromPosition = new Position(6, 0);
        Position toPosition = new Position(5, 1);
        _gameEngine.makeMove(new Move(fromPosition, toPosition));
        assertEquals(_gameEngine.getChessBoard(), initialChessBoard);
    }

    @Test
    public void testMovePieceToNonEmptyPlaceForFirstPlayer()
    {
        ChessBoard initialChessBoard = _gameEngine.getChessBoard();
        Position fromPosition = new Position(1, 0);
        Position toPosition = new Position(1, 1);
        _gameEngine.makeMove(new Move(fromPosition, toPosition));
        assertEquals(_gameEngine.getChessBoard(), initialChessBoard);
    }

    @Test
    public void testMovePieceToNonEmptyPlaceForSecondPlayer()
    {
        ChessBoard initialChessBoard = _gameEngine.getChessBoard();
        Position fromPosition = new Position(5, 0);
        Position toPosition = new Position(5, 1);
        _gameEngine.makeMove(new Move(fromPosition, toPosition));
        assertEquals(_gameEngine.getChessBoard(), initialChessBoard);
    }
}
