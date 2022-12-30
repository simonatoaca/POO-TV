package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import database.Database;
import fileio.ActionInput;
import fileio.Output;
import fileio.OutputWriter;
import streamingservice.StreamingService;
import users.User;
import webpages.SeeDetails;

public class SubscribeAction extends Action {
    private final String genre;

    public SubscribeAction(final ActionInput action) {
        genre = action.getSubscribedGenre();
    }

    /**
     * {@inheritDoc}:
     * The current user subscribes to one of the genres of the current movie
     */
    @Override
    public void execute(final SeeDetails page)
            throws JsonProcessingException {
        User currentUser = StreamingService.getCurrentUser();

        if (currentUser == null) {
            OutputWriter.addToOutput(new Output("Error"));
            return;
        }

        // If the current movie does not have the genre the user wants to subscribe to
        if (!page.getMovie().getGenres().contains(genre)) {
            OutputWriter.addToOutput(new Output("Error"));
            return;
        }

        // If the user is already subscribed to this genre
        if (currentUser.getSubscribedGenres().contains(genre)) {
            OutputWriter.addToOutput(new Output("Error"));
            return;
        }

        currentUser.getSubscribedGenres().add(genre);
        Database.getInstance().addObserver(currentUser);
    }
}
