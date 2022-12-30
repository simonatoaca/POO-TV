package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import database.Database;
import fileio.ActionInput;
import movies.Movie;
import webpages.HomepageAuthorized;
import webpages.HomepageUnauthorized;
import webpages.Login;
import webpages.MoviePage;
import webpages.Register;
import webpages.SeeDetails;
import webpages.Upgrades;

public class AddMovieAction extends Action {

    private final Movie addedMovie;
    public AddMovieAction(final ActionInput action) {
        addedMovie = action.getAddedMovie();
    }

    /**
     * Helper function that ads a new movie to the database
     */
    public void execute()
            throws JsonProcessingException {
        Database.getInstance().addMovie(addedMovie);
    }

    /**
     * {@inheritDoc}:
     * Adds a new movie to the database
     */
    @Override
    public void execute(final HomepageUnauthorized page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Adds a new movie to the database
     */
    @Override
    public void execute(final HomepageAuthorized page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Adds a new movie to the database
     */
    @Override
    public void execute(final Login page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Adds a new movie to the database
     */
    @Override
    public void execute(final Register page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Adds a new movie to the database
     */
    @Override
    public void execute(final MoviePage page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Adds a new movie to the database
     */
    @Override
    public void execute(final SeeDetails page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Adds a new movie to the database
     */
    @Override
    public void execute(final Upgrades page)
            throws JsonProcessingException {
        execute();
    }
}
