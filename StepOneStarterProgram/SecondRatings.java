
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    // Default constructor
    public SecondRatings() {
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    // Constructor with parameters
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);
    }

    // Get number of movies
    public int getMovieSize() {
        return myMovies.size();
    }

    // Get number of raters
    public int getRaterSize() {
        return myRaters.size();
    }

    // Helper method to get average rating by movie ID
    double getAverageByID(String id, int minimalRaters) {
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
        } else {
            return 0.0;
        }
    }

    // Public method to get average ratings for all movies with at least minimalRaters ratings
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> avgRatings = new ArrayList<>();

        for (Movie movie : myMovies) {
            double avgRating = getAverageByID(movie.getID(), minimalRaters);
            if (avgRating > 0.0) {
                avgRatings.add(new Rating(movie.getID(), avgRating));
            }
        }

        return avgRatings;
    }

    // Get title by movie ID
    public String getTitle(String id) {
        for (Movie movie : myMovies) {
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "ID not found.";
    }

    public String getID(String title) {
        for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
        return "NO SUCH TITLE.";
    }

    // Public method to count movies with at least minimalRaters ratings
    public int countMoviesWithRatings(int minimalRaters) {
        int count = 0;
        for (Movie movie : myMovies) {
            int ratingCount = 0;
            for (Rater rater : myRaters) {
                if (rater.hasRating(movie.getID())) {
                    ratingCount++;
                }
            }
            if (ratingCount >= minimalRaters) {
                count++;
            }
        }
        return count;
    }

    // Public method to find the movie with the lowest average rating with at least minimalRaters ratings
    public String getLowestRatedMovie(int minimalRaters) {
        double lowestRating = Double.MAX_VALUE;
        String lowestRatedMovie = "";

        for (Movie movie : myMovies) {
            double avgRating = getAverageByID(movie.getID(), minimalRaters);
            if (avgRating > 0.0 && avgRating < lowestRating) {
                lowestRating = avgRating;
                lowestRatedMovie = movie.getTitle();
            }
        }

        return lowestRatedMovie;
    }
}
