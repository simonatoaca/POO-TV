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
import java.util.Objects;

public class RateAction extends Action
                implements PageVisitor {

    public RateAction(ActionInput action) {
        this.rate = action.getRate();
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

    @Override
    public void execute(Upgrades page) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }
}
