package model1;
import java.time.LocalDate;

public class Expense {
    private String title;
    private double amount;
    private String category;
    private LocalDate date;
    private String note;

    public Expense() {} // Required by Jackson

    public Expense(String title, double amount, String category, LocalDate date, String note) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = note;
    }

    public String getTitle() { return title; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public LocalDate getDate() { return date; }
    public String getNote() { return note; }
}

