package recommendations;

import lombok.Getter;
import lombok.Setter;
import movies.Movie;
import streamingservice.StreamingService;
import users.Notification;
import users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RecommendationGenerator {
    private final User user;
    private final List<Movie> likedMovies;
    private final List<Genre> topMoviesGenres;
    private final Map<String, Integer> numLikesByGenre;

    public RecommendationGenerator(final User currentUser) {
        user = new User(currentUser);
        likedMovies = new ArrayList<>();
        for (Movie movie : currentUser.getLikedMovies()) {
            likedMovies.add(new Movie(movie));
        }

        numLikesByGenre = getNumLikesByGenre();
        topMoviesGenres = getLikedMovieGenres();
    }

    /**
     * Goes through the user's liked movies and compiles
     * the statistics of movie genre - number of likes
     * @return a map of the genres and the number of likes associated to each of them
     */
    private Map<String, Integer> getNumLikesByGenre() {
        Map<String, Integer> numLikesByGenre = new HashMap<>();
        likedMovies.forEach(movie -> movie.getGenres().forEach(genre -> {
            if (!numLikesByGenre.containsKey(genre)) {
                numLikesByGenre.put(genre, 0);
            }
            int numLikes = numLikesByGenre.get(genre);
            numLikesByGenre.put(genre, numLikes + 1);
        }));

        return  numLikesByGenre;
    }

    @Getter
    @Setter
    public static class Genre {
        private String name;
        private int numLikes;

        public Genre(final String name, final int numLikes) {
            this.name = name;
            this.numLikes = numLikes;
        }
    }

    /**
     * Using the map numLikesByGenre, it
     * creates the top genres liked by the user.
     * @return a list of the top genres, in descending order
     */
    private List<Genre> getLikedMovieGenres() {
        List<Genre> likedMoviesGenres = new ArrayList<>();
        numLikesByGenre.forEach((genre, numLikes) -> likedMoviesGenres.add(new Genre(genre, numLikes)));
        likedMoviesGenres.removeIf(m -> m.numLikes == 0);
        likedMoviesGenres.sort((o1, o2) -> {
            if (o1.numLikes != o2.numLikes) {
                return Integer.compare(o2.numLikes, o1.numLikes);
            }
            return String.CASE_INSENSITIVE_ORDER.compare(o1.name, o2.name);
        });

        return likedMoviesGenres;
    }

    /**
     * Based on the top genres of the user, it searches for
     * a movie recommendation in the database.
     * @return notification containing the recommendation
     */
    public Notification getRecommendation() {
        if (Objects.equals(user.getCredentials().getAccountType(), "standard")) {
            return null;
        }

        // search for the most liked unwatched movie from the top genre
        List<Movie> topLikedMovies = new ArrayList<>();
        for (Movie movie : StreamingService.getMovieList()) {
            topLikedMovies.add(new Movie(movie));
        }
        topLikedMovies.sort((o1, o2) -> Integer.compare(o2.getNumLikes(), o1.getNumLikes()));

        for (Genre genre : topMoviesGenres) {
            for (Movie movie : topLikedMovies) {
                if (movie.getGenres().contains(genre.name)
                    && user.getWatchedMovies()
                        .stream()
                        .noneMatch(movie1 -> Objects.equals(movie1.getName(), movie.getName()))) {
                    return new Notification(movie.getName(), Notification.REC);
                }
            }
        }

        return new Notification(Notification.NO_REC, Notification.REC);
    }
}
