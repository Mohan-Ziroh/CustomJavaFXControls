package com.ziroh.customjavafxcontrols;

import com.ziroh.customjavafxcontrols.textfield.CustomTextFieldSkin;
import com.ziroh.customjavafxcontrols.textfield.Side;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Skin;

public class MaskedTextField extends PasswordField {

	private SimpleBooleanProperty maskedProperty = new SimpleBooleanProperty(true);
	private ObjectProperty<Node> leftGraphic = new SimpleObjectProperty<>();
	private ObjectProperty<Node> rightGraphic = new SimpleObjectProperty<>();
	private String dot = "\u25CF";

	public MaskedTextField(String dotCode) {
		this.dot = dotCode;
	}

	public MaskedTextField(Node node, Side side) {
		if(side == Side.LEFT) setLeftGraphic(node);
		else setRightGraphic(node);
	}

	public MaskedTextField(Node lnode, Node rnode) {
		setLeftGraphic(lnode);
		setRightGraphic(rnode);
	}

	public MaskedTextField() {
		getStyleClass().addAll("custom-text-field", "custom-password-field");
	}

	public final ObjectProperty<Node> getLeftGraphicProperty() {
		return leftGraphic;
	}

	public final Node getLeftGraphic() {
		return leftGraphic.getValue();
	}

	public final void setLeftGraphic(Node value) {
		leftGraphic.setValue(value);
	}

	public final ObjectProperty<Node> getRightGraphicProperty() {
		return rightGraphic;
	}

	public final Node getRightGraphic() {
		return rightGraphic.getValue();
	}

	public final void setRightGraphic(Node value) {
		rightGraphic.setValue(value);
	}


	@Override
	protected Skin<?> createDefaultSkin() {
		return new CustomTextFieldSkin(this) {
			@Override
			public ObjectProperty<Node> leftNodeProperty() {
				return MaskedTextField.this.getLeftGraphicProperty();
			}

			@Override
			public ObjectProperty<Node> rightNodeProperty() {
				return MaskedTextField.this.getRightGraphicProperty();
			}

			@Override
			protected String maskText(String txt) {
				if(getMasked()) return dot.repeat(txt.length());
				return txt;
			}
		};
	}

	public String getMaskedText() {
		return getText();
	}

	public boolean toggleMask() {
		setMasked(!getMasked());
		String text = getText();
		clear();
		setText(text);
		return getMasked();
	}

	public boolean getMasked() {
		return maskedProperty.getValue();
	}

	public void setMasked(Boolean masked) {
		maskedProperty.setValue(masked);
	}

	public SimpleBooleanProperty getMaskedProperty() {
		return maskedProperty;
	}

	public void setMaskedProperty(SimpleBooleanProperty maskedProperty) {
		this.maskedProperty = maskedProperty;
	}
}
