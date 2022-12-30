package users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import database.Database;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import movies.Movie;

import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
public class User implements Observer {
    protected Credentials credentials;
    protected int tokensCount;
    protected int numFreePremiumMovies;
    protected List<Movie> purchasedMovies;
    protected List<Movie> watchedMovies;
    protected List<Movie> likedMovies;
    protected List<Movie> ratedMovies;
    protected List<Notification> notifications;

    @JsonIgnore
    protected List<String> subscribedGenres;

    @JsonIgnore
    protected static final int FREE_PREMIUM_MOVIES = 15;
    @JsonIgnore
    protected static final int PREMIUM_ACC_PRICE = 10;

    public User() {
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
        subscribedGenres = new ArrayList<>();
        notifications = new ArrayList<>();
    }

    /**
     * Deep copies the user;
     * Used for output
     * @param user the user that is copied
     */
    public User(final User user) {
        this.credentials = new Credentials(user.getCredentials());
        this.tokensCount = user.getTokensCount();
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();
        this.purchasedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
        this.subscribedGenres = new ArrayList<>();
        this.notifications = new ArrayList<>();

        for (Movie movie : user.getPurchasedMovies()) {
            this.purchasedMovies.add(new Movie(movie));
        }

        for (Movie movie : user.getWatchedMovies()) {
            this.watchedMovies.add(new Movie(movie));
        }

        for (Movie movie : user.getLikedMovies()) {
            this.likedMovies.add(new Movie(movie));
        }

        for (Movie movie : user.getRatedMovies()) {
            this.ratedMovies.add(new Movie(movie));
        }

        for (Notification notification : user.getNotifications()) {
            this.notifications.add(new Notification(notification.getMovieName(), notification.getMessage()));
        }
    }

    /**
     * Adds a movie to the purchased movies list
     * and makes the user pay according to its account type
     * @param movie the movie to be purchased
     * @return if the purchase was successful
     */
    public boolean purchaseMovie(final Movie movie) {
        if (tokensCount == 0) {
            return false;
        }

        // Check if the user purchased the movie
        for (Movie moviePurchased : purchasedMovies) {
            if (Objects.equals(moviePurchased.getName(), movie.getName())) {
                return false;
            }
        }

        tokensCount = tokensCount - 2;
        purchasedMovies.add(movie);
        return true;
    }

    /**
     * Buy tokens action;
     * @param count the value
     */
    public void buyTokens(final int count) {
        credentials.subtractBalance(count);
        tokensCount += count;
    }

    /**
     * Turns a standard account into a premium account
     * @return the premium account of the user
     */
    public PremiumUser buyPremiumAcc() {
        tokensCount -= PREMIUM_ACC_PRICE;
        return new PremiumUser(this);
    }

    protected void getRefund() {
        // Basic refund for standard users
        tokensCount += 2;
    }

    @Override
    public void update(Observable o, Object arg) {
        Notification newNotification = (Notification) arg;

        if (Objects.equals(newNotification.getMessage(), Notification.DELETE)) {
            purchasedMovies.removeIf(movie -> Objects.equals(movie.getName(), newNotification.getMovieName()));
            likedMovies.removeIf(movie -> Objects.equals(movie.getName(), newNotification.getMovieName()));
            ratedMovies.removeIf(movie -> Objects.equals(movie.getName(), newNotification.getMovieName()));
            watchedMovies.removeIf(movie -> Objects.equals(movie.getName(), newNotification.getMovieName()));

            getRefund();
            notifications.add(newNotification);
            return;
        }

        Movie movie = Database.getInstance().getMovie(newNotification.getMovieName());

        // Check if the movie is available to the user / the genre is one of the movie's genres
        if (movie.getCountriesBanned().contains(getCredentials().getCountry())
            || getSubscribedGenres().stream().noneMatch(movie.getGenres()::contains)) {
            return;
        }

        notifications.add(newNotification);
    }
}
