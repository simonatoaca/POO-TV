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
            StreamingService.setCurrentPage(Database.getInstance().getPage(this.page));
            System.out.println("[CHANGE PAGE] to " + this.page);

            if (Objects.equals(this.page, "see details")) {
                Movie movie = Database.getInstance().getMovie(this.movie);
                ((SeeDetails)StreamingService.getCurrentPage()).setMovie(movie);
            }

            if (Objects.equals(this.page, "movies")
                    || Objects.equals(this.page, "see details")) {

                // Remove movies which are banned in the country of the current user
                List<Movie> moviesAvailable = new ArrayList<>(StreamingService.getMovieList());
                String userCountry = StreamingService.getCurrentUser().getCredentials().getCountry();
                moviesAvailable.removeIf(movie -> movie.getCountriesBanned().contains(userCountry));

                StreamingService.setCurrentMovieList(moviesAvailable);
                OutputWriter.addToOutput(new Output());
            }

            return;
        }

        if (Objects.equals(this.page, "logout")) {
            System.out.println("[LOGOUT]");
            StreamingService.setCurrentPage(new HomepageUnauthorized());
            StreamingService.setCurrentUser(null);
            return;
        }

        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentUser(null);
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
