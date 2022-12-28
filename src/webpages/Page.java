package webpages;

import actions.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Page {
    private Set<String> subPages;

    /**
     * Accepts a visit from Action
     * @param action the action that visits the page
     */
    public abstract void accept(Action action)
            throws JsonProcessingException;

    public abstract String getPageName();
}
