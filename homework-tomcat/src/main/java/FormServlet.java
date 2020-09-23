import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/task10"})
public class FormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name") == null |
                req.getParameter("tel") == null |
                req.getParameter("email") == null) {
            getServletContext().getRequestDispatcher("/index.html").forward(req, resp);
        }
        resp.setContentType("text/html");
        try {
            PrintWriter out = resp.getWriter();
            String name = req.getParameter("name");
            String tel = (req.getParameter("tel"));
            String email = req.getParameter("email");
            out.println("<html>"
                    + "<head>"
                    + "<title>FormServlet</title>"
                    + "</head>"
                    + "<body>"
                    + "<h3>Hello " + name + "!</h3>"
                    + "<h3>your number: " + tel + "</h3>"
                    + "<h3>your email: " + email + "</h3>"
                    + "</body>"
                    + "</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
