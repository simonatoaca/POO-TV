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

    @Override
    public void accept(Action action) throws JsonProcessingException {
        action.execute(this);
    }
}
