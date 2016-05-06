import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        Editor editor = new Editor("This is some\n test text");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(editor);
        frame.pack();
        frame.setVisible(true);
    }
}
