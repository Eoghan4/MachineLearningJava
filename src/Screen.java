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

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class Screen extends JFrame {
    
    // Attributes
    private final String title;
    private final int width;
    private final int height;
    DataHandler dataHandler;
    
    // Constructor
    public Screen(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }
    
    // Methods
    public void createScreen(){
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Top panel
        JPanel topPanel = new JPanel();
        
        // Bottom panel
        JPanel bottomPanel = new JPanel();
        // Add 4 text fields for data input
        JPanel inputPanel1 = new JPanel();
        javax.swing.JLabel label1 = new javax.swing.JLabel("Field 1:");
        inputPanel1.add(label1);
        JTextArea textField1 = new JTextArea(1, 10);
        textField1.setBackground(java.awt.Color.LIGHT_GRAY);
        inputPanel1.add(textField1);

        JPanel inputPanel2 = new JPanel();
        javax.swing.JLabel label2 = new javax.swing.JLabel("Field 2:");
        inputPanel2.add(label2);
        JTextArea textField2 = new JTextArea(1, 10);
        textField2.setBackground(java.awt.Color.LIGHT_GRAY);
        inputPanel2.add(textField2);

        JPanel inputPanel3 = new JPanel();
        javax.swing.JLabel label3 = new javax.swing.JLabel("Field 3:");
        inputPanel3.add(label3);
        JTextArea textField3 = new JTextArea(1, 10);
        textField3.setBackground(java.awt.Color.LIGHT_GRAY);
        inputPanel3.add(textField3);

        JPanel inputPanel4 = new JPanel();
        javax.swing.JLabel label4 = new javax.swing.JLabel("Field 4:");
        inputPanel4.add(label4);
        JTextArea textField4 = new JTextArea(1, 10);
        textField4.setBackground(java.awt.Color.LIGHT_GRAY);
        inputPanel4.add(textField4);

        bottomPanel.add(inputPanel1);
        

        bottomPanel.add(inputPanel2);
        
        bottomPanel.add(inputPanel3);
        
        bottomPanel.add(inputPanel4);       
        
        //Add lbael for result
        javax.swing.JLabel resultLabel = new javax.swing.JLabel("");

        //Add file indicator label
        javax.swing.JLabel fileLabel = new javax.swing.JLabel("No File Selected.");
        
        
        // Add file selector
        JFileChooser fileChooser = new JFileChooser();
        JButton openFileButton = new JButton("Select Training Data");
        openFileButton.addActionListener(_ -> {
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println("Selected file: " + fileChooser.getSelectedFile().getAbsolutePath());
            FileHandler fileHandler = new FileHandler(fileChooser.getSelectedFile().getAbsolutePath());
            fileLabel.setText("File Selected.");
            
            dataHandler = new DataHandler(fileHandler.readFile());
            
            dataHandler.frequencyTable();
            //textField.setText(dataHandler.returnDataItems());
            dataHandler.printDataItems();

            //Set the labels as the features
            System.out.println(dataHandler.getDataItems().get(0).getName().split(",")[0]);
            label1.setText(dataHandler.getDataItems().get(0).getName().split(",")[0] + " : ");
            label2.setText(dataHandler.getDataItems().get(0).getName().split(",")[1] + " : ");
            label3.setText(dataHandler.getDataItems().get(0).getName().split(",")[2] + " : ");
            label4.setText(dataHandler.getDataItems().get(0).getName().split(",")[3] + " : ");

            }
        });

        // Add buttons
        JButton Submit = new JButton("Submit Data");
        bottomPanel.add(Submit);
        
        Submit.addActionListener(_ -> {
            System.out.println("Submit button clicked.");
            String permutation = new String();
            permutation = textField1.getText() + "," + textField2.getText() + "," + textField3.getText() + "," + textField4.getText();
            for(DataItems item : dataHandler.getDataItems()){
                if(item.getName().equals(permutation)){
                    if(item.getPercentage() > 50){
                        resultLabel.setText("Yes - Confidence = " + item.getPercentage() + "%");
                        break;
                    } else {
                        resultLabel.setText("No - Confidence =  " + (100 - item.getPercentage()) + "%");
                        break;
                    }
                }
                else {
                    resultLabel.setText("Please ensure data entered is correct.");
                }
            }
        });
        
        topPanel.add(fileLabel);
        topPanel.add(openFileButton);
        topPanel.add(resultLabel);

        // Add 2 pannels to the main panel
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        this.add(panel);
        this.setTitle(this.title);
        this.setSize(this.width, this.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
}