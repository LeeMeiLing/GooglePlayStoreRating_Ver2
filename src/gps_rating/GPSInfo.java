package gps_rating;

import java.util.LinkedList;
import java.util.List;

public class GPSInfo {
    
    private String category;
    private double highestRating;
    private double lowestRating;
    private double averageRating;
    private double accumRating;
    private List<String> highestRatedApp = new LinkedList<>();
    private List<String> lowestRatedApp = new LinkedList<>();
    private double count;


    public GPSInfo(String category) {
        this.category = category;
        this.highestRating = 0d;
        this.lowestRating = 5d;
        this.averageRating = 0d;
        this.count = 0;
    }

    public String getCategory() {
        return category;
    }

    public double getHighestRating() {
        return highestRating;
    }

    public void setHighestRating(String appName, double rating) {
        if (rating > this.highestRating) {
            this.highestRating = rating;
            //replace list
            this.highestRatedApp.clear();
            this.highestRatedApp.add(appName);

        }else if ( rating == this.highestRating){
            // append to list
            this.highestRatedApp.add(appName);
        }

    }

    public double getLowestRating() {
        return lowestRating;
    }

    public void setLowestRating(String appName, double rating) {
        if (rating < this.lowestRating) {
            this.lowestRating = rating;
            // replace list
            this.lowestRatedApp.clear();
            this.lowestRatedApp.add(appName);

        } else if(rating == this.lowestRating){
            // append to list
            this.lowestRatedApp.add(appName);
        }
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double rating) {
        this.count++;
        this.accumRating = rating + this.accumRating;
        this.averageRating =  this.accumRating / this.count ;
        // System.out.println("rating = " + rating);
        // System.out.println("accumRating = " + this.accumRating);
        // System.out.println("count = " + this.count);
        // System.out.println("averageRating = " +this.averageRating);
    }

    public List<String> getHighestRatedApp() {
        return highestRatedApp;
    }

    public List<String> getLowestRatedApp() {
        return lowestRatedApp;
    }

    @Override
    public String toString() {
        return "GPSInfo [category=" + category + " :\n" + "highestRating=" + highestRating + "\n"+ "lowestRating=" + lowestRating
        + "\n" + "averageRating=" + averageRating + "\n" + "highestRatedApp=" + highestRatedApp + "\n" + "lowestRatedApp="
                + lowestRatedApp + "\n" + "# App in this category =" + count + "]";
    }

}
