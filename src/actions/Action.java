package actions;

import actionutils.Filter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import users.Credentials;

@Getter
@Setter
@NoArgsConstructor
public class Action {
    protected String type;
    protected String page;
    protected String movie;
    protected String feature;
    protected String startsWith;
    protected Credentials credentials;
    protected int count;
    protected int rate;
    protected Filter filters;

    @Override
    public String toString() {
        return "{" +
                "type='" + type + '\'' +
                ", page='" + page + '\'' +
                ", feature='" + feature + '\'' +
                ", credentials=" + credentials +
                '}';
    }
}
