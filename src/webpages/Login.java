package webpages;

import actions.Action;
import com.fasterxml.jackson.core.JsonProcessingException;


public class Login extends Page {
    @Override
    public void accept(Action action) throws JsonProcessingException {
        action.execute(this);
    }
}