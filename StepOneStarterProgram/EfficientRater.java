
/**
 * 在这里给出对类 EfficientRater 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

    @Override
    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item, rating));
    }

    @Override
    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }

    @Override
    public String getID() {
        return myID;
    }

    @Override
    public double getRating(String item) {
        Rating rating = myRatings.get(item);
        if (rating != null) {
            return rating.getValue();
        }
        return -1;
    }

    @Override
    public int numRatings() {
        return myRatings.size();
    }

    @Override
    public ArrayList<String> getItemsRated() {
        return new ArrayList<>(myRatings.keySet());
    }
}

