package com.ziroh.customjavafxcontrols.task;

import javafx.concurrent.Task;

import java.util.concurrent.Callable;

/**
 * @author Mohan
 */
public class FXTask<T> extends Task<T> {

	private final Callable<T> callable;

	public FXTask(Callable<T> callable) {
		this.callable = callable;
	}

	@Override
	protected T call() throws Exception {
		return callable.call();
	}
}
