
/**
 * 在这里给出对类 FourthRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class FourthRatings {

    private double getAverageByID(String id, int minimalRaters) {
        int count = 0;
        double total = 0.0;
        for (Rater rater : RaterDatabase.getRaters()) {
            if (rater.hasRating(id)) {
                count++;
                total += rater.getRating(id);
            }
        }
        if (count >= minimalRaters) {
            return total / count;
        }
        return 0.0;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> avgRatings = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (String movieID : movies) {
            double avg = getAverageByID(movieID, minimalRaters);
            if (avg > 0.0) {
                avgRatings.add(new Rating(movieID, avg));
            }
        }
        return avgRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> avgRatings = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        for (String movieID : movies) {
            double avg = getAverageByID(movieID, minimalRaters);
            if (avg > 0.0) {
                avgRatings.add(new Rating(movieID, avg));
            }
        }
        return avgRatings;
    }

    private double dotProduct(Rater me, Rater r) {
        double dotProduct = 0.0;
        for (String movieID : me.getItemsRated()) {
            if (r.hasRating(movieID)) {
                double meRating = me.getRating(movieID) - 5.0;
                double rRating = r.getRating(movieID) - 5.0;
                dotProduct += meRating * rRating;
            }
        }
        return dotProduct;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similarities = new ArrayList<>();
        Rater me = RaterDatabase.getRater(id);

        for (Rater r : RaterDatabase.getRaters()) {
            if (!r.getID().equals(id)) {
                double similarity = dotProduct(me, r);
                if (similarity > 0) {
                    similarities.add(new Rating(r.getID(), similarity));
                }
            }
        }
        Collections.sort(similarities, Collections.reverseOrder());
        return similarities;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> weightedRatings = new ArrayList<>();
        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (String movieID : movies) {
            double totalWeightedRating = 0.0;
            int count = 0;
            for (int i = 0; i < numSimilarRaters && i < similarities.size(); i++) {
                Rating rating = similarities.get(i);
                Rater rater = RaterDatabase.getRater(rating.getItem());
                if (rater.hasRating(movieID)) {
                    count++;
                    totalWeightedRating += rating.getValue() * rater.getRating(movieID);
                }
            }
            if (count >= minimalRaters) {
                weightedRatings.add(new Rating(movieID, totalWeightedRating / count));
            }
        }
        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> weightedRatings = new ArrayList<>();
        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        for (String movieID : movies) {
            double totalWeightedRating = 0.0;
            int count = 0;
            for (int i = 0; i < numSimilarRaters && i < similarities.size(); i++) {
                Rating rating = similarities.get(i);
                Rater rater = RaterDatabase.getRater(rating.getItem());
                if (rater.hasRating(movieID)) {
                    count++;
                    totalWeightedRating += rating.getValue() * rater.getRating(movieID);
                }
            }
            if (count >= minimalRaters) {
                weightedRatings.add(new Rating(movieID, totalWeightedRating / count));
            }
        }
        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }
}

