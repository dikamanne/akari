package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;

import java.util.Random;

public class ControllerImpl implements AlternateMvcController {
  private final Model model;

  public ControllerImpl(Model model) {
    if (model == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    if (model.getActivePuzzleIndex() < model.getPuzzleLibrarySize()) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
      model.resetPuzzle();
    }
  }

  @Override
  public void clickPrevPuzzle() {
    if (model.getActivePuzzleIndex() > 0) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    }
  }

  @Override
  public void clickRandPuzzle() {
    Random rand = new Random();
    int randInt = rand.nextInt(model.getPuzzleLibrarySize());
    model.setActivePuzzleIndex(randInt);
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (model.isLamp(r, c) && model.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      model.removeLamp(r, c);
    } else if (!model.isLamp(r, c)
        && model.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      model.addLamp(r, c);
    }
  }

  public boolean isLit(int r, int c) {
    return model.isLit(r, c);
  }

  public boolean isLamp(int r, int c) {
    return model.isLamp(r, c);
  }

  public boolean isClueSatisfied(int r, int c) {
    return model.isClueSatisfied(r, c);
  }

  public boolean isSolved() {
    return model.isSolved();
  }

  @Override
  public Puzzle getActivePuzzle() {
    return model.getActivePuzzle();
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    return model.isLampIllegal(r, c);
  }

  public int getPuzzleLibrarySize() {
    return model.getPuzzleLibrarySize();
  }

  @Override
  public int getActivePuzzleIndex() {
    return model.getActivePuzzleIndex();
  }
}
