package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import database.Database;
import fileio.Output;
import fileio.OutputWriter;
import movies.Movie;
import streamingservice.StreamingService;
import users.User;
import webpages.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class WatchAction extends Action
    implements PageVisitor {

    public WatchAction(ActionInput action) {
        this.movie = action.getMovie();
    }

    @Override
    public void execute(HomepageUnauthorized page) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(HomepageAuthorized page) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(Login page) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(Register page) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(Logout page) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(MoviePage page) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
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

    @Override
    public void execute(Upgrades page) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }
}
