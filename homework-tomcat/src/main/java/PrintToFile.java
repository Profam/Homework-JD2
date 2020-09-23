import java.io.FileOutputStream;
import java.io.IOException;

public class PrintToFile {
    public void printToFile(String data, String fileName) {
        try (FileOutputStream fileOutputStream
                     = new FileOutputStream(fileName)) {
            fileOutputStream.write(data.getBytes());
            fileOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
