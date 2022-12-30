package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.ActionInput;
import fileio.Output;
import fileio.OutputWriter;
import movies.Movie;
import streamingservice.StreamingService;
import webpages.MoviePage;

import java.util.List;

public class SearchAction extends Action {

    public SearchAction(final ActionInput action) {
        this.startsWith = action.getStartsWith();
    }

    /**
     * {@inheritDoc}:
     * The user searches for a movie that starts with a certain word
     */
    public void execute(final MoviePage page) throws JsonProcessingException {
        List<Movie> currentMovieList = StreamingService.getCurrentMovieList();
        currentMovieList.removeIf(movie -> !movie.getName().startsWith(this.startsWith));

        OutputWriter.addToOutput(new Output());
    }
}
