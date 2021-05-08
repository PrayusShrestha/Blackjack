package persistence;

import model.Player;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
/*
 *
 * Represents reader that reads the JSON data and return appropriate bankroll
 * Credits to the Demo project that was given for phase 2
 *
 */
public class Reader {
    private String route;

    // EFFECTS: constructs reader to read from source file
    public Reader(String route) {
        this.route = route;
    }

    // EFFECTS: reads names from file and returns corresponding bankrolls;
    //          throws IOException if an error occurs reading data from file
    //          throws JSONException if the given name is not found in the file
    public int read(String name) throws IOException, JSONException {
        String jsonData = readFile(route);
        JSONObject jsonObject = new JSONObject(jsonData);
        Player p = parsePlayer(jsonObject, name);
        return p.getBankroll();
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses player fields from JSON object and returns a newly created player
    private Player parsePlayer(JSONObject jsonObject, String name) {
        int amount = jsonObject.getInt(name);
        Player p = new Player(name, amount);
        return p;
    }
}
