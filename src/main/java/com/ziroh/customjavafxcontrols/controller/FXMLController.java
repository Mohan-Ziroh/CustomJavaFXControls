package com.ziroh.customjavafxcontrols.controller;

import com.ziroh.customjavafxcontrols.style.StyleSheetsLoader;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Mohan
 */
public abstract class FXMLController implements Initializable {

	@Override
	public abstract void initialize(URL location, ResourceBundle resources);

	protected Region loadedComponent;

	protected <T extends FXMLController> T loadController(URL url, String... sheets) {
		try {
			FXMLLoader loader = new FXMLLoader(url);
			loadedComponent = loader.load();
			StyleSheetsLoader.load(loadedComponent, sheets);
			return loader.getController();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected <T extends FXMLController> T loadController(URL url,ResourceBundle resourceBundle, String... sheets) {
		try {
			FXMLLoader loader = new FXMLLoader(url, resourceBundle);
			loadedComponent = loader.load();
			StyleSheetsLoader.load(loadedComponent, sheets);
			return loader.getController();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected void changeToLoading(Button button) {
		button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}

	protected void resetToText(Button button) {
		button.setContentDisplay(ContentDisplay.TEXT_ONLY);
	}

	protected <T> boolean checkTaskRunning(Task<T> task) {
		return checkNonNull(task) && task.isRunning();
	}
	protected boolean checkNonNull(Object object) {
		return Objects.nonNull(object);
	}

	protected boolean checkNull(Object object) {
		return Objects.isNull(object);
	}
}
