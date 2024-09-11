
/**
 * 在这里给出对类 ExportData 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class CSVTester {

    public void tester() {
        // 创建 FileResource 实例
        FileResource fr = new FileResource();
        
        // 调用 countryInfo 方法
        CSVParser parser = fr.getCSVParser();
        String info = countryInfo(parser, "Nauru");
        System.out.println(info);

        // 调用 listExportersTwoProducts 方法
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");

        // 调用 numberOfExporters 方法
        parser = fr.getCSVParser();
        int count = numberOfExporters(parser, "cocoa");
        System.out.println("Number of exporters of cocoa: " + count);

        // 调用 bigExporters 方法
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String recordCountry = record.get("Country");
            if (recordCountry.equalsIgnoreCase(country)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                return recordCountry + ": " + exports + ": " + value;
            }
        }
        return "NOT FOUND";
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String country = record.get("Country");
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                System.out.println(country + " " + value);
            }
        }
    }
}
