package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI
    stage.setTitle("Akari Game");

    PuzzleLibrary puzzleLibrary = new PuzzleLibraryImpl();

    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));

    Model model = new ModelImpl(puzzleLibrary);
    ControllerImpl controller = new ControllerImpl(model);

    View view = new View(controller, stage);

    Parent parent = view.render();
    Scene scene = new Scene(view.render(), 600, 600);
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);

    model.addObserver(
        (Model m) -> {
          scene.setRoot(view.render());
          stage.sizeToScene();
        });
    stage.setWidth(650);
    stage.setHeight(650);
    stage.show();
  }
}
