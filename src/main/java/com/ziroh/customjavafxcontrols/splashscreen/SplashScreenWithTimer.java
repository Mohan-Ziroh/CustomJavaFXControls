package com.ziroh.customjavafxcontrols.splashscreen;

import com.ziroh.customjavafxcontrols.task.TaskBuilder;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * @author Mohan
 */
public class SplashScreenWithTimer extends SplashScreen {

	private Consumer<Throwable> onFailed;
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

	public void onFinished(Runnable runnable) {
		taskBuilder.onSucceeded(value ->  {
			runnable.run();
			super.onFinished();
		});
	}

	public void onFailed(Consumer<Throwable> onFailed) {
		this.onFailed = onFailed;
	}

	@Override
	public void start() {
		super.start();
		taskBuilder.onFailed(error -> {
			super.onFinished();
			if(Objects.nonNull(onFailed))
				onFailed.accept(error);
		});
		service.execute(taskBuilder.build());
	}
}
