package users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Notification {
    // The default notification is the no recommendation one
    private String movieName = NO_REC;
    private String message = REC;

    public Notification(final String movieName, final String message) {
        this.movieName = movieName;
        this.message = message;
    }

    @JsonIgnore
    public static final String ADD = "ADD";
    @JsonIgnore
    public static final String DELETE = "DELETE";
    @JsonIgnore
    public static final String REC = "Recommendation";
    @JsonIgnore
    public static final String NO_REC = "No recommendation";
}
