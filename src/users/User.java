package users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import movies.Movie;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class User {
    protected Credentials credentials;
    protected int tokensCount;
    protected int numFreePremiumMovies;
    protected List<Movie> purchasedMovies;
    protected List<Movie> watchedMovies;
    protected List<Movie> likedMovies;
    protected List<Movie> ratedMovies;

    @JsonIgnore
    protected static final int FREE_PREMIUM_MOVIES = 15;

    public User() {
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
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
        tokensCount -= 10;
        return new PremiumUser(this);
    }
}
