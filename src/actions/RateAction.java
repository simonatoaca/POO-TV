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

    public RateAction(ActionInput action) {
        this.rate = action.getRate();
    }

    public void execute(SeeDetails page) throws JsonProcessingException {
        System.out.println("[RATE]");
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

        if (movieWasWatched && this.getRate() < 6 && this.getRate() > 0) {
            currentUser.getRatedMovies().add(movie);
            System.out.println("[this.getRate()] " + this.getRate());
            movie.addRating(this.getRate());
            OutputWriter.addToOutput(new Output());
            return;
        }

        OutputWriter.addToOutput(new Output("Error"));
    }
}
