package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import webpages.*;

public interface PageVisitor {
    void execute(HomepageUnauthorized page) throws JsonProcessingException;
    void execute(HomepageAuthorized page) throws JsonProcessingException;
    void execute(Login page) throws JsonProcessingException;
    void execute(Register page) throws JsonProcessingException;
    void execute(Logout page) throws JsonProcessingException;
    void execute(MoviePage page) throws JsonProcessingException;
    void execute(SeeDetails page) throws JsonProcessingException;
    void execute(Upgrades page) throws JsonProcessingException;
}
