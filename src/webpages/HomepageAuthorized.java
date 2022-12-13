package webpages;

import actions.Action;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class HomepageAuthorized extends Page {
    private final Set<String> subPages = new HashSet<>(Set.of("movies", "upgrades", "logout"));

    @Override
    public void accept(Action action) throws JsonProcessingException {
        action.execute(this);
    }
}
