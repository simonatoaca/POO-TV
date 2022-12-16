package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.ActionInput;
import fileio.Output;
import fileio.OutputWriter;
import streamingservice.StreamingService;
import users.User;
import webpages.*;

public class BuyTokensAction extends Action {
    public BuyTokensAction(ActionInput action) {
        this.count = action.getCount();
    }

    public void execute(Upgrades page) throws JsonProcessingException {
        System.out.println("[BUY TOKENS]");
        User currentUser = StreamingService.getCurrentUser();
        currentUser.buyTokens(this.count);
    }
}
