package webpages;

import actions.Action;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
public class MoviePage extends Page {
    private final Set<String> subPages = new HashSet<>(Set.of("movies", "see details"));

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(final Action action) throws JsonProcessingException {
        action.execute(this);
    }
}
