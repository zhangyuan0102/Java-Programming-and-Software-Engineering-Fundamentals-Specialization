
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        // initialize records to an empty ArrayList
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        // create a FileResource
        FileResource fr = new FileResource(filename);
        // iterate over all the lines in the file
        for (String line : fr.lines()) {
            // create a LogEntry
            LogEntry logEntry = WebLogParser.parseEntry(line);
            // store it in the records ArrayList
            records.add(logEntry);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            // Use the toString method to print the log entry
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        HashSet<String> uniqueIPs = new HashSet<String>();
        for (LogEntry le : records) {
            uniqueIPs.add(le.getIpAddress());
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPsOnDay = new ArrayList<String>();
        HashSet<String> uniqueIPs = new HashSet<String>();
        for (LogEntry le : records) {
            String dateStr = le.getAccessTime().toString();
            if (dateStr.contains(someday) && !uniqueIPs.contains(le.getIpAddress())) {
                uniqueIPsOnDay.add(le.getIpAddress());
                uniqueIPs.add(le.getIpAddress());
            }
        }
        return uniqueIPsOnDay;
    }

    public int countUniqueIPsInRange(int low, int high) {
        HashSet<String> uniqueIPsInRange = new HashSet<String>();
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode >= low && statusCode <= high) {
                uniqueIPsInRange.add(le.getIpAddress());
            }
        }
        return uniqueIPsInRange.size();
    }
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int maxVisits = 0;
        for (int visits : counts.values()) {
            if (visits > maxVisits) {
                maxVisits = visits;
            }
        }
        return maxVisits;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> iPs = new ArrayList<String>();
        int maxVisits = mostNumberVisitsByIP(counts);
        for (String ip : counts.keySet()) {
            if (counts.get(ip) == maxVisits) {
                iPs.add(ip);
            }
        }
        return iPs;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> daysIPs = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            String dateStr = le.getAccessTime().toString().substring(4, 10);
            String ip = le.getIpAddress();
            if (!daysIPs.containsKey(dateStr)) {
                daysIPs.put(dateStr, new ArrayList<String>());
            }
            daysIPs.get(dateStr).add(ip);
        }
        return daysIPs;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> daysIPs) {
        String dayWithMost = "";
        int maxIPs = 0;
        for (String day : daysIPs.keySet()) {
            int currentSize = daysIPs.get(day).size();
            if (currentSize > maxIPs) {
                maxIPs = currentSize;
                dayWithMost = day;
            }
        }
        return dayWithMost;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> daysIPs, String day) {
        ArrayList<String> ipsOnDay = daysIPs.get(day);
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (String ip : ipsOnDay) {
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return iPsMostVisits(counts);
    }
}
  
     
