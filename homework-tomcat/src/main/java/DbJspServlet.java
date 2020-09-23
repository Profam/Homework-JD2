import expense.Expense;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(
        initParams = {
                @WebInitParam(name = "DB_URL", value = "jdbc:mysql://localhost:3306/listexpenses"),
                @WebInitParam(name = "USER", value = "root"),
                @WebInitParam(name = "PASS", value = "root")
        },
        urlPatterns = {"/task15"}
)
public class DbJspServlet extends HttpServlet implements ServletConfig {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    getInitParameter("DB_URL"),
                    getInitParameter("USER"),
                    getInitParameter("PASS"));
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM task3";
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
            conn.close();
            request.setAttribute("table", expenses);
            getServletContext().getRequestDispatcher("/task15.jsp").forward(request, response);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
