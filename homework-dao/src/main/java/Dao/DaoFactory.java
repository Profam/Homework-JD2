package Dao;

import mysql.MyDaoImpl;

import java.security.InvalidParameterException;
import java.sql.SQLException;

public class DaoFactory {

    private static MyDaoImpl dao;

    public static MyDaoImpl getDao(String database) throws SQLException {
        if ("listexpenses".equals(database)) {
            if (dao == null) {
                dao = new MyDaoImpl();
            }
            return dao;
        } else if ("listexpenses_test".equals(database)) {
            if (dao == null) {
                dao = new MyDaoImpl(true);
            }
            return dao;
        }
        throw new InvalidParameterException("No such database implemented" + database);
    }
}
