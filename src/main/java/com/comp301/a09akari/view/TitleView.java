package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class TitleView implements FXComponent {
  private final AlternateMvcController controller;

  public TitleView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox titleFrame = new HBox();
    HBox title = new HBox();
    HBox puzzleIdxBox = new HBox();

    Label puzzleIdx =
        new Label(
            "Puzzle "
                + (controller.getActivePuzzleIndex() + 1)
                + " of "
                + controller.getPuzzleLibrarySize());
    Label akariTitle = new Label("Akari");

    puzzleIdxBox.getChildren().add(puzzleIdx);
    title.getChildren().add(akariTitle);

    title.getStyleClass().add("AkariTitle");

    titleFrame.getChildren().add(puzzleIdxBox);
    HBox.setHgrow(puzzleIdxBox, Priority.ALWAYS);
    titleFrame.getChildren().add(title);

    return titleFrame;
  }
}
