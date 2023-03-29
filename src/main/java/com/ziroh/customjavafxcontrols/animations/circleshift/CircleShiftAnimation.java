package com.ziroh.customjavafxcontrols.animations.circleshift;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class CircleShiftAnimation extends HBox {

	private final Timeline timeline;

	public CircleShiftAnimation() {
		this(37, 20, 1);
	}

	public CircleShiftAnimation(double width, double height, double seconds) {
		super();

		StackPane stackPane = new StackPane();
		stackPane.setMinSize(width, height);
		stackPane.setMaxSize(width, height);

		getChildren().add(stackPane);

		setMinSize(width, height);
		setMaxSize(width, height);

		HBox backgroundBox = new HBox();
		backgroundBox.setMinSize(width, height);
		backgroundBox.setMaxSize(width, height);

		Circle backgroundCircle = new Circle();

		HBox backgroundPusher = new HBox();

		backgroundCircle.setRadius(height / 2);
		backgroundCircle.setFill(Color.color(1,1, 1, 0.6));
		backgroundBox.getChildren().addAll(backgroundPusher, backgroundCircle);


		HBox frontBox = new HBox();
		frontBox.setMinSize(width, height);
		frontBox.setMaxSize(width, height);

		HBox frontPusher = new HBox();

		Circle frontCircle = new Circle();
		frontCircle.setRadius(height / 2);
		frontCircle.setFill(Color.color(1,1, 1));
		frontBox.getChildren().addAll(frontPusher, frontCircle);

		stackPane.getChildren().addAll(backgroundBox, frontBox);

		timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(true);

		timeline.getKeyFrames().addAll(getKeyFrames(backgroundPusher, frontPusher, width, height, seconds));

		timeline.play();
	}

	private List<KeyFrame> getKeyFrames(HBox backgroundPusher, HBox frontPusher, double width, double height, double seconds) {

		var totalFrames = 30;
		var partWidth = (width - height) / totalFrames;
		var partSeconds = (seconds  * 1000) / totalFrames;

		var keyFrames = new ArrayList<KeyFrame>();

		keyFrames.add(new KeyFrame(
				Duration.ZERO,
				event -> {
					setWidth(backgroundPusher, width - height);
					setWidth(frontPusher, 0);
				}
		));

		for(int i = 1; i < totalFrames; i++) {
			var tempCount = i;
			keyFrames.add(new KeyFrame(
					Duration.millis(partSeconds * i),
					event -> {
						setWidth(backgroundPusher, (width - height) - (partWidth * tempCount));
						setWidth(frontPusher, (partWidth * tempCount));
					}
			));
		}

		return keyFrames;
	}

	private void setWidth(HBox child, double value) {
		child.setMinWidth(value);
		child.setPrefWidth(value);
		child.setMaxWidth(value);
	}

	public void start() {
		timeline.play();
	}

	public void stop() {
		timeline.stop();
	}
}
