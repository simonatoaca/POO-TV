package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import database.Database;
import fileio.ActionInput;
import movies.Movie;
import webpages.*;

public class DeleteMovieAction extends Action {
    private final String deletedMovie;
    public DeleteMovieAction(final ActionInput action) {
        deletedMovie = action.getDeletedMovie();
    }

    public void execute()
            throws JsonProcessingException {
        Database.getInstance().deleteMovie(deletedMovie);
    }

    /**
     * {@inheritDoc}:
     * Deletes the movie from the database
     */
    @Override
    public void execute(final HomepageUnauthorized page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Deletes the movie from the database
     */
    @Override
    public void execute(final HomepageAuthorized page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Deletes the movie from the database
     */
    @Override
    public void execute(final Login page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Deletes the movie from the database
     */
    @Override
    public void execute(final Register page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Deletes the movie from the database
     */
    @Override
    public void execute(final MoviePage page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Deletes the movie from the database
     */
    @Override
    public void execute(final SeeDetails page)
            throws JsonProcessingException {
        execute();
    }

    /**
     * {@inheritDoc}:
     * Deletes the movie from the database
     */
    @Override
    public void execute(final Upgrades page)
            throws JsonProcessingException {
        execute();
    }
}
