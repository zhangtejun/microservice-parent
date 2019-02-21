package test.java8;

import java.util.Comparator;

class ComparatorTest implements Comparator<Banana> {

    @Override
    public int compare(Banana o1, Banana o2) {
        return o1.getColor().compareTo(o2.getColor());
    }
}
