package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import database.Database;
import fileio.Output;
import fileio.OutputWriter;
import streamingservice.StreamingService;
import users.PremiumUser;
import users.User;
import webpages.*;

public class BuyPremiumAccAction extends Action {
    public void execute(Upgrades page) {
        System.out.println("[BUY PREMIUM]");
        User currentUser = StreamingService.getCurrentUser();
        String userName = currentUser.getCredentials().getName();

        PremiumUser premiumUser = currentUser.buyPremiumAcc();

        StreamingService.setCurrentUser(premiumUser);
        Database.getInstance().getUsers().put(userName, premiumUser);
    }
}
