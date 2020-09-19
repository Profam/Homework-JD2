package mysql;

import Dao.DaoFactory;
import Dao.Expense;
import Dao.Receiver;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MyDaoImplTest {

    private MyDaoImpl myDao;
    private IDatabaseConnection connection;

    @Before
    public void setUp() throws Exception {
        try {
            myDao = DaoFactory.getDao("listexpenses_test");
            connection = new MySqlConnection(MySqlDataSource.getTestConnection(), "listexpenses_test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
        myDao = null;
    }

    @Test
    public void getReceiver() throws DatabaseUnitException, SQLException {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(MyDaoImplTest.class.getResourceAsStream("MyDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        //When
        Receiver receiver = myDao.getReceiver(1);
        //Then
        assertNotNull(receiver);
        assertEquals("name1", receiver.getName());
        DatabaseOperation.DELETE.execute(connection, dataSet);
    }

    @Test
    public void getReceivers() throws DatabaseUnitException, SQLException {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(MyDaoImplTest.class.getResourceAsStream("MyDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        //When
        ArrayList<Receiver> receiver = myDao.getReceivers();
        //Then
        assertNotNull(receiver);
        assertEquals(myDao.getReceiver(1).getName(), receiver.get(0).getName());
        DatabaseOperation.DELETE.execute(connection, dataSet);
    }

    @Test
    public void getExpense() throws DatabaseUnitException, SQLException {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(MyDaoImplTest.class.getResourceAsStream("MyDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        //When
        Expense expense = myDao.getExpense(1);
        //Then
        assertNotNull(expense);
        assertEquals("2020-11-01", expense.getPaydate());
        DatabaseOperation.DELETE.execute(connection, dataSet);
    }

    @Test
    public void getExpenses() throws DatabaseUnitException, SQLException {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(MyDaoImplTest.class.getResourceAsStream("MyDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        //When
        ArrayList<Expense> expense = myDao.getExpenses();
        //Then
        assertNotNull(expense);
        assertEquals(myDao.getExpense(1).getNum(), expense.get(0).getNum());
        DatabaseOperation.DELETE.execute(connection, dataSet);
    }

    @Test
    public void addExpense() throws DatabaseUnitException, SQLException {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(MyDaoImplTest.class.getResourceAsStream("MyDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        //When
        Expense expense = new Expense();
        expense.setNum(6);
        expense.setPaydate("2020-11-06");
        expense.setReceiver(6);
        expense.setValue(6.0);
        myDao.addExpense(expense);
        //Then
        assertNotNull(expense);
        assertNotEquals(myDao.getExpense(6), expense);
        assertEquals(myDao.getExpense(6).getNum(), expense.getNum());
        DatabaseOperation.DELETE.execute(connection, dataSet);
    }

    @Test
    public void addReceiver() throws DatabaseUnitException, SQLException {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(MyDaoImplTest.class.getResourceAsStream("MyDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        //When
        Receiver receiver = new Receiver();
        receiver.setNum(6);
        receiver.setName("name6");
        myDao.addReceiver(receiver);
        //Then
        assertNotNull(receiver);
        assertNotEquals(myDao.getReceiver(6), receiver);
        assertEquals(myDao.getReceiver(6).getName(), receiver.getName());
        DatabaseOperation.DELETE.execute(connection, dataSet);
    }
}