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
    public WatchAction(ActionInput action) {
        this.movie = action.getMovie();
    }

    public void execute(SeeDetails page) throws JsonProcessingException {
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

        if (movieWasPurchased) {
            currentUser.getWatchedMovies().add(movie);
            OutputWriter.addToOutput(new Output());
            return;
        }

        OutputWriter.addToOutput(new Output("Error"));
    }
}
