package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Puzzle;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PuzzleView implements FXComponent {
  private final AlternateMvcController controller;

  public PuzzleView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane board = new GridPane();
    ImageView tile;
    Puzzle active_puzzle = controller.getActivePuzzle();
    int rStorage = 0;
    int cStorage = 0;
    for (int r = 0; r < active_puzzle.getHeight(); r++) {
      for (int c = 0; c < active_puzzle.getWidth(); c++) {
        if (active_puzzle.getCellType(r, c) == CellType.CORRIDOR) {
          double radius = Math.min(50, 50) / 4.0;
          Circle circle = new Circle(25, 25, radius);
          circle.setFill(Color.BLUE);
          circle.setFill(Color.TRANSPARENT);
          Button button = new Button();
          button.setPrefHeight(50);
          button.setPrefWidth(50);
          int finalR = r;
          int finalC = c;
          button.setOnMousePressed(
              (MouseEvent event) -> {
                controller.clickCell(finalR, finalC);
              });
          Rectangle block = new Rectangle(50, 50);
          block.setFill(Color.WHITE);
          block.setStroke(Color.BLACK);
          ImageView image = new ImageView();
          if (controller.isLamp(r, c)) {
            if (controller.isLampIllegal(r, c)) {
              image.setImage(new Image("https://cdn-icons-png.flaticon.com/512/2779/2779262.png"));
              block.setFill(Color.YELLOW);
              circle.setFill(Color.RED);
            } else {
              image.setImage(new Image("light-bulb.png"));
              block.setFill(Color.YELLOW);
              circle.setFill(Color.BLACK);
            }
          } else if (controller.isLit(r, c)) {
            block.setFill(Color.YELLOW);
          }
          StackPane stack = new StackPane(block, circle);
          WritableImage writableImage = new WritableImage(50, 50);
          SnapshotParameters snapshotParameters = new SnapshotParameters();
          snapshotParameters.setFill(Color.TRANSPARENT);
          stack.snapshot(snapshotParameters, writableImage);
          tile = new ImageView(writableImage);
          tile.setFitHeight(50);
          tile.setFitWidth(50);
          button.setBackground(Background.EMPTY);
          board.add(tile, c, r);
          board.add(button, c, r);
        } else if (active_puzzle.getCellType(r, c) == CellType.WALL) {
          Rectangle block = new Rectangle(50, 50);
          block.setFill(Color.BLACK);
          StackPane stack = new StackPane(block);
          WritableImage writableImage = new WritableImage(50, 50);
          SnapshotParameters snapshotParameters = new SnapshotParameters();
          snapshotParameters.setFill(Color.TRANSPARENT);
          stack.snapshot(snapshotParameters, writableImage);
          tile = new ImageView(writableImage);
          tile.setFitHeight(50);
          tile.setFitWidth(50);
          board.add(tile, c, r);
        } else if (active_puzzle.getCellType(r, c) == CellType.CLUE) {
          Text text = new Text("");
          Rectangle block = new Rectangle(50, 50, Color.WHITE);
          block.setStroke(Color.BLACK);
          int clue = active_puzzle.getClue(r, c);
          if (clue == 0) {
            text.setText("0");
            if (controller.isClueSatisfied(r, c)) {
              block.setFill(Color.YELLOW);
            }
          } else if (clue == 1) {
            text.setText("1");
            if (controller.isClueSatisfied(r, c)) {
              block.setFill(Color.YELLOW);
            }
          } else if (clue == 2) {
            text.setText("2");
            if (controller.isClueSatisfied(r, c)) {
              block.setFill(Color.YELLOW);
            }

          } else if (clue == 3) {
            text.setText("3");
            if (controller.isClueSatisfied(r, c)) {
              block.setFill(Color.YELLOW);
            }
          } else if (clue == 4) {
            text.setText("4");
            if (controller.isClueSatisfied(r, c)) {
              block.setFill(Color.YELLOW);
            }
          }
          text.setFont(Font.font(40));
          text.setFill(Color.BLACK);
          StackPane stack = new StackPane(block, text);
          WritableImage writableImage = new WritableImage(50, 50);
          SnapshotParameters snapshotParameters = new SnapshotParameters();
          snapshotParameters.setFill(Color.TRANSPARENT);
          stack.snapshot(snapshotParameters, writableImage);
          tile = new ImageView(writableImage);
          tile.setFitHeight(50);
          tile.setFitWidth(50);
          board.add(tile, c, r);
        }
      }
    }
    StackPane stackPane = new StackPane();
    GridPane background = new GridPane();
    background.setPrefSize(600, 600);
    background.setStyle("fx-background-color: white;");
    stackPane.getChildren().addAll(background, board);
    stackPane.setAlignment(board, Pos.CENTER);
    return stackPane;
  }
}
