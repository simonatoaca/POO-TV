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

public final class OutputWriter {
    private static ArrayNode output;
    private OutputWriter() { }

    /**
     * Initializes the array node in which the output will be put
     * before writing it into the output file
     */
    public static void config() {
        ObjectMapper objectMapper = new ObjectMapper();
        OutputWriter.output = objectMapper.createArrayNode();
    }

    /**
     * Adds a new entry in the array node
     * @param outputObject the entry
     */
    public static void addToOutput(final Output outputObject)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("error", outputObject.getError());

        if (outputObject.getCurrentMoviesList() == null) {
            objectNode.putPOJO("currentMoviesList", null);
        } else {
            ArrayNode currentMoviesList = objectMapper.createArrayNode();
            for (Movie movie : outputObject.getCurrentMoviesList()) {
                currentMoviesList.addPOJO(new Movie(movie));
            }

            objectNode.putPOJO("currentMoviesList", currentMoviesList);
        }

        User user = null;
        if (outputObject.getCurrentUser() != null) {
            user = new User(outputObject.getCurrentUser());
        }

        objectNode.putPOJO("currentUser", user);

        OutputWriter.output.add(objectNode);
    }

    /**
     * Writes the array node to the output file using pretty printer
     * @param outputFileName the output file
     */
    public static void write(final String outputFileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(outputFileName), output);
    }
}
