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

public class WatchAction extends Action {
    public WatchAction(final ActionInput action) {
        this.movie = action.getMovie();
    }

    /**
     * {@inheritDoc}:
     * The user watches the current movie on see details page
     * (only if he/she already purchased it)
     */
    public void execute(final SeeDetails page) throws JsonProcessingException {
        System.out.println("[WATCH]");
        User currentUser = StreamingService.getCurrentUser();

        if (currentUser == null || page.getMovie() == null) {
            OutputWriter.addToOutput(new Output("Error"));
            return;
        }

        // Check if the user purchased the movie
        Movie movie = page.getMovie();
        boolean movieWasPurchased = false;
        for (Movie moviePurchased : currentUser.getPurchasedMovies()) {
            if (Objects.equals(moviePurchased.getName(), movie.getName())) {
                movieWasPurchased = true;
                break;
            }
        }

        boolean movieWasWatched = false;
        for (Movie movieWatched : currentUser.getWatchedMovies()) {
            if (Objects.equals(movieWatched.getName(), movie.getName())) {
                movieWasWatched = true;
                break;
            }
        }

        if (movieWasPurchased) {
            if (movieWasWatched) {
                return;
            }
            currentUser.getWatchedMovies().add(movie);
            OutputWriter.addToOutput(new Output());
            return;
        }

        OutputWriter.addToOutput(new Output("Error"));
    }
}
