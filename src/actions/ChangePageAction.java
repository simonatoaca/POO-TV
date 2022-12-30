package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import database.Database;
import fileio.ActionInput;
import fileio.Output;
import fileio.OutputWriter;
import movies.Movie;
import streamingservice.StreamingService;
import webpages.HomepageAuthorized;
import webpages.HomepageUnauthorized;
import webpages.Login;
import webpages.Logout;
import webpages.MoviePage;
import webpages.Page;
import webpages.Register;
import webpages.SeeDetails;
import webpages.Upgrades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChangePageAction extends Action {
    public ChangePageAction(final ActionInput action) {
        this.page = action.getPage();
        this.movie = action.getMovie();
    }

    /**
     * SeeDetails page specific actions,
     * Shows the movie details on success
     * @param nextPage the page to move to after seeDetails page
     */
    private void handleSeeDetails(final Page nextPage)
            throws JsonProcessingException {

        if (StreamingService.getCurrentUser() == null) {
            return;
        }

        // Movie to "see details" on
        Movie movieToBeSeen = Database.getInstance().getMovie(this.movie);

        // Get the available movies
        List<Movie> moviesAvailable = new ArrayList<>(StreamingService.getCurrentMovieList());

        // If the movie is available show it
        if (moviesAvailable.contains(movieToBeSeen)) {
            moviesAvailable.removeIf(movie -> !Objects.equals(movie.getName(), this.movie));
            StreamingService.setCurrentMovieList(moviesAvailable);
            ((SeeDetails) nextPage).setMovie(movieToBeSeen);
            StreamingService.getPageHistory().add(0, StreamingService.getCurrentPage());
            StreamingService.setCurrentPage(nextPage);
            OutputWriter.addToOutput(new Output());
        } else {
            ((SeeDetails) nextPage).setMovie(null);
            OutputWriter.addToOutput(new Output("Error"));
        }
    }

    /**
     * Movie page specific actions,
     * CurrentMoviesList is populated
     * @param nextPage the page to move to after movie page
     */
    private void handleMoviePage(final Page nextPage)
            throws JsonProcessingException {
        // Remove movies which are banned in the country of the current user
        List<Movie> movieList = StreamingService.getMovieList();
        List<Movie> moviesAvailable = new ArrayList<>(movieList);
        if (StreamingService.getCurrentUser() == null) {
            return;
        }

        String userCountry = StreamingService.getCurrentUser()
                .getCredentials()
                .getCountry();
        moviesAvailable.removeIf(movie -> movie.getCountriesBanned()
                .contains(userCountry));

        StreamingService.getPageHistory().add(0, StreamingService.getCurrentPage());
        StreamingService.setCurrentPage(nextPage);
        StreamingService.setCurrentMovieList(moviesAvailable);
        OutputWriter.addToOutput(new Output());
    }

    /**
     * Logout specific actions:
     * the current page becomes homepage unauthorized;
     * the user is set to null;
     * the current movie list is erased;
     */
    private void handleLogout() {
        StreamingService.setCurrentPage(new HomepageUnauthorized());
        StreamingService.setCurrentMovieList(new ArrayList<>());
        StreamingService.setCurrentUser(null);
        StreamingService.setPageHistory(new ArrayList<>());
    }

    /**
     * {@inheritDoc}:
     * Changes the page according to the input
     */
    public void execute(final Page page)
            throws JsonProcessingException {
        if (page.getSubPages() == null) {
            return;
        }

        boolean hasThisSubPage = page.getSubPages().contains(this.page);

        if (hasThisSubPage) {
            Page nextPage = Database.getInstance().getPage(this.page);

            if (Objects.equals(this.page, "see details")) {
                handleSeeDetails(nextPage);
                return;
            }

            if (Objects.equals(this.page, "movies")) {
                handleMoviePage(nextPage);
                return;
            }

            StreamingService.setCurrentPage(nextPage);
            StreamingService.getPageHistory().add(0, page);

            return;
        }

        if (Objects.equals(this.page, "logout")
                && StreamingService.getCurrentUser() != null) {
            handleLogout();
            return;
        }

        if ((Objects.equals(this.page, "login")
                || Objects.equals(this.page, "register"))
                && StreamingService.getCurrentUser() == null) {
            StreamingService.setCurrentPage(new HomepageUnauthorized());
        }

        OutputWriter.addToOutput(new Output("Error"));
    }

    /**
     * {@inheritDoc}:
     * Changes the page according to the input
     */
    @Override
    public void execute(final HomepageUnauthorized page)
            throws JsonProcessingException {
        execute((Page) page);
    }

    /**
     * {@inheritDoc}:
     * Changes the page according to the input
     */
    @Override
    public void execute(final HomepageAuthorized page)
            throws JsonProcessingException {
        execute((Page) page);
    }

    /**
     * {@inheritDoc}:
     * Changes the page according to the input
     */
    @Override
    public void execute(final Login page)
            throws JsonProcessingException {
        execute((Page) page);
    }

    /**
     * {@inheritDoc}:
     * Changes the page according to the input
     */
    @Override
    public void execute(final Register page)
            throws JsonProcessingException {
        execute((Page) page);
    }

    /**
     * {@inheritDoc}:
     * Changes the page according to the input
     */
    @Override
    public void execute(final Logout page)
            throws JsonProcessingException {
        execute((Page) page);
    }

    /**
     * {@inheritDoc}:
     * Changes the page according to the input
     */
    @Override
    public void execute(final MoviePage page)
            throws JsonProcessingException {
        execute((Page) page);
    }

    /**
     * {@inheritDoc}:
     * Changes the page according to the input
     */
    @Override
    public void execute(final SeeDetails page)
            throws JsonProcessingException {
        execute((Page) page);
    }

    /**
     * {@inheritDoc}:
     * Changes the page according to the input
     */
    @Override
    public void execute(final Upgrades page)
            throws JsonProcessingException {
        execute((Page) page);
    }
}
