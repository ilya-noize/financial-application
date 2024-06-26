import java.util.ArrayList;

public class ExpensesManager {
    ArrayList<Double> expenses;

    ExpensesManager() {
        expenses = new ArrayList<>();
    }

    double saveExpense(double moneyBeforeSalary, double expense) {
        moneyBeforeSalary = moneyBeforeSalary - expense;
        expenses.add(expense);
        System.out.println("Значение сохранено! Ваш текущий баланс в рублях: " + moneyBeforeSalary);
        if (moneyBeforeSalary < 1000) {
            System.out.println("На вашем счету осталось совсем немного. Стоит начать экономить!");
        }
        return moneyBeforeSalary;
    }

    void printAllExpenses() {
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println("Трата № " + (i + 1) + ". Потрачено " + expenses.get(i) + " рублей");
        }
    }

    double findMaxExpense() {
        double maxExpense = 0;
        for (Double exp : expenses) {
            if (exp > maxExpense) {
                maxExpense = exp;
            }
        }
        return maxExpense;
    }

    void removeAllExpenses(){
        if(!expenses.isEmpty()) expenses.clear();
        System.out.println("Список трат пуст.");
    }

    boolean isEmpty(){
        return expenses.isEmpty();
    }

    void removeExpense(double expense){
        if(isEmpty())
            System.out.println("Список трат пуст.");
        else{
            if(expenses.contains(expense)){
                expenses.remove(expense);
                System.out.println("Трата удалена!");
            }
            else System.out.println("Такой траты нет.");
        }
    }
}