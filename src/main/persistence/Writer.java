package persistence;

import model.Player;
import org.json.JSONObject;


import java.io.*;


/*
 *
 * Represents a writer that writes JSON representation of player to file
 * This class uses code from the given demo project for phase 2
 *
 */
public class Writer {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public Writer(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of player to file
    public void write(Player p) {
       //  JSONObject json = p.toJson();
        JSONObject json = new JSONObject();
        json.put(p.getName(), p.getBankroll());
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
