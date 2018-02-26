import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Андрей on 18.02.2018.
 */
public class TestJavaBaseStream {


    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")));
        Stream<String> stream = br.lines();
        stream
                .map(String::toLowerCase)
                .map(line -> line.split("[\\p{Blank}\\p{Punct}]+"))
                .flatMap(Arrays::stream)
                .collect(Collectors.groupingBy(w->w, Collectors.summingInt(w->1)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey((Comparator.naturalOrder())))
                .sorted(Map.Entry.comparingByValue((Comparator.reverseOrder())))
                .limit(10)

                .forEach(e -> System.out.println(e.getKey())); //+ ": " + e.getValue()));

    }
}
