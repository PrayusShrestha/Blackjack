package persistence;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    String failRoute;
    String realRoute;

    @BeforeEach
    void setup() {
        failRoute = "./data/test/nothingHere.json";
        realRoute = "./data/test.json";
    }

    @Test
    void testReaderNoFile() {
        String testName = "Prayus";
        Reader reader = new Reader(failRoute);
        try {
            int amount = reader.read(testName);
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNoName() {
        String testName = "Joe";
        Reader reader = new Reader(realRoute);
        try {
            int amount = reader.read(testName);
            fail("JSONException expected");
        } catch (IOException e) {
            fail("JSONException expected");
        } catch (JSONException e) {
            // pass
        }
    }

    @Test
    void testReaderFoundName() {
        String testName = "Prayus";
        Reader reader = new Reader(realRoute);

        try {
            int amount = reader.read(testName);
            assertEquals(10, amount);
        } catch (IOException e) {
            fail("Name should be found");
        } catch (JSONException e) {
            fail("Name should be found");
        }
    }



}
