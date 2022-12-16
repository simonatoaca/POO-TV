package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import database.Database;
import fileio.Output;
import fileio.OutputWriter;
import movies.Movie;
import streamingservice.StreamingService;
import webpages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChangePageAction extends Action
        implements PageVisitor {
    public ChangePageAction(ActionInput action) {
        this.page = action.getPage();
        this.movie = action.getMovie();
    }

    public void execute(Page page) throws JsonProcessingException {
        boolean hasThisSubPage = false;
        if (page.getSubPages() != null) {
            hasThisSubPage = page.getSubPages().contains(this.page);
        }

        if (hasThisSubPage) {
            Page nextPage = Database.getInstance().getPage(this.page);
            StreamingService.setCurrentPage(nextPage);
            System.out.println("[CHANGE PAGE] to " + this.page);

            if (Objects.equals(this.page, "see details")) {
                // Movie to "see details" on
                Movie movieToBeSeen = Database.getInstance().getMovie(this.movie);

                // Get the available movies
                List<Movie> moviesAvailable = new ArrayList<>(StreamingService.getMovieList());
                if (StreamingService.getCurrentUser() != null) {
                    String userCountry = StreamingService.getCurrentUser().getCredentials().getCountry();
                    moviesAvailable.removeIf(movie -> movie.getCountriesBanned().contains(userCountry));
                }

                // If the movie is available show it
                if (moviesAvailable.contains(movieToBeSeen)) {
                    moviesAvailable.removeIf(movie -> !Objects.equals(movie.getName(), this.movie));
                    StreamingService.setCurrentMovieList(moviesAvailable);
                    ((SeeDetails)nextPage).setMovie(movieToBeSeen);
                    OutputWriter.addToOutput(new Output());
                } else {
                    ((SeeDetails)nextPage).setMovie(null);
                    OutputWriter.addToOutput(new Output("Error"));
                }
            }

            if (Objects.equals(this.page, "movies")) {
                // Remove movies which are banned in the country of the current user
                List<Movie> moviesAvailable = new ArrayList<>(StreamingService.getMovieList());
                if (StreamingService.getCurrentUser() != null) {
                    String userCountry = StreamingService.getCurrentUser().getCredentials().getCountry();
                    moviesAvailable.removeIf(movie -> movie.getCountriesBanned().contains(userCountry));
                }

                StreamingService.setCurrentMovieList(moviesAvailable);
                OutputWriter.addToOutput(new Output());
            }

            return;
        }

        if (Objects.equals(this.page, "logout")
                && StreamingService.getCurrentUser() != null) {
            System.out.println("[LOGOUT]");
            StreamingService.setCurrentPage(new HomepageUnauthorized());
            StreamingService.setCurrentMovieList(new ArrayList<>());
            StreamingService.setCurrentUser(null);
            return;
        }

        if ((Objects.equals(this.page, "login") || Objects.equals(this.page, "register")) &&
        StreamingService.getCurrentUser() == null) {
            StreamingService.setCurrentPage(new HomepageUnauthorized());
        }

        OutputWriter.addToOutput(new Output("Error"));
    }

    @Override
    public void execute(HomepageUnauthorized page) throws JsonProcessingException {
        execute((Page)page);
    }

    public void execute(HomepageAuthorized page) throws JsonProcessingException {
        execute((Page)page);
    }

    @Override
    public void execute(Login page) throws JsonProcessingException {
        execute((Page)page);
    }

    @Override
    public void execute(Register page) throws JsonProcessingException {
        execute((Page)page);
    }

    @Override
    public void execute(Logout page) throws JsonProcessingException {
        execute((Page)page);
    }

    @Override
    public void execute(MoviePage page) throws JsonProcessingException {
        execute((Page)page);
    }

    @Override
    public void execute(SeeDetails page) throws JsonProcessingException {
        execute((Page)page);
    }

    @Override
    public void execute(Upgrades page) throws JsonProcessingException {
        execute((Page)page);
    }
}
