
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    // Method to find the index of the largest depth
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    // Method to sort quakes by largest depth using selection sort
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        int passes = Math.min(70, in.size());
        for (int i = 0; i < passes; i++) {
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i, qmax);
            in.set(maxIdx, qi);
        }
    }
    // Method to perform one pass of bubble sort
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 0; i < quakeData.size() - 1 - numSorted; i++) {
            if (quakeData.get(i).getMagnitude() > quakeData.get(i + 1).getMagnitude()) {
                QuakeEntry temp = quakeData.get(i);
                quakeData.set(i, quakeData.get(i + 1));
                quakeData.set(i + 1, temp);
            }
        }
    }
    
    // Method to sort quakes by magnitude using bubble sort
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        int passes = in.size() - 1;
        for (int i = 0; i < passes; i++) {
            System.out.println("Before pass " + i + ": " + in);
            onePassBubbleSort(in, i);
            System.out.println("After pass " + i + ": " + in);
        }
    }
    // Method to sort quakes by magnitude using bubble sort with check
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int passes = in.size() - 1;
        for (int i = 0; i < passes; i++) {
            onePassBubbleSort(in, i);
            if (checkInSortedOrder(in)) {
                System.out.println("Sorted after " + (i + 1) + " passes.");
                break;
            }
        }
    }

    // Method to check if the list is in sorted order
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i = 0; i < quakes.size() - 1; i++) {
            if (quakes.get(i).getMagnitude() > quakes.get(i + 1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }

    // Method to sort quakes by magnitude using selection sort with check
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int passes = in.size();
        for (int i = 0; i < passes; i++) {
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
            if (checkInSortedOrder(in)) {
                System.out.println("Sorted after " + (i + 1) + " passes.");
                break;
            }
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for " + list.size() + " quakes");    
        sortByMagnitudeWithBubbleSortWithCheck(list); // Test selection sort with check
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
    }
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
