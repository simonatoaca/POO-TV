package database;

import lombok.Getter;
import lombok.Setter;
import movies.Movie;
import users.PremiumUser;
import users.StandardUser;
import users.User;
import webpages.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class Database {
    private static Database database = null;
    private Map<String, User> users;
    private Map<String, Movie> movies;
    private static Map<String, Page> pages;

    private Database() {
        users = new HashMap<>();
        movies  = new HashMap<>();
    };

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }

        return database;
    }

    public User getUser(String name) {
        return users.get(name);
    }

    public Movie getMovie(String name) {
        return movies.get(name);
    }

    public Page getPage(String name) {
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

    public void addUser(User user) {
        if (Objects.equals(user.getCredentials().getAccountType(), "standard")) {
            users.put(user.getCredentials().getName(), new StandardUser(user.getCredentials()));
        } else {
            users.put(user.getCredentials().getName(), new PremiumUser(user.getCredentials()));
        }
    }

    public void addMovie(Movie movie) {
        movies.put(movie.getName(), movie);
    }

    public void clear() {
        users = new HashMap<>();
        movies = new HashMap<>();
    }
}
