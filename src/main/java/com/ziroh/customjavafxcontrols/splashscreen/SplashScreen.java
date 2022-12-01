package com.ziroh.customjavafxcontrols.splashscreen;

import com.ziroh.customjavafxcontrols.style.StyleSheetsLoader;
import javafx.animation.Transition;
import javafx.scene.Group;
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
public class SplashScreen {

	protected final Stage stage;
	private final ImageView imageView;
	private final List<Transition> transitions = new ArrayList<>();
	private final VBox splashBox = new VBox();
	private double width = 500;
	private double height = 400;

	public SplashScreen(ImageView imageView) {
		stage = new Stage();
		this.imageView = imageView;
	}

	public void addTransitions(Transition... transitions) {
		this.transitions.addAll(List.of(transitions));
	}

	public void addStyleSheets(String... sheets) {
		StyleSheetsLoader.load(splashBox, sheets);
	}

	public void setPrefSize(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public void onFinished() {
		stage.close();
	}

	public void start() {

		stage.initStyle(StageStyle.TRANSPARENT);

		imageView.getStyleClass().add("icon");

		splashBox.getChildren().add(new Group(imageView));
		splashBox.getStyleClass().add("parent");
		splashBox.getStylesheets().add(getClass().getClassLoader().getResource("com/ziroh/customjavafxcontrols/spashscreen.css").toString());

		stage.setScene(new Scene(splashBox, width, height, Color.TRANSPARENT));
		stage.show();

		transitions.forEach(Transition::play);

	}

}
