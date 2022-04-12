package com.example.customjavafxcontrols;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class MaskedTextField extends TextField {

	private boolean isMasked;
	private final StringBuilder maskedText;

	public MaskedTextField() {
		isMasked = true;
		maskedText = new StringBuilder("");
		setTextFormatter(new TextFormatter<>(change -> {
			if (change.isAdded() && !change.getText().isEmpty()) {
				maskedText.insert(change.getRangeStart(), change.getText());
				if (isMasked) {
					change.setText("\u25CF".repeat(change.getText().length()));
				}
			} else if (change.isDeleted()) {
				maskedText.delete(change.getRangeStart(), change.getRangeEnd());
			}
			return change;
		}));
	}

	public String getMaskedText() {
		return maskedText.toString();
	}

	public boolean toggleMask() {
		isMasked = !isMasked;
		String maskText = getMaskedText();
		clear();
		setText(maskText);
		return isMasked;
	}
}
