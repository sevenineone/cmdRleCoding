package rleTest;

import main.java.rle.RleLaunch;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class rleTest {
    private static String readInString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    private String[] arguments1 = {"-u", "-out", "test\\outputTests\\outTest1",
            "test\\inputTests\\inTest1"};

    @Test
    public void test1() {
        new RleLaunch().launch(arguments1);
        String ans1 = "aaabcvdddd\n";
        String res1 = readInString("test\\outputTests\\outTest1");
        assertEquals(ans1, res1);
    }

    private String[] arguments2 = {"-z", "-out", "test\\outputTests\\outTest2",
            "test\\inputTests\\inTest2"};

    @Test
    public void test2() {
        new RleLaunch().launch(arguments2);
        String ans2 = "4b-9asdfghjkl-3qwe9f9f8f-2df9d3d\n";
        String res2 = readInString("test\\outputTests\\outTest2");
        assertEquals(ans2, res2);
    }


}
