
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // Sort the quakeData based on distance to the current location
        Collections.sort(quakeData, new Comparator<QuakeEntry>() {
            public int compare(QuakeEntry q1, QuakeEntry q2) {
                double distance1 = q1.getLocation().distanceTo(current);
                double distance2 = q2.getLocation().distanceTo(current);
                return Double.compare(distance1, distance2);
            }
        });
        
        // Add the closest howMany quakes to the ret list
        for (int i = 0; i < Math.min(howMany, quakeData.size()); i++) {
            ret.add(quakeData.get(i));
        }
        
        return ret;
    }
    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,10);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
