package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.ActionInput;
import streamingservice.StreamingService;
import users.User;
import webpages.*;

public class BuyTokensAction extends Action {
    public BuyTokensAction(final ActionInput action) {
        this.count = action.getCount();
    }

    /**
     * {@inheritDoc}:
     * Lets the user buy tokens
     */
    public void execute(final Upgrades page) throws JsonProcessingException {
        User currentUser = StreamingService.getCurrentUser();
        currentUser.buyTokens(this.count);
    }
}
