
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // create a LogAnalyzer object
        LogAnalyzer la = new LogAnalyzer();
        // call readFile on the data file short-test_log
        la.readFile("short-test_log");
        // call printAll to print all the web logs
        la.printAll();
    }
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("Number of unique IPs: " + uniqueIPs);
    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("Unique IPs on Sep 27: " + uniqueIPs.size());
        for (String ip : uniqueIPs) {
            System.out.println(ip);
        }
        uniqueIPs = la.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("Unique IPs on Sep 30: " + uniqueIPs.size());
        for (String ip : uniqueIPs) {
            System.out.println(ip);
        }
    }

    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPsInRange(400,499);
        System.out.println("Unique IPs in range 400,499: " + uniqueIPs);
    }
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println("Visits per IP: " + counts);
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int maxVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("Most number of visits by IP: " + maxVisits);
    }

    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> iPs = la.iPsMostVisits(counts);
        System.out.println("IPs with most visits: " + iPs);
    }

    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> daysIPs = la.iPsForDays();
        System.out.println("IPs for days: " + daysIPs);
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> daysIPs = la.iPsForDays();
        String dayWithMost = la.dayWithMostIPVisits(daysIPs);
        System.out.println("Day with most IP visits: " + dayWithMost);
    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> daysIPs = la.iPsForDays();
        ArrayList<String> iPs = la.iPsWithMostVisitsOnDay(daysIPs, "Sep 29");
        System.out.println("IPs with most visits on Sep 29: " + iPs);
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.testLogAnalyzer();
        tester.testUniqueIP();
        tester.testPrintAllHigherThanNum();
        tester.testUniqueIPVisitsOnDay();
        tester.testCountUniqueIPsInRange();
        tester.testCountVisitsPerIP();
        tester.testMostNumberVisitsByIP();
        tester.testIPsMostVisits();
    }
}
