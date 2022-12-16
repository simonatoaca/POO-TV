package webpages;

import actions.Action;
import com.fasterxml.jackson.core.JsonProcessingException;


public class Login extends Page {

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(final Action action) throws JsonProcessingException {
        action.execute(this);
    }
}
