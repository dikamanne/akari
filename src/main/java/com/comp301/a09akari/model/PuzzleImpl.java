package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle  {
    private int[][] board;
    public PuzzleImpl(int[][] board) {
        this.board = board;

    }
    @Override
    public int getWidth() {
        return board[0].length;
    }

    @Override
    public int getHeight() {
        return board.length;
    }

  @Override
  public CellType getCellType(int r, int c) {
    int cellValue = board[r][c];
    if (cellValue == 5) {
      return CellType.WALL;
    } else {
      return CellType.CORRIDOR;
    }
        }

    @Override
    public int getClue(int r, int c) {
        int cellValue = board[r][c];
        if (cellValue >= 1 && cellValue <= 4) {
            return cellValue;
        } else {
            return 0;
        }
    }

}
