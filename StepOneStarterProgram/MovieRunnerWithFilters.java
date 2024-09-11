
/**
 * 在这里给出对类 MovieRunnerWithFilters 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        int minimalRaters = 35;
        ArrayList<Rating> avgRatings = tr.getAverageRatings(minimalRaters);
        System.out.println("found " + avgRatings.size() + " movies");

        Collections.sort(avgRatings);

        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYear() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        int minimalRaters = 20;
        int year = 2000;
        YearAfterFilter yearFilter = new YearAfterFilter(year);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, yearFilter);
        System.out.println("found " + avgRatings.size() + " movies");

        Collections.sort(avgRatings);

        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    public void printAverageRatingsByGenre() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        int minimalRaters = 20;
        String genre = "Comedy";
        GenreFilter genreFilter = new GenreFilter(genre);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, genreFilter);
        System.out.println("found " + avgRatings.size() + " movies");

        Collections.sort(avgRatings);

        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    public void printAverageRatingsByMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        int minimalRaters = 5;
        int minMinutes = 105;
        int maxMinutes = 135;
        MinutesFilter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, minutesFilter);
        System.out.println("found " + avgRatings.size() + " movies");

        Collections.sort(avgRatings);

        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    public void printAverageRatingsByDirectors() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        int minimalRaters = 4;
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        DirectorsFilter directorsFilter = new DirectorsFilter(directors);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, directorsFilter);
        System.out.println("found " + avgRatings.size() + " movies");

        Collections.sort(avgRatings);

        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        int minimalRaters = 8;
        int year = 1990;
        String genre = "Drama";
        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(year));
        filters.addFilter(new GenreFilter(genre));
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, filters);
        System.out.println(avgRatings.size() + " movie matched");

        Collections.sort(avgRatings);

    }

    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        int minimalRaters = 3;
        int minMinutes = 90;
        int maxMinutes = 180;
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        AllFilters filters = new AllFilters();
        filters.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        filters.addFilter(new DirectorsFilter(directors));
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, filters);
        System.out.println(avgRatings.size() + " movies matched");

        Collections.sort(avgRatings);

        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    public static void main(String[] args) {
        MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();
        mrwf.printAverageRatings();
        mrwf.printAverageRatingsByYear();
    }
}

