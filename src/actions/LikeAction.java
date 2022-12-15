package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import database.Database;
import fileio.Output;
import fileio.OutputWriter;
import movies.Movie;
import streamingservice.StreamingService;
import users.User;
import webpages.*;

import java.io.IOException;
import java.util.Collections;

public class LikeAction extends Action
                    implements PageVisitor{

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
        Movie movie = Database.getInstance().getMovie(this.movie);
        int idx = Collections.binarySearch(currentUser.getWatchedMovies(), movie,
                (o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName()));

        if (idx >= 0) {
            currentUser.getLikedMovies().add(movie);
            movie.incrementNumLikes();
            OutputWriter.addToOutput(new Output());
            return;
        }

        OutputWriter.addToOutput(new Output("Error"));
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

    @Override
    public void execute(Upgrades page) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }
}
