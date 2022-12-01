package com.ziroh.customjavafxcontrols;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class MaskedTextField extends TextField {

	private boolean isMasked = true;;
	private final StringBuffer maskedText = new StringBuffer();
	private String dot = "\u25CF";

	public MaskedTextField(String dotCode) {
		this();
		this.dot = dotCode;
	}

	public MaskedTextField() {
		setTextFormatter(new TextFormatter<>(change -> {
			String changeText = change.getText().trim();
			if(change.isReplaced()) {
				maskedText.replace(change.getRangeStart(), change.getRangeEnd(), changeText);
				if (isMasked) {
					change.setText(dot.repeat(changeText.length()));
				}
			} else if (change.isDeleted()) {
				maskedText.delete(change.getRangeStart(), change.getRangeEnd());
			}else if (change.isAdded() && !changeText.isEmpty()) {
				maskedText.insert(change.getRangeStart(), changeText);
				if (isMasked) {
					change.setText(dot.repeat(changeText.length()));
				}
			}
			return change;
		}));
	}

	public String getMaskedText() {
		return maskedText.toString();
	}

	public boolean toggleMask() {
		isMasked = !isMasked;
		String text = maskedText.toString();
		clear();
		setText(text);
		return isMasked;
	}
}
