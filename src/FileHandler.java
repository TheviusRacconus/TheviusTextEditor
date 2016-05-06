import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileHandler
{
    public static String currentFile = "";

    public static void saveFile(String location, List<String> content)
    {
        Path file = Paths.get(location);
        try
        {
            Files.write(file, content, Charset.forName("UTF-8"));
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static String openFile(String location)
    {
        String content = "";
        try
        {
            File file = new File(location);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine())
            {
                content += scanner.nextLine() + "\n";
            }
            scanner.close();
            currentFile = location;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            content = "File was not found";
        }

        return content;
    }
}
