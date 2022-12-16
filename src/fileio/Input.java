package fileio;

import lombok.Getter;
import lombok.Setter;
import movies.Movie;
import users.User;

import java.util.List;

@Getter
@Setter
public class Input {
    private List<User> users;
    private List<Movie> movies;
    private List<ActionInput> actions;
}
