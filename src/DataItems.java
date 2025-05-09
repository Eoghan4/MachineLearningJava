/************************************************************
 * DataItems Class                                          *
 *                                                          *
 * Eoghan McGough                                           *
 *                                                          *
 * This class represents individual data items with their   *
 * attributes, including name, yes/no counts, total, and    *
 * percentage calculations.                                 *
 ************************************************************/

public class DataItems {

    // Attributes
    private String name;
    private int yes;
    private int no;
    private int total;
    private double percentage;

    // Constructor
    public DataItems(String name, int yes, int no) {
        this.name = name;
        this.yes = yes;
        this.no = no;
        this.total = yes + no;
        calculatePercentage();
    }

    // Methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYes() {
        return yes;
    }

    public void setYes(int yes) {
        this.yes = yes;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void incrementYes() {
        this.yes++;
        this.total++;
        calculatePercentage();
    }

    public void incrementNo() {
        this.no++;
        this.total++;
        calculatePercentage();
    }

    public void calculatePercentage() {
        if (total > 0) {
            this.percentage = ((double) yes / total) * 100;
        } else {
            this.percentage = 0;
        }
    }
}
