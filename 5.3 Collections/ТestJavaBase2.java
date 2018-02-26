/**
 * Created by Андрей on 17.02.2018.
 */
import java.io.*;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class ТestJavaBase2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.ENGLISH);
        String token;
        Double res = new Double(0);
        while (scanner.hasNext()) {
            try {
                res += scanner.nextDouble();
            } catch (InputMismatchException e){
                scanner.next();
            }

        }
        System.out.print(String.format(Locale.ENGLISH, "%.6f", res));
    }
}
