package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.ActionInput;
import fileio.Output;
import fileio.OutputWriter;
import movies.Movie;
import streamingservice.StreamingService;
import users.User;
import webpages.*;

import java.util.Objects;

public class RateAction extends Action {
    private static final int MIN_RATING = 1;
    private static final int MAX_RATING = 5;

    public RateAction(final ActionInput action) {
        this.rate = action.getRate();
    }

    /**
     * {@inheritDoc}:
     * The user rates the current movie on see details page (1 to 5)
     * (only if he/she already watched it)
     */
    public void execute(final SeeDetails page) throws JsonProcessingException {
        User currentUser = StreamingService.getCurrentUser();

        if (currentUser == null || page.getMovie() == null) {
            OutputWriter.addToOutput(new Output("Error"));
            return;
        }

        // Check if the user watched the movie
        Movie movie = page.getMovie();
        boolean movieWasWatched = false;
        for (Movie movieWatched : currentUser.getWatchedMovies()) {
            if (Objects.equals(movieWatched.getName(), movie.getName())) {
                movieWasWatched = true;
                break;
            }
        }

        boolean movieWasRated = false;
        for (Movie movieRated : currentUser.getRatedMovies()) {
            if (Objects.equals(movieRated.getName(), movie.getName())) {
                movieWasRated = true;
                break;
            }
        }

        if (movieWasWatched && this.getRate() <= MAX_RATING && this.getRate() >= MIN_RATING) {
            if (!movieWasRated) {
                currentUser.getRatedMovies().add(movie);
            }
            movie.addRating(this.getRate());
            OutputWriter.addToOutput(new Output());
            return;
        }

        OutputWriter.addToOutput(new Output("Error"));
    }
}
