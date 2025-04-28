/************************************************************
 * Screen Class                                              *
 *                                                          *
 * Eoghan McGough                                           *
 *                                                          *
 * This class creates and manages the main GUI window of    *
 * the application, including file selection, text display, *
 * and button controls.                                     *
 ************************************************************/

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class Screen extends JFrame {
    
    // Attributes
    private final String title;
    private final int width;
    private final int height;
    private JTextArea textArea;
    private JLabel resultLabel;
    private JTextField textField1, textField2, textField3, textField4;
    private JRadioButton yesRadioButton, noRadioButton;
    private DataHandler dataHandler;
    
    // Constructor
    public Screen(String title, int width, int height) {
        super(title);
        this.title = title;
        this.width = width;
        this.height = height;
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialize components
        textArea = new JTextArea();
        textArea.setEditable(false);
        
        resultLabel = new JLabel(" ");
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        textField3 = new JTextField(10);
        textField4 = new JTextField(10);
        yesRadioButton = new JRadioButton("Yes");
        noRadioButton = new JRadioButton("No");
        
        // Add action listeners to make radio buttons mutually exclusive
        yesRadioButton.addActionListener(_ -> {
            if (yesRadioButton.isSelected()) {
                noRadioButton.setSelected(false);
            }
        });
        
        noRadioButton.addActionListener(_ -> {
            if (noRadioButton.isSelected()) {
                yesRadioButton.setSelected(false);
            }
        });
    }
    
    // Methods
    public void createScreen() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Top panel for file selection and result display
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel filePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel resultPanel = new JPanel();

        // File indicator label
        javax.swing.JLabel fileLabel = new javax.swing.JLabel("No File Selected.");
        JButton openFileButton = new JButton("Select Training Data");
        filePanel.add(fileLabel);
        filePanel.add(openFileButton);

        // Result label
        resultPanel.add(resultLabel);

        topPanel.add(filePanel, BorderLayout.NORTH);
        topPanel.add(resultPanel, BorderLayout.SOUTH);

        // Center panel for input fields
        JPanel centerPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // 4 rows, 2 columns, 10px gap

        // Add 4 text fields for data input
        javax.swing.JLabel label1 = new javax.swing.JLabel("Field 1:");
        centerPanel.add(label1);
        centerPanel.add(textField1);

        javax.swing.JLabel label2 = new javax.swing.JLabel("Field 2:");
        centerPanel.add(label2);
        centerPanel.add(textField2);

        javax.swing.JLabel label3 = new javax.swing.JLabel("Field 3:");
        centerPanel.add(label3);
        centerPanel.add(textField3);

        javax.swing.JLabel label4 = new javax.swing.JLabel("Field 4:");
        centerPanel.add(label4);
        centerPanel.add(textField4);

        // Bottom panel for buttons and toggle switch
        JPanel togglePanel = new JPanel(new GridLayout(1, 2, 10, 10)); // 1 row, 2 columns, 10px gap

        javax.swing.JLabel toggleLabel = new javax.swing.JLabel("Label (Yes/No):");
        togglePanel.add(toggleLabel);
        togglePanel.add(yesRadioButton);
        togglePanel.add(noRadioButton);

        mainPanel.add(togglePanel, BorderLayout.EAST);
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 10, 10)); // 1 row, 3 columns, 10px gap

        JButton trainButton = new JButton("Train");
        JButton submitButton = new JButton("Predict");
        JButton addButton = new JButton("Add Row");

        bottomPanel.add(trainButton);
        bottomPanel.add(submitButton);
        bottomPanel.add(addButton);

        // Add action listeners
        openFileButton.addActionListener(_ -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                System.out.println("Selected file: " + fileChooser.getSelectedFile().getAbsolutePath());
                FileHandler fileHandler = new FileHandler(fileChooser.getSelectedFile().getAbsolutePath());
                fileLabel.setText("File Selected.");

                dataHandler = new DataHandler(fileHandler.readFile());
                dataHandler.trainData();

                // Set the labels as the features
                label1.setText(dataHandler.getData().get(0).split(",")[0] + " : ");
                label2.setText(dataHandler.getData().get(0).split(",")[1] + " : ");
                label3.setText(dataHandler.getData().get(0).split(",")[2] + " : ");
                label4.setText(dataHandler.getData().get(0).split(",")[3] + " : ");
                toggleLabel.setText(dataHandler.getData().get(0).split(",")[4] + " : ");
            }
        });

        trainButton.addActionListener(_ -> {
            dataHandler.trainData();
            dataHandler.frequencyTable();
            textArea.setText(dataHandler.returnDataItems());
            resultLabel.setText("Data trained successfully.");
        });

        submitButton.addActionListener(_ -> {
            System.out.println("Predict button clicked.");

            String permutation = textField1.getText() + "," + textField2.getText() + "," + textField3.getText() + "," + textField4.getText();
            for (DataItems item : dataHandler.getDataItems()) {
                if (item.getName().equals(permutation)) {
                    if (item.getPercentage() >= 50) {
                        resultLabel.setText("Yes - Confidence = " + item.getPercentage() + "%");
                        break;
                    } else {
                        resultLabel.setText("No - Confidence =  " + (100 - item.getPercentage()) + "%");
                        break;
                    }
                } else {
                    resultLabel.setText("Please ensure data entered is correct.");
                }
            }
        });

        addButton.addActionListener(_ -> {
            String newRow = textField1.getText() + "," + textField2.getText() + "," + textField3.getText() + "," + textField4.getText() + "," + (yesRadioButton.isSelected() ? "yes" : "no");
            String newRowName = textField1.getText() + "," + textField2.getText() + "," + textField3.getText() + "," + textField4.getText();
            for (DataItems item : dataHandler.getDataItems()) {
                if (item.getName().equals(newRowName)) {
                    dataHandler.addData(newRow);
                    resultLabel.setText("Row added successfully.");
                    break;
                }
                else {
                    resultLabel.setText("Please ensure data entered is correct.");
                }
            }
            System.out.println("Add Row button clicked.");
            
        });

        // Add panels to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.setTitle(this.title);
        this.setSize(this.width, this.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}