import expense.Expense;
import mysql.MySqlDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

//<user username="tomcat" password="tomcat" roles="secured"/>
@WebServlet(urlPatterns = {"/task16"})
@ServletSecurity(
        value = @HttpConstraint(
                rolesAllowed = {
                        "secured"
                }),
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = {
                        "secured"
                }),
                @HttpMethodConstraint(value = "POST")
        })
public class ManagerServlet extends HttpServlet {

    private Connection connection;

    private void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            this.connection = MySqlDataSource.getConnection();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            connect();
            Statement stmt = connection.createStatement();
            String sql;
            sql = "SELECT * FROM expenses";
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Expense> expenses = new ArrayList<Expense>();
            while (rs.next()) {
                int num = rs.getInt("num");
                Date paydate = rs.getDate("paydate");
                int receiver = rs.getInt("receiver");
                Double value = rs.getDouble("value");
                expenses.add(new Expense(num, paydate, receiver, value));
            }
            rs.close();
            stmt.close();
            connection.close();
            request.setAttribute("table", expenses);
            getServletContext().getRequestDispatcher("/task16.jsp").forward(request, response);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
