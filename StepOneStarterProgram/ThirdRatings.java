
/**
 * 在这里给出对类 ThirdRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsfile);
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters) {
        int count = 0;
        double total = 0.0;
        for (Rater rater : myRaters) {
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

    public int countMoviesWithRatings(int minimalRaters) {
        int count = 0;
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (String movieID : movies) {
            int ratingCount = 0;
            for (Rater rater : myRaters) {
                if (rater.hasRating(movieID)) {
                    ratingCount++;
                }
            }
            if (ratingCount >= minimalRaters) {
                count++;
            }
        }

        return count;
    }

    // Get the movie with the lowest average rating with at least minimalRaters ratings
    public String getLowestRatedMovie(int minimalRaters) {
        double lowestRating = Double.MAX_VALUE;
        String lowestRatedMovie = "";
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (String movieID : movies) {
            double avgRating = getAverageByID(movieID, minimalRaters);
            if (avgRating > 0.0 && avgRating < lowestRating) {
                lowestRating = avgRating;
                lowestRatedMovie = MovieDatabase.getTitle(movieID);
            }
        }

        return lowestRatedMovie;
    }
}

