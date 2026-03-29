package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

@Component
public class TableUtility {


    public <T> void addColumnToOneColumnTable(TableView<T> tableView, String columnHeader, String propertyName) {
        TableColumn<T, String> valueColumn = new TableColumn<>();
        valueColumn.textProperty().setValue(columnHeader);
        valueColumn.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        valueColumn.prefWidthProperty().bind(tableView.prefWidthProperty());
        tableView.getColumns().add(valueColumn);
    }

    public <T> TableColumn<T, String> addColumnToTableAndSpecifyWidth(TableView<T> tableView, String columnHeader, String propertyName, Integer width) {
        TableColumn<T, String> valueColumn = createColumn(tableView, columnHeader, propertyName);
        valueColumn.setPrefWidth(width);
        tableView.getColumns().add(valueColumn);
        return valueColumn;
    }

    public <T> TableColumn<T, String> addColumnToTable(TableView<T> tableView, String columnHeader, String propertyName) {
        TableColumn<T, String> valueColumn = createColumn(tableView, columnHeader, propertyName);
        tableView.getColumns().add(valueColumn);
        return valueColumn;
    }

    private <T> TableColumn<T, String> createColumn(TableView<T> tableView, String columnHeader, String propertyName) {
        TableColumn<T, String> valueColumn = new TableColumn<>();
        valueColumn.textProperty().setValue(columnHeader);
        valueColumn.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return valueColumn;
    }

    public <T> void setRowHeightOnTable(TableView<T> tableView, Integer rowHeight) {
        tableView.setRowFactory(weaponLogTableView -> {
            TableRow<T> row = new TableRow<>();
            row.setMinHeight(rowHeight);
            return row;
        });
    }

    public <T> void letColumnTakeUpRemainingSpace(TableView<T> tableView, TableColumn<T, ?> expandColumn, Integer widthOfOtherColumns) {

        expandColumn.prefWidthProperty().bind(tableView.widthProperty()
                .subtract(widthOfOtherColumns)
                .subtract(15));
    }

}