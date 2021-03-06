package countries;

import java.io.IOException;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

import java.time.ZoneId;

public class Homework2 {

    private List<Country> countries;

    public Homework2() {
        countries = new CountryRepository().getAll();
    }

    /**
     * Returns the longest country name translation.
     */
    public Optional<String> streamPipeline1() {
        
        return countries.stream().flatMap(n -> n.getTranslations().values().stream())
                .max(Comparator.comparing(m -> m.length()));
    }

    /**
     * Returns the longest Italian (i.e., {@code "it"}) country name translation.
     */
    public Optional<String> streamPipeline2() {
        
        return countries.stream().flatMap(n -> n.getTranslations().entrySet().stream())
                .filter(n -> n.getKey() == "it")
                .max(Comparator.comparing(n -> n.getValue().length()))
                .map(n -> n.getValue());
    }

    /**
     * Prints the longest country name translation together with its language code in the form language=translation.
     */
    public void streamPipeline3() {
        System.out.println(countries.stream()
                .flatMap(n -> n.getTranslations().entrySet().stream())
                .max(Comparator.comparingInt(n -> n.getValue().length()))
                .get());
    }

    /**
     * Prints single word country names (i.e., country names that do not contain any space characters).
     */
    public void streamPipeline4() {
         countries.stream()
                .map(Country::getName)
                .filter(n -> !n.contains(" "))
                .forEach(System.out::println);
    }

    /**
     * Returns the country name with the most number of words.
     */
    public Optional<String> streamPipeline5() {
        
        return countries.stream()
                .max(Comparator.comparingInt(n -> n.getName().split(" ").length))
                .map(Country::getName);
    }

    /**
     * Returns whether there exists at least one capital that is a palindrome.
     */
    public boolean streamPipeline6() {
         return countries.stream()
                .map(Country::getCapital)
                .anyMatch(n -> n.toLowerCase().equals(new StringBuilder(n).reverse().toString()));
    }

    /**
     * Returns the country name with the most number of {@code 'e'} characters ignoring case.
     */

   private int charCounter(String s, char c)
   {
        int count = 0;
        for (char item : s.toLowerCase().toCharArray()) 
        {
            if (item == c)
                count++;
        }
        return count;
    }

    public Optional<String> streamPipeline7() {
         return countries.stream()
                .map(Country::getName)
                .max(Comparator.comparingInt(n -> charCounter(n, 'e')));
    }

    /**
     *  Returns the capital with the most number of English vowels (i.e., {@code 'a'}, {@code 'e'}, {@code 'i'}, {@code 'o'}, {@code 'u'}).
     */

    private int vowelCounter(String s)
    {
        return charCounter(s, 'a') +
                charCounter(s, 'e') +
                charCounter(s, 'i') +
                charCounter(s, 'o') +
                charCounter(s, 'u');
    }

    public Optional<String> streamPipeline8() {
        return countries.stream()
                .map(Country::getCapital)
                .max(Comparator.comparingInt(n -> vowelCounter(n)));
    }

    /**
     * Returns a map that contains for each character the number of occurrences in country names ignoring case.
     */
    public Map<Character, Long> streamPipeline9() {
         return countries.stream()
                .map(Country::getName)
                .flatMap(n -> n.toLowerCase().chars().mapToObj(c -> (char)c))
                .collect(groupingBy(n->n, counting()));
    }

    /**
     * Returns a map that contains the number of countries for each possible timezone.
     */
    public Map<ZoneId, Long> streamPipeline10() {
         return countries.stream()
                .map(Country::getTimezones)
                .flatMap(n -> n.stream())
                .collect(groupingBy(n->n, counting()));
    }

    /**
     * Returns the number of country names by region that starts with their two-letter country code ignoring case.
     */
    public Map<Region, Long> streamPipeline11() {
        return countries.stream()
               .filter(n -> n.getCode().equalsIgnoreCase(n.getName().substring(0,2)))
               .collect(groupingBy(n -> n.getRegion(), counting()));
    }

    /**
     * Returns a map that contains the number of countries whose population is greater or equal than the population average versus the the number of number of countries with population below the average.
     */
    public Map<Boolean, Long> streamPipeline12() {
         return countries.stream().
                collect(groupingBy(n -> n.getPopulation()>=countries.stream().mapToLong(Country::getPopulation).average().getAsDouble(), counting()));
    }

    /**
     * Returns a map that contains for each country code the name of the corresponding country in Portuguese ({@code "pt"}).
     */
    public Map<String, String> streamPipeline13() {
        // TODO
        return null;
    }

    /**
     * Returns the list of capitals by region whose name is the same is the same as the name of their country.
     */
    public Map<Region, List<String>> streamPipeline14() {
        // TODO
        return null;
    }

    /**
     *  Returns a map of country name-population density pairs.
     */
    public Map<String, Double> streamPipeline15() {
        // TODO
        return null;
    }

}
