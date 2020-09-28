import mysql.MySqlDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//task16
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    private Connection connection;

    private void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            this.connection = MySqlDataSource.getConnection();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/delete.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int num = Integer.parseInt(request.getParameter("num"));
            delete(num);
            response.sendRedirect(request.getContextPath() + "/task16");
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }

    public boolean delete(int num) throws SQLException {
        connect();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "delete from expenses where num=?");
        preparedStatement.setInt(1, num);
        boolean result = preparedStatement.execute();
        preparedStatement.close();
        return result;
    }
}