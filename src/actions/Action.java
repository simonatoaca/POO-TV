package actions;

import actionutils.Filter;
import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.Output;
import fileio.OutputWriter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import users.Credentials;
import webpages.HomepageAuthorized;
import webpages.HomepageUnauthorized;
import webpages.Login;
import webpages.Logout;
import webpages.MoviePage;
import webpages.Register;
import webpages.SeeDetails;
import webpages.Upgrades;

@Getter
@Setter
@NoArgsConstructor
public class Action implements PageVisitor {
    protected String type;
    protected String page;
    protected String movie;
    protected String feature;
    protected String startsWith;
    protected Credentials credentials;
    protected int count;
    protected int rate;
    protected Filter filters;
    private String subscribedGenre;
    private String addedMovie;
    private String deletedMovie;


    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final HomepageUnauthorized currentPage) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final HomepageAuthorized currentPage) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Login currentPage) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Register currentPage) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Logout currentPage) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final MoviePage currentPage) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final SeeDetails currentPage) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Upgrades currentPage) throws JsonProcessingException {
        OutputWriter.addToOutput(new Output("Error"));
    }
}
