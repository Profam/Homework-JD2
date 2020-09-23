import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/task12"})
public class ImageCountServlet extends HttpServlet {
    private static final String COUNT = "count-for-image";
    private int count;

    @Override
    public void init() {
        File counterFile = new File(COUNT);
        try {
            counterFile.createNewFile();
        } catch (IOException e) {
            Logger.getLogger(ImageCountServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        ReadFromFile readFromFile = new ReadFromFile();
        if (counterFile.length() == 0) {
            new PrintToFile().printToFile("0", COUNT);
        }
        count = Integer.parseInt(readFromFile.readFromFile(COUNT));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        response.setContentType("image/jpeg");
        BufferedImage image = new BufferedImage(500,200, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(new Font("Serif", Font.ITALIC, 22));
        graphics.setColor(Color.green);
            count++;
        String str = "You visited this page " + count + " times";
        graphics.drawString(str,100,100);
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "jpeg", outputStream);
        } catch (IOException e) {
            Logger.getLogger(ImageCountServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void destroy() {
        new PrintToFile().printToFile(String.valueOf(count), COUNT);
    }
}