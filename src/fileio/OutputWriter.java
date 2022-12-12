package fileio;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class OutputWriter {
    private static ArrayNode output;

    public static void addToOutput(Output output) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (OutputWriter.output == null) {
            OutputWriter.output = objectMapper.createArrayNode();
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("error", output.getError());
        objectNode.put("currentMovieList", objectMapper.writeValueAsString(output.getCurrentMovieList()));
        objectNode.put("currentUser", objectMapper.writeValueAsString(output.getCurrentUser()));

        OutputWriter.output.add(objectNode);
    }

    public static void write(String outputFileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(outputFileName), output);
    }
}
