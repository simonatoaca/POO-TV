package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import webpages.*;

public interface PageVisitor {
    /**
     * Contains the implementation specific to the page
     * that is visited by it (the action)
     * @param page page that is visited
     */
    void execute(HomepageUnauthorized page) throws JsonProcessingException;

    /**
     * Contains the implementation specific to the page
     * that is visited by it (the action)
     * @param page page that is visited
     */
    void execute(HomepageAuthorized page) throws JsonProcessingException;

    /**
     * Contains the implementation specific to the page
     * that is visited by it (the action)
     * @param page page that is visited
     */
    void execute(Login page) throws JsonProcessingException;

    /**
     * Contains the implementation specific to the page
     * that is visited by it (the action)
     * @param page page that is visited
     */
    void execute(Register page) throws JsonProcessingException;

    /**
     * Contains the implementation specific to the page
     * that is visited by it (the action)
     * @param page page that is visited
     */
    void execute(Logout page) throws JsonProcessingException;

    /**
     * Contains the implementation specific to the page
     * that is visited by it (the action)
     * @param page page that is visited
     */
    void execute(MoviePage page) throws JsonProcessingException;

    /**
     * Contains the implementation specific to the page
     * that is visited by it (the action)
     * @param page page that is visited
     */
    void execute(SeeDetails page) throws JsonProcessingException;

    /**
     * Contains the implementation specific to the page
     * that is visited by it (the action)
     * @param page page that is visited
     */
    void execute(Upgrades page) throws JsonProcessingException;
}
