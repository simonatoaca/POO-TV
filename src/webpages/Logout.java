package webpages;

import actions.Action;
import com.fasterxml.jackson.core.JsonProcessingException;
import streamingservice.StreamingService;

public class Logout extends Page {

    @Override
    public void accept(Action action) throws JsonProcessingException {
        action.execute(this);
    }
}
