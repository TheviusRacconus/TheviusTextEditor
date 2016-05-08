package com.thevius.theviustexteditor.file;

import java.io.*;

public class FileHandler
{
    public static String readFile(String path)
    {
        String text = "";
        String line;
        try
        {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null)
            {
                text += line + "\n";
            }

            bufferedReader.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            return null;
        }

        return text;
    }

    public static boolean saveFile(String path, String text)
    {
        try
        {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text);
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public static String getFileName(String path)
    {
        File file = new File(path);
        return file.getName();
    }
}
