package recommendations;

import database.Database;
import lombok.Getter;
import lombok.Setter;
import movies.Movie;
import streamingservice.StreamingService;
import users.Notification;
import users.User;

import java.util.*;

public class Recommendation {
    private User user;
    private List<Movie> likedMovies;
    private List<Genre> topMoviesGenres;
    private Map<String, Integer> numLikesByGenre;

    public Recommendation(User currentUser) {
        user = new User(currentUser);
        likedMovies = new ArrayList<>();
        for (Movie movie : currentUser.getLikedMovies()) {
            likedMovies.add(new Movie(movie));
        }

        numLikesByGenre = getNumLikesByGenre();
        topMoviesGenres = getLikedMovieGenres();
    }

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
        public String name;
        public int numLikes;

        public Genre(String name, int numLikes) {
            this.name = name;
            this.numLikes = numLikes;
        }
    }

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
