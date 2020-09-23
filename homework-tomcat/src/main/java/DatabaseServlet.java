import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(
        initParams = {
                @WebInitParam(name = "DB_URL", value = "jdbc:mysql://localhost:3306/listexpenses"),
                @WebInitParam(name = "USER", value = "root"),
                @WebInitParam(name = "PASS", value = "root")
        },
        urlPatterns = {"/task14"}
)
public class DatabaseServlet extends HttpServlet implements ServletConfig {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Database Result";

        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n");
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
            while (rs.next()) {
                int num = rs.getInt("num");
                Date paydate = rs.getDate("paydate");
                int receiver = rs.getInt("receiver");
                Double value = rs.getDouble("value");
                out.println("num: " + num + "<br>");
                out.println(", paydate: " + paydate + "<br>");
                out.println(", receiver: " + receiver + "<br>");
                out.println(", value: " + value + "<br>");
            }
            out.println("</body></html>");
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
