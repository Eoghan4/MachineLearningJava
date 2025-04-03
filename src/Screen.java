import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class Screen extends JFrame {
    
    // Attributes
    private final String title;
    private final int width;
    private final int height;
    
    // Constructor
    public Screen(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }
    
    // Methods
    public void createScreen(){
        
        JPanel panel = new JPanel();
        
        // Add text area
        JTextArea textField = new JTextArea(20, 56); // 20 rows, 60 columns
        textField.setLineWrap(true);
        textField.setWrapStyleWord(true);
        textField.setEditable(false);
        panel.add(textField);

        // Add buttons
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        panel.add(button1);
        panel.add(button2);
        
        // Add file selector
        JFileChooser fileChooser = new JFileChooser();
        JButton openFileButton = new JButton("Open File");
        openFileButton.addActionListener(_ -> {
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println("Selected file: " + fileChooser.getSelectedFile().getAbsolutePath());
            FileHandler fileHandler = new FileHandler(fileChooser.getSelectedFile().getAbsolutePath());
            textField.setText(fileHandler.readFile().toString());
            DataHandler dataHandler = new DataHandler(fileHandler.readFile());
            dataHandler.frequencyTable();
            dataHandler.printDataItems();
            }
        });
        panel.add(openFileButton);

        this.add(panel);
        this.setTitle(this.title);
        this.setSize(this.width, this.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
}
