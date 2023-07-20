import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseTracker {
    private List<Expense> expenses;
    private List<String> categories;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
        categories = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Expense Tracker!");

        while (true) {
            System.out.println();
            System.out.println("1. Record an Expense");
            System.out.println("2. View Expense Summary");
            System.out.println("3. Manage Categories");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    recordExpense(scanner);
                    break;
                case 2:
                    viewExpenseSummary(scanner);
                    break;
                case 3:
                    manageCategories(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the Expense Tracker!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void recordExpense(Scanner scanner) {
        System.out.println();
        System.out.println("Record Expense");

        System.out.print("Enter the date (e.g., DD/MM/YYYY): ");
        String date = scanner.nextLine();

        System.out.print("Enter the amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the category: ");
        String category = scanner.nextLine();

        System.out.print("Enter the description: ");
        String description = scanner.nextLine();

        Expense expense = new Expense(date, amount, category, description);
        expenses.add(expense);

        System.out.println("Expense recorded successfully.");
    }

    private void viewExpenseSummary(Scanner scanner) {
        System.out.println();
        System.out.println("View Expense Summary");

        System.out.println("1. Total Expenses");
        System.out.println("2. Expenses by Category");
        System.out.println("3. Back");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                double totalExpenses = calculateTotalExpenses();
                System.out.println("Total Expenses: " + totalExpenses);
                break;
            case 2:
                System.out.print("Enter the category: ");
                String category = scanner.nextLine();
                double expensesByCategory = calculateExpensesByCategory(category);
                System.out.println("Expenses in " + category + ": " + expensesByCategory);
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void manageCategories(Scanner scanner) {
        System.out.println();
        System.out.println("Manage Categories");

        System.out.println("1. Add Category");
        System.out.println("2. Remove Category");
        System.out.println("3. Back");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                System.out.print("Enter the category name: ");
                String category = scanner.nextLine();
                categories.add(category);
                System.out.println("Category added successfully.");
                break;
            case 2:
                System.out.print("Enter the category name: ");
                String categoryToRemove = scanner.nextLine();
                boolean removed = categories.remove(categoryToRemove);
                if (removed) {
                    System.out.println("Category removed successfully.");
                    removeExpensesByCategory(categoryToRemove);
                } else {
                    System.out.println("Category not found.");
                }
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private double calculateTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    private double calculateExpensesByCategory(String category) {
        double total = 0;
        for (Expense expense : expenses) {
            if (expense.getCategory().equals(category)) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    private void removeExpensesByCategory(String category) {
        expenses.removeIf(expense -> expense.getCategory().equals(category));
    }

    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();
        expenseTracker.start();
    }
}

class Expense {
    private String date;
    private double amount;
    private String category;
    private String description;

    public Expense(String date, double amount, String category, String description) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
