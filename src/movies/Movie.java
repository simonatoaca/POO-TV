package movies;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import streamingservice.StreamingService;
import users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
public class Movie {
    private String name;
    private int year;
    private int duration;
    private List<String> genres;
    private List<String> actors;
    private List<String> countriesBanned;
    private int numLikes;
    private double rating;
    private int numRatings;

    @JsonIgnore
    private int totalScore;
    @JsonIgnore
    private Map<String, Integer> userRatings;

    public Movie() {
        genres = new ArrayList<>();
        actors = new ArrayList<>();
        countriesBanned = new ArrayList<>();
        userRatings = new HashMap<>();
    }

    /**
     * Deep copies a movie;
     * Used for output
     * @param movie the movie to be copied
     */
    public Movie(final Movie movie) {
        this.name = movie.name;
        this.rating = movie.rating;
        this.year = movie.year;
        this.duration = movie.duration;
        this.numLikes = movie.numLikes;
        this.numRatings = movie.numRatings;
        this.totalScore = movie.totalScore;
        this.genres = new ArrayList<>(movie.genres);
        this.actors = new ArrayList<>(movie.actors);
        this.countriesBanned = new ArrayList<>(movie.countriesBanned);
        this.userRatings = new HashMap<>(movie.userRatings);
    }

    /**
     * Increments the number of likes of this movie
     */
    public void incrementNumLikes() {
        numLikes++;
    }

    /**
     * Adds a rating to the movie and recalculates the average rating
     * @param rating the rating added
     */
    public void addRating(final int rating) {
        String currentUser = StreamingService.getCurrentUser()
                            .getCredentials().getName();

        if (!userRatings.containsKey(currentUser)) {
            userRatings.put(currentUser, rating);
            numRatings++;
        } else {
            totalScore -= userRatings.get(currentUser);
        }

        totalScore += rating;
        this.rating = (double) totalScore / (double) numRatings;
    }

    @JsonGetter("year")
    public String getYear() {
        return Integer.toString(year);
    }
}
