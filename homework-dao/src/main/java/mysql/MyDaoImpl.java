package mysql;

import Dao.Dao;

import Dao.Receiver;
import Dao.Expense;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MyDaoImpl implements Dao {

    private static Logger log = Logger.getLogger(MyDaoImpl.class.getName());

    private Connection connection;
    boolean isTestInstance;

    public MyDaoImpl() throws SQLException {
        this.isTestInstance = false;
    }

    public MyDaoImpl(boolean isTestInstance) throws SQLException {
        this.isTestInstance = isTestInstance;
    }

    private void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            if (isTestInstance) {
                this.connection = MySqlDataSource.getTestConnection();
            } else {
                this.connection = MySqlDataSource.getConnection();
            }
        }
    }

    @Override
    public Receiver getReceiver(int num) throws SQLException {
        connect();
        PreparedStatement statement = connection
                .prepareStatement("select * from receivers where num=?");
        statement.setInt(1, num);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Receiver> receiverList = parseReceiverResultSet(resultSet);
        statement.close();
        return receiverList.size() > 0 ? receiverList.get(0) : null;

    }

    private ArrayList<Receiver> parseReceiverResultSet(ResultSet resultSet) throws SQLException {
        connect();
        ArrayList<Receiver> receivers = new ArrayList<>();
        while (resultSet.next()) {
            Receiver receiver = new Receiver();
            receiver.setNum(resultSet.getInt(1));
            receiver.setName(resultSet.getString(2));
            receivers.add(receiver);
        }
        return receivers;
    }

    @Override
    public ArrayList<Receiver> getReceivers() throws SQLException {
        connect();
        PreparedStatement statement = connection
                .prepareStatement("select * from receivers");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Receiver> receiverList = parseReceiverResultSet(resultSet);
        statement.close();
        return receiverList;
    }

    @Override
    public Expense getExpense(int num) throws SQLException {
        connect();
        PreparedStatement statement = connection
                .prepareStatement("select * from expenses where num=?");
        statement.setInt(1, num);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Expense> expenseList = parseExpenseResultSet(resultSet);
        statement.close();
        return expenseList.size() > 0 ? expenseList.get(0) : null;

    }

    private ArrayList<Expense> parseExpenseResultSet(ResultSet resultSet) throws SQLException {
        connect();
        ArrayList<Expense> expenses = new ArrayList<>();
        while (resultSet.next()) {
            Expense expense = new Expense();
            expense.setNum(resultSet.getInt(1));
            expense.setPaydate(resultSet.getString(2));
            expense.setValue(resultSet.getInt(3));
            expense.setReceiver(resultSet.getInt(4));
            expenses.add(expense);
        }
        return expenses;
    }

    @Override
    public ArrayList<Expense> getExpenses() throws SQLException {
        connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select * from expenses");
        ArrayList<Expense> expenseList = parseExpenseResultSet(resultSet);
        statement.close();
        return expenseList;
    }

    @Override
    public int addExpense(Expense expense) throws SQLException {
        connect();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into expenses " +
                        "values (?,?,?,?)"
        );

        preparedStatement.setInt(1, expense.getNum());
        preparedStatement.setString(2, expense.getPaydate());
        preparedStatement.setInt(3, expense.getReceiver());
        preparedStatement.setDouble(4, expense.getValue());
        boolean result = preparedStatement.execute();
        preparedStatement.close();
        if (result) return expense.getNum();
        else return -1;
    }

    @Override
    public int addReceiver(Receiver receiver) throws SQLException {
        connect();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into receivers " +
                        "values (?,?)"
        );
        preparedStatement.setInt(1, receiver.getNum());
        preparedStatement.setString(2, receiver.getName());
        boolean result = preparedStatement.execute();
        preparedStatement.close();
        if (result) return receiver.getNum();
        else return -1;
    }

}
