
/**
 * 在这里给出对类 parseWeatherData 的描述。
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
public class parseWeatherData {
    static class ColdestResult {
        private CSVRecord record;
        private int count;

        public ColdestResult(CSVRecord record, int count) {
            this.record = record;
            this.count = count;
        }

        public CSVRecord getRecord() {
            return record;
        }

        public int getCount() {
            return count;
        }
    }
    public static ColdestResult coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRecord = null;
        double coldestTemp = Double.POSITIVE_INFINITY;
        int count = 0;

        for (CSVRecord record : parser) {
            String tempStr = record.get("TemperatureF");
            try {
                double temp = Double.parseDouble(tempStr);
                if (temp != -9999) {
                    if (temp < coldestTemp) {
                        coldestTemp = temp;
                        coldestRecord = record;
                        count = 1; // Reset count for the new coldest temperature
                    } else if (temp == coldestTemp) {
                        count++;
                    }
                }
            } catch (NumberFormatException e) {
                // Ignore invalid temperature values
            }
        }

        return new ColdestResult(coldestRecord, count);
    }

    public static String fileWithColdestTemperature(List<String> files) {
        String coldestFile = null;
        double coldestTemp = Double.POSITIVE_INFINITY;

        for (String file : files) {
            try {
                Reader reader = Files.newBufferedReader(Paths.get(file));
                CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
                ColdestResult result = coldestHourInFile(parser);
                CSVRecord coldestRecord = result.getRecord();

                if (coldestRecord != null) {
                    double temp = Double.parseDouble(coldestRecord.get("TemperatureF"));
                    if (temp < coldestTemp) {
                        coldestTemp = temp;
                        coldestFile = file;
                    }
                }

                parser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return coldestFile;
    }

    public static CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidityRecord = null;
        double lowestHumidity = Double.POSITIVE_INFINITY;

        for (CSVRecord record : parser) {
            String humidityStr = record.get("Humidity");
            if (!humidityStr.equals("N/A")) {
                try {
                    double humidity = Double.parseDouble(humidityStr);
                    if (humidity < lowestHumidity) {
                        lowestHumidity = humidity;
                        lowestHumidityRecord = record;
                    }
                } catch (NumberFormatException e) {
                    // Ignore invalid humidity values
                }
            }
        }

        return lowestHumidityRecord;
    }

    public static CSVRecord lowestHumidityInManyFiles(List<String> files) {
        CSVRecord lowestHumidityRecord = null;
        double lowestHumidity = Double.POSITIVE_INFINITY;

        for (String file : files) {
            try {
                Reader reader = Files.newBufferedReader(Paths.get(file));
                CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
                CSVRecord currentRecord = lowestHumidityInFile(parser);

                if (currentRecord != null) {
                    double currentHumidity = Double.parseDouble(currentRecord.get("Humidity"));
                    if (currentHumidity < lowestHumidity) {
                        lowestHumidity = currentHumidity;
                        lowestHumidityRecord = currentRecord;
                    }
                }

                parser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lowestHumidityRecord;
    }

    public static double averageTemperatureInFile(CSVParser parser) {
        double totalTemp = 0.0;
        int count = 0;

        for (CSVRecord record : parser) {
            String tempStr = record.get("TemperatureF");
            try {
                double temp = Double.parseDouble(tempStr);
                totalTemp += temp;
                count++;
            } catch (NumberFormatException e) {
                // Ignore invalid temperature values
            }
        }

        return count == 0 ? 0.0 : totalTemp / count;
    }

    public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double totalTemp = 0.0;
        int count = 0;

        for (CSVRecord record : parser) {
            String humidityStr = record.get("Humidity");
            String tempStr = record.get("TemperatureF");

            if (!humidityStr.equals("N/A")) {
                try {
                    double humidity = Double.parseDouble(humidityStr);
                    double temp = Double.parseDouble(tempStr);

                    if (humidity >= value) {
                        totalTemp += temp;
                        count++;
                    }
                } catch (NumberFormatException e) {
                    // Ignore invalid temperature or humidity values
                }
            }
        }

        return count == 0 ? 0.0 : totalTemp / count;
    }
    public static List<String> getFilesFromDirectory(String directoryPath) {
        try (Stream<Path> paths = Files.list(Paths.get(directoryPath))) {
            return paths.filter(Files::isRegularFile)
                        .map(Path::toString)
                        .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
    public static void testColdestHourInFile(String filePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());

            ColdestResult result = coldestHourInFile(parser);
            CSVRecord coldestRecord = result.getRecord();
            int count = result.getCount();

            if (coldestRecord != null) {
                System.out.println("Coldest temperature record:");
                System.out.println("Date and Time: " + coldestRecord.get("DateUTC") + " " + coldestRecord.get("TimeEST"));
                System.out.println("Temperature: " + coldestRecord.get("TemperatureF"));
                System.out.println("Number of occurrences of the coldest temperature: " + count);
            } else {
                System.out.println("No valid temperature data found.");
            }

            parser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testFileWithColdestTemperature(List<String> files) {
        String coldestFile = fileWithColdestTemperature(files);
        if (coldestFile != null) {
            System.out.println("Coldest day was in file " + coldestFile);

            try {
                Reader reader = Files.newBufferedReader(Paths.get(coldestFile));
                CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
                ColdestResult result = coldestHourInFile(parser);
                CSVRecord coldestRecord = result.getRecord();
                double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));

                System.out.println("Coldest temperature on that day was " + coldestTemp);
                System.out.println("All the Temperatures on the coldest day were:");

                // Re-open the parser to iterate over all records again
                parser.close();
                reader = Files.newBufferedReader(Paths.get(coldestFile));
                parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());

                for (CSVRecord record : parser) {
                    String date = record.get("DateUTC");
                    String time = record.get("TimeEST");
                    String temp = record.get("TemperatureF");
                    System.out.println(date + " " + time + ": " + temp);
                }

                parser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No valid temperature data found.");
        }
    }
    public static void testLowestHumidityInFile(String filePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());

            CSVRecord lowestHumidityRecord = lowestHumidityInFile(parser);
            if (lowestHumidityRecord != null) {
                String humidity = lowestHumidityRecord.get("Humidity");
                String dateUTC = lowestHumidityRecord.get("DateUTC");
                System.out.println("Lowest Humidity was " + humidity + " at " + dateUTC);
            } else {
                System.out.println("No valid humidity data found.");
            }

            parser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testLowestHumidityInManyFiles(List<String> files) {
        CSVRecord lowestHumidityRecord = lowestHumidityInManyFiles(files);
        if (lowestHumidityRecord != null) {
            String humidity = lowestHumidityRecord.get("Humidity");
            String dateUTC = lowestHumidityRecord.get("DateUTC");
            System.out.println("Lowest Humidity was " + humidity + " at " + dateUTC);
        } else {
            System.out.println("No valid humidity data found.");
        }
    }

    public static void testAverageTemperatureInFile(String filePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());

            double averageTemp = averageTemperatureInFile(parser);
            System.out.println("Average temperature in file is " + averageTemp);

            parser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testAverageTemperatureWithHighHumidityInFile(String filePath, int value) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());

            double averageTemp = averageTemperatureWithHighHumidityInFile(parser, value);
            if (averageTemp == 0.0) {
                System.out.println("No temperatures with that humidity");
            } else {
                System.out.println("Average Temp when high Humidity is " + averageTemp);
            }

            parser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        testColdestHourInFile("nc_weather/2014/weather-2014-05-01.csv");
        List<String> files = getFilesFromDirectory("nc_weather/2014");
        testFileWithColdestTemperature(files);
        testLowestHumidityInFile("nc_weather/2014/weather-2014-04-01.csv");
        testLowestHumidityInManyFiles(files);
        testAverageTemperatureInFile("nc_weather/2014/weather-2014-06-01.csv");
        testAverageTemperatureWithHighHumidityInFile("nc_weather/2014/weather-2014-03-30.csv", 80);
    }
}
    

