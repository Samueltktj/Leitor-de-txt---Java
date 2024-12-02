package panelSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class AboutHelp extends JDialog implements ActionListener {
    
    private JTextArea TextHA = new JTextArea();
    private final JButton exit = new JButton(); 
    private final JSplitPane splitPane = new JSplitPane();
    private final JPanel leftPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();
    private final JPanel bottomPanel = new JPanel();
    private JScrollPane scrollPane;

    private String filePath;

    public AboutHelp(JFrame mainWindow, String title, String filePath) {
       super(mainWindow, title);

       this.filePath = filePath;

       setLocation(50,290);
       setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.4), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.35));
       setLocationRelativeTo(mainWindow);
       setResizable(true);

       setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
       setDefaultCloseOperation(DISPOSE_ON_CLOSE);

       this.add(splitPane);
       splitPane.setLeftComponent(leftPanel);
       splitPane.setRightComponent(rightPanel);
       splitPane.setDividerLocation(250);
       splitPane.setDividerSize(7);
       splitPane.setResizeWeight(0.5);
       splitPane.setEnabled(false);

       TextHA.setEditable(false);
       scrollPane = new JScrollPane(TextHA);

       TextHA.setEditable(false);
       rightPanel.setLayout(new BorderLayout()); 
       rightPanel.add(scrollPane, BorderLayout.CENTER);

       ImageIcon imageIcon = new ImageIcon("ProjetoIGUI\\bin\\unicampLogo.png");
       JLabel imageLabel = new JLabel(imageIcon);
       leftPanel.setLayout(new BorderLayout());
       leftPanel.add(imageLabel, BorderLayout.CENTER);
       leftPanel.setBackground(Color.WHITE);

       exit.setPreferredSize(new Dimension(100, 30));
       exit.setText("Fechar");

       bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
       bottomPanel.setBackground(Color.LIGHT_GRAY);
       bottomPanel.add(exit);        
       this.add(bottomPanel, BorderLayout.SOUTH);

       exit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      this.setVisible(false);
    }

    public void showAboutHelp() {
      loadAboutHelpFile(filePath);
      TextHA.setCaretPosition(0);
      setVisible(true);
    }

    private void loadAboutHelpFile(String filePath) 
    {
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) 
      {
            String line = reader.readLine();
            TextHA.setText("");
            while (line != null) {
                TextHA.append(line + "\n");
                TextHA.setLineWrap(true);
                TextHA.setWrapStyleWord(true);
                TextHA.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                line = reader.readLine();
            }
      }
      catch (IOException e) 
      {
          e.printStackTrace();
          TextHA.setText("Erro ao carregar o arquivo: " + e.getMessage());
      }
    }
}