package persistence;

import model.Player;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {

    @Test
    void testBadFileRoute() {
        try {
            Player p = new Player("testName", 500);
            Writer writer = new Writer("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Player p = new Player("testName", 10000);
            Writer writer = new Writer("./data/testPlayers.json");
            writer.open();
            writer.write(p);
            writer.close();

            Reader reader = new Reader("./data/testPlayers.json");
            int amount = reader.read("testName");
            assertEquals(10000, p.getBankroll());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
