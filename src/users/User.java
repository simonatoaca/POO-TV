package users;

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

    public User() {
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
    }

    public User(User user) {
        this.credentials = new Credentials(user.getCredentials());
        this.tokensCount = user.getTokensCount();
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();
        this.purchasedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();

        for (Movie movie : user.getPurchasedMovies())
            this.purchasedMovies.add(new Movie(movie));

        for (Movie movie : user.getWatchedMovies())
            this.watchedMovies.add(new Movie(movie));

        for (Movie movie : user.getLikedMovies())
            this.likedMovies.add(new Movie(movie));

        for (Movie movie : user.getRatedMovies())
            this.ratedMovies.add(new Movie(movie));
    }

    public void purchaseMovie(Movie movie) {
        tokensCount = tokensCount - 2;
        purchasedMovies.add(movie);
    }

    public void buyTokens(int count) {
        credentials.subtractBalance(count);
        tokensCount += count;
    }

    public PremiumUser buyPremiumAcc() {
        tokensCount -= 10;
        return new PremiumUser(this);
    }

    @Override
    public String toString() {
        return "{" +
                "credentials=" + credentials +
                ", tokensCount=" + tokensCount +
                ", numFreePremiumMovies=" + numFreePremiumMovies +
                ", purchasedMovies=" + purchasedMovies +
                ", watchedMovies=" + watchedMovies +
                ", likedMovies=" + likedMovies +
                ", ratedMovies=" + ratedMovies +
                '}';
    }
}
