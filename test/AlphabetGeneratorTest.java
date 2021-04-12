import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AlphabetGeneratorTest {

    private final InputStream stdIn = System.in;
    private AlphabetGenerator generator;

    @Before
    public void setUp() {
        generator = new AlphabetGenerator();
    }

    @After
    public void restoreInputStream() {
        System.setIn(stdIn);
    }

    public void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testRightSequence() throws IOException {
        FileInputStream fstream = new FileInputStream("test/rightSequence.txt");
        String input = "";
        int i;

        while ((i = fstream.read()) != -1) {
            input = input.concat(String.valueOf((char) i));
        }

        provideInput(input);
        String expected = "atrnlbcdefghijkmopqsuvwxyz";
        String actual = generator.generateAlphabet();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testImpossibleSequence() throws IOException {
        FileInputStream fstream = new FileInputStream("test/impossibleSequence.txt");
        String input = "";
        int i;

        while ((i = fstream.read()) != -1) {
            input = input.concat(String.valueOf((char) i));
        }

        provideInput(input);
        String expected = "Impossible";
        String actual = generator.generateAlphabet();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFullLettersSequence() throws IOException {
        FileInputStream fstream = new FileInputStream("test/fullLettersSequence.txt");
        String input = "";
        int i;

        while ((i = fstream.read()) != -1) {
            input = input.concat(String.valueOf((char) i));
        }

        provideInput(input);
        String expected = "abcdefghijklmnopqrstuvwxyz";
        String actual = generator.generateAlphabet();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEqualLettersSequence() throws IOException {
        FileInputStream fstream = new FileInputStream("test/equalLettersSequence.txt");
        String input = "";
        int i;

        while ((i = fstream.read()) != -1) {
            input = input.concat(String.valueOf((char) i));
        }

        provideInput(input);
        String expected = "Impossible";
        String actual = generator.generateAlphabet();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIncorrectOrder() throws IOException {
        FileInputStream fstream = new FileInputStream("test/incorrectOrder.txt");
        String input = "";
        int i;

        while ((i = fstream.read()) != -1) {
            input = input.concat(String.valueOf((char) i));
        }

        provideInput(input);
        String expected = "Impossible";
        String actual = generator.generateAlphabet();

        Assert.assertEquals(expected, actual);
    }
}