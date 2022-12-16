package fileio;

import actions.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Database;
import lombok.Getter;
import lombok.Setter;
import movies.Movie;
import users.PremiumUser;
import users.StandardUser;
import users.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public final class InputHandler {
    private String fileName;
    private Input input;

    public InputHandler(final String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        input = objectMapper.readValue(new File(fileName), Input.class);
    }

    /**
     * Loads the input into the database
     */
    public void loadInputIntoDatabase() {
        List<User> users = input.getUsers();
        List<Movie> movies = input.getMovies();
        Database database = Database.getInstance();

        for (User user : users) {
            if (Objects.equals(user.getCredentials().getAccountType(), "standard")) {
                database.getUsers().put(user.getCredentials().getName(),
                        new StandardUser(user.getCredentials()));
            } else {
                database.getUsers().put(user.getCredentials().getName(),
                        new PremiumUser(user.getCredentials()));
            }
        }

        for (Movie movie : movies) {
            database.getMovies().put(movie.getName(), movie);
        }
    }

    /**
     * Gets the actions as Action objects;
     * It uses an ActionFactory
     * @return the list of Actions
     */
    public List<Action> getActions() {
        List<Action> actions = new ArrayList<>();
        for (ActionInput action : input.getActions()) {
           actions.add(ActionFactory.createAction(action));
        }
        return actions;
    }
}
