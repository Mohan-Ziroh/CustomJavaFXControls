package com.ziroh.customjavafxcontrols.splashscreen;

import com.ziroh.customjavafxcontrols.task.TaskBuilder;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * @author Mohan
 */
public class SplashScreenWithTimer extends SplashScreen {

	private final ExecutorService service;
	private final TaskBuilder<Void> taskBuilder;

	public SplashScreenWithTimer(ImageView imageView, Duration splashTime, ExecutorService service) {
		super(imageView);
		this.service = service;
		taskBuilder = new TaskBuilder<>(() -> {
			Thread.sleep((long) splashTime.toMillis());
			return null;
		});
	}

	public SplashScreenWithTimer onFinished(Runnable runnable) {
		taskBuilder.onSucceeded(value ->  {
			runnable.run();
			super.onFinished();
		});
		return this;
	}

	public SplashScreenWithTimer onFailed(Consumer<Throwable> onFailed) {
		taskBuilder.onFailed(error -> {
			super.onFinished();
			onFailed.accept(error);
		});
		return this;
	}

	@Override
	public void start() {
		super.start();
		service.execute(taskBuilder.build());
	}
}
