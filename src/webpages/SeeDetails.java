package webpages;

import actions.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;
import movies.Movie;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SeeDetails extends Page {
    private final Set<String> subPages = new HashSet<>(Set.of("movies", "upgrades", "see details"));
    private Movie movie;

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(final Action action) throws JsonProcessingException {
        action.execute(this);
    }

    @Override
    public String getPageName() {
        return "see details";
    }
}
