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
        System.out.println("[SUCCESS]");
        this.currentMoviesList = StreamingService.getCurrentMovieList();
        this.currentUser = StreamingService.getCurrentUser();
    }

    public Output(String error) {
        System.out.println("[ERROR]");
        this.error = error;
        this.currentMoviesList = new ArrayList<>();
        this.currentUser = null;
    }
}
