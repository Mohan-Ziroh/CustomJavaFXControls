package com.ziroh.customjavafxcontrols.task;

import javafx.concurrent.Task;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * @author Mohan
 */
public class TaskBuilder<T> {
	private final FXTask<T> task;

	public TaskBuilder(Callable<T> callable) {
		task = new FXTask<>(callable);
	}

	public TaskBuilder<T> onRunning(Runnable runnable) {
		task.setOnRunning(event -> runnable.run());
		return this;
	}

	public TaskBuilder<T> onScheduled(Consumer<T> value) {
		task.setOnScheduled(event -> value.accept(getValue()));
		return this;
	}

	public TaskBuilder<T> onCancelled(Runnable runnable) {
		task.setOnCancelled(event -> runnable.run());
		return this;
	}

	public TaskBuilder<T> onSucceeded(Consumer<T> value) {
		task.setOnSucceeded(event -> value.accept(getValue()));
		return this;
	}

	public TaskBuilder<T> onFailed(Consumer<Throwable> exception) {
		task.setOnFailed(event -> exception.accept(getException()));
		return this;
	}

	public T getValue() {
		return task.getValue();
	}


	public Throwable getException() {
		return task.getException();
	}

	public Task<T> build() {
		return task;
	}

}
