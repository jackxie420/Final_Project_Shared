module com.company.final_project_shared {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.company.final_project_shared to javafx.fxml;
    exports com.company.final_project_shared;
}