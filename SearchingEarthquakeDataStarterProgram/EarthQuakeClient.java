import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
       for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            double depth = qe.getDepth();
            if (depth > minDepth && depth < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            String title = qe.getInfo();
            if (where.equals("start") && title.startsWith(phrase)) {
                answer.add(qe);
            } else if (where.equals("end") && title.endsWith(phrase)) {
                answer.add(qe);
            } else if (where.equals("any") && title.contains(phrase)) {
                answer.add(qe);
            }
        }
        return answer;
    }
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> bigQuakes = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : bigQuakes) {
            System.out.println(qe);
        }
        System.out.println("Found " + bigQuakes.size() + " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> closeQuakes = filterByDistanceFrom(list, 1000 * 1000, city); // 1000 kilometers
        for (QuakeEntry qe : closeQuakes) {
            double distance = qe.getLocation().distanceTo(city) / 1000; // Convert meters to kilometers
            System.out.printf("%.2f km %s\n", distance, qe.getInfo());
        }
        System.out.println("Found " + closeQuakes.size() + " quakes that match that criteria");
    }
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        ArrayList<QuakeEntry> depthQuakes = filterByDepth(list, minDepth, maxDepth);
        for (QuakeEntry qe : depthQuakes) {
            System.out.println(qe);
        }
        System.out.println("Found " + depthQuakes.size() + " quakes that match that criteria");
    }
    public void quakesByPhrase(String where, String phrase) {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

    System.out.println("Find quakes with phrase \"" + phrase + "\" at " + where);
        ArrayList<QuakeEntry> phraseQuakes = filterByPhrase(list, where, phrase);
        for (QuakeEntry qe : phraseQuakes) {
            System.out.println(qe);
        }
        System.out.println("Found " + phraseQuakes.size() + " quakes that match that criteria");
    }
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    public static void main(String[] args) {
    EarthQuakeClient client = new EarthQuakeClient();
    client.quakesByPhrase("start", "Quarry Blast");
    client.quakesByPhrase("end", "Alaska"); 
    client.quakesByPhrase("any", "Can");    
    }
}
