package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.ActionInput;
import fileio.Output;
import fileio.OutputWriter;
import movies.Movie;
import streamingservice.StreamingService;
import webpages.*;
import java.util.List;

public class SearchAction extends Action {

    public SearchAction(ActionInput action) {
        this.startsWith = action.getStartsWith();
    }

    public void execute(MoviePage page) throws JsonProcessingException {
        System.out.println("[SEARCH]");

        List<Movie> currentMovieList = StreamingService.getCurrentMovieList();
        currentMovieList.removeIf(movie -> !movie.getName().startsWith(this.startsWith));

        OutputWriter.addToOutput(new Output());
    }
}
