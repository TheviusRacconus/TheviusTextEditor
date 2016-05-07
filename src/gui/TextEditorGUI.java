package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import file.FileHandler;

public class TextEditorGUI extends JFrame
{
    public static int newFileCount;

    private JPanel bottomInfo;
    private JMenu editMenu;
    private JTabbedPane editor;
    private JMenu fileMenu;
    private JButton newFileButton;
    private JButton openFileButton;
    private JButton saveFileButton;
    private JButton saveFileAsButton;
    private JToolBar.Separator jSeparator1;
    private JToolBar.Separator jSeparator2;
    private JToolBar.Separator jSeparator3;
    private JMenuBar menuBar;
    private JToolBar toolbar;

    public TextEditorGUI()
    {
        newFileCount = 1;
        initComponents();
        actionListeners();
        newFile();
    }

    @SuppressWarnings("unchecked")
    private void initComponents()
    {

        toolbar = new JToolBar();
        newFileButton = new JButton();
        jSeparator2 = new JToolBar.Separator();
        openFileButton = new JButton();
        jSeparator1 = new JToolBar.Separator();
        saveFileButton = new JButton();
        jSeparator3 = new JToolBar.Separator();
        saveFileAsButton = new JButton();
        editor = new JTabbedPane();
        bottomInfo = new JPanel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        editMenu = new JMenu();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        toolbar.setRollover(true);

        newFileButton.setText("New File");
        newFileButton.setFocusable(false);
        newFileButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newFileButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolbar.add(newFileButton);
        toolbar.add(jSeparator2);

        openFileButton.setText("Open File");
        openFileButton.setFocusable(false);
        openFileButton.setHorizontalTextPosition(SwingConstants.CENTER);
        openFileButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolbar.add(openFileButton);
        toolbar.add(jSeparator1);

        saveFileButton.setText("Save File");
        saveFileButton.setFocusable(false);
        saveFileButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveFileButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolbar.add(saveFileButton);
        toolbar.add(jSeparator3);

        saveFileAsButton.setText("Save File As");
        saveFileAsButton.setFocusable(false);
        saveFileAsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveFileAsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolbar.add(saveFileAsButton);

        GroupLayout bottomInfoLayout = new GroupLayout(bottomInfo);
        bottomInfo.setLayout(bottomInfoLayout);
        bottomInfoLayout.setHorizontalGroup(
                bottomInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        bottomInfoLayout.setVerticalGroup(
                bottomInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 21, Short.MAX_VALUE)
        );

        fileMenu.setText("File");
        menuBar.add(fileMenu);

        editMenu.setText("Edit");
        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(toolbar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bottomInfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editor, GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(toolbar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editor, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bottomInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setVisible(true);
    }

    private void actionListeners()
    {
        newFileButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                newFile();
            }
        });

        openFileButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               openFile();
            }
        });

        saveFileButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                saveFile();
            }
        });

        saveFileAsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                saveFileAs();
            }
        });
    }

    private void newFile()
    {
        editor.add("New File " + newFileCount, new FilePanel());
        editor.setSelectedIndex(editor.getTabCount() - 1);
        newFileCount++;
    }

    private void openFile()
    {
        String path, text;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a file...");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(fileChooser.showOpenDialog(openFileButton) == JFileChooser.APPROVE_OPTION)
        {
            path = fileChooser.getSelectedFile().getAbsolutePath();
            text = FileHandler.readFile(path);
            if(text == null)
            {
                JOptionPane.showMessageDialog(this, "Error!");
            }
            else
            {
                editor.add(FileHandler.getFileName(path), new FilePanel(text, path));
                editor.setSelectedIndex(editor.getTabCount() - 1);
            }
        }
    }

    private void saveFile()
    {
        String path, text;
        FilePanel panel;
        int selectedIndex = editor.getSelectedIndex();
        panel = (FilePanel) editor.getComponentAt(selectedIndex);
        path = panel.getFilePath();

        if(path != null)
        {
            text = panel.getText();

            if(!FileHandler.saveFile(path, text))
            {
                JOptionPane.showMessageDialog(this, "Error!");
            }
        }
        else
        {
            saveFileAs();
        }
    }

    private void saveFileAs()
    {
        String path, text;
        FilePanel panel;
        int selectedIndex = editor.getSelectedIndex();

        panel = (FilePanel) editor.getComponentAt(selectedIndex);
        text = panel.getText();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a location...");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if(fileChooser.showOpenDialog(saveFileAsButton) == JFileChooser.APPROVE_OPTION)
        {
            path = fileChooser.getSelectedFile().getAbsolutePath();
            if(!path.contains("."))
            {
                path += ".txt";
            }
            if(FileHandler.saveFile(path, text))
            {
                panel.setFilePath(path);
                editor.setTitleAt(selectedIndex, FileHandler.getFileName(path));
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Error!");
            }
        }
    }
}

