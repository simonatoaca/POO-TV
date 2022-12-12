package fileio;

import actions.Action;
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
    private List<Action> actions;

    @Override
    public String toString() {
        return "Input{" +
                "users=" + users +
                ", movies=" + movies +
                ", actions=" + actions +
                '}';
    }
}
