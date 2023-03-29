package com.ziroh.customjavafxcontrols.animations;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class ArcRotationAnimation extends HBox {

	private final Timeline timeline = new Timeline();

	public ArcRotationAnimation(double radius, double length, double seconds) {

		super();

		Arc arc = new Arc();

		arc.setRadiusX(radius);
		arc.setRadiusY(radius);

		arc.setStartAngle(0);
		arc.setLength(length);

		arc.getStyleClass().add("arc");

		getChildren().add(arc);
		getStyleClass().add("arc-box");

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.getKeyFrames().addAll(getKeyFrames(arc, seconds));
	}

	private List<KeyFrame> getKeyFrames(Arc arc, double seconds) {
		int totalFrames = 60;
		int totalRotation = 360;
		double frameSeconds = (seconds * 1000) / totalFrames;
		int frameRotation = totalRotation / totalFrames;

		List<KeyFrame> frames = new ArrayList<>();

		for(int i=1; i <= totalFrames; i++) {
			var tempFrameCount = i;
			frames.add(new KeyFrame(
					Duration.millis(frameSeconds * tempFrameCount),
					event -> arc.setRotate(frameRotation * tempFrameCount)
			));
		}

		return frames;
	}

	public void start() {
		timeline.play();
	}
}
