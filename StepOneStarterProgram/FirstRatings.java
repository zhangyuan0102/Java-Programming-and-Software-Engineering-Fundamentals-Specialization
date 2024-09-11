
/**
 * 在这里给出对类 FirstRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

        for (CSVRecord record : parser) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String genres = record.get("genre");
            String director = record.get("director");
            String country = record.get("country");
            String poster = record.get("poster");
            int minutes = Integer.parseInt(record.get("minutes"));

            Movie movie = new Movie(id, title, year, genres, director, country, poster, minutes);
            movies.add(movie);
        }
        return movies;
    }

    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raters = new ArrayList<>();
        HashMap<String, Rater> raterMap = new HashMap<>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

        for (CSVRecord record : parser) {
            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));

            Rater rater = raterMap.getOrDefault(raterID, new EfficientRater(raterID));
            rater.addRating(movieID, rating);
            raterMap.put(raterID, rater);
        }
        raters.addAll(raterMap.values());
        return raters;
    }

    public void testLoadMovies() {
        ArrayList<Movie> movies = loadMovies("ratedmovies_short.csv");
        System.out.println("Number of movies: " + movies.size());
        for (Movie movie : movies) {
            System.out.println(movie);
        }

        int comedyCount = 0;
        int longMoviesCount = 0;
        HashMap<String, Integer> directorMovieCount = new HashMap<>();

        for (Movie movie : movies) {
            if (movie.getGenres().contains("Comedy")) {
                comedyCount++;
            }
            if (movie.getMinutes() > 150) {
                longMoviesCount++;
            }
            String[] directors = movie.getDirector().split(",");
            for (String director : directors) {
                director = director.trim();
                directorMovieCount.put(director, directorMovieCount.getOrDefault(director, 0) + 1);
            }
        }

        System.out.println("Number of Comedy movies: " + comedyCount);
        System.out.println("Number of movies longer than 150 minutes: " + longMoviesCount);

        int maxMoviesByDirector = Collections.max(directorMovieCount.values());
        ArrayList<String> directorsWithMaxMovies = new ArrayList<>();
        for (String director : directorMovieCount.keySet()) {
            if (directorMovieCount.get(director) == maxMoviesByDirector) {
                directorsWithMaxMovies.add(director);
            }
        }
        System.out.println("Maximum number of movies by any director: " + maxMoviesByDirector);
        System.out.println("Directors with max movies: " + directorsWithMaxMovies);
    }

    public void testLoadRaters() {
        ArrayList<Rater> raters = loadRaters("ratings_short.csv");
        System.out.println("Number of raters: " + raters.size());

        for (Rater rater : raters) {
            System.out.println("Rater ID: " + rater.getID() + " Number of ratings: " + rater.numRatings());
            for (String movieID : rater.getItemsRated()) {
                System.out.println("Movie ID: " + movieID + " Rating: " + rater.getRating(movieID));
            }
        }

        String raterID = "2";
        for (Rater rater : raters) {
            if (rater.getID().equals(raterID)) {
                System.out.println("Rater " + raterID + " has " + rater.numRatings() + " ratings.");
            }
        }

        int maxRatings = 0;
        for (Rater rater : raters) {
            if (rater.numRatings() > maxRatings) {
                maxRatings = rater.numRatings();
            }
        }

        ArrayList<String> ratersWithMaxRatings = new ArrayList<>();
        for (Rater rater : raters) {
            if (rater.numRatings() == maxRatings) {
                ratersWithMaxRatings.add(rater.getID());
            }
        }
        System.out.println("Maximum number of ratings by any rater: " + maxRatings);
        System.out.println("Raters with max ratings: " + ratersWithMaxRatings);

        String movieID = "1798709";
        int movieRatingCount = 0;
        for (Rater rater : raters) {
            if (rater.hasRating(movieID)) {
                movieRatingCount++;
            }
        }
        System.out.println("Movie " + movieID + " has " + movieRatingCount + " ratings.");

        HashSet<String> uniqueMovies = new HashSet<>();
        for (Rater rater : raters) {
            uniqueMovies.addAll(rater.getItemsRated());
        }
        System.out.println("Number of different movies rated: " + uniqueMovies.size());
    }

    public static void main(String[] args) {
        FirstRatings fr = new FirstRatings();
        fr.testLoadMovies();
        fr.testLoadRaters();
    }
}

