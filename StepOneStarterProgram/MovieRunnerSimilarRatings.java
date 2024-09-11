
/**
 * 在这里给出对类 MovieRunnerSimilarRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        FourthRatings fr = new FourthRatings();
        System.out.println("Number of raters: " + RaterDatabase.size());

        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        int minimalRaters = 1;
        ArrayList<Rating> avgRatings = fr.getAverageRatings(minimalRaters);
        System.out.println("found " + avgRatings.size() + " movies");

        Collections.sort(avgRatings);

        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings fr = new FourthRatings();
        System.out.println("Number of raters: " + RaterDatabase.size());

        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        int minimalRaters = 1;
        int year = 1980;
        String genre = "Romance";
        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(year));
        filters.addFilter(new GenreFilter(genre));
        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(minimalRaters, filters);
        System.out.println(avgRatings.size() + " movie matched");

        Collections.sort(avgRatings);

        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatings() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        int numSimilarRaters = 20;
        int minimalRaters = 5;
        String raterID = "71";

        ArrayList<Rating> similarRatings = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
        System.out.println("found " + similarRatings.size() + " movies");

        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenre() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        int numSimilarRaters = 20;
        int minimalRaters = 5;
        String raterID = "964";
        String genre = "Mystery";

        GenreFilter genreFilter = new GenreFilter(genre);
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, genreFilter);
        System.out.println("found " + similarRatings.size() + " movies");

        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatingsByDirector() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        int numSimilarRaters = 10;
        int minimalRaters = 2;
        String raterID = "120";
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";

        DirectorsFilter directorsFilter = new DirectorsFilter(directors);
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, directorsFilter);
        System.out.println("found " + similarRatings.size() + " movies");

        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        int numSimilarRaters = 10;
        int minimalRaters = 3;
        String raterID = "168";
        String genre = "Drama";
        int minMinutes = 80;
        int maxMinutes = 160;

        AllFilters filters = new AllFilters();
        filters.addFilter(new GenreFilter(genre));
        filters.addFilter(new MinutesFilter(minMinutes, maxMinutes));

        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filters);
        System.out.println("found " + similarRatings.size() + " movies");

        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        int numSimilarRaters = 10;
        int minimalRaters = 5;
        String raterID = "314";
        int year = 1975;
        int minMinutes = 70;
        int maxMinutes = 200;

        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(year));
        filters.addFilter(new MinutesFilter(minMinutes, maxMinutes));

        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filters);
        System.out.println("found " + similarRatings.size() + " movies");

        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " Year: " + MovieDatabase.getYear(rating.getItem()) + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatings mrsr = new MovieRunnerSimilarRatings();
        mrsr.printAverageRatings();
        mrsr.printAverageRatingsByYearAfterAndGenre();
        mrsr.printSimilarRatings();
        mrsr.printSimilarRatingsByGenre();
        mrsr.printSimilarRatingsByDirector();
        mrsr.printSimilarRatingsByGenreAndMinutes();
        mrsr.printSimilarRatingsByYearAfterAndMinutes();
    }
}
