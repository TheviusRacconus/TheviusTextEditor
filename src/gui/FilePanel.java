package gui;

import javax.swing.*;

public class FilePanel extends JPanel
{
    private JPanel lineNumbers;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private String filePath;

    public FilePanel()
    {
        initComponents();
    }

    public FilePanel(String text, String path)
    {
        initComponents();
        textArea.setText(text);
        filePath = path;
    }

    public String getText()
    {
        return textArea.getText();
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String path)
    {
        filePath = path;
    }

    @SuppressWarnings("unchecked")
    private void initComponents()
    {

        lineNumbers = new JPanel();
        scrollPane = new JScrollPane();
        textArea = new JTextArea();

        GroupLayout lineNumbersLayout = new GroupLayout(lineNumbers);
        lineNumbers.setLayout(lineNumbersLayout);
        lineNumbersLayout.setHorizontalGroup(
                lineNumbersLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 29, Short.MAX_VALUE)
        );
        lineNumbersLayout.setVerticalGroup(
                lineNumbersLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        textArea.setColumns(20);
        textArea.setRows(5);
        scrollPane.setViewportView(textArea);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lineNumbers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lineNumbers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );
    }
}
