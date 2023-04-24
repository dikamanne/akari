package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  private int[][] board;

  public PuzzleImpl(int[][] board) {
    if (board == null) {
      throw new IllegalArgumentException();
    }
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
    if (r < 0 || r >= getHeight() || c < 0 || c >= getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    int val = board[r][c];
    if (val >= 0 && val <= 4) {
      return CellType.CLUE;
    } else if (val == 5) {
      return CellType.WALL;
    } else if (val == 6) {
      return CellType.CORRIDOR;
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  @Override
  public int getClue(int r, int c) {
    if (r < 0 || r >= getHeight() || c < 0 || c >= getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    return board[r][c];
  }
}
