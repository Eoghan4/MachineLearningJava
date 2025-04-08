/************************************************************
 * FileHandler Class                                        *
 *                                                          *
 * Eoghan McGough                                           *
 *                                                          *
 * This class handles file operations including reading     *
 * data from files and managing file connections.           *
 ************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    // Attributes
    private String fileName;
    private File file;
    private Scanner scanner;

    // Constructor
    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    // Methods

    public void connectToFile(){
        this.file = new File(this.fileName);
    }

    public ArrayList<String> readFile(){
        connectToFile(); // Ensure file is initialized
        ArrayList<String> lines = new ArrayList<>();
        try{
            this.scanner = new Scanner(this.file);
            this.scanner.useDelimiter(",|\\n");
            while(scanner.hasNextLine()){
                lines.add(scanner.nextLine());
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found.");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return lines;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
