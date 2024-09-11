
/**
 * 在这里给出对类 MovieRunnerAverage 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        int minimalRaters = 35;
        ArrayList<Rating> avgRatings = tr.getAverageRatings(minimalRaters);
        Collections.sort(avgRatings);

        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void getAverageRatingOneMovie() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        String title = "The Godfather";
        String id = MovieDatabase.getID(title);

        if (id.equals("NO SUCH TITLE.")) {
            System.out.println(id);
        } else {
            double avgRating = tr.getAverageByID(id, 1);  // assuming we want the average rating regardless of minimalRaters
            System.out.println("Average rating for " + title + " is " + avgRating);
        }
    }

    public void printMoviesWithMinRatings(int minimalRaters) {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        int count = tr.countMoviesWithRatings(minimalRaters);
        System.out.println("Number of movies with at least " + minimalRaters + " ratings: " + count);
    }

    public void printLowestRatedMovie(int minimalRaters) {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        String lowestRatedMovie = tr.getLowestRatedMovie(minimalRaters);
        System.out.println("The movie with the lowest rating with at least " + minimalRaters + " ratings is: " + lowestRatedMovie);
    }

    public static void main(String[] args) {
        MovieRunnerAverage mra = new MovieRunnerAverage();
        mra.printAverageRatings();
        mra.getAverageRatingOneMovie();
        mra.printMoviesWithMinRatings(50);  // Print number of movies with at least 50 ratings
        mra.printLowestRatedMovie(12);  // Print the movie with the lowest rating with at least 12 ratings
    }
}


