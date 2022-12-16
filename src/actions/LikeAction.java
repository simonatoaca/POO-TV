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

public class LikeAction extends Action {

    public LikeAction(final ActionInput action) {
        this.movie = action.getMovie();
    }

    /**
     * {@inheritDoc}:
     * The user likes the current movie on see details page
     * (only if he/she already watched it)
     */
    public void execute(final SeeDetails page)
            throws JsonProcessingException {
        User currentUser = StreamingService.getCurrentUser();

        if (currentUser == null || this.movie == null) {
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

        if (movieWasWatched) {
            currentUser.getLikedMovies().add(movie);
            movie.incrementNumLikes();
            OutputWriter.addToOutput(new Output());
            return;
        }

        OutputWriter.addToOutput(new Output("Error"));
    }
}
