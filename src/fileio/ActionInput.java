package fileio;

import actionutils.Filter;
import lombok.Getter;
import lombok.Setter;
import users.Credentials;

@Getter
@Setter
public final class ActionInput {
    private String type;
    private String page;
    private String movie;
    private String feature;
    private String startsWith;
    private Credentials credentials;
    private int count;
    private int rate;
    private Filter filters;
}
