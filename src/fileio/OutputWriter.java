package fileio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import movies.Movie;
import users.User;

import java.io.File;
import java.io.IOException;

public class OutputWriter {
    private static ArrayNode output;

    public static void config() {
        ObjectMapper objectMapper = new ObjectMapper();
        OutputWriter.output = objectMapper.createArrayNode();
    }
    public static void addToOutput(Output output) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("error", output.getError());

        ArrayNode currentMoviesList = objectMapper.createArrayNode();
        for (Movie movie : output.getCurrentMoviesList()) {
            currentMoviesList.addPOJO(new Movie(movie));
        }

        objectNode.putPOJO("currentMoviesList", currentMoviesList);

        User user = null;
        if (output.getCurrentUser() != null) {
            user = new User(output.getCurrentUser());
        }
        objectNode.putPOJO("currentUser", user);

        OutputWriter.output.add(objectNode);
    }

    public static void write(String outputFileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(outputFileName), output);
    }
}
