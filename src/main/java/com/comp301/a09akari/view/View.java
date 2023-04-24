package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View implements FXComponent, ModelObserver {
  private ControllerImpl controller;
  private Stage stage;

  public View(ControllerImpl _controller, Stage _stage) {
    this.controller = _controller;
    this.stage = _stage;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();

    layout.getStyleClass().add("layout");

    PuzzleView puzzleView = new PuzzleView(controller);
    TitleView titleView = new TitleView(controller);
    ControlView controlView = new ControlView(controller);

    layout.getChildren().add(titleView.render());
    layout.getChildren().add(puzzleView.render());
    layout.getChildren().add(controlView.render());

    return layout;
  }

  @Override
  public void update(Model model) {
    Scene scene = new Scene(render());
    scene.getStylesheets().add("main.css");

    stage.setScene(scene);
    stage.sizeToScene();
  }
}
