package com.example.lele;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import java.time.LocalDate;

public class ExpenseTrackerController {
    @FXML
    private TableView<Expense> expenseTable;

    @FXML
    private TableColumn<Expense, String> descriptionColumn;

    @FXML
    private TableColumn<Expense, Double> amountColumn;

    @FXML
    private TableColumn<Expense, LocalDate> dateColumn;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField amountField;

    @FXML
    private DatePicker datePicker;

    private ObservableList<Expense> expenses = FXCollections.observableArrayList();

    public void initialize() {

        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        expenseTable.setItems(expenses);
    }

    @FXML
    private void handleAddExpense() {
        String description = descriptionField.getText();
        double amount = Double.parseDouble(amountField.getText());
        LocalDate date = datePicker.getValue();

        Expense expense = new Expense(description, amount, date);
        expenses.add(expense);

        descriptionField.clear();
        amountField.clear();
        datePicker.setValue(null);
    }

    @FXML
    private void handleDeleteExpense() {
        Expense selectedExpense = expenseTable.getSelectionModel().getSelectedItem();
        if (selectedExpense != null) {
            expenses.remove(selectedExpense);
        } else {
        }
    }
}
