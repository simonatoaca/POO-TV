package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import database.Database;
import fileio.ActionInput;
import fileio.Output;
import fileio.OutputWriter;
import streamingservice.StreamingService;
import users.Credentials;
import users.User;
import webpages.*;

import java.util.Objects;

public class LoginAction extends Action
            implements PageVisitor {
    private final Credentials credentials;

    public LoginAction(ActionInput action) {
        credentials = action.getCredentials();
    }

    public void execute(Login page) throws JsonProcessingException {
        System.out.println("[LOGIN] " + credentials.getName());
        User user = Database.getInstance().getUsers().get(credentials.getName());
        if (user != null) {
            // Check password
            if (Objects.equals(user.getCredentials().getPassword(),
                    credentials.getPassword())) {
                StreamingService.setCurrentUser(user);
                StreamingService.setCurrentPage(new HomepageAuthorized());
                OutputWriter.addToOutput(new Output());
                return;
            }
        }

        Page nextPage = Database.getInstance().getPage("homepage unauth");
        StreamingService.setCurrentPage(nextPage);
        OutputWriter.addToOutput(new Output("Error"));
    }
}
