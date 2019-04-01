import java.util.*;

public class MultisetTester {
    public static void main(String args[]){
        Multiset t1 = new Multiset("dog");
        Multiset t2 = new Multiset("dgo");

        Set<Multiset> ss = new HashSet<Multiset>();

        ss.add(t1);


        System.out.println(t1.equals(t2));
        System.out.println(t1.hashCode() == t2.hashCode());

        System.out.println(ss.contains(t2));
    }
}