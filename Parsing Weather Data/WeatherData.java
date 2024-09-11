
/**
 * 在这里给出对类 coldest_temperatures 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import org.apache.commons.csv.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import edu.duke.*;

public class WeatherData {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRecord = null;

        for (CSVRecord current : parser) {
            double currentTemp = Double.parseDouble(current.get("TemperatureF"));
            if (currentTemp == -9999) {
                continue; // skip this record
            }
            if(coldestRecord == null){
                coldestRecord = current ;
            }
            else{
                double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));
                if(currentTemp<coldestTemp){
                    coldestRecord = current;
                }
            }
        }
        return coldestRecord;
    }

    public CSVRecord fileWithColdestTemperature() {
        CSVRecord coldestinfile = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f :dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            if(coldestinfile == null){
                coldestinfile = current;
            }
            else{
                double currentTemp = Double.parseDouble(current.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestinfile.get("TemperatureF"));
                if(currentTemp<coldestTemp){
                    coldestinfile = current;
                }                
            }

        }

        return coldestinfile ;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidity = null;

        for (CSVRecord current : parser) {
            String currentHumidityStr = current.get("Humidity");
            if (currentHumidityStr.equals("N/A")) {
                continue; // skip this record
            }

            double currentHumidity = Double.parseDouble(currentHumidityStr);
            if (lowestHumidity == null) {
                lowestHumidity = current;
            } else {
                double lowestHumidityVal = Double.parseDouble(lowestHumidity.get("Humidity"));
                if (currentHumidity < lowestHumidityVal) {
                    lowestHumidity = current;
                }
            }
        }
        return lowestHumidity;
    }

    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumidityRecord = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f :dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            if(lowestHumidityRecord == null){
                lowestHumidityRecord = current;
            }
            else{
                double currenthum = Double.parseDouble(current.get("Humidity"));
                double lowesthum = Double.parseDouble(lowestHumidityRecord.get("Humidity"));
                if(lowesthum>currenthum){
                    lowestHumidityRecord = current;
                }                
            }

        }

        return lowestHumidityRecord;
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double totalTemp = 0.0;
        int count = 0;

        for (CSVRecord current : parser) {
            String tempStr = current.get("TemperatureF");
            double temp = Double.parseDouble(tempStr);
            totalTemp += temp;
            count++;

        }
        return totalTemp / count;
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double totalTemp = 0.0;
        int count = 0;
        for (CSVRecord current: parser) {
            String humidityStr = current.get("Humidity");
            String tempStr = current.get("TemperatureF");

            if (!humidityStr.equals("N/A")) {

                double humidity = Double.parseDouble(humidityStr);
                double temp = Double.parseDouble(tempStr);

                if (humidity >= value) {
                    totalTemp += temp;
                    count++;
                }

            }
        }

        return totalTemp / count;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        String timeField = coldest.isMapped("TimeEST") ? "TimeEST" : "TimeEDT";
        System.out.println("Coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get(timeField));
    }

    public void testFileWithColdestTemperature() {
            
        CSVRecord coldestinfile = fileWithColdestTemperature();
        System.out.println("coldest temperature was"+ coldestinfile.get("TemperatureF")+"at"+coldestinfile.get("DateUTC"));
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-07-22.csv");
        CSVRecord lowest = lowestHumidityInFile (fr.getCSVParser());
        System.out.println("lowest humidity was"+lowest.get("Humidity")+"at"+lowest.get("DateUTC"));        

    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestinfile = lowestHumidityInManyFiles();
        System.out.println("lowest humidity was"+ lowestinfile.get("Humidity")+"at"+ lowestinfile.get("DateUTC"));
    }
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource("nc_weather/2013/weather-2013-08-10.csv");
        double avgtemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average Temperature was"+ avgtemp);
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource("nc_weather/2013/weather-2013-09-02.csv");
        double avgtemp = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
        System.out.println("Average Temperature was"+ avgtemp);

    }
    public void main(String[] args) {
        testColdestHourInFile();
        testFileWithColdestTemperature();
        testLowestHumidityInManyFiles();
        testAverageTemperatureInFile();
        testAverageTemperatureWithHighHumidityInFile();
    }
}

