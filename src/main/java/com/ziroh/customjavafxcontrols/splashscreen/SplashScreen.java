package com.ziroh.customjavafxcontrols.splashscreen;

import com.ziroh.customjavafxcontrols.style.StyleSheetsLoader;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohan
 */
public class SplashScreen extends VBox {

	protected final Stage stage;

	public SplashScreen() {
		this.stage = new Stage();
	}

	public void onFinished() {
		stage.close();
	}

	public void show() {

		stage.initStyle(StageStyle.TRANSPARENT);

		getStyleClass().add("splashbox");

		StyleSheetsLoader.load(this, "com/ziroh/customjavafxcontrols/spashscreen.css");

		stage.setScene(new Scene(this, Color.TRANSPARENT));
		stage.show();

	}

	public void addChildren(Node... children) {
		getChildren().addAll(children);
	}

}
