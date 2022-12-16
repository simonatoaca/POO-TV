package movies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    public Movie() {
        genres = new ArrayList<>();
        actors = new ArrayList<>();
        countriesBanned = new ArrayList<>();
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
        numRatings++;
        totalScore += rating;
        this.rating = (double) totalScore / (double) numRatings;
    }
}
