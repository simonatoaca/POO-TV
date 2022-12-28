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

public class GoBackAction extends Action {
    public void execute()
            throws JsonProcessingException {
        System.out.println("[BACK]");

        if (StreamingService.getCurrentUser() == null) {
            OutputWriter.addToOutput(new Output("Error"));
            return;
        }

        if (StreamingService.getPageHistory().isEmpty()) {
            OutputWriter.addToOutput(new Output("Error"));
            return;
        }

        Page previousPage = StreamingService.getPageHistory().remove(0);
        System.out.println(" to " + previousPage.getPageName());

        changePage(previousPage);
    }


    /**
     * SeeDetails page specific actions,
     * Shows the movie details on success
     * @param nextPage the page to move to after seeDetails page
     */
    private void handleSeeDetails(final Page nextPage)
            throws JsonProcessingException {
        // Movie to "see details" on
        Movie movieToBeSeen = Database.getInstance().getMovie(this.movie);

        // Get the available movies
        List<Movie> moviesAvailable = new ArrayList<>(StreamingService.getMovieList());
        if (StreamingService.getCurrentUser() == null) {
            return;
        }

        String userCountry = StreamingService.getCurrentUser().getCredentials().getCountry();
        moviesAvailable.removeIf(movie -> movie.getCountriesBanned().contains(userCountry));

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
     * {@inheritDoc}:
     * Changes the page according to the input
     */
    public void changePage(final Page previousPage)
            throws JsonProcessingException {

        if (Objects.equals(previousPage.getPageName(), "see details")) {
            handleSeeDetails(previousPage);
            return;
        }

        if (Objects.equals(previousPage.getPageName(), "movies")) {
            handleMoviePage(previousPage);
            return;
        }

        if ((Objects.equals(previousPage.getPageName(), "login")
                || Objects.equals(previousPage.getPageName(), "register"))) {
            System.out.println("back to login/register");
//            StreamingService.setCurrentPage(new HomepageUnauthorized());
            OutputWriter.addToOutput(new Output("Error"));
            return;
        }

        StreamingService.getPageHistory().add(0, StreamingService.getCurrentPage());
        StreamingService.setCurrentPage(previousPage);
    }

    /**
     * {@inheritDoc}:
     * Goes to the previous page
     */
    @Override
    public void execute(final HomepageAuthorized page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Goes to the previous page
     */
    @Override
    public void execute(final Login page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Goes to the previous page
     */
    @Override
    public void execute(final Register page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Goes to the previous page
     */
    @Override
    public void execute(final MoviePage page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Goes to the previous page
     */
    @Override
    public void execute(final SeeDetails page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Goes to the previous page
     */
    @Override
    public void execute(final Upgrades page)
            throws JsonProcessingException {
        execute();
    }
}
