package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws SQLException {
        try {
            Dao myDao = DaoFactory.getDao("listexpenses");
            //получаем данные по получателю платежа(задан номер)
            Receiver receiverNum = myDao.getReceiver(1);
            System.out.println(receiverNum.getNum() + " "
                    + receiverNum.getName());

            //получаем данные по платежу (задан номер)
            Expense expenseNum = myDao.getExpense(1);
            System.out.println(expenseNum.getNum() + " "
                    + expenseNum.getPaydate() + " "
                    + expenseNum.getValue() + " "
                    + expenseNum.getReceiver());

            // получаем список получателей
            ArrayList<Receiver> receivers = myDao.getReceivers();
            for (int i = 0; i < receivers.size(); i++) {
                Receiver receiver = receivers.get(i);
                System.out.println(receiver.getNum() + " "
                        + receiver.getName());
            }

            // получаем список платежей
            ArrayList<Expense> expenses = myDao.getExpenses();
            for (int i = 0; i < expenses.size(); i++) {
                Expense expense = expenses.get(i);
                System.out.println(expense.getNum() + " "
                        + expense.getPaydate() + " "
                        + expense.getValue() + " "
                        + expense.getReceiver());
            }

            //добавление нового получателя
            Receiver receiver = new Receiver();
            receiver.setNum(10);
            receiver.setName("Гипермаркет proStore");
            myDao.addReceiver(receiver);

            //добавление нового платежа
            Expense expense = new Expense();
            expense.setNum(10);
            expense.setPaydate("01.09.2020");
            expense.setReceiver(2);
            expense.setValue(12345);
            myDao.addExpense(expense);

        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            System.exit(-1);
        } finally {
            log.info("Finished successfully");
            System.exit(0);
        }
    }
}
