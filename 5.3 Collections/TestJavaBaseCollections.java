
import java.util.*;


/**
 * Created by Андрей on 17.02.2018.
 */
public class TestJavaBaseCollections {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean odd = false;
        Integer curr;
        //List<Integer> list = new LinkedList<>();
        Deque<Integer> list = new LinkedList<>();
        while(scanner.hasNext()) {
            if (odd) {
                list.push(new Integer(scanner.nextInt()));
                odd = false;
            }
            else{
                scanner.next();
                odd = true;
            }
        }
        while(!list.isEmpty()){
            System.out.print(list.pop());
            System.out.print(" ");
        }
        System.out.flush();

    }
}
