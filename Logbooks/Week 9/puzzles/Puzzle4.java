import java.util.List;

public class Puzzle4 {

    // Starter: 140 characters (spaces and newlines count).
    // Return every word in upper case,
    // e.g. ["hi","yo"] -> ["HI","YO"].
    public static List<String> upperAll(List<String> words) {
        words.replaceAll(String::toUpperCase); return words;
    }
}