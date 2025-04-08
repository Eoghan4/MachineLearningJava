/************************************************************
 * DataHandler Class                                        *
 *                                                          *
 * Eoghan McGough                                           *
 *                                                          *
 * This class processes and analyzes data from input files, *
 * creating frequency tables and managing data items.       *
 ************************************************************/

import java.util.ArrayList;

public class DataHandler {
    
    private ArrayList<String> data;
    private ArrayList<DataItems> dataItems = new ArrayList<>();

    public DataHandler(ArrayList<String> data) {
        this.data = data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public ArrayList<DataItems> getDataItems() {
        return dataItems;
    }

    public void addDataItems(DataItems dataItem) {
        this.dataItems.add(dataItem);
    }

    public void frequencyTable() {
        ArrayList<String> uniqueCombinations = new ArrayList<>();
        ArrayList<Integer> yesFrequency = new ArrayList<>();
        ArrayList<Integer> noFrequency = new ArrayList<>();

        for (String line : data) {
            String[] parts = line.split(",");
            if (parts.length < 5) continue; // Skip invalid lines
            String combination = String.join(",", parts[0], parts[1], parts[2], parts[3]);
            String result = parts[4].trim().toLowerCase();

            if (!uniqueCombinations.contains(combination)) {
                uniqueCombinations.add(combination);
                yesFrequency.add(result.equals("yes") ? 1 : 0);
                noFrequency.add(result.equals("no") ? 1 : 0);
            } else {
                int index = uniqueCombinations.indexOf(combination);
                if (result.equals("yes")) {
                    yesFrequency.set(index, yesFrequency.get(index) + 1);
                } else if (result.equals("no")) {
                    noFrequency.set(index, noFrequency.get(index) + 1);
                }
            }
        }

        for (int i = 0; i < uniqueCombinations.size(); i++) {
            //System.out.println(uniqueCombinations.get(i) + " -> Yes: " + yesFrequency.get(i) + ", No: " + noFrequency.get(i));
            addDataItems(new DataItems(uniqueCombinations.get(i), yesFrequency.get(i), noFrequency.get(i)));
        }
    }

    public void printDataItems() {
        for (DataItems item : dataItems) {
            System.out.println(item.getName() + " -> Yes: " + item.getYes() + ", No: " + item.getNo() + ", Total: " + item.getTotal() + ", Percentage: " + item.getPercentage() + "%");
        }
    }
}