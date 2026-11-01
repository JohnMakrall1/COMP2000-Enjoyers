import java.util.List;

public class Puzzle6 {

    // Starter: 166 characters (spaces and newlines count).
    // Return every word reversed,
    // e.g. ["hi","cat"] -> ["ih","tac"].
    public static List<String> reversed(List<String> words) {
        words.replaceAll(w -> new StringBuilder(w).reverse().toString()); return words;
    }
}