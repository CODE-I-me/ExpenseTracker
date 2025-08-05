
package service;
import model1.Expense;
import storage.ExpenseStorage;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ExpenseService {
    private final ExpenseStorage storage = new ExpenseStorage();
    private List<Expense> expenses;
    public ExpenseService() { expenses = storage.load(); }

    public void addExpense(Expense e) { expenses.add(e); storage.save(expenses); }
    public List<Expense> getAll() { return expenses; }
    public List<Expense> filterByDate(LocalDate from, LocalDate to) {
        return expenses.stream()
            .filter(e -> !e.getDate().isBefore(from) && !e.getDate().isAfter(to))
            .collect(Collectors.toList());
    }
    public double total(List<Expense> list) {
        return list.stream().mapToDouble(Expense::getAmount).sum();
    }
    public Map<String, Double> getCategorySpending(List<Expense> list) {
        Map<String, Double> map = new HashMap<>();
        for (Expense e : list) map.put(e.getCategory(), map.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        return map;
    }
    public String mostSpentCategory(List<Expense> list) {
        return getCategorySpending(list).entrySet().stream()
            .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("--");
    }
    public double averageDaily(List<Expense> list) {
        if (list.isEmpty()) return 0.0;
        long days = list.stream().map(Expense::getDate).distinct().count();
        return total(list) / days;
    }
    public Map<Integer, Double> getMonthlyTotals(List<Expense> list, int year) {
        Map<Integer, Double> result = new HashMap<>();
        for (Expense e : list) {
            if (e.getDate().getYear() == year) {
                int month = e.getDate().getMonthValue();
                result.put(month, result.getOrDefault(month, 0.0) + e.getAmount());
            }
        }
        return result;
    }
}

