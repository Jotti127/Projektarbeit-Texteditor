package projektarbeit_texteditor;

//importieren Sie die nötigen Klassen
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

//das Swing-Fenster
//die Klasse erbt von der Swing-Klasse JFrame
//JFrame bildet ein Anwendungsfenster ab

public class EditorGui extends JFrame{
    //erstellen Sie Instanzvariablen für die Komponenten
    private JTextArea textArea;

    //Innere Klasse für ActionListener
    class MyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("neu")) {
                Actions.neu(textArea);
            }
            if (e.getActionCommand().equals("beenden")) {
                Actions.exit();
            }
            if (e.getActionCommand().equals("laden")) {
                Actions.loadDialog(textArea);
            }
            if (e.getActionCommand().equals("speichern")) {
                String saveText = textArea.getText();
                Actions.saveDialog(saveText);
            }
            if (e.getActionCommand().equals("increase")) {
                Actions.increaseFontSize(textArea);
            }
            if (e.getActionCommand().equals("decrease")) {
                Actions.decreaseFontSize(textArea);
            }
        }
    }

    public EditorGui(String fenstertitel) {
        super(fenstertitel);

        // Layout für das Fenster
        setLayout(new BorderLayout());

        // Textbereich erstellen
        textArea = new JTextArea();
        textArea.setLineWrap(true); //Zeilenumbruch
        textArea.setWrapStyleWord(true); //Zeilenumbruch nur nach Leerzeichen

       //Scroll Pane für Textbereich
        JScrollPane scrollPane = new JScrollPane(textArea);


        //erzeugen Sie die Komponenten
        //setzen Sie die ActionCommands für die Komponenten
        menu();
        add(symbolleiste(), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        //stellen Sie die Verbindung zum Listener her

        //Fenstergröße für Editor
        setSize(800, 600);
        //das Verhalten beim Schließen setzen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //das Fenster sichtbar machen
        setVisible(true);
    }

    //Menüleiste
    private void menu() {
        JMenuBar menu = new JMenuBar();
        MyListener listener = new MyListener();

        //Menü -> Datei Reiter
        JMenu dateiMenu = new JMenu("Datei");

        //Menü-Item Neu
        JMenuItem dateiNeu = new JMenuItem();
        dateiNeu.setText("Neu");
        dateiNeu.setActionCommand("neu");
        dateiNeu.setIcon(new ImageIcon("icon/new.png"));
        dateiNeu.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
        dateiNeu.addActionListener(listener);
        dateiMenu.add(dateiNeu);

        //Menü-Item Laden
        JMenuItem dateiLaden = new JMenuItem();
        dateiLaden.setText("Laden");
        dateiLaden.setActionCommand("laden");
        dateiLaden.setIcon(new ImageIcon("icon/open.png"));
        dateiLaden.setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.CTRL_DOWN_MASK));
        dateiLaden.addActionListener(listener);
        dateiMenu.add(dateiLaden);

        //Menü-Item Speichern
        JMenuItem dateiSpeichern = new JMenuItem();
        dateiSpeichern.setText("Speichern");
        dateiSpeichern.setActionCommand("speichern");
        dateiSpeichern.setIcon(new ImageIcon("icon/save.png"));
        dateiSpeichern.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        dateiSpeichern.addActionListener(listener);
        dateiMenu.add(dateiSpeichern);

        //Menü-Item Beenden
        JMenuItem dateiBeenden = new JMenuItem();
        dateiBeenden.setText("Beenden");
        dateiBeenden.setActionCommand("beenden");
        dateiBeenden.setIcon(new ImageIcon("icon/exit.png"));
        dateiBeenden.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_DOWN_MASK));
        dateiBeenden.addActionListener(listener);
        dateiMenu.add(dateiBeenden);


        //Menü -> Bearbeiten Reiter
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

        //Hinzufügen der Menü-Reiter
        menu.add(dateiMenu);
        menu.add(editMenu);
        this.setJMenuBar(menu);
    }

    //Symbolleiste
    private JToolBar symbolleiste() {
        JToolBar bar = new JToolBar();
        MyListener listener = new MyListener();

        JButton dateiNeuButton = new JButton();
        dateiNeuButton.setActionCommand("neu");
        dateiNeuButton.setIcon(new ImageIcon("icon/new.png"));
        dateiNeuButton.setToolTipText("Erstellt ein neues Dokument");
        dateiNeuButton.addActionListener(listener);
        bar.add(dateiNeuButton);

        JButton dateiLadenButton = new JButton();
        dateiLadenButton.setActionCommand("laden");
        dateiLadenButton.setIcon(new ImageIcon("icon/open.png"));
        dateiLadenButton.setToolTipText("Öffnet ein Dokument");
        dateiLadenButton.addActionListener(listener);
        bar.add(dateiLadenButton);

        JButton dateiSpeichernButton = new JButton();
        dateiSpeichernButton.setActionCommand("speichern");
        dateiSpeichernButton.setIcon(new ImageIcon("icon/save.png"));
        dateiSpeichernButton.setToolTipText("Speichert das Dokument");
        dateiSpeichernButton.addActionListener(listener);
        bar.add(dateiSpeichernButton);

        JButton increaseFontButton = new JButton();
        increaseFontButton.setActionCommand("increase");
        increaseFontButton.setIcon(new ImageIcon("icon/increaseFont.png"));
        increaseFontButton.setToolTipText("Schrift vergrößern");
        increaseFontButton.addActionListener(listener);
        bar.add(increaseFontButton);

        JButton decreaseFontButton = new JButton();
        decreaseFontButton.setActionCommand("decrease");
        decreaseFontButton.setIcon(new ImageIcon("icon/decreaseFont.png"));
        decreaseFontButton.setToolTipText("Schrift verkleinern");
        decreaseFontButton.addActionListener(listener);
        bar.add(decreaseFontButton);

        JButton dateiBeendenButton = new JButton();
        dateiBeendenButton.setActionCommand("beenden");
        dateiBeendenButton.setIcon(new ImageIcon("icon/exit.png"));
        dateiBeendenButton.setToolTipText("Beendet den Editor");
        dateiBeendenButton.addActionListener(listener);
        bar.add(dateiBeendenButton);

        return (bar);
    }
}
