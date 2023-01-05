package com.ziroh.customjavafxcontrols;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Circle;

public class ToggleButton extends HBox {

	private final SimpleBooleanProperty selectedState = new SimpleBooleanProperty();
	private final PseudoClass ACTIVE = PseudoClass.getPseudoClass("active");

	public ToggleButton() {

		getStyleClass().add("toggleButton");

		HBox filler = new HBox();

		Circle toggleCircle = new Circle();

		toggleCircle.getStyleClass().add("toggleCircle");

		toggleCircle.radiusProperty().bind(heightProperty().multiply(0.4));

		selectedState.addListener((obs, oldVal, newVal) -> {
			HBox.setHgrow(filler, newVal ? Priority.ALWAYS : Priority.NEVER);
			super.pseudoClassStateChanged(ACTIVE, newVal);
		});

		setOnMouseClicked(event -> {
			selectedState.set(!selectedState.get());
		});

		setState(false);

		getChildren().addAll(filler, toggleCircle);

	}

	public SimpleBooleanProperty selectedStateProperty() {
		return selectedState;
	}


	/**
	 * This method has to be used instead to MouseClicked function.
	 *
	 * @param eventHandler
	 */
	public void setOnClicked(EventHandler<? super MouseEvent> eventHandler) {
		super.setOnMouseClicked(event -> {
			selectedState.set(!selectedState.get());
			eventHandler.handle(event);
		});
	}

	public void setState(boolean state) {
		selectedState.set(state);
	}

	public boolean getState() {return selectedState.get();}

	public void setToolTip(Tooltip tooltip) {
		Tooltip.install(this, tooltip);
	}

}
