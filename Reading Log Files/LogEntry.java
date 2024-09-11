
/**
 * 在这里给出对类 ReadingWebLogs 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.Date;

public class LogEntry {
    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;

    public LogEntry(String ip, Date time, String req, int status, int bytes) {
        ipAddress = ip;
        accessTime = time;
        request = req;
        statusCode = status;
        bytesReturned = bytes;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public String getRequest() {
        return request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getBytesReturned() {
        return bytesReturned;
    }

    public String toString() {
        return ipAddress + " " + accessTime + " " + request + " " + statusCode + " " + bytesReturned;
    }

    // Add the getInfo method
    public String getInfo() {
        return "IP Address: " + ipAddress + ", Access Time: " + accessTime + ", Request: " + request + 
               ", Status Code: " + statusCode + ", Bytes Returned: " + bytesReturned;
    }
}
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
            // Use the getInfo method to print the log entry
            System.out.println(le.getInfo());
        }
    }
}
