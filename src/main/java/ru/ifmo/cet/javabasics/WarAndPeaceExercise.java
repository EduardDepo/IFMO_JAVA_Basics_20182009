package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;


public class WarAndPeaceExercise {

    public static String warAndPeace() {
        //final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        //final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        String s = (read("WAP12.txt") + read("WAP34.txt")).replaceAll("[^а-яА-Яa-zA-Z]", " ");
        int i = 0;
        int bword;
        int eword;
        HashMap<String, Integer> wap = new HashMap<>();
        while (i < s.length()) {
            while ( i < s.length() && (Character.isSpaceChar(s.charAt(i))))
                i++;
            bword = i;
            while (i < s.length() && !Character.isSpaceChar(s.charAt(i)))
                i++;
            eword = i;
            if (!Character.isSpaceChar(s.charAt(i - 1)) && (eword - bword >= 4)) {
                if (wap.containsKey(s.substring(bword, eword).toLowerCase()))
                    wap.put(s.substring(bword, eword).toLowerCase(), wap.get(s.substring(bword, eword).toLowerCase()) + 1);
                else
                    wap.put(s.substring(bword, eword).toLowerCase(), 1);
            }
        }

        SortedSet<WordCount> v = new TreeSet<>();

        for (Map.Entry<String, Integer> it : wap.entrySet()){
            if (it.getValue() >= 10)
            {
                WordCount wc = new WordCount();
                wc.word = it.getKey();
                wc.count = it.getValue();
                v.add(wc);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (WordCount wc : v)
            sb.append(String.format("%s - %d\n", wc.word, wc.count));

        String res = sb.toString();
        return res.substring(0, res.length() - 1);
    }

    private static String read(String fileName)
    {
        try {
            return String.join(" ", Files.readAllLines(Paths.get("src", "main", "resources", fileName),
                    Charset.forName("windows-1251")));
        }catch(IOException ex)
        {
            return "";
        }
    }
}

class WordCount implements Comparable<WordCount>
{
    public String word;
    public int count;

    @Override
    public int compareTo(WordCount o) {
        if (this.count == o.count)
            return this.word.compareTo(o.word);
        else
            return o.count - this.count;
    }
}
