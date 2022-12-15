package actions;

import actionutils.Contains;
import actionutils.Filter;
import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.Output;
import fileio.OutputWriter;
import movies.Movie;
import streamingservice.StreamingService;
import webpages.*;

import java.util.ArrayList;
import java.util.List;


public class FilterAction extends Action
                    implements PageVisitor {

    private Filter filters;

    public FilterAction(ActionInput action) {
        filters = action.getFilters();
    }

    @Override
    public void execute(HomepageUnauthorized page) throws JsonProcessingException {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(HomepageAuthorized page) throws JsonProcessingException {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(Login page) throws JsonProcessingException {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(Register page) throws JsonProcessingException {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(Logout page) throws JsonProcessingException {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }

    public void execute(MoviePage page) throws JsonProcessingException {
        System.out.println("[FILTER]");
        List<Movie> moviesAvailable = new ArrayList<>(StreamingService.getMovieList());
        if (StreamingService.getCurrentUser() != null) {
            String userCountry = StreamingService.getCurrentUser().getCredentials().getCountry();
            moviesAvailable.removeIf(movie -> movie.getCountriesBanned().contains(userCountry));
        }

        StreamingService.setCurrentMovieList(moviesAvailable);

        List<Movie> currentMovieList = StreamingService.getCurrentMovieList();
        if (this.filters.getSort() != null) {
            switch (this.filters.getSort().getRating()) {
                case "decreasing" -> {
                    currentMovieList.sort((o1, o2) -> Double.compare(o2.getRating(), o1.getRating()));
                }
                case "increasing" -> {
                    currentMovieList.sort((o1, o2) -> Double.compare(o1.getRating(), o2.getRating()));
                }
            }

            switch (this.filters.getSort().getDuration()) {
                case "decreasing" -> {
                    currentMovieList.sort((o1, o2) -> Double.compare(o2.getDuration(), o1.getDuration()));
                }
                case "increasing" -> {
                    currentMovieList.sort((o1, o2) -> Double.compare(o1.getDuration(), o2.getDuration()));
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

    @Override
    public void execute(SeeDetails page) throws JsonProcessingException {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(Upgrades page) throws JsonProcessingException {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
        OutputWriter.addToOutput(new Output("Error"));
    }
}
