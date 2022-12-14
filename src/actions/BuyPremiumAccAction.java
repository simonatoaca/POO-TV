package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import database.Database;
import fileio.Output;
import fileio.OutputWriter;
import streamingservice.StreamingService;
import users.User;
import webpages.*;

public class BuyPremiumAccAction extends Action
        implements PageVisitor {

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
    public void execute(SeeDetails page) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }

    public void execute(Upgrades page) {
        User currentUser = StreamingService.getCurrentUser();
        String userName = currentUser.getCredentials().getName();

        Database.getInstance().getUsers().put(userName, currentUser.buyPremiumAcc());
        StreamingService.setCurrentUser(Database.getInstance().getUser(userName));
    }
}