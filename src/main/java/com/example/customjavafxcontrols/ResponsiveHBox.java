package com.example.customjavafxcontrols;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ResponsiveHBox extends HBox {

	private JustifyContentType justifyContentType;
	private final ObservableList<Node> children;

	public ResponsiveHBox() {
		justifyContentType = JustifyContentType.NONE;
		children = FXCollections.observableArrayList();
	}

	public void add(Node node) {
		if(justifyContentType != JustifyContentType.NONE) {
			getChildren().addAll(getFiller(), node);
		} else {
			getChildren().add(node);
		}
		children.add(node);
	}

	public void add(int index, Node node) {
		if(index < 0 || index > children.size())
			throw new IndexOutOfBoundsException();
		else {
			if(justifyContentType == JustifyContentType.SPACEAROUND) {
				getChildren().add((index * 2) + 1, node);
			} else {
				getChildren().add(index * 2, node);
			}
			children.add(index, node);
		}
	}

	public void addAll(Node... nodes) {
		for(Node node: nodes) {
			add(node);
		}
	}

	public void clear() {
		getChildren().clear();
		children.clear();
	}

	private Node getFiller() {
		HBox filler = new HBox();
		HBox.setHgrow(filler, Priority.ALWAYS);
		return filler;
	}

	@Deprecated
	public ObservableList<Node> getChildren() {
		return super.getChildren();
	}

	public ObservableList<Node> getAll() {
		return children;
	}

	public Node get(int index) {
		return children.get(index);
	}

	public void setJustifyContentType(JustifyContentType justifyContentType) {
		this.justifyContentType = justifyContentType;
		justifyContent();
	}

	private void justifyContent() {
		if(justifyContentType == JustifyContentType.SAPCEBETWEEN) {
			addFillers(1, (getChildren().size() * 2) - 1);
		} else {
			addFillers(0, (getChildren().size() * 2) - 1);
		}
	}

	private void addFillers(int start, int end) {
		while (start < end) {
			getChildren().add(start, getFiller());
			start += 2;
		}
	}

	public void remove(int index) {
		if(index < 0 || index > children.size())
			throw new IndexOutOfBoundsException();
		else {
			if(justifyContentType == JustifyContentType.SPACEAROUND) {
				getChildren().remove((index * 2) + 1);
			} else {
				getChildren().remove(index * 2);
			}
			children.remove(index);
		}
	}

	public void remove(Node node) {
		int index = getChildren().indexOf(node);
		if(index != -1) {
			getChildren().remove(index--);
			getChildren().remove(index);
			children.remove(index);
		}
	}
}
