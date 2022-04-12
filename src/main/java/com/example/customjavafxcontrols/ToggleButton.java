package com.example.customjavafxcontrols;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Circle;

public class ToggleButton extends HBox {

	private final SimpleBooleanProperty currentState = new SimpleBooleanProperty();
	private final PseudoClass ACTIVE = PseudoClass.getPseudoClass("active");

	public ToggleButton() {

		getStyleClass().add("toggleButton");

		HBox filler = new HBox();

		Circle toggleCircle = new Circle();

		toggleCircle.getStyleClass().add("toggleCircle");

		toggleCircle.radiusProperty().bind(heightProperty().multiply(0.4));

		currentState.addListener((obs, oldVal, newVal) -> {
			HBox.setHgrow(filler, newVal ? Priority.ALWAYS : Priority.NEVER);
			super.pseudoClassStateChanged(ACTIVE, newVal);
		});

		setOnMouseClicked(event -> {
			currentState.set(!currentState.get());
		});

		currentState.setValue(false);

		getChildren().addAll(filler, toggleCircle);

	}

	public SimpleBooleanProperty currentStateProperty() {
		return currentState;
	}


	/**
	 * This method has to be used instead to MouseClicked function.
	 *
	 * @param eventHandler
	 */
	public void setOnClicked(EventHandler<? super MouseEvent> eventHandler) {
		super.setOnMouseClicked(event -> {
			currentState.set(!currentState.get());
			eventHandler.handle(event);
		});
	}

	public void setState(boolean state) {
		currentState.set(state);
	}

	public boolean getState() {return currentState.getValue();}

	public void setToolTip(Tooltip tooltip) {
		Tooltip.install(this, tooltip);
	}

}
