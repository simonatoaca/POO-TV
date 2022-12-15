package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.Output;
import fileio.OutputWriter;
import streamingservice.StreamingService;
import users.User;
import webpages.*;

public class BuyTokensAction extends Action
    implements PageVisitor {

    public BuyTokensAction(ActionInput action) {
        this.count = action.getCount();
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
    public void execute(SeeDetails page) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }

    public void execute(Upgrades page) throws JsonProcessingException {
        System.out.println("[BUY TOKENS]");
        User currentUser = StreamingService.getCurrentUser();
        currentUser.buyTokens(this.count);
    }
}
