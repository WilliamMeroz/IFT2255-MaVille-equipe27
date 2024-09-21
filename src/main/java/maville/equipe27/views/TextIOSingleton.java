package maville.equipe27.views;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class TextIOSingleton {
    private static TextIO instance;

    private TextIOSingleton() { }

    public static TextIO getInstance() {
        if (instance == null) {
            instance = TextIoFactory.getTextIO();
        }

        return instance;
    }
}
