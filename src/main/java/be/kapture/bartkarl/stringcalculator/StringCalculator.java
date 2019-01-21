package be.kapture.bartkarl.stringcalculator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StringCalculator {
    private static final String DEFAULT_DELIMITERS = ",|\n";

    public static int add(String s) {
        requireNonNull(s);

        if (s.length() == 0) {
            return 0;
        }

        List<Integer> s1 = getNumbers(s);
        requireNoNegativeNumbers1(s1);

        return s1.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static void requireNoNegativeNumbers(List<Integer> s1) {
        String negativeNumbersString = s1.stream()
                .filter(i -> i < 0)
                .map(Object::toString)
                .collect(joining(" "));
        if (!negativeNumbersString.isEmpty()) {
            throw new IllegalArgumentException(negativeNumbersString);
        }
    }

    private static void requireNoNegativeNumbers1(List<Integer> integerList) {
        integerList.stream()
                .filter(i -> i < 0)
                .map(Object::toString)
                .reduce((s1, s2) -> String.join(" ", s1, s2))
                .ifPresent(s -> {
                    throw new IllegalArgumentException(s);
                });

    }

    private static List<Integer> getNumbers(String s) {
        if (s.startsWith("//")) {
            String delimitersRegex = fetchDelimitersRegex(s.substring(2, 3));
            return getNumbersWithDelimiters(s.substring(4), delimitersRegex);
        }
        return getNumbersWithDelimiters(s, DEFAULT_DELIMITERS);
    }

    private static String fetchDelimitersRegex(String substring) {
        return substring + "|\n";
    }

    private static List<Integer> getNumbersWithDelimiters(String s, String delimiters) {
        return Stream.of(s.split(delimiters))
                .map(Integer::parseInt)
                .collect(toList());
    }

}
