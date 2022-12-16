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

    public LikeAction(ActionInput action) {
        this.movie = action.getMovie();
    }
    public void execute(SeeDetails page) throws JsonProcessingException {
        System.out.println("[LIKE]");
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
