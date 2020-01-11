package practice.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GenericsDemo{

    public static void main(String[] args) {
        Container<String> stringStore = new Store<>();
        stringStore.set("java");
        //stringStore.set(1);
        System.out.println(stringStore.get());

        Container<Integer> integerStore = new Store<>();
        integerStore.set(1);
        System.out.println(integerStore.get());

        Container<List<Integer>> listStore = new Store<>();
        listStore.set(Arrays.asList(1, 2, 3));
        System.out.println(listStore.get());

        //Container<int> intStore = new Store<>();
        List<Number> list = new ArrayList<>();
        list.add(new Integer(1));
        list.add(new Double(22.0));
        //list.add(new String("22.0"));

        List[] array = new List[2];
        array[0] = new ArrayList();
        array[1] = new LinkedList();

        // Raw type demo:
        // rawTypeTest();

        List<String> strList1 = Arrays.asList("a", "b", "c");
        List<String> strList2 = Arrays.asList("b", "c", "d");
        //getCommonElementsCount(strList1, strList2);

        // Wildcard
        getCommonElementsCountWithWildcard(strList1, strList2);

        Container<?> someStore = stringStore;
        Object object = someStore.get();
        System.out.println("Stored element: " + object);

        List<Integer> intList1 = Arrays.asList(1, 2);
        List<Integer> intList2 = Arrays.asList(3, 4);
        invalidAggregate(intList1, intList2, new ArrayList());
//Invariance and Cocariance test
       // go(new ArrayList<Integer>());
        go(new Integer[1]);
    }

    // Invariance
    static void go(List<Number> list) {}

    // Covariance
    static void go(Number[] list) {
        list[0] = 24.4;
    }

    public static void invalidAggregate(List<?> l1, List<?> l2, List<?> l3) {
        //l3.addAll(null); // null ok
        //l3.addAll(l2);
    }

    public static int getCommonElementsCountWithWildcard(List<?> list1, List<?> list2) {
        int count = 0;
        for (Object element : list1) {
            if (list2.contains(element)) {
                count++;
            }
        }
        System.out.println("Common elements count: " + count);
        return count;
    }

    public static int getCommonElementsCount(List list1, List list2) {
        int count = 0;
        for (Object element : list1) {
            if (list2.contains(element)) {
                count++;
            }
        }
        System.out.println("Common elements count: " + count);
        return count;
    }

    public static void rawTypeTest() {
        System.out.println("\n\nInside rawTypeTest ...");
        int ISBN = 1505297729;
        List<Double> prices = new ArrayList<>();

        HalfIntegrator.getPrice(ISBN, prices);
        Double price = prices.get(0);
    }
}

class HalfIntegrator {

    public static void getPrice(int ISBN, List prices) {
        prices.add(45);
    }

}

interface Container<T> {
    void set(T a);
    T get();
}

class Store<T> implements Container<T> {
    private T a;

    public void set(T a) {
        this.a = a;
    }

    public T get() {
        return a;
    }
}