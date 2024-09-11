
/**
 * 在这里给出对接口 Rater 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public interface Rater {
    void addRating(String item, double rating);
    boolean hasRating(String item);
    String getID();
    double getRating(String item);
    int numRatings();
    ArrayList<String> getItemsRated();
}

