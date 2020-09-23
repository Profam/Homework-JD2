import expense.Expense;
import mysql.MySqlDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//task16
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private Connection connection;

    private void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            this.connection = MySqlDataSource.getConnection();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        try {
            final String num = request.getParameter("num");
            final String paydate = request.getParameter("paydate");
            final String receiver = request.getParameter("receiver");
            final String value = request.getParameter("value");
            final Expense expense = new Expense(Integer.parseInt(num), Date.valueOf(paydate), Integer.parseInt(receiver), Double.parseDouble(value));
            addExpense(expense);
            response.sendRedirect(request.getContextPath() + "/task16");
        } catch (SQLException ex) {
            getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
        }
    }

    public int addExpense(Expense expense) throws SQLException {
        connect();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into task3 " +
                        "values (?,?,?,?)"
        );
        preparedStatement.setInt(1, expense.getNum());
        preparedStatement.setDate(2, expense.getPaydate());
        preparedStatement.setInt(3, expense.getReceiver());
        preparedStatement.setDouble(4, expense.getValue());
        boolean result = preparedStatement.execute();
        preparedStatement.close();
        if (result) return expense.getNum();
        else return -1;
    }
}