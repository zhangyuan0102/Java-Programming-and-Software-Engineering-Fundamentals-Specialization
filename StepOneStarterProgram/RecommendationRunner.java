
/**
 * 在这里给出对类 RecommendationRunner 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class RecommendationRunner implements Recommender {

    @Override
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movieIDs = new ArrayList<>();
        ArrayList<String> allMovies = MovieDatabase.filterBy(new TrueFilter());
        Collections.shuffle(allMovies);
        
        // Get a subset of movies to rate
        int numMovies = Math.min(20, allMovies.size());
        for (int i = 0; i < numMovies; i++) {
            movieIDs.add(allMovies.get(i));
        }
        
        return movieIDs;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        
        ArrayList<Rating> recommendations = fr.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
        
        if (recommendations.size() == 0) {
            System.out.println("<h2>No movie recommendations available</h2>");
        } else {
            System.out.println("<style>");
            System.out.println("table { width: 100%; border-collapse: collapse; }");
            System.out.println("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
            System.out.println("th { background-color: #f2f2f2; }");
            System.out.println("</style>");
            System.out.println("<table>");
            System.out.println("<tr><th>Title</th><th>Year</th><th>Genres</th><th>Director(s)</th><th>Minutes</th><th>Rating</th></tr>");
            
            int numResults = Math.min(20, recommendations.size());
            for (int i = 0; i < numResults; i++) {
                Rating rating = recommendations.get(i);
                String movieID = rating.getItem();
                String title = MovieDatabase.getTitle(movieID);
                int year = MovieDatabase.getYear(movieID);
                String genres = MovieDatabase.getGenres(movieID);
                String director = MovieDatabase.getDirector(movieID);
                int minutes = MovieDatabase.getMinutes(movieID);
                double movieRating = rating.getValue();
                
                System.out.println("<tr>");
                System.out.println("<td>" + title + "</td>");
                System.out.println("<td>" + year + "</td>");
                System.out.println("<td>" + genres + "</td>");
                System.out.println("<td>" + director + "</td>");
                System.out.println("<td>" + minutes + "</td>");
                System.out.println("<td>" + String.format("%.1f", movieRating) + "</td>");
                System.out.println("</tr>");
            }
            System.out.println("</table>");
        }
    }

    public static void main(String[] args) {
        RecommendationRunner runner = new RecommendationRunner();
        ArrayList<String> itemsToRate = runner.getItemsToRate();
        System.out.println("Items to Rate:");
        for (String id : itemsToRate) {
            System.out.println(MovieDatabase.getTitle(id));
        }
        
        System.out.println("\nRecommendations for Rater 65:");
        runner.printRecommendationsFor("65");
    }
}

