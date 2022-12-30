package streamingservice;

import actions.Action;
import database.Database;
import fileio.InputHandler;
import fileio.Output;
import fileio.OutputWriter;
import lombok.Getter;
import lombok.Setter;
import movies.Movie;
import recommendations.RecommendationGenerator;
import users.Notification;
import users.User;
import webpages.HomepageUnauthorized;
import webpages.Page;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class StreamingService {
    @Getter
    @Setter
    private static String inputFileName;
    @Getter
    @Setter
    private static String outputFileName;
    @Getter
    @Setter
    private static Page currentPage;
    @Getter
    @Setter
    private static User currentUser;
    @Getter
    @Setter
    private static StreamingService instance;
    @Getter
    @Setter
    private static List<Movie> movieList;
    @Getter
    @Setter
    private static List<Movie> currentMovieList;

    @Getter
    @Setter
    private static List<Page> pageHistory;

    /**
     * Initializes the instance of the streaming service
     * @param inputFileName input file
     * @param outputFileName output file
     */
    private StreamingService(final String inputFileName, final String outputFileName) {
        currentUser = null;
        StreamingService.inputFileName = inputFileName;
        StreamingService.outputFileName = outputFileName;
        currentPage = new HomepageUnauthorized();
        movieList = new ArrayList<>();
        currentMovieList = new ArrayList<>();
        pageHistory = new ArrayList<>();
    }

    /**
     * This is the entry point of the streaming service POO TV
     * It handles I/O and the actions from the users
     * @param inputFileName input file
     * @param outputFileName output file
     * @throws IOException output exception
     */
    public static void start(final String inputFileName,
                             final String outputFileName) throws IOException {
        instance = new StreamingService(inputFileName, outputFileName);

        // Get input and load it into the database
        InputHandler inputHandler = new InputHandler(inputFileName);
        inputHandler.loadInputIntoDatabase();

        // Get actions and movieList
        List<Action> actions = inputHandler.getActions();
        movieList = new ArrayList<>(inputHandler.getInput().getMovies());

        OutputWriter.config();

        // Handle actions
        for (Action action : actions) {
            currentPage.accept(action);
        }

        // Give recommendations if the user is premium
        if (currentUser != null) {
            Notification recommendation = new RecommendationGenerator(currentUser)
                    .getRecommendation();
            if (recommendation != null) {
                // Clear movie list
                currentMovieList = null;
                currentUser.getNotifications().add(recommendation);
                OutputWriter.addToOutput(new Output());
            }
        }

        // Write output
        OutputWriter.write(StreamingService.outputFileName);
        Database.getInstance().clear();
    }
}
