package pl.noCompany.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.noCompany.main.AppBootstrapper;
import pl.noCompany.main.Repository;

public class TableController {
    private AppBootstrapper main;
    @FXML
    private TableView<Repository> repositoryTable;
    @FXML
    private Label nameLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private TableColumn<Repository, String> nameColumn;
    @FXML
    private TableColumn<Repository, String> dataColumn;

    @FXML
    public void initialize(){
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        dataColumn.setCellValueFactory(cell -> cell.getValue().timeProperty());
        repositoryTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showRepositoryDetails(newValue));

    }
    private void showRepositoryDetails(Repository repository){
        if(repository != null) {
            nameLabel.setText(repository.getName());
            dateLabel.setText(repository.getTime());
        }else{
            nameLabel.setText("");
            dateLabel.setText("");
        }
    }
}
