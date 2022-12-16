package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.ActionInput;
import fileio.Output;
import fileio.OutputWriter;
import streamingservice.StreamingService;
import users.User;
import webpages.*;

public class PurchaseAction extends Action {

    public PurchaseAction(ActionInput action) {
        this.movie = action.getMovie();
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
}
