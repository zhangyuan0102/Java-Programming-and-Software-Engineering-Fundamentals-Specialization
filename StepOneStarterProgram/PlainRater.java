
/**
 * Write a description of class Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class PlainRater implements Rater {
    private String myID;
    private ArrayList<Rating> myRatings;

    public PlainRater(String id) {
        myID = id;
        myRatings = new ArrayList<Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.add(new Rating(item, rating));
    }

    public boolean hasRating(String item) {
        for (Rating r : myRatings) {
            if (r.getItem().equals(item)) {
                return true;
            }
        }
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        for (Rating r : myRatings) {
            if (r.getItem().equals(item)) {
                return r.getValue();
            }
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (Rating r : myRatings) {
            list.add(r.getItem());
        }
        return list;
    }
}
