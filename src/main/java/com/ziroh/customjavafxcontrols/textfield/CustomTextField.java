package com.ziroh.customjavafxcontrols.textfield;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

public class CustomTextField extends TextField {

	public CustomTextField() {
		getStyleClass().addAll("custom-text-field");
	}

	private ObjectProperty<Node> leftNode = new SimpleObjectProperty<>(this, "leftNode");
	private ObjectProperty<Node> rightNode = new SimpleObjectProperty<>(this, "rightNode");

	public final ObjectProperty<Node> leftNodeProperty() {
		return leftNode;
	}

	public final Node getLeftNode() {
		return leftNode.get();
	}

	public final void setLeftNode(Node value) {
		leftNode.set(value);
	}

	public final ObjectProperty<Node> rightNodeProperty() {
		return rightNode;
	}

	public final Node getRightNode() {
		return rightNode.get();
	}

	public final void setRightNode(Node value) {
		rightNode.set(value);
	}


	@Override
	protected Skin<?> createDefaultSkin() {
		return new CustomTextFieldSkin(this) {
			@Override
			public ObjectProperty<Node> leftNodeProperty() {
				return CustomTextField.this.leftNodeProperty();
			}

			@Override
			public ObjectProperty<Node> rightNodeProperty() {
				return CustomTextField.this.rightNodeProperty();
			}
		};
	}

}