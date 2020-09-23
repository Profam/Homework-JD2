import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/task9"})
public class CountServlet extends HttpServlet {
    private static final String COUNT = "count";
    private int count;

    @Override
    public void init() {
        File counterFile = new File(COUNT);
        ReadFromFile readFromFile = new ReadFromFile();
        if (counterFile.length() == 0) {
            new PrintToFile().printToFile("0", COUNT);
        }
        count = Integer.parseInt(readFromFile.readFromFile(COUNT));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        count++;
        try {PrintWriter out = response.getWriter();
            out.println("<html>"
                    + "<head>"
                    + "<title>CountServlet</title>"
                    + "</head>"
                    + "<body>"
                    + "<h3>You visited this page " + count + " times</h3>"
                    + "</body>"
                    + "</html>");
        } catch (IOException ex) {
            Logger.getLogger(CountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy() {
        new PrintToFile().printToFile(String.valueOf(count), COUNT);
    }
}