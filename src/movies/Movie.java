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

    public void incrementNumLikes() {
        numLikes++;
    }

    public void addRating(int rating) {
        numRatings++;
        totalScore += rating;
        this.rating = (double)totalScore / (double)numRatings;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", genres=" + genres +
                ", actors=" + actors +
                ", countriesBanned=" + countriesBanned +
                '}';
    }
}
