package com.example.lele;

import java.util.ArrayList;
import java.util.List;

public class ExpenseSearch {

    public static List<Expense> searchByName(List<Expense> expenses, String searchTerm) {
        List<Expense> searchResults = new ArrayList<>();
        for (Expense expense : expenses) {
            boolean nameMatches = expense.getDescription().toLowerCase().contains(searchTerm.toLowerCase());
            if (nameMatches) {
                searchResults.add(expense);
            }
        }
        return searchResults;
    }
}
