package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.ActionInput;
import fileio.Output;
import fileio.OutputWriter;
import streamingservice.StreamingService;
import users.User;
import webpages.SeeDetails;

public class PurchaseAction extends Action {

    public PurchaseAction(final ActionInput action) {
        this.movie = action.getMovie();
    }

    /**
     * {@inheritDoc}:
     * The user purchases the current movie on see details page
     */
    public void execute(final SeeDetails page)
            throws JsonProcessingException {
        User currentUser = StreamingService.getCurrentUser();

        if (currentUser == null || page.getMovie() == null) {
            OutputWriter.addToOutput(new Output("Error"));
            return;
        }

        if (!StreamingService.getCurrentMovieList().contains(page.getMovie())) {
            OutputWriter.addToOutput(new Output("Error"));
            return;
        }

        if (currentUser.purchaseMovie(page.getMovie())) {
            OutputWriter.addToOutput(new Output());
        } else {
            OutputWriter.addToOutput(new Output("Error"));
        }
    }
}
