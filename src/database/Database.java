package database;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.Output;
import fileio.OutputWriter;
import lombok.Getter;
import lombok.Setter;
import movies.Movie;
import users.Notification;
import users.User;
import webpages.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;


@Getter
@Setter
public final class Database extends Observable {
    private static Database database;
    private Map<String, User> users;
    private Map<String, Movie> movies;
    private static Map<String, Page> pages;

    private Database() {
        users = new HashMap<>();
        movies  = new HashMap<>();
    }

    /**
     * Singleton specific method
     * Gets the only instance of the class
     * @return the class instance
     */
    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }

        return database;
    }

    /**
     * Gets user from database by name
     * @param name name of the user
     * @return the user
     */
    public User getUser(final String name) {
        return users.get(name);
    }

    /**
     * Gets movie from database by name
     * @param name name of the movie
     * @return the movie
     */
    public Movie getMovie(final String name) {
        return movies.get(name);
    }

    public void addMovie(final Movie movie) throws JsonProcessingException {
        if (getMovies().containsKey(movie.getName())) {
            OutputWriter.addToOutput(new Output("Error"));
            return;
        }

        movies.put(movie.getName(), movie);
        setChanged();

        notifyObservers(new Notification(movie.getName(), Notification.ADD));
    }

    public void deleteMovie(final String movie) throws JsonProcessingException {
        if (!getMovies().containsKey(movie)) {
            OutputWriter.addToOutput(new Output("Error"));
            return;
        }

        movies.remove(movie);
        setChanged();

        notifyObservers(new Notification(movie, Notification.DELETE));
    }

    /**
     * Gets page from database by name
     * Used for populating the subPages field of Page classes
     * @param name name of the page
     * @return the page
     */
    public Page getPage(final String name) {
        if (pages == null) {
            pages = new HashMap<>(Map.of("login", new Login(),
                    "register", new Register(),
                    "homepage auth", new HomepageAuthorized(),
                    "homepage unauth", new HomepageUnauthorized(),
                    "logout", new Logout(),
                    "movies", new MoviePage(),
                    "see details", new SeeDetails(),
                    "upgrades", new Upgrades()));
        }

        return pages.get(name);
    }

    /**
     * Clears the database to receive the next input
     */
    public void clear() {
        users = new HashMap<>();
        movies = new HashMap<>();
    }
}
