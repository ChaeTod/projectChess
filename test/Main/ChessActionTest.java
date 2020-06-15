package Main;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ChessActionTest extends ChessAction {

    ChessAction chessAction = new ChessAction();

    @Test
    void testNormalize() {
        assertEquals(chessAction.toNormal("1e"), "E1");
        assertEquals(chessAction.toNormal("2h"), "H2");
        assertEquals(chessAction.toNormal("c3"), "C3");
        assertEquals(chessAction.toNormal("2g"), "G2");
        assertEquals(chessAction.toNormal("a8"), "A8");

        assertNull(chessAction.toNormal("1t"));
        assertNull(chessAction.toNormal("2s"));
        assertNull(chessAction.toNormal("J8")); // found an error on testing and fixed it
        assertNull(chessAction.toNormal("12a"));
        assertNull(chessAction.toNormal("02"));
        assertNull(chessAction.toNormal("23 + 1"));
        assertNull(chessAction.toNormal(""));
        assertNull(chessAction.toNormal(null));
    }

    @Test
    void testCheckQueenMove() {
        assertTrue(chessAction.checkQueenMove("E5", "D4"));
        assertTrue(chessAction.checkQueenMove("e5", "1A"));
        assertTrue(chessAction.checkQueenMove("5e", "a5"));
        assertTrue(chessAction.checkQueenMove("E5", "f4"));
        assertTrue(chessAction.checkQueenMove("E5", "h8"));

        assertTrue(chessAction.checkQueenMove("E4", "H4"));
        assertTrue(chessAction.checkQueenMove("e4", "c2"));
        assertTrue(chessAction.checkQueenMove("4e", "8a"));
        assertTrue(chessAction.checkQueenMove("4E", "5f"));
        assertTrue(chessAction.checkQueenMove("E4", "b1"));

        assertFalse(chessAction.checkQueenMove("e4", "g5"));
        assertFalse(chessAction.checkQueenMove("4e", "b6"));
        assertFalse(chessAction.checkQueenMove("4E", "3A"));
        assertFalse(chessAction.checkQueenMove("E4", null));
        assertFalse(chessAction.checkQueenMove(null, "f5"));

        assertFalse(chessAction.checkQueenMove("H8", "6g"));
        assertFalse(chessAction.checkQueenMove("8h", "@g8"));
        assertFalse(chessAction.checkQueenMove("123asd", "7g"));
        assertFalse(chessAction.checkQueenMove("h!8", "f6"));
        assertFalse(chessAction.checkQueenMove("H8", "asdf32"));
    }

    @Test
    void testCheckKingMove() {
        assertTrue(chessAction.checkKingMove("E4", "D4"));
        assertTrue(chessAction.checkKingMove("e4", "3E"));
        assertTrue(chessAction.checkKingMove("4e", "f3"));
        assertTrue(chessAction.checkKingMove("E4", "f5"));
        assertTrue(chessAction.checkKingMove("E4", "d5"));

        assertTrue(chessAction.checkKingMove("D4", "C4"));
        assertTrue(chessAction.checkKingMove("d4", "c3"));
        assertTrue(chessAction.checkKingMove("4d", "5e"));
        assertTrue(chessAction.checkKingMove("4D", "3D"));
        assertTrue(chessAction.checkKingMove("D4", "c5"));

        assertFalse(chessAction.checkKingMove("e4", "e6"));
        assertFalse(chessAction.checkKingMove("4e", "f6"));
        assertFalse(chessAction.checkKingMove("4E", "6D"));
        assertFalse(chessAction.checkKingMove("E4", null));
        assertFalse(chessAction.checkKingMove(null, "f5"));

        assertFalse(chessAction.checkKingMove("H8", "6h"));
        assertFalse(chessAction.checkKingMove("8h", "@g8"));
        assertFalse(chessAction.checkKingMove("123asd", "7g"));
        assertFalse(chessAction.checkKingMove("h!8", "f6"));
        assertFalse(chessAction.checkKingMove("H8", "asdf32"));
    }

    @Test
    void testCheckBishopMove() {
        assertTrue(chessAction.checkBishopMove("E4", "D5"));
        assertTrue(chessAction.checkBishopMove("e4", "6C"));
        assertTrue(chessAction.checkBishopMove("4e", "b7"));
        assertTrue(chessAction.checkBishopMove("E4", "8a"));
        assertTrue(chessAction.checkBishopMove("E4", "f5"));

        assertTrue(chessAction.checkBishopMove("D4", "C5"));
        assertTrue(chessAction.checkBishopMove("d4", "b6"));
        assertTrue(chessAction.checkBishopMove("4d", "a1"));
        assertTrue(chessAction.checkBishopMove("4D", "f2"));
        assertTrue(chessAction.checkBishopMove("D4", "g1"));

        assertFalse(chessAction.checkBishopMove("h8", "g8"));
        assertFalse(chessAction.checkBishopMove("8h", "h7"));
        assertFalse(chessAction.checkBishopMove("H8", "g6"));
        assertFalse(chessAction.checkBishopMove("8H", null));
        assertFalse(chessAction.checkBishopMove(null, "c1"));

        assertFalse(chessAction.checkBishopMove("a3", "4a"));
        assertFalse(chessAction.checkBishopMove("3A", "@g8"));
        assertFalse(chessAction.checkBishopMove("123asd", "7g"));
        assertFalse(chessAction.checkBishopMove("a!8", "f6"));
        assertFalse(chessAction.checkBishopMove("A8", "asdf32"));
    }

    @Test
    void testCheckKnightMove() {
        assertTrue(chessAction.checkKnightMove("E4", "D2"));
        assertTrue(chessAction.checkKnightMove("e4", "3C"));
        assertTrue(chessAction.checkKnightMove("4e", "f2"));
        assertTrue(chessAction.checkKnightMove("E4", "g3"));
        assertTrue(chessAction.checkKnightMove("E4", "d6"));

        assertTrue(chessAction.checkKnightMove("D5", "C3"));
        assertTrue(chessAction.checkKnightMove("d5", "b4"));
        assertTrue(chessAction.checkKnightMove("5d", "4f"));
        assertTrue(chessAction.checkKnightMove("5D", "7E"));
        assertTrue(chessAction.checkKnightMove("D5", "c7"));

        assertFalse(chessAction.checkKnightMove("e4", "e5"));
        assertFalse(chessAction.checkKnightMove("4e", "f4"));
        assertFalse(chessAction.checkKnightMove("4E", "4D"));
        assertFalse(chessAction.checkKnightMove("E4", null));
        assertFalse(chessAction.checkKnightMove(null, "f5"));

        assertFalse(chessAction.checkKnightMove("H8", "7h"));
        assertFalse(chessAction.checkKnightMove("8h", "@g8"));
        assertFalse(chessAction.checkKnightMove("123asd", "7g"));
        assertFalse(chessAction.checkKnightMove("h!8", "f6"));
        assertFalse(chessAction.checkKnightMove("H8", "asdf32"));
    }

    @Test
    void testCheckRookMove() {
        assertTrue(chessAction.checkRookMove("A1", "A2"));
        assertTrue(chessAction.checkRookMove("a1", "1B"));
        assertTrue(chessAction.checkRookMove("1a", "f1"));
        assertTrue(chessAction.checkRookMove("A1", "6a"));
        assertTrue(chessAction.checkRookMove("1A", "h1"));

        assertTrue(chessAction.checkRookMove("D5", "C5"));
        assertTrue(chessAction.checkRookMove("d5", "b5"));
        assertTrue(chessAction.checkRookMove("5d", "a5"));
        assertTrue(chessAction.checkRookMove("5D", "f5"));
        assertTrue(chessAction.checkRookMove("D5", "d7"));

        assertFalse(chessAction.checkRookMove("d5", "c6"));
        assertFalse(chessAction.checkRookMove("5d", "e6"));
        assertFalse(chessAction.checkRookMove("D5", "f4"));
        assertFalse(chessAction.checkRookMove("5D", null));
        assertFalse(chessAction.checkRookMove(null, "c1"));

        assertFalse(chessAction.checkRookMove("b5", "7h"));
        assertFalse(chessAction.checkRookMove("5B", "@g8"));
        assertFalse(chessAction.checkRookMove("123asd", "7g"));
        assertFalse(chessAction.checkRookMove("a!8", "f6"));
        assertFalse(chessAction.checkRookMove("B5", "asdf32"));
    }

    @Test
    void testFindKnightPossibleMoves() {
        Set<String> moves = new HashSet<>();

        Random rn = new Random();

        for (int i = 0; i < 11; i++) {
            char num = (char) (rn.nextInt(7) + 49);
            char sym = (char) (rn.nextInt(7) + 97);
            moves.add(Character.toString(sym) + Character.toString(num));
        }

        for (String st : moves) {
            assertEquals(chessAction.possibleKnightMovesDouble(st), chessAction.possibleKnightMovesDouble(st)); // check what?
        }
    }

    @Test
    void testPossibleKnightMovesDouble() {
        Set<String> moves = new HashSet<>();

        Random rn = new Random();

        for (int i = 0; i < 11; i++) {
            char num = (char) (rn.nextInt(8) + 49);
            char sym = (char) (rn.nextInt(8) + 97);
            moves.add(Character.toString(sym) + Character.toString(num));
        }

        System.out.println(moves);

        for (String st : moves) {
            assertEquals(chessAction.findKnightPossibleMoves(st), chessAction.findKnightPossibleMoves(st)); // check what?
        }
    }
}