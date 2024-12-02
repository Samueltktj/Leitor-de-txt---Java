package panelSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class mainPanel extends JFrame implements ActionListener {

    private static final long serialVersionUID = 5924196340829656017L;
    private String titulo = "Projeto I - GUI Grupo VI";

    private final JPanel leftPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();
    
    private JSplitPane sp = new JSplitPane();
    
    private final JPanel statusBar = new JPanel();
    private final JLabel statusText = new JLabel();

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenu = new JMenu("Arquivos");
    private final JMenuItem openFile = new JMenuItem("Abrir Arquivo");
    private final JMenuItem closeFile = new JMenuItem("Fechar Arquivo");
    private final JMenuItem exitApp = new JMenuItem("Sair");

    private final JMenu configMenu = new JMenu("Configurações");
    private final JMenuItem defaultConfig = new JMenuItem("Padrões");
    private final JMenu colorConfig = new JMenu("Cores");
    private final JMenuItem backgroundColor = new JMenuItem("Fundo");
    private final JMenuItem animationColor = new JMenuItem("Animação");
        
    private final JMenu speedConfig = new JMenu("Velocidade");
    private final JMenuItem slowSpeed = new JMenuItem("Lento");
    private final JMenuItem mediumSpeed = new JMenuItem("Médio");
    private final JMenuItem fastSpeed = new JMenuItem("Rápido");
    private final JMenuItem veryFastSpeed = new JMenuItem("Muito rápido");

    private JMenu helpMenu = new JMenu("Ajuda");
    private JMenuItem helpHelp = new JMenuItem("Ajuda");
    private JMenuItem helpAbout = new JMenuItem("Sobre");

    private final JFileChooser file = new JFileChooser();
    private final JTextArea fileText = new JTextArea();
    private JScrollPane scrollPane;
    
    private final BackgroundAnimation animation = new BackgroundAnimation();
    private short optionForDefault = 1;

    public void Start() {
    	
        setProgramIcon();
    	
    	this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.45), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.45));
        this.setLocationRelativeTo(null);
    	
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setupWindowsListener();

        fileMenu.add(openFile);
        fileMenu.add(closeFile);
        fileMenu.add(exitApp);

        configMenu.add(defaultConfig);
        configMenu.add(colorConfig);
        colorConfig.add(backgroundColor);
        colorConfig.add(animationColor);
        
        configMenu.add(speedConfig);
        speedConfig.add(slowSpeed);
        speedConfig.add(mediumSpeed);
        speedConfig.add(fastSpeed);
        speedConfig.add(veryFastSpeed);

        helpMenu.add(helpHelp);
        helpMenu.add(helpAbout);

        menuBar.add(fileMenu);
        menuBar.add(configMenu);
        menuBar.add(helpMenu);
        
        this.setTitle(this.titulo);
        
        this.add(sp);
        sp.setLeftComponent(leftPanel);
        sp.setRightComponent(rightPanel);
        sp.setDividerLocation(412);
        sp.setDividerSize(7);
        sp.setResizeWeight(0.5);
        sp.setEnabled(false);

        this.setJMenuBar(menuBar);
        this.setVisible(true);
        
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(animation, BorderLayout.CENTER);
        
        statusBar.setLayout(new FlowLayout(FlowLayout.CENTER, 10 , 15));
        statusBar.setBackground(Color.LIGHT_GRAY);        
        this.add(statusBar, BorderLayout.SOUTH);
        
        statusText.setText("Menu Principal");
        statusBar.add(statusText);
    
        defaultConfig.addActionListener(this);
        backgroundColor.addActionListener(this);
        animationColor.addActionListener(this);
        veryFastSpeed.addActionListener(this);
        fastSpeed.addActionListener(this);
        mediumSpeed.addActionListener(this);
        slowSpeed.addActionListener(this);
        openFile.addActionListener(this);
        closeFile.addActionListener(this);
        exitApp.addActionListener(this);
        helpHelp.addActionListener(this);
        helpAbout.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == this.openFile) {
            getFile();
        }
        if (e.getSource() == this.closeFile) {
            new Thread(() -> {
                closeFile();
            }).start();
        }
        if (e.getSource() == this.defaultConfig) {
            new Thread(() -> {
                changeDefaultAnimation();
            }).start();
        }
        if (e.getSource() == this.backgroundColor) {
            new Thread(() -> { 
                changeBackgroundColor(); 
            }).start();
        }
        if (e.getSource() == this.animationColor) {
            new Thread(() -> {
                changeAnimationColor();
            }).start();
        }
        if (e.getSource() == this.slowSpeed) {
            new Thread(() -> {
        	    changeAnimationSpeed(2000);
            }).start();
        }
        if (e.getSource() == this.mediumSpeed) {
            new Thread(() -> {
        	    changeAnimationSpeed(750);
            }).start();
        }
        if (e.getSource() == this.fastSpeed) {
            new Thread(() -> {
        	    changeAnimationSpeed(200);
            }).start();
        }
        if (e.getSource() == this.veryFastSpeed) {
            new Thread(() -> {
        	    changeAnimationSpeed(10);
            }).start();
        }
        if (e.getSource() == this.helpHelp) {
            showHelp();
        }
        if (e.getSource() == this.helpAbout) {
            showAbout();
        }
        if (e.getSource() == this.exitApp) {
            exit();
        }
    }

    private void getFile() {

    	statusText.setText("Abrir arquivo");
        int accept = file.showSaveDialog(null);
        if (accept == JFileChooser.APPROVE_OPTION) {

            try 
            {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file.getSelectedFile()), StandardCharsets.UTF_8))) 
                {
                    String line = in.readLine();
                    fileText.setText("");
                    while (line != null) {
                        fileText.append(line + "\n");
                        fileText.setLineWrap(true);
                        fileText.setWrapStyleWord(true);
                        fileText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        line = in.readLine();
                    }
                    statusText.setText("Arquivo aberto - " + file.getName(file.getSelectedFile()));
                }
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }

            leftPanel.add(fileText, BorderLayout.CENTER);
            leftPanel.repaint();
            leftPanel.revalidate();
            scrollPane = new JScrollPane(fileText);
            leftPanel.setLayout(new BorderLayout());
            leftPanel.add(scrollPane, BorderLayout.CENTER);

        }
    }

    private void closeFile() {

        try {
            customizeTextStatus("Fechando o arquivo");
        }
        catch (InterruptedException ex) { }

        fileText.setText("");
        leftPanel.remove(scrollPane);
        leftPanel.repaint();
        leftPanel.revalidate();

        statusText.setText("Menu Principal");
    }
    
    private void changeDefaultAnimation() {
        try {
            customizeTextStatus("Alterando padrão");
        }
        catch (InterruptedException ex) { }

    	optionForDefault += 1;
    	
    	if (optionForDefault == 4)
    		optionForDefault = 1;
    	
    	animation.configDefaultAnimation(optionForDefault);
        statusText.setText("Menu Principal");
    }
    
    private void changeBackgroundColor() {
        Color color = JColorChooser.showDialog(this, "Selecione uma cor" ,Color.BLUE);
        try {
            customizeTextStatus("Alterando a cor de fundo da animação");
        }
        catch (InterruptedException ex) { }

    	animation.setBackgroundColor(color);
        statusText.setText("Menu Principal");
    }
    
    private void changeAnimationColor() { 

        Color color = JColorChooser.showDialog(this, "Selecione uma cor" ,Color.BLUE);
        try {
            customizeTextStatus("Alterando a cor da animação");
        }
        catch (InterruptedException ex) { }

    	animation.setAnimationColor(color);
    }
    
    private void changeAnimationSpeed(long speed) {
        try {
            customizeTextStatus("Alterando a velocidade da animação");
        }
        catch (InterruptedException ex) { }

    	animation.setSpeed(speed);

        statusText.setText("Menu Principal");
    }

    private void showHelp() {  
        AboutHelp screen = new AboutHelp(this, "Ajuda", "ProjetoIGUI\\bin\\helpText.txt");
        screen.showAboutHelp();
    }
    
    private void showAbout() {
        AboutHelp screen = new AboutHelp(this, "Sobre", "ProjetoIGUI\\bin\\aboutText.txt");
        screen.showAboutHelp();
    }

    private void setupWindowsListener() {

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                exit();
            }
        });

    }

    private void exit() {
        JOptionPane.showMessageDialog(null, "Fechando...");
        animation.setRunning(false);
        System.exit(1);
    }

    private void customizeTextStatus(String text) throws InterruptedException {
       
        statusText.setText(text);

        for (int i = 0; i < 4; i++) {
            try {
                Thread.sleep(500);
            } 
            catch (InterruptedException e) {
                if (Thread.currentThread().isInterrupted()) 
                    throw new InterruptedException("Um problema ocorreu. Motivo: " + e.getMessage());
            }
            statusText.setText(statusText.getText() + "."); 
        }
    }

    private void setProgramIcon() {
        ImageIcon img = new ImageIcon("ProjetoIGUI\\bin\\ftIcon.png");
    	this.setIconImage(img.getImage());
    }

}