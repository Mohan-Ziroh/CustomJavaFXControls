package com.ziroh.customjavafxcontrols.splashscreen;

import com.ziroh.customjavafxcontrols.task.TaskBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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

	public SplashScreenWithTimer(Duration splashTime, ExecutorService service) {
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
	public void show() {
		super.show();
		taskBuilder.onFailed(error -> {
			super.onFinished();
			if(Objects.nonNull(onFailed))
				onFailed.accept(error);
		});
		service.execute(taskBuilder.build());
	}
}
