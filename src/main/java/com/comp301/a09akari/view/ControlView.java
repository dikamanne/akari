package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ControlView implements FXComponent {
  private ControllerImpl controller;

  public ControlView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox pane = new HBox();

    Button prev = new Button("prev");
    pane.getChildren().add(prev);
    prev.setOnAction(
        (ActionEvent event) -> {
          controller.clickPrevPuzzle();
        });

    Button next = new Button("next");
    pane.getChildren().add(next);
    next.setOnAction(
        (ActionEvent event) -> {
          controller.clickNextPuzzle();
        });
    Button reset = new Button("reset");
    pane.getChildren().add(reset);
    reset.setOnAction(
        (ActionEvent event) -> {
          controller.clickResetPuzzle();
        });
    Button selectRand = new Button("select random");
    pane.getChildren().add(selectRand);
    selectRand.setOnAction(
        (ActionEvent event) -> {
          controller.clickRandPuzzle();
        });

    if (controller.getModel().isSolved()) {
      Label solved = new Label("SOLVED!!!");
      solved.getStyleClass().add("AkariTitle");
      solved.getStyleClass().add("solvedClue");
      pane.getChildren().add(solved);
    }

    return pane;
  }
}
