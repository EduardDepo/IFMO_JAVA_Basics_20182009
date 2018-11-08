package ru.ifmo.cet.javabasics;


public class BottleSong {

    private int bottleT;
    private final static String [] numbers = {"zero", "one", "two", "three", "four", "five", "six",
            "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "thirty", "forty", "fifty",
            "sixty", "seventy", "eighty", "ninety"};

    public BottleSong(int bottleTakenAtOnce) {
        if (bottleTakenAtOnce < 1 || bottleTakenAtOnce > 99)
            throw new IllegalArgumentException("Недопустимое количество бутылок");
        bottleT = bottleTakenAtOnce;
        //TODO
    }
    private String numberName(int n)
    {
        if (n <= 20)
            return numbers[n];
        if (n % 10 != 0)
            return(numbers[n/10 + 18] + " " + numbers[n%10]);
        else
            return numbers[n/10 + 18];
    }

    public String getBottleSongLyrics() {
        StringBuilder sb = new StringBuilder();
        int left = 99;

        sb.append("99 bottles of beer on the wall, 99 bottles of beer.\n");
        for (int i = 99 - bottleT; i>0; i-= bottleT)
        {
            String end = i > 1 ? "s" : "";
            sb.append(String.format("Take %s down and pass around, %d bottle%s of beer on the wall.\n", numberName(bottleT), i, end));
            sb.append(String.format("%d bottle%s of beer on the wall, %d bottle%s of beer.\n", i, end, i, end));
            left = i;
        }

        sb.append(String.format("Take %s down and pass around, no more bottles of beer on the wall.\n" +
                "No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, 99 bottles of beer on the wall.\n", numberName(left)));

        return sb.toString();
    }
}
