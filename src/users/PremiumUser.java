package users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import movies.Movie;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class PremiumUser extends User {
    public PremiumUser(Credentials credentials) {
        this.credentials = credentials;
        tokensCount = 0;
        numFreePremiumMovies = 15;
    }

    public PremiumUser(User user) {
        System.out.println("[BUY PREMIUM]");
        credentials = user.getCredentials();
        credentials.setAccountType("premium");
        tokensCount = user.getTokensCount();
        numFreePremiumMovies = user.getNumFreePremiumMovies();
        purchasedMovies = user.getPurchasedMovies();
        watchedMovies = user.getWatchedMovies();
        likedMovies = user.getLikedMovies();
        ratedMovies = user.getRatedMovies();
    }

    @Override
    public boolean purchaseMovie(Movie movie) {
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
}
