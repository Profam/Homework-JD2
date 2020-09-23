import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/task13"})
public class CockieCountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            Cookie cookie = new Cookie("cookieCount", "1");
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>"
                    + "<head>"
                    + "<title>CountServlet</title>"
                    + "</head>"
                    + "<body>"
                    + "<h3>It's your first visit for last 24 hours! Lets count your visits!</h3>"
                    + "</body>"
                    + "</html>");
        } else if (cookies[0].getMaxAge() != 0) {
            int cookieValue = Integer.parseInt(cookies[0].getValue());
            cookieValue++;
            cookies[0].setValue(String.valueOf(cookieValue));
            response.addCookie(cookies[0]);
            PrintWriter out = response.getWriter();
            out.println("<html>"
                    + "<head>"
                    + "<title>CountServlet</title>"
                    + "</head>"
                    + "<body>"
                    + "<h3>You visited this page " + cookies[0].getValue() + " times</h3>"
                    + "</body>"
                    + "</html>");
        }
    }
}