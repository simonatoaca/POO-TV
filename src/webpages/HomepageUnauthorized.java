package webpages;

import actions.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
public class HomepageUnauthorized extends Page {
    private final Set<String> subPages = new HashSet<>(Set.of("login", "register"));

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(final Action action) throws JsonProcessingException {
        action.execute(this);
    }
}
