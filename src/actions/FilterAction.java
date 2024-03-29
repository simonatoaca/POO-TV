package actions;

import actionutils.Contains;
import actionutils.Filter;
import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.ActionInput;
import fileio.Output;
import fileio.OutputWriter;
import movies.Movie;
import streamingservice.StreamingService;
import webpages.MoviePage;

import java.util.ArrayList;
import java.util.List;


public class FilterAction extends Action {
    private final Filter filters;

    public FilterAction(final ActionInput action) {
        filters = action.getFilters();
    }

    /**
     * {@inheritDoc}:
     * Filters currentMoviesList and prints it to output on success
     */
    public void execute(final MoviePage page)
            throws JsonProcessingException {
        List<Movie> moviesAvailable = new ArrayList<>(StreamingService.getMovieList());

        if (StreamingService.getCurrentUser() != null) {
            String userCountry = StreamingService.getCurrentUser().getCredentials().getCountry();
            moviesAvailable.removeIf(movie -> movie.getCountriesBanned().contains(userCountry));
        }

        StreamingService.setCurrentMovieList(moviesAvailable);

        List<Movie> currentMovieList = StreamingService.getCurrentMovieList();

        if (this.filters.getSort() != null) {
            if (this.filters.getSort().getRating() != null) {
                switch (this.filters.getSort().getRating()) {
                    case "decreasing" -> {
                        currentMovieList.sort((o1, o2) ->
                                Double.compare(o2.getRating(), o1.getRating()));
                    }
                    case "increasing" -> {
                        currentMovieList.sort((o1, o2) ->
                                Double.compare(o1.getRating(), o2.getRating()));
                    }
                    default -> { }
                }
            }

            if (this.filters.getSort().getDuration() != null) {
                switch (this.filters.getSort().getDuration()) {
                    case "decreasing" -> {
                        currentMovieList.sort((o1, o2) ->
                                Integer.compare(o2.getDuration(), o1.getDuration()));
                    }
                    case "increasing" -> {
                        currentMovieList.sort((o1, o2) ->
                                Integer.compare(o1.getDuration(), o2.getDuration()));
                    }
                    default -> { }
                }
            }
        }

        Contains filterContains = this.filters.getContains();
        if (filterContains != null) {
            if (filterContains.getActors() != null) {
                for (String actor : this.filters.getContains().getActors()) {
                    currentMovieList.removeIf(movie -> !movie.getActors().contains(actor));
                }
            }

            if (filterContains.getGenre() != null) {
                for (String genre : this.filters.getContains().getGenre()) {
                    currentMovieList.removeIf(movie -> !movie.getGenres().contains(genre));
                }
            }
        }

        OutputWriter.addToOutput(new Output());
    }
}
