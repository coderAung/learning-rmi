package dev.aung.app.game;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeUtils {

    private static final TicTacToeCell c1 = new TicTacToeCell(1 , 1);
    private static final TicTacToeCell c2 = new TicTacToeCell(1 , 2);
    private static final TicTacToeCell c3 = new TicTacToeCell(1 , 3);
    private static final TicTacToeCell c4 = new TicTacToeCell(2 , 1);
    private static final TicTacToeCell c5 = new TicTacToeCell(2 , 2);
    private static final TicTacToeCell c6 = new TicTacToeCell(2 , 3);
    private static final TicTacToeCell c7 = new TicTacToeCell(3 , 1);
    private static final TicTacToeCell c8 = new TicTacToeCell(3 , 2);
    private static final TicTacToeCell c9 = new TicTacToeCell(3 , 3);

    private record TicTacToeWinPattern (
            TicTacToeCell cell1,
            TicTacToeCell cell2,
            TicTacToeCell cell3
    ) {}

    private static final List<TicTacToeWinPattern> patterns = List.of(
            new TicTacToeWinPattern(c1, c2, c3),
            new TicTacToeWinPattern(c4, c5, c6),
            new TicTacToeWinPattern(c7, c8, c9),
            new TicTacToeWinPattern(c1, c4, c7),
            new TicTacToeWinPattern(c2, c5, c8),
            new TicTacToeWinPattern(c3, c6, c9),
            new TicTacToeWinPattern(c1, c5, c9),
            new TicTacToeWinPattern(c3, c5, c7)
    );

    private static boolean calculatePatternRelation(TicTacToeWinPattern pattern, TicTacToeCell cell) {
        return pattern.cell1.equals(cell) || pattern.cell2.equals(cell) || pattern.cell3.equals(cell);
    }

    private static List<TicTacToeWinPattern> getRelatedPattern(TicTacToeCell cell) {
        return patterns.stream().filter(p -> calculatePatternRelation(p, cell)).toList();
    }

    public static List<TicTacToeWinPattern> calculateWinPattern(TicTacToeCell cell, List<TicTacToeCell> cells) {
        List<TicTacToeWinPattern> result = new ArrayList<>();
        for (var pattern : getRelatedPattern(cell)) {
            var count = cells.stream().filter(c -> calculatePatternRelation(pattern, c)).count();
            if(count == 3) {
                result.add(pattern);
            }
        }
        return result;
    }

}
