package com.ziroh.customjavafxcontrols.animations.loadingbar;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class LoadingBarAnimation extends HBox {

	private final Timeline timeline = new Timeline();
	private final HBox progressbar;
	private final HBox pusher;

	public LoadingBarAnimation() {
		this(300, 1.5);
	}

	public LoadingBarAnimation(double width, double seconds) {
		super();

		getStyleClass().add("loadingbar");

		progressbar = new HBox();
		progressbar.getStyleClass().add("progressbar");
		pusher = new HBox();
		pusher.getStyleClass().add("pusher");
		getChildren().addAll(pusher, progressbar);

		timeline.setCycleCount(Animation.INDEFINITE);

		var frames = getFrames(width, seconds);

		timeline.getKeyFrames().addAll(frames);
	}

	private List<KeyFrame> getFrames(double width, double seconds) {

		double totalFrames = 60;
		double speed = (seconds * 1000) / totalFrames;
		double frameWidth = width / totalFrames;

		var frames = new ArrayList<KeyFrame>();

		int currentFrame = 1;
		double part = totalFrames / 3;
		while (currentFrame <= totalFrames) {
			var tempFrame = currentFrame;
			if (currentFrame <= part) {
				frames.add(new KeyFrame(Duration.millis(speed * tempFrame), event -> {
					setWidth(progressbar, (frameWidth * ((totalFrames / 2) / part )) * tempFrame);
					setWidth(pusher, 0);
				}));
			} else if (currentFrame <= (part * 2)) {
				frames.add(new KeyFrame(Duration.millis(speed * tempFrame), event -> {
					setWidth(pusher, (frameWidth * ((totalFrames / 2) / part )) * (tempFrame - part));
				}));
			} else {
				frames.add(new KeyFrame(Duration.millis(speed * tempFrame), event -> {
					setWidth(pusher, (frameWidth * ((totalFrames / 2) / part )) * (tempFrame - part));
					setWidth(progressbar, (frameWidth * ((totalFrames / 2) / part )) * (totalFrames - tempFrame));
				}));
			}
			currentFrame += 1;
		}

		return frames;
	}

	private void setWidth(HBox child, double value) {
		child.setMinWidth(value);
		child.setPrefWidth(value);
		child.setMaxWidth(value);
	}

	public void start() {
		timeline.play();
	}
}
