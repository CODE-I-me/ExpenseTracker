# Expense Tracker with Charts and Category Insights

A Java desktop application to help you track your daily expenses, categorize spending, visualize data with charts, and gain insights into your financial habits. Built using Java Swing for the GUI and JFreeChart for charting, this project follows the MVC design pattern and persists data in JSON files for simplicity.

---

## Features

- Add expenses with details: title, amount, category (Food, Travel, Bills, etc.), date, and optional notes.
- View all expenses in a sortable, searchable table.
- Filter expenses by month.
- Visualize spending by category with an interactive pie chart.
- Insights including total monthly spending, most expensive category, and average daily spending.
- Data saved and loaded from a JSON file.
- Clean MVC architecture for easy code maintenance and extension.

---

## Tech Stack

- Java SE 8+
- Java Swing (Desktop UI)
- JFreeChart (Chart visualization)
- Jackson (JSON serialization)
- JSON file-based storage
- Java Time API (Date handling)

---

## Getting Started

### Prerequisites

- JDK 8 or higher installed
- Maven build tool installed (optional but recommended)
- Internet connection to download dependencies if using Maven

### Installation

1. Clone the repository:

```

git clone https://github.com/yourusername/expense-tracker.git
cd expense-tracker

```

2. If you use Maven, run:

```

mvn compile
mvn exec:java -Dexec.mainClass="Main"

```

3. Or compile manually (Windows example):

```

javac -cp ".;lib/*" Main.java model\Expense.java service\ExpenseService.java storage\ExpenseStorage.java ui\ExpenseTrackerUI.java charts\ChartRenderer.java
java -cp ".;lib/*" Main

```

### Usage

- Launch the app.
- Use the input fields at the top to add a new expense.
- View all expenses in the table.
- Filter by month using the dropdown on the right.
- See your spending breakdown by category in the pie chart.
- View key insights below the chart.

---

## Project Structure

```

ExpenseTracker/
├── Main.java
├── model/
│   └── Expense.java
├── service/
│   └── ExpenseService.java
├── storage/
│   └── ExpenseStorage.java
├── ui/
│   └── ExpenseTrackerUI.java
├── charts/
│   └── ChartRenderer.java

```

---

## Future Enhancements

- Multi-user login system
- Cloud synchronization (API integration)
- Export/Import expenses to/from CSV
- Dark/Light theme toggle for better UX
- Mobile/Android version

---
