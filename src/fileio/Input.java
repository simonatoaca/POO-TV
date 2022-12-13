package fileio;

import actions.Action;
import actions.ActionInput;
import lombok.Getter;
import lombok.Setter;
import movies.Movie;
import users.Credentials;
import users.User;

import java.util.List;

@Getter
@Setter
public class Input {
    private List<User> users;
    private List<Movie> movies;
    private List<ActionInput> actions;

    @Override
    public String toString() {
        return "Input{" +
                "users=" + users +
                ", movies=" + movies +
                ", actions=" + actions +
                '}';
    }
}
