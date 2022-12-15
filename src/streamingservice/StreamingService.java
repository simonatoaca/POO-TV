package streamingservice;

import actions.Action;
import database.Database;
import fileio.InputHandler;
import fileio.OutputWriter;
import lombok.Getter;
import lombok.Setter;
import movies.Movie;
import users.User;
import webpages.HomepageUnauthorized;
import webpages.Page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StreamingService {
    private static String inputFileName;
    private static String outputFileName;
    private static Page currentPage;
    private static User currentUser;
    private static StreamingService instance;
    private static List<Movie> movieList;
    private static List<Movie> currentMovieList;

    private StreamingService(String inputFileName, String outputFileName) {
        currentUser = null;
        StreamingService.inputFileName = inputFileName;
        StreamingService.outputFileName = outputFileName;
        currentPage = new HomepageUnauthorized();
        movieList = new ArrayList<>();
        currentMovieList = new ArrayList<>();
    }

    public static void start(String inputFileName, String outputFileName) throws IOException {
        System.out.println("START");
        instance = new StreamingService(inputFileName, outputFileName);

        InputHandler inputHandler = new InputHandler(inputFileName);
        inputHandler.loadInputIntoDatabase();
        List<Action> actions = inputHandler.getActions();

        movieList = new ArrayList<>(inputHandler.getInput().getMovies());

//        StreamingService.outputFileName = inputFileName.replace("in", "out");
        OutputWriter.config();

        for (Action action : actions) {
            currentPage.accept(action);
        }

        OutputWriter.write(StreamingService.outputFileName);
        Database.getInstance().clear();
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentPage(Page page) {
        currentPage = page;
    }

    public static Page getCurrentPage() {
        return currentPage;
    }

    public static List<Movie> getCurrentMovieList() {
        return currentMovieList;
    }

    public static void setCurrentMovieList(List<Movie> movies) {
        currentMovieList = movies;
    }

    public static List<Movie> getMovieList() {
        return movieList;
    }

    public static String getOutputFileName() {
        return outputFileName;
    }
}
