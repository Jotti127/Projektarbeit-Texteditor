package projektarbeit_texteditor;

//Importierte Klassen
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;

//Swing Fenster
public class EditorGui extends JFrame{

    //Instanzvariablen
    private JTextArea textArea;
    private JLabel wordCountLabel;
    private File currentFile;

    //Innere Klasse für ActionListener
    class MyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("new")) {
                Actions.newFile(textArea);
                currentFile = null;
                updateWindowTitle();
            }
            if (e.getActionCommand().equals("exit")) {
                Actions.exit();
            }
            if (e.getActionCommand().equals("load")) {
                File file = Actions.loadDialog(textArea);
                if (file != null) {
                    currentFile = file;
                    updateWindowTitle();
                }
            }
            if (e.getActionCommand().equals("save")) {
                String saveText = textArea.getText();
                File file = Actions.saveDialog(saveText);
                if (file != null) {
                    currentFile = file;
                    updateWindowTitle();
                }
            }
            if (e.getActionCommand().equals("increase")) {
                Actions.increaseFontSize(textArea);
            }
            if (e.getActionCommand().equals("decrease")) {
                Actions.decreaseFontSize(textArea);
            }
            if (e.getActionCommand().equals("find")) {
                Actions.find(textArea);
            }
        }
    }

    //EditorGUI
    public EditorGui(String windowTitle) {
        super(windowTitle);

        // Layout für das Fenster
        setLayout(new BorderLayout());

        // Textbereich erstellen
        textArea = new JTextArea();
        textArea.setLineWrap(true); //Zeilenumbruch
        textArea.setWrapStyleWord(true); //Zeilenumbruch nur nach Leerzeichen
        JScrollPane scrollPane = new JScrollPane(textArea); //Scroll Pane für Textbereich

        //Komponenten hinzufügen
        menu();
        statusMenu();
        add(createToolBar(), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        //Weitere Einstellungen
        setSize(800, 600); //Fenstergröße für Editor
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //das Verhalten beim Schließen setzen
        updateWindowTitle();
        setVisible(true); //das Fenster sichtbar machen
    }

    //Menüleiste und Items
    private void menu() {
        JMenuBar menu = new JMenuBar();
        MyListener listener = new MyListener();

        //*** Menüleiste Reiter "Datei" ***
        JMenu dataMenu = new JMenu("Datei");

        //Menü-Item Neu
        JMenuItem dataNew = new JMenuItem();
        dataNew.setText("Neu");
        dataNew.setActionCommand("new");
        dataNew.setIcon(new ImageIcon("icon/new.png"));
        dataNew.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK |
                InputEvent.SHIFT_DOWN_MASK));
        dataNew.addActionListener(listener);
        dataMenu.add(dataNew);

        //Menü-Item Laden
        JMenuItem dataLoad = new JMenuItem();
        dataLoad.setText("Laden");
        dataLoad.setActionCommand("load");
        dataLoad.setIcon(new ImageIcon("icon/open.png"));
        dataLoad.setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.CTRL_DOWN_MASK));
        dataLoad.addActionListener(listener);
        dataMenu.add(dataLoad);

        //Menü-Item Speichern
        JMenuItem dataSave = new JMenuItem();
        dataSave.setText("Speichern");
        dataSave.setActionCommand("save");
        dataSave.setIcon(new ImageIcon("icon/save.png"));
        dataSave.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        dataSave.addActionListener(listener);
        dataMenu.add(dataSave);

        //Menü-Item Beenden
        JMenuItem dataExit = new JMenuItem();
        dataExit.setText("Beenden");
        dataExit.setActionCommand("exit");
        dataExit.setIcon(new ImageIcon("icon/exit.png"));
        dataExit.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_DOWN_MASK));
        dataExit.addActionListener(listener);
        dataMenu.add(dataExit);


        //*** Menüleiste Reiter "Bearbeiten" ***
        JMenu editMenu = new JMenu("Bearbeiten");

        //Menü-Item Schrift vergrößern
        JMenuItem increaseFont = new JMenuItem();
        increaseFont.setText("Schrift vergrößern");
        increaseFont.setActionCommand("increase");
        increaseFont.setIcon(new ImageIcon("icon/increaseFont.png"));
        increaseFont.setAccelerator(KeyStroke.getKeyStroke('G', InputEvent.CTRL_DOWN_MASK));
        increaseFont.addActionListener(listener);
        editMenu.add(increaseFont);

        //Menü-Item Schrift verkleinern
        JMenuItem decreaseFont = new JMenuItem();
        decreaseFont.setText("Schrift verkleinern");
        decreaseFont.setActionCommand("decrease");
        decreaseFont.setIcon(new ImageIcon("icon/decreaseFont.png"));
        decreaseFont.setAccelerator(KeyStroke.getKeyStroke('K', InputEvent.CTRL_DOWN_MASK));
        decreaseFont.addActionListener(listener);
        editMenu.add(decreaseFont);

        //Menü-Item Suche
        JMenuItem findItem = new JMenuItem();
        findItem.setText("Suche");
        findItem.setActionCommand("find");
        findItem.setIcon(new ImageIcon("icon/find.png"));
        findItem.setAccelerator(KeyStroke.getKeyStroke('F', InputEvent.CTRL_DOWN_MASK |
                InputEvent.SHIFT_DOWN_MASK));
        findItem.addActionListener(listener);
        editMenu.add(findItem);

        //Hinzufügen der Menü-Reiter
        menu.add(dataMenu);
        menu.add(editMenu);
        this.setJMenuBar(menu);
    }

    //Symbolleiste und Icons
    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        MyListener listener = new MyListener();

        //Symbolleiste Neu-Icon
        JButton dataNewButton = new JButton();
        dataNewButton.setActionCommand("new");
        dataNewButton.setIcon(new ImageIcon("icon/new.png"));
        dataNewButton.setToolTipText("Erstellt ein neues Dokument");
        dataNewButton.addActionListener(listener);
        toolBar.add(dataNewButton);

        //Symbolleiste Laden-Icon
        JButton dataLoadButton = new JButton();
        dataLoadButton.setActionCommand("load");
        dataLoadButton.setIcon(new ImageIcon("icon/open.png"));
        dataLoadButton.setToolTipText("Öffnet ein Dokument");
        dataLoadButton.addActionListener(listener);
        toolBar.add(dataLoadButton);

        //Symbolleiste Speichern-Icon
        JButton dataSaveButton = new JButton();
        dataSaveButton.setActionCommand("save");
        dataSaveButton.setIcon(new ImageIcon("icon/save.png"));
        dataSaveButton.setToolTipText("Speichert das Dokument");
        dataSaveButton.addActionListener(listener);
        toolBar.add(dataSaveButton);

        //Symbolleiste Schrift-vergrößern-Icon
        JButton increaseFontButton = new JButton();
        increaseFontButton.setActionCommand("increase");
        increaseFontButton.setIcon(new ImageIcon("icon/increaseFont.png"));
        increaseFontButton.setToolTipText("Schrift vergrößern");
        increaseFontButton.addActionListener(listener);
        toolBar.add(increaseFontButton);

        //Symbolleiste Schrift-verkleinern-Icon
        JButton decreaseFontButton = new JButton();
        decreaseFontButton.setActionCommand("decrease");
        decreaseFontButton.setIcon(new ImageIcon("icon/decreaseFont.png"));
        decreaseFontButton.setToolTipText("Schrift verkleinern");
        decreaseFontButton.addActionListener(listener);
        toolBar.add(decreaseFontButton);

        //Symbolleiste Suchen-Icon
        JButton findButton = new JButton();
        findButton.setActionCommand("find");
        findButton.setIcon(new ImageIcon("icon/find.png"));
        findButton.setToolTipText("Suche");
        findButton.addActionListener(listener);
        toolBar.add(findButton);

        //Symbolleiste Beenden-Icon
        JButton dataExitButton = new JButton();
        dataExitButton.setActionCommand("exit");
        dataExitButton.setIcon(new ImageIcon("icon/exit.png"));
        dataExitButton.setToolTipText("Beendet den Editor");
        dataExitButton.addActionListener(listener);
        toolBar.add(dataExitButton);

        return (toolBar);
    }

    //Status-Menüleiste für Wortzähler
    private void statusMenu() {
        JPanel status = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wordCountLabel = new JLabel("Wörter: 0");

        //Hinzufügen der Komponenten
        status.add(wordCountLabel);
        add(status, BorderLayout.SOUTH);

        //Document Listener zum Auslösen von Updates bei Wortzählung
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateWordCount();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                updateWordCount();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateWordCount();
            }
        });
    }

    //Methode zum Aktualisieren der Wortzählung
    private void updateWordCount() {
        int count = Actions.countWords(textArea);
        wordCountLabel.setText("Wörter: " + count);
    }

    //Methode zum Aktualisieren des Fenstertitels (Dateiname)
    private void updateWindowTitle () {
        if (currentFile != null) {
            setTitle("TextEditor - " + currentFile.getName());
        }
        else setTitle("TextEditor - Unbenannt");
    }
}
