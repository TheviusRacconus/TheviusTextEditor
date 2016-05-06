import javax.swing.*;
import java.awt.*;

public class Editor extends JPanel
{
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private String text;

    /**
     * Constructs a new Editor object
     * creates a new text area and adds it to
     * the panel
     */
    public Editor()
    {
        this("");
    }

    public Editor(String str)
    {
        text = str;
        textArea = new JTextArea(str);
        textArea.setFont(new Font("Serif", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        add(scrollPane);
    }
}
