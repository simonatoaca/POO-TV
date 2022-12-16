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
import webpages.*;
import java.util.Objects;

public class RegisterAction extends Action {
    private final Credentials credentials;

    public RegisterAction(ActionInput action) {
        credentials = action.getCredentials();
    }

    public void execute(Register page) throws JsonProcessingException {
        System.out.println("[REGISTER] " + credentials.getName());
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
