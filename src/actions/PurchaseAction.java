package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import database.Database;
import fileio.Output;
import fileio.OutputWriter;
import movies.Movie;
import streamingservice.StreamingService;
import users.User;
import webpages.*;

public class PurchaseAction extends Action
                implements PageVisitor {

    public PurchaseAction(ActionInput action) {
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
        System.out.println("[PURCHASE]" + page.getMovie());
        System.out.println("current movies \n" + StreamingService.getCurrentMovieList());
        User currentUser = StreamingService.getCurrentUser();

        if (currentUser == null || page.getMovie() == null) {
            OutputWriter.addToOutput(new Output("Error"));
            return;
        }

        if (!StreamingService.getCurrentMovieList().contains(page.getMovie())) {
            OutputWriter.addToOutput(new Output("Error"));
            return;
        }

        if(currentUser.purchaseMovie(page.getMovie())) {
            OutputWriter.addToOutput(new Output());
        } else {
            OutputWriter.addToOutput(new Output("Error"));
        }
    }

    @Override
    public void execute(Upgrades page) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }
}
