package com.ziroh.customjavafxcontrols.textfield;

import javafx.beans.property.ObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.layout.StackPane;
import javafx.scene.text.HitInfo;

import java.util.Objects;

public abstract class CustomTextFieldSkin extends TextFieldSkin {

	private StackPane leftPane;
	private StackPane rightPane;

	public CustomTextFieldSkin(final TextField control) {
		super(control);

		updateChildren();

		registerChangeListener(leftNodeProperty(), e -> updateChildren());
		registerChangeListener(rightNodeProperty(), e -> updateChildren());
	}

	public abstract ObjectProperty<Node> leftNodeProperty();

	public abstract ObjectProperty<Node> rightNodeProperty();

	private void updateChildren() {
		Node newLeft = leftNodeProperty().get();
		getChildren().remove(leftPane);
		if (Objects.nonNull(newLeft)) {
			leftPane = new StackPane(newLeft);
			leftPane.setManaged(false);
			leftPane.setAlignment(Pos.CENTER_LEFT);
			leftPane.getStyleClass().add("left-pane");
			getChildren().add(0, leftPane);
		} else {
			leftPane = null;
		}

		Node newRight = rightNodeProperty().get();
		getChildren().remove(rightPane);
		if (newRight != null) {
			rightPane = new StackPane(newRight);
			rightPane.setManaged(false);
			rightPane.setAlignment(Pos.CENTER_RIGHT);
			rightPane.getStyleClass().add("right-pane");
			getChildren().add(rightPane);
		} else {
			rightPane = null;
		}

	}

	@Override
	protected void layoutChildren(double x, double y, double w, double h) {
		final double fullHeight = h + snappedTopInset() + snappedBottomInset();

		final double leftWidth = leftPane == null ? 0.0 : snapSizeX(leftPane.prefWidth(fullHeight));
		final double rightWidth = rightPane == null ? 0.0 : snapSizeX(rightPane.prefWidth(fullHeight));

		final double textFieldStartX = snapPositionX(x) + snapSizeX(leftWidth);
		final double textFieldWidth = w - snapSizeX(leftWidth) - snapSizeX(rightWidth);

		super.layoutChildren(textFieldStartX, 0, textFieldWidth, fullHeight);

		if (leftPane != null) {
			final double leftStartX = 0;
			leftPane.resizeRelocate(leftStartX, 0, leftWidth, fullHeight);
		}

		if (rightPane != null) {
			final double rightStartX = w - rightWidth + snappedLeftInset();
			rightPane.resizeRelocate(rightStartX, 0, rightWidth, fullHeight);
		}
	}

	@Override
	public HitInfo getIndex(double x, double y) {
		final double leftWidth = leftPane == null ? 0.0 : snapSizeX(leftPane.prefWidth(getSkinnable().getHeight()));
		return super.getIndex(x - leftWidth, y);
	}

	@Override
	protected double computePrefWidth(double h, double topInset, double rightInset, double bottomInset, double leftInset) {
		final double pw = super.computePrefWidth(h, topInset, rightInset, bottomInset, leftInset);
		final double leftWidth = leftPane == null ? 0.0 : snapSizeX(leftPane.prefWidth(h));
		final double rightWidth = rightPane == null ? 0.0 : snapSizeX(rightPane.prefWidth(h));

		return pw + leftWidth + rightWidth;
	}

	@Override
	protected double computePrefHeight(double w, double topInset, double rightInset, double bottomInset, double leftInset) {
		final double ph = super.computePrefHeight(w, topInset, rightInset, bottomInset, leftInset);
		final double leftHeight = leftPane == null ? 0.0 : snapSizeY(leftPane.prefHeight(-1));
		final double rightHeight = rightPane == null ? 0.0 : snapSizeY(rightPane.prefHeight(-1));

		return Math.max(ph, Math.max(leftHeight, rightHeight));
	}

	@Override
	protected double computeMinWidth(double h, double topInset, double rightInset, double bottomInset, double leftInset) {
		final double mw = super.computeMinWidth(h, topInset, rightInset, bottomInset, leftInset);
		final double leftWidth = leftPane == null ? 0.0 : snapSizeX(leftPane.minWidth(h));
		final double rightWidth = rightPane == null ? 0.0 : snapSizeX(rightPane.minWidth(h));

		return mw + leftWidth + rightWidth;
	}

	@Override
	protected double computeMinHeight(double w, double topInset, double rightInset, double bottomInset, double leftInset) {
		final double mh = super.computeMinHeight(w, topInset, rightInset, bottomInset, leftInset);
		final double leftHeight = leftPane == null ? 0.0 : snapSizeY(leftPane.minHeight(-1));
		final double rightHeight = rightPane == null ? 0.0 : snapSizeY(rightPane.minHeight(-1));

		return Math.max(mh, Math.max(leftHeight, rightHeight));
	}
}
