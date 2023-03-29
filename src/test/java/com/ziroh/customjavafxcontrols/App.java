package com.ziroh.customjavafxcontrols;

import com.ziroh.customjavafxcontrols.animations.ArcRotationAnimation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		showCircleLoading(primaryStage);
	}

	private void showCircleLoading(Stage stage) {
		ArcRotationAnimation arcRotationAnimation = new ArcRotationAnimation(90, 270, 1.5);

		stage.setScene(new Scene(arcRotationAnimation, 200, 300, Color.BLACK));
		stage.show();
		arcRotationAnimation.start();
	}


}
