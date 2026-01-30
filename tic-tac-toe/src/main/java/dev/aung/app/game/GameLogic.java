package dev.aung.app.game;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class GameLogic implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final List<GameMove> gameMoves;

    public GameLogic(List<GameMove> gameMoves) {
        this.gameMoves = gameMoves;
    }

    public void move(GameMove gameMove) {
        gameMoves.add(gameMove);
    }

    public PlayerType getWinner() {
        List<GameMove> oMoves = gameMoves.stream().filter(a -> a.type().equals(PlayerType.You)).toList();
        List<GameMove> xMoves = gameMoves.stream().filter(a -> a.type().equals(PlayerType.Opponent)).toList();
        if(doOWin(oMoves)) {
            return PlayerType.You;
        }
        if(doXWin(xMoves)) {
            return PlayerType.Opponent;
        }
        return null;
    }

    private boolean doXWin(List<GameMove> xMoves) {
        return isWin(xMoves);
    }

    private boolean doOWin(List<GameMove> oMoves) {
        return isWin(oMoves);
    }

    private boolean isWin(List<GameMove> moves) {
        if(moves.size() >= 3) {
            List<TicTacToeCell> cells = moves.stream().map(GameMove::cell).toList();
            for (var cell : cells) {
                if(!TicTacToeUtils.calculateWinPattern(cell, cells).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }
}
