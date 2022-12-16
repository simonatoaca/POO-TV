package actions;

import database.Database;
import streamingservice.StreamingService;
import users.PremiumUser;
import users.User;
import webpages.*;

public class BuyPremiumAccAction extends Action {

    /**
     * {@inheritDoc}:
     * Lets the user buy a premium account
     */
    public void execute(final Upgrades page) {
        User currentUser = StreamingService.getCurrentUser();
        String userName = currentUser.getCredentials().getName();

        PremiumUser premiumUser = currentUser.buyPremiumAcc();

        StreamingService.setCurrentUser(premiumUser);
        Database.getInstance().getUsers().put(userName, premiumUser);
    }
}
