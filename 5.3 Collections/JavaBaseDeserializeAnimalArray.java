import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Created by Андрей on 17.02.2018.
 */
public class JavaBaseDeserializeAnimalArray {

    public static Animal[] deserializeAnimalArray(byte[] data) throws IOException {

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(data));
        } catch (IOException e) {
            throw new IOException(e);
        }
        int size = 0;
        try {
            size = ois.readInt();

        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        Animal[] animals = new Animal[size];
        for(int i = 0; i < size; i++){
            try {
                animals[i] = (Animal) ois.readObject();
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }

        }
        return (size != 0) ? animals :  new Animal[0] ;

    }


}
