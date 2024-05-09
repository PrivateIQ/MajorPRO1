package com.example.lele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class ExpenseTrackerController {

    private Main main;





    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private TableView<Expense> expenseTable;

    @FXML
    private TableColumn<Expense, String> descriptionColumn;

    @FXML
    private TableColumn<Expense, Double> amountColumn;

    @FXML
    private TableColumn<Expense, LocalDate> dateColumn;
    @FXML
    private TableColumn<Expense, String> commentsColumn;
    @FXML
    private TextField descriptionField;

    @FXML
    private TextField amountField;

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField searchField;
    @FXML
    private TextField commentField;
    private ObservableList<Expense> expenses = FXCollections.observableArrayList();

    public void initialize() {
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        commentsColumn.setCellValueFactory(cellData -> cellData.getValue().commentsProperty());
        expenseTable.setItems(expenses);
    }

    @FXML
    private void handleAddExpense() {
        String description = descriptionField.getText();
        double amount = Double.parseDouble(amountField.getText());
        LocalDate date = datePicker.getValue();
        String comments = commentField.getText();
        Expense expense = new Expense(description, amount, date, comments);
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
        }
    }
    @FXML
    private void handleSave() {
        try {
            File file = new File("expenses.txt");

            FileWriter writer = new FileWriter(file);

            for (Expense expense : expenses) {
                writer.write(expense.getDescription() + "," + expense.getAmount() + "," + expense.getDate() + "," + expense.getComments() + "\n");
            }

            writer.close();

            System.out.println("saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save expenses: " + e.getMessage());
        }
    }
    @FXML
    private void handleLoad() {
        try {
            File file = new File("expenses.txt");

            if (!file.exists()) {
                System.out.println("File does not exist.");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            expenses.clear();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String description = parts[0];
                double amount = Double.parseDouble(parts[1]);
                LocalDate date = LocalDate.parse(parts[2]);
                String comments = parts[3];

                expenses.add(new Expense(description, amount, date, comments));
            }

            reader.close();

            expenseTable.setItems(expenses);

            System.out.println("loaded successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load expenses: " + e.getMessage());
        }
    }



    @FXML
    private void handleSearch() {
        String searchTerm = searchField.getText();
        if (!searchTerm.isEmpty()) {
            List<Expense> searchResults = ExpenseSearch.searchByName(expenses, searchTerm);
            expenseTable.setItems(FXCollections.observableArrayList(searchResults));
        } else {
            expenseTable.setItems(expenses);
        }
    }
}
