package webpages;

import actions.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import database.Database;
import lombok.Getter;
import lombok.Setter;
import movies.Movie;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class SeeDetails extends Page {
    private final Set<String> subPages = new HashSet<>(Set.of("movies", "upgrades", "logout", "see details"));
    private Movie movie;

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


    @Override
    public void accept(Action action) throws JsonProcessingException {
        action.execute(this);
    }
}
