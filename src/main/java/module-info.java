module com.example.customjavafxcontrols {
	requires javafx.controls;
	requires javafx.fxml;

	opens com.ziroh.customjavafxcontrols to javafx.fxml;
	exports com.ziroh.customjavafxcontrols;
	exports com.ziroh.customjavafxcontrols.task;
	opens com.ziroh.customjavafxcontrols.task to javafx.fxml;
	exports com.ziroh.customjavafxcontrols.controller;
	opens com.ziroh.customjavafxcontrols.controller to javafx.fxml;
	exports com.ziroh.customjavafxcontrols.style;
	opens com.ziroh.customjavafxcontrols.style to javafx.fxml;
}