/**
 * Created by Андрей on 17.02.2018.
 */

import java.util.*;
public class Pair<T, U>{
    private final T value1;
    private final U value2;

    private Pair(T value1, U value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
    public T getFirst(){
        return this.value1;
    }
    public U getSecond() {
        return this.value2;
    }
    public static <T1,U1> Pair<T1,U1> of(T1 value1, U1 value2) {
        return new Pair<>(value1, value2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (value1 != null ? !value1.equals(pair.value1) : pair.value1 != null) return false;
        return value2 != null ? value2.equals(pair.value2) : pair.value2 == null;

    }

    @Override
    public int hashCode() {
        int result = value1 != null ? value1.hashCode() : 0;
        result = 31 * result + (value2 != null ? value2.hashCode() : 0);
        return result;
    }

    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> diff = new LinkedHashSet<T>();
        for (T elem: set1) {
            if (!set2.contains(elem))
                diff.add(elem);
        }
        for (T elem: set2) {
            if (!set1.contains(elem))
             diff.add(elem);
        }
        return diff;
    }
}
