module com.example.customjavafxcontrols {
	requires javafx.controls;
	requires javafx.fxml;

	opens com.example.customjavafxcontrols to javafx.fxml;
	exports com.example.customjavafxcontrols;
}