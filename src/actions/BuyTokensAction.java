package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.Output;
import fileio.OutputWriter;
import streamingservice.StreamingService;
import users.User;
import webpages.*;

public class BuyTokensAction extends Action
    implements PageVisitor {
    @Override
    public void execute(HomepageUnauthorized page) throws JsonProcessingException {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(HomepageAuthorized page) throws JsonProcessingException {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(Login page) throws JsonProcessingException {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(Register page) throws JsonProcessingException {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(Logout page) throws JsonProcessingException {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(MoviePage page) throws JsonProcessingException {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(SeeDetails page) throws JsonProcessingException {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }

    public void execute(Upgrades page) {
        User currentUser = StreamingService.getCurrentUser();
        currentUser.buyTokens(this.count);
    }
}
