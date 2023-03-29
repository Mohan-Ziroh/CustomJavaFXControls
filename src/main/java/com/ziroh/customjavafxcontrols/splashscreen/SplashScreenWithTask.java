package com.ziroh.customjavafxcontrols.splashscreen;

import com.ziroh.customjavafxcontrols.task.TaskBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * @author Mohan
 */
public class SplashScreenWithTask<T> extends SplashScreen {

	private Consumer<Throwable> onFailed;
	private final ExecutorService service;
	private final TaskBuilder<T> taskBuilder;

	public SplashScreenWithTask(Callable<T> callable, ExecutorService service) {
		this.service = service;
		taskBuilder = new TaskBuilder<>(callable);
	}

	public void onFinished(Consumer<T> onFinished) {
		taskBuilder.onSucceeded(value -> {
			onFinished.accept(value);
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
			if(Objects.nonNull(onFailed)) onFailed.accept(error);
		});
		service.execute(taskBuilder.build());
	}


}
