package com.example.lele;

import javafx.beans.property.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Stack;

public class Expense {
    private final StringProperty description;
    private final DoubleProperty amount;
    private final ObjectProperty<LocalDate> date;


    private Stack<Expense> expenseStack;
    private LinkedList<Expense> expenseLinkedList;

    public Expense(String description, double amount, LocalDate date) {
        this.description = new SimpleStringProperty(description);
        this.amount = new SimpleDoubleProperty(amount);
        this.date = new SimpleObjectProperty<>(date);

        expenseStack = new Stack<>();
        expenseLinkedList = new LinkedList<>();
    }


    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    // Additional methods for stack and linked list operations

    public void addToStack() {
        expenseStack.push(this);
    }

    public Expense removeFromStack() {
        return expenseStack.pop();
    }

    public void addToLinkedList() {
        expenseLinkedList.add(this);
    }

    public Expense removeFromLinkedList() {
        return expenseLinkedList.remove();
    }
}
