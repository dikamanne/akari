package com.comp301.a09akari.model;

import java.util.ArrayList;

public class ModelImpl implements Model {
  private PuzzleLibrary library;
  private Puzzle activePuzzle;
  private int[][] lampMap;
  private int activePuzzleIndex;
  private ArrayList<ModelObserver> observers;

  public ModelImpl(PuzzleLibrary _library) {
    if (_library == null) {
      throw new IllegalArgumentException();
    }

    library = _library;
    this.activePuzzleIndex = 0;
    this.activePuzzle = library.getPuzzle(activePuzzleIndex);
    this.observers = new ArrayList<>();

    lampMap =
        new int[library.getPuzzle(activePuzzleIndex).getHeight()]
            [library.getPuzzle(activePuzzleIndex).getWidth()];
    observers = new ArrayList<ModelObserver>();
  }

  @Override
  public void addLamp(int r, int c) {
    if (r < 0
        || r >= library.getPuzzle(activePuzzleIndex).getHeight()
        || c < 0
        || c >= library.getPuzzle(activePuzzleIndex).getWidth()) {
      throw new IndexOutOfBoundsException();
    } else if (library.getPuzzle(activePuzzleIndex).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    } else {
      lampMap[r][c] = 1;
    }
    notifyObservers();
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r < 0
        || r >= library.getPuzzle(activePuzzleIndex).getHeight()
        || c < 0
        || c >= library.getPuzzle(activePuzzleIndex).getWidth()) {
      throw new IndexOutOfBoundsException();
    } else if (library.getPuzzle(activePuzzleIndex).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    } else {
      lampMap[r][c] = 0;
    }
    notifyObservers();
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r < 0
        || r >= library.getPuzzle(activePuzzleIndex).getHeight()
        || c < 0
        || c >= library.getPuzzle(activePuzzleIndex).getWidth()) {
      throw new IndexOutOfBoundsException();
    } else if (library.getPuzzle(activePuzzleIndex).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }

    for (int i = r; i < library.getPuzzle(activePuzzleIndex).getHeight(); i++) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (lampMap[i][c] == 1) {
        return true;
      }
    }

    for (int i = r; i >= 0; i--) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (lampMap[i][c] == 1) {
        return true;
      }
    }

    for (int i = c; i < library.getPuzzle(activePuzzleIndex).getWidth(); i++) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (lampMap[r][i] == 1) {
        return true;
      }
    }

    for (int i = c; i >= 0; i--) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (lampMap[r][i] == 1) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r < 0
        || r >= library.getPuzzle(activePuzzleIndex).getHeight()
        || c < 0
        || c >= library.getPuzzle(activePuzzleIndex).getWidth()) {
      throw new IndexOutOfBoundsException();
    } else if (library.getPuzzle(activePuzzleIndex).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }

    return lampMap[r][c] == 1;
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r < 0
        || r >= library.getPuzzle(activePuzzleIndex).getHeight()
        || c < 0
        || c >= library.getPuzzle(activePuzzleIndex).getWidth()) {
      throw new IndexOutOfBoundsException();
    } else if (!isLamp(r, c)) {
      throw new IllegalArgumentException();
    }

    for (int i = r; i < library.getPuzzle(activePuzzleIndex).getHeight(); i++) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (lampMap[i][c] == 1 && i != r) {
        return true;
      }
    }

    for (int i = r; i >= 0; i--) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (lampMap[i][c] == 1 && i != r) {
        return true;
      }
    }

    for (int i = c; i < library.getPuzzle(activePuzzleIndex).getWidth(); i++) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (lampMap[r][i] == 1 && i != c) {
        return true;
      }
    }

    for (int i = c; i >= 0; i--) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (lampMap[r][i] == 1 && i != c) {
        return true;
      }
    }

    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return activePuzzle;
  }

  @Override
  public int getActivePuzzleIndex() {
    return activePuzzleIndex;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index >= library.size()) {
      throw new IndexOutOfBoundsException("Invalid puzzle index");
    } else {
      activePuzzleIndex = index;
      activePuzzle = library.getPuzzle(activePuzzleIndex);
      lampMap =
          new int[library.getPuzzle(activePuzzleIndex).getHeight()]
              [library.getPuzzle(activePuzzleIndex).getWidth()];
    }
    notifyObservers();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return library.size();
  }

  @Override
  public void resetPuzzle() {
    lampMap =
        new int[library.getPuzzle(activePuzzleIndex).getHeight()]
            [library.getPuzzle(activePuzzleIndex).getWidth()];
    notifyObservers();
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < library.getPuzzle(activePuzzleIndex).getHeight(); i++) {
      for (int j = 0; j < library.getPuzzle(activePuzzleIndex).getWidth(); j++) {
        if (library.getPuzzle(activePuzzleIndex).getCellType(i, j) == CellType.CORRIDOR) {
          if (!isLit(i, j)) {
            return false;
          }
          if (isLamp(i, j)) {
            if (isLampIllegal(i, j)) {
              return false;
            }
          }
        } else if (activePuzzle.getCellType(i, j) == CellType.CLUE) {
          if (!isClueSatisfied(i, j)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r < 0 || r >= activePuzzle.getHeight() || c < 0 || c >= activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    } else if (activePuzzle.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    int expectedLamps = activePuzzle.getClue(r, c);
    int actualLamps = 0;
    if (r + 1 >= 0
        && r + 1 < activePuzzle.getHeight()
        && lampMap[r + 1][c] == 1
        && r + 1 >= 0
        && r + 1 < activePuzzle.getHeight()) {
      actualLamps += 1;
    }
    if (r - 1 >= 0
        && r - 1 < activePuzzle.getHeight()
        && lampMap[r - 1][c] == 1
        && r - 1 >= 0
        && r - 1 < activePuzzle.getHeight()) {
      actualLamps += 1;
    }
    if (c + 1 >= 0
        && c + 1 < activePuzzle.getWidth()
        && lampMap[r][c + 1] == 1
        && c + 1 >= 0
        && c + 1 < activePuzzle.getWidth()) {
      actualLamps += 1;
    }
    if (c - 1 >= 0
        && c - 1 < activePuzzle.getWidth()
        && lampMap[r][c - 1] == 1
        && c - 1 >= 0
        && c - 1 < activePuzzle.getWidth()) {
      actualLamps += 1;
    }
    return expectedLamps == actualLamps;
  }

  @Override
  public void addObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  public void notifyObservers() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }
}
