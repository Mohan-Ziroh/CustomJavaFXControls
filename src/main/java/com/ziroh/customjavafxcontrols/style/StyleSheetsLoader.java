package com.ziroh.customjavafxcontrols.style;

import com.ziroh.customjavafxcontrols.splashscreen.SplashScreen;
import javafx.scene.layout.Region;

/**
 * @author Mohan
 */
public class StyleSheetsLoader {

	public static void load(Region node, String... sheets) {

		for(String sheet: sheets) {
			node.getStylesheets().add(StyleSheetsLoader.class.getClassLoader().getResource(sheet).toString());
		}
	}
}
