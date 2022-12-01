package com.ziroh.customjavafxcontrols.splashscreen;

import com.ziroh.customjavafxcontrols.task.TaskBuilder;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author Mohan
 */
public class SplashScreenWithTask<T> extends SplashScreen {

	private final Callable<T> callable;
	private final ExecutorService service;
	private final TaskBuilder<T> taskBuilder;

	public SplashScreenWithTask(ImageView imageView, Callable<T> callable, ExecutorService service) {
		super(imageView);
		this.callable = callable;
		this.service = service;
		taskBuilder = new TaskBuilder<>(callable)
				.onFailed(Throwable::printStackTrace);
	}

	public void onFinished(Consumer<T> onFinished) {
		taskBuilder.onSucceeded(value -> {
			onFinished.accept(value);
			super.onFinished();
		});
	}

	@Override
	public void start() {
		super.start();
		service.execute(taskBuilder.build());
	}


}
