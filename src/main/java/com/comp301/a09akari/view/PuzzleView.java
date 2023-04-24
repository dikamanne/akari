package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.CellType;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;

public class PuzzleView implements FXComponent {
  ControllerImpl controller;

  public PuzzleView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane board = new GridPane();
    board.getStyleClass().add("Gridpane");
    board.setHgap(2);
    board.setVgap(2);

    for (int i = 0; i < controller.getModel().getActivePuzzle().getHeight(); i++) {
      for (int j = 0; j < controller.getModel().getActivePuzzle().getWidth(); j++) {
        board.add(makeTile(i, j, controller.getModel().getActivePuzzle().getCellType(i, j)), j, i);
      }
    }
    GridPane.setHgrow(board, Priority.ALWAYS);
    GridPane.setVgrow(board, Priority.ALWAYS);

    return board;
  }

  public TilePane makeTile(int r, int c, CellType type) {
    TilePane tile = new TilePane();
    if (type == CellType.CLUE) {
      if (controller.getModel().isClueSatisfied(r, c)) {
        //                tile = new TilePane();
        Label clueNumber = new Label(controller.getModel().getActivePuzzle().getClue(r, c) + "");
        clueNumber.getStyleClass().add("solvedClue");
        tile.getChildren().add(clueNumber);
        tile.getStyleClass().add("clueTile");

      } else {
        //                tile = new TilePane();
        Label clueNumber = new Label(controller.getModel().getActivePuzzle().getClue(r, c) + "");
        clueNumber.getStyleClass().add("unsolvedClue");
        tile.getChildren().add(clueNumber);
        tile.getStyleClass().add("clueTile");
      }
    } else if (type == CellType.CORRIDOR) {
      if (controller.getModel().isLit(r, c)) {
        //                tile = new TilePane();
        Label litCorridor = new Label();
        tile.getStyleClass().add("litCorridorTile");

        tile.setOnMousePressed(
            (MouseEvent event) -> {
              controller.clickCell(r, c);
            });

      } else {
        //                tile = new TilePane();
        Label corridor = new Label();
        tile.getStyleClass().add("corridorTile");

        tile.setOnMousePressed(
            (MouseEvent event) -> {
              controller.clickCell(r, c);
            });
      }

      if (controller.getModel().isLamp(r, c)) {
        Image lightBulbImage = new Image("/light-bulb.png");
        ImageView lightBulbView = new ImageView(lightBulbImage);

        HBox lightBulb = new HBox();
        lightBulb.getStyleClass().add("lightBulb");
        lightBulb.getChildren().add(lightBulbView);

        lightBulbView.setFitHeight(50);
        lightBulbView.setFitWidth(50);
        lightBulbView.setPreserveRatio(true);

        if (controller.getModel().isLampIllegal(r, c)) {
          lightBulb.getStyleClass().add("illegalLamp");
        }

        tile.getChildren().add(lightBulb);
      }

    } else if (type == CellType.WALL) {
      //            tile = new TilePane();
      Label wall = new Label();
      tile.getStyleClass().add("wallTile");
    }

    tile.getStyleClass().add("tile");
    return tile;
  }
}
