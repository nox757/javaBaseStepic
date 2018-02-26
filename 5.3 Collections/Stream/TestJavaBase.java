/**
 * Created by Андрей on 17.02.2018.
 */
import java.io.*;
public class TestJavaBase {

        // arguments are passed using the text field below this editor
        public static void main(String[] args) throws java.io.IOException
        {
            System.setIn(new ByteArrayInputStream(new byte[] {65, 13, 10, 10, 13}));
            System.out.print((int)('Ы'));
            int read = System.in.read();
            int prev = read;
            while(read >= 0) {
                //System.out.print(read);
                read = System.in.read();
                if (read == -1) {
                    //System.out.write(prev);
                    System.out.println(prev);
                    break;
                }
                if (read == 10 && prev == 13) {
                   // System.out.write(0x10)
                    prev = read;

                }
                else {
                    //System.out.write(prev);
                    //System.out.write(read);
                    System.out.println(prev);
                    prev = read;
                }

            }
            System.out.flush();
        }
}
