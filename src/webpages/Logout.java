package webpages;

import actions.Action;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Logout extends Page {

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(final Action action) throws JsonProcessingException {
        action.execute(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPageName() {
        return "logout";
    }
}
