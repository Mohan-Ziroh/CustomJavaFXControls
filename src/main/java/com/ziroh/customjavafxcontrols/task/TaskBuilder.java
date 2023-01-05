package com.ziroh.customjavafxcontrols.task;

import javafx.concurrent.Task;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * @author Mohan
 */
public class TaskBuilder<T> {
	private final Callable<T> callable;
	private Runnable onRunning;
	private Consumer<T> onScheduled;
	private Runnable onCancelled;
	private Consumer<T> onSucceeded;
	private Consumer<Throwable> onFailed;
	private Task<T> task;

	public TaskBuilder(Callable<T> callable) {
		this.callable = callable;
	}

	public TaskBuilder<T> onRunning(Runnable runnable) {
		this.onRunning = runnable;
		return this;
	}

	public TaskBuilder<T> onScheduled(Consumer<T> value) {
		this.onScheduled = value;
		return this;
	}

	public TaskBuilder<T> onCancelled(Runnable runnable) {
		this.onCancelled = runnable;
		return this;
	}

	public TaskBuilder<T> onSucceeded(Consumer<T> value) {
		this.onSucceeded = value;
		return this;
	}

	public TaskBuilder<T> onFailed(Consumer<Throwable> exception) {
		this.onFailed = exception;
		return this;
	}

	public Task<T> build() {
		if(isNull(task) || task.isDone() || task.isCancelled()) buildTask();
		return task;
	}

	private void buildTask() {
		task = new FXTask<>(callable);
		if(nonNull(onSucceeded)) task.setOnSucceeded(event -> onSucceeded.accept(task.getValue()));
		if(nonNull(onFailed)) task.setOnFailed(event -> onFailed.accept(task.getException()));
		if(nonNull(onRunning)) task.setOnRunning(event -> onRunning.run());
		if(nonNull(onScheduled)) task.setOnScheduled(event -> onScheduled.accept(task.getValue()));
		if(nonNull(onCancelled)) task.setOnCancelled(event -> onCancelled.run());
	}

	private boolean isNull(Object object) {
		return Objects.isNull(object);
	}

	private boolean nonNull(Object object) {
		return Objects.nonNull(object);
	}

}
