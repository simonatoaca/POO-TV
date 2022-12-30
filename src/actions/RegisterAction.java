package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import database.Database;
import fileio.ActionInput;
import fileio.Output;
import fileio.OutputWriter;
import streamingservice.StreamingService;
import users.Credentials;
import users.PremiumUser;
import users.StandardUser;
import webpages.HomepageAuthorized;
import webpages.HomepageUnauthorized;
import webpages.Register;

import java.util.Objects;

public class RegisterAction extends Action {
    private final Credentials credentials;

    public RegisterAction(final ActionInput action) {
        credentials = action.getCredentials();
    }

    /**
     * {@inheritDoc}:
     * A new user registers using the given credentials
     */
    public void execute(final Register page) throws JsonProcessingException {
        Database database = Database.getInstance();

        boolean userExists = database.getUsers().containsKey(credentials.getName());
        if (!userExists) {
            if (Objects.equals(credentials.getAccountType(), "standard")) {
                database.getUsers().put(credentials.getName(),
                        new StandardUser(credentials));
            } else {
                database.getUsers().put(credentials.getName(),
                        new PremiumUser(credentials));
            }

            StreamingService.getPageHistory().add(0, StreamingService.getCurrentPage());
            StreamingService.setCurrentPage(new HomepageAuthorized());
            StreamingService.setCurrentUser(database.getUser(credentials.getName()));
            OutputWriter.addToOutput(new Output());

            return;
        }

        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }
}
