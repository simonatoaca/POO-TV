package fileio;

import lombok.Getter;
import lombok.Setter;
import movies.Movie;
import streamingservice.StreamingService;
import users.User;

import java.util.List;

@Getter
@Setter
public class Output {
    private String error;
    private List<Movie> currentMovieList;
    private User currentUser;

    public Output() {
        this.currentMovieList = StreamingService.getCurrentMovieList();
        this.currentUser = StreamingService.getCurrentUser();
    }

    public Output(String error) {
        this.error = error;
        this.currentMovieList = StreamingService.getCurrentMovieList();
        this.currentUser = StreamingService.getCurrentUser();
    }
}
