package users;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import movies.Movie;

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
