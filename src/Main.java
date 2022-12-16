import streamingservice.StreamingService;

import java.io.IOException;

public class Main {
    public static void main(final String[] args) throws IOException {
        StreamingService.start(args[0], args[1]);
    }
}
