
/**
 * Write a description of class DifferentSorters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
public class DifferentSorters {
    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample2.atom";  // Specify your data file here
        ArrayList<QuakeEntry> list = parser.read(source);

        Collections.sort(list);


        if (list.size() > 600) {
            System.out.println("Quake entry at position 51:");
            System.out.println(list.get(600));
        }
    }   
    public void sortByTitleAndDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample1.atom";  // Specify your data file here
        ArrayList<QuakeEntry> list = parser.read(source);

        Collections.sort(list, new TitleAndDepthComparator());


        if (list.size() > 500) {
            System.out.println("Quake entry at position 51:");
            System.out.println(list.get(500));
        }
    }
    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }
    public void sortByLastWordInTitleThenByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample1.atom";  // Specify your data file here
        ArrayList<QuakeEntry> list = parser.read(source);

        Collections.sort(list, new TitleLastAndMagnitudeComparator());

        if (list.size() > 500) {
            System.out.println("Quake entry at position 51:");
            System.out.println(list.get(500));
        }
    }
    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }
}
