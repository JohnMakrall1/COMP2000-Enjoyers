import java.util.List;
import java.util.Comparator;
import java.util.Iterator;

public class Puzzle5 {

    // Starter: 471 characters (spaces and newlines count).
    // Shout every word, drop the words shorter than 3 letters,
    // then sort the survivors by length, shortest first,
    // e.g. ["banana","hi","apple","a"] -> ["APPLE","BANANA"].
    public static List<String> normalize(List<String> words) {
        words.replaceAll(String::toUpperCase);
        words.removeIf(w -> w.length() < 3);
        words.sort(Comparator.comparingInt(String::length));
        return words;
    }
}