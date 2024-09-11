
/**
 * 在这里给出对类 LargestQuakes 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class LargestQuakes {

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        // Get the largest 3 quakes
        ArrayList<QuakeEntry> largestQuakes = getLargest(list, 50);
        for (QuakeEntry qe : largestQuakes) {
            System.out.println(qe);
        }

        // Print the magnitude of the third largest earthquake
        if (largestQuakes.size() >= 50) {
            String title = largestQuakes.get(49).getInfo();
            System.out.printf("The fifth largest earthquake is in: %s\n", title);
        } else {
            System.out.println("There are less than 5 earthquakes in the data.");
        }
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int largestIndex = 0;
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).getMagnitude() > data.get(largestIndex).getMagnitude()) {
                largestIndex = i;
            }
        }
        return largestIndex;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> largest = new ArrayList<QuakeEntry>();
        
        for (int i = 0; i < howMany; i++) {
            if (copy.size() == 0) break;
            int largestIndex = indexOfLargest(copy);
            largest.add(copy.get(largestIndex));
            copy.remove(largestIndex);
        }
        
        return largest;
    }

    public static void main(String[] args) {
        LargestQuakes lq = new LargestQuakes();
        lq.findLargestQuakes();
    }
}

