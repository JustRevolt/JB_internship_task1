import java.io.BufferedInputStream;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        AlphabetGenerator generator = new AlphabetGenerator();
        String alphabet = generator.generateAlphabet(System.in);
        System.out.println(alphabet);
    }
}
