package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WarAndPeaceExercise {

    public static String warAndPeace() {

        String str = (read("WAP12.txt") + read("WAP34.txt")).replaceAll("[^а-яА-Яa-zA-Z]", " ");

        return Arrays.stream(str.split(" ")).collect(Collectors.groupingBy(s -> s.toLowerCase())).entrySet().stream()
                .filter(it -> it.getKey().length() >= 4 && it.getValue().size() >= 10)
                .sorted((a, b) -> a.getValue().size() == b.getValue().size() ?
                (a.getKey().compareTo(b.getKey())) : b.getValue().size() - a.getValue().size())
                .map(it -> it.getKey() + " - " + it.getValue().size())
                .collect(Collectors.joining("\n"));

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