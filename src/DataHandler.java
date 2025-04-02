import java.util.ArrayList;

public class DataHandler {
    
    private ArrayList<String> data;

    public DataHandler(ArrayList<String> data) {
        this.data = data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void frequencyTable() {
        ArrayList<String> uniqueWords = new ArrayList<>();
        ArrayList<Integer> wordFrequency = new ArrayList<>();
        for (String word : data) {
            if (!uniqueWords.contains(word)) {
                uniqueWords.add(word);
                wordFrequency.add(1);
            } else {
                int index = uniqueWords.indexOf(word);
                wordFrequency.set(index, wordFrequency.get(index) + 1);
            }
        }
        for (int i = 0; i < uniqueWords.size(); i++) {
            System.out.println(uniqueWords.get(i) + " : " + wordFrequency.get(i));
        }
       
    }
}