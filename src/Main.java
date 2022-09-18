import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static int MaxSymbolsInWord = 30;

    public static void main(String[] args) throws IOException {
        Path pathname = Paths.get("src/Input.txt");
        String data = Files.readString(pathname);
        List<String> words = SplitByEvrything(data);

        Map<String , Integer> NikaRes = countWords(words);

        System.out.println(NikaRes);
    }

    private static Map<String , Integer> countWords(List<String> strings){
        Map<String , Integer> res = new HashMap<>();
        for (var str : strings) {
            res.computeIfAbsent(str, s -> 0);
            var localRes = res.get(str);
            localRes++;
            res.replace(str , localRes);
        }
        return res;
    }


    private static List<String> SplitByEvrything(String data) {
        List<String> res = new ArrayList<String>();
        int oldStart = 0 , currentIndex = -1;
        while(currentIndex < data.length()){
            currentIndex++;
            if (currentIndex != data.length() && ((data.charAt(currentIndex) >= 'a' && data.charAt(currentIndex) <= 'z') || (data.charAt(currentIndex) >= 'A' && data.charAt(currentIndex) <= 'Z'))){
                continue;
            }

            String substr = data.substring(oldStart , currentIndex);
            if (substr.length() > MaxSymbolsInWord)
                substr = substr.substring(0 , MaxSymbolsInWord);
            if (substr.length() > 0)
                res.add(substr);
            oldStart = currentIndex + 1;
        }
        return res;
    }
}