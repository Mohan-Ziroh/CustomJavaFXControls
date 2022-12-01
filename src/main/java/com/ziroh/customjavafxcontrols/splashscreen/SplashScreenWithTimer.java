package com.ziroh.customjavafxcontrols.splashscreen;

import com.ziroh.customjavafxcontrols.task.TaskBuilder;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.concurrent.ExecutorService;

/**
 * @author Mohan
 */
public class SplashScreenWithTimer extends SplashScreen {

	private final Duration splashTime;
	private final ExecutorService service;
	private final TaskBuilder<Void> taskBuilder;

	public SplashScreenWithTimer(ImageView imageView, Duration splashTime, ExecutorService service) {
		super(imageView);
		this.splashTime = splashTime;
		this.service = service;
		taskBuilder = new TaskBuilder<>(() -> {
			Thread.sleep((long) splashTime.toMillis());
			return null;
		});
	}

	public void onFinished(Runnable runnable) {
		taskBuilder.onSucceeded(value ->  {
			runnable.run();
			super.onFinished();
		});
	}

	@Override
	public void start() {
		super.start();
		service.execute(taskBuilder.build());
	}
}
