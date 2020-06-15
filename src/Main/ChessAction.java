package Main;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChessAction {
    private final String CHESS_BOARD = "[a-h][1-8]|[a-h][1-8]";
    private final Pattern pattern = Pattern.compile(CHESS_BOARD);

    public String toNormal(String input) {
        if (input == null || input.length() != 2)
            return null;

        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            if (Character.isDigit(input.charAt(0)) && Character.isLetter(input.charAt(1))) {
                input = input.charAt(1) + String.valueOf(input.charAt(0));
                matcher = pattern.matcher(input.toLowerCase());
                if (matcher.find()) {
                    return input.toUpperCase();
                } else {
                    return null;
                }
            } else {
                matcher = pattern.matcher(input.toLowerCase());
                if (matcher.find()) {
                    return input.toUpperCase();
                } else {
                    return null;
                }
            }
        }
        return input.toUpperCase();
    }

    public boolean checkQueenMove(String position, String destination) {
        position = toNormal(position);
        destination = toNormal(destination);

        if (position == null || destination == null || position.equals(destination))
            return false;

        int x = Math.abs(position.charAt(0) - destination.charAt(0));
        int y = Math.abs(position.charAt(1) - destination.charAt(1));

        return (x == y || (position.charAt(0) == destination.charAt(0)) || (position.charAt(1) == destination.charAt(1)));
    }

    public boolean checkKingMove(String position, String destination) {
        position = toNormal(position);
        destination = toNormal(destination);

        if (position == null || destination == null || position.equals(destination))
            return false;

        return (Math.abs(position.charAt(0) - destination.charAt(0)) <= 1) && (Math.abs(position.charAt(1) - destination.charAt(1)) <= 1);
    }

    public boolean checkBishopMove(String position, String destination) {
        position = toNormal(position);
        destination = toNormal(destination);

        if (position == null || destination == null || position.equals(destination))
            return false;

        return Math.abs(position.charAt(0) - destination.charAt(0)) == Math.abs(position.charAt(1) - destination.charAt(1));
    }

    public boolean checkKnightMove(String position, String destination) {
        position = toNormal(position);
        destination = toNormal(destination);

        if (position == null || destination == null || position.equals(destination))
            return false;

        int x = Math.abs(position.charAt(0) - destination.charAt(0));
        int y = Math.abs(position.charAt(1) - destination.charAt(1));

        return (x == 1 && y == 2) || (x == 2 && y == 1);
    }

    public Set findKnightPossibleMoves(String position) {
        position = toNormal(position);

        if (position == null)
            return null;

        Set<String> knightMovesList = new HashSet<>();

        String destination = null;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                destination = Character.toString((char) (i + 65)) + Character.toString((char) (j + 49)); // 65 - A || 49 - 1
                destination = toNormal(destination);
                int x = Math.abs(position.charAt(0) - destination.charAt(0));
                int y = Math.abs(position.charAt(1) - destination.charAt(1));
                if (((x == 1 && y == 2) || (x == 2 && y == 1)) && toNormal(destination) != null)
                    knightMovesList.add(destination);
            }
        }
        return knightMovesList;
    }

    public Set possibleKnightMovesDouble(String position){
        position = toNormal(position);

        if (position == null)
            return null;

        Set<String> knightMovesList = findKnightPossibleMoves(position);
        //System.out.println(knightMovesList);
        Set<String> knightMovesListDouble = new HashSet<>();

        for (String st : knightMovesList) {
            knightMovesListDouble.addAll(findKnightPossibleMoves(st));
            //System.out.println(knightMovesListDouble);
        }

        return knightMovesListDouble;
    }

    public boolean checkRookMove(String position, String destination) {
        position = toNormal(position);
        destination = toNormal(destination);

        if (position == null || destination == null || position.equals(destination))
            return false;

        return position.charAt(0) == destination.charAt(0) || position.charAt(1) == destination.charAt(1);
    }
}
