
/**
 * 在这里给出对类 babynames 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class BabyNames {
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int girlsBirths = 0;
        int boysBirths = 0;
        int girlsNamesCount = 0;
        int boysNamesCount = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            String gender = rec.get(1);
            totalBirths += numBorn;
            if (gender.equals("F")) {
                girlsBirths += numBorn;
                girlsNamesCount++;
            } else if (gender.equals("M")) {
                boysBirths += numBorn;
                boysNamesCount++;
            }
        }

        int totalNamesCount = girlsNamesCount + boysNamesCount;
        System.out.println("Total births: " + totalBirths);
        System.out.println("Total girls births: " + girlsBirths);
        System.out.println("Total boys births: " + boysBirths);
        System.out.println("Number of girls names: " + girlsNamesCount);
        System.out.println("Number of boys names: " + boysNamesCount);
        System.out.println("Total names: " + totalNamesCount);
    }
    public int getRank(int year, String name, String gender) {
        String fileName = "data/yob" + year + ".csv"; // 构建文件名
        FileResource fr = new FileResource(fileName);
        int rank = 0;
        boolean nameFound = false;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank++;
                if (rec.get(0).equals(name)) {
                    nameFound = true;
                    break;
                }
            }
        }

        if (nameFound) {
            return rank;
        } else {
            return -1;
        }
    }
    public String getName(int year, int rank, String gender) {
        String fileName = "data/yob" + year + ".csv"; // 构建文件名
        FileResource fr = new FileResource(fileName);
        int currentRank = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                currentRank++;
                if (currentRank == rank) {
                    return rec.get(0); // 返回对应排名的名字
                }
            }
        }
        
        return "NO NAME"; // 如果排名不存在，返回 "NO NAME"
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        if (rank == -1) {
            System.out.println("The name " + name + " was not found in the year " + year);
        } else {
            String newName = getName(newYear, rank, gender);
            System.out.println(name + " born in " + year + " would be " + newName + " if they were born in " + newYear);
        }
    }
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = Integer.MAX_VALUE;
        int yearOfHighestRank = -1;

        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            int currentRank = getRank(year, name, gender);
            
            if (currentRank != -1 && currentRank < highestRank) {
                highestRank = currentRank;
                yearOfHighestRank = year;
            }
        }

        return yearOfHighestRank;
    }
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        int count = 0;

        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            int currentRank = getRank(year, name, gender);

            if (currentRank != -1) {
                totalRank += currentRank;
                count++;
            }
        }

        if (count == 0) {
            return -1.0;
        } else {
            return (double) totalRank / count;
        }
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String fileName = "data/yob"+ year + ".csv"; // 构建文件名
        FileResource fr = new FileResource(fileName);
        int totalBirths = 0;
        boolean nameFound = false;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (rec.get(0).equals(name)) {
                    nameFound = true;
                    break;
                } else {
                    totalBirths += Integer.parseInt(rec.get(2));
                }
            }
        }

        return totalBirths;
    }
    public void testTotalBirths() {
        FileResource fr = new FileResource("data/yob1900.csv"); // 替换为您的文件路径
        totalBirths(fr);
        FileResource fr1 = new FileResource("data/yob1905.csv"); // 替换为您的文件路径
        totalBirths(fr1);        
    }
    public void testGetRank() {
        int rank = getRank(1960, "Emily", "F");
        System.out.println("Rank of Emily in 1960 (F): " + rank);
        rank = getRank(1971, "Frank", "M");
        System.out.println("Rank of Frank in 1971 (M): " + rank);
    }
    public void testGetName() {
        String name = getName(1980, 350, "F");
        System.out.println("Name at rank 350 in 1980 (F): " + name);
        name = getName(1982, 450, "M");
        System.out.println("Name at rank 450 in 1982 (M): " + name);
    }
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Susan", 1972, 2014, "F");
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    public void testYearOfHighestRank() {
        int year = yearOfHighestRank("Genevieve", "F");
        System.out.println("Year of highest rank for Genevieve (F): " + year);
        year = yearOfHighestRank("Mich", "M");
        System.out.println("Year of highest rank for Mich (M): " + year);
    }
    public void testGetAverageRank() {
        double avgRank = getAverageRank("Susan", "F");
        System.out.println("Average rank for Susan (F): " + avgRank);
        avgRank = getAverageRank("Robert", "M");
        System.out.println("Average rank for Robert (M): " + avgRank);
    }
    public void testGetTotalBirthsRankedHigher() {
        int totalBirths = getTotalBirthsRankedHigher(1990, "Emily", "F");
        System.out.println("Total births ranked higher than Emily (F) in 1990: " + totalBirths);
        totalBirths = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println("Total births ranked higher than Drew (M) in 1990: " + totalBirths);
    }
    public static void main(String[] args) {
        BabyNames babyNames = new BabyNames();
        babyNames.testTotalBirths();
    }
}