package fileio;

import lombok.Getter;
import lombok.Setter;
import movies.Movie;
import streamingservice.StreamingService;
import users.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Output {
    private String error;
    private List<Movie> currentMoviesList;
    private User currentUser;

    public Output() {
        this.currentMoviesList = StreamingService.getCurrentMovieList();
        this.currentUser = StreamingService.getCurrentUser();
    }

    public Output(final String error) {
        this.error = error;
        this.currentMoviesList = new ArrayList<>();
        this.currentUser = null;
    }
}
