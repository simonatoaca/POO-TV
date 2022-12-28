package users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import movies.Movie;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class PremiumUser extends User {
    public PremiumUser(final Credentials credentials) {
        this.credentials = credentials;
        tokensCount = 0;
        numFreePremiumMovies = FREE_PREMIUM_MOVIES;
    }

    public PremiumUser(final User user) {
        credentials = user.getCredentials();
        credentials.setAccountType("premium");
        tokensCount = user.getTokensCount();
        numFreePremiumMovies = user.getNumFreePremiumMovies();
        purchasedMovies = user.getPurchasedMovies();
        watchedMovies = user.getWatchedMovies();
        likedMovies = user.getLikedMovies();
        ratedMovies = user.getRatedMovies();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean purchaseMovie(final Movie movie) {
        // Check if the user purchased the movie
        for (Movie moviePurchased : purchasedMovies) {
            if (Objects.equals(moviePurchased.getName(), movie.getName())) {
                return false;
            }
        }

        if (numFreePremiumMovies == 0) {
            if (tokensCount == 0) {
                return false;
            }

            tokensCount = tokensCount - 2;
            purchasedMovies.add(movie);
            return true;
        }

        numFreePremiumMovies--;
        purchasedMovies.add(movie);
        return true;
    }

    @Override
    protected void getRefund() {
        numFreePremiumMovies++;
    }
}
