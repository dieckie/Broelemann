package de.dieckie.raetsel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Broele {

    int product;
    boolean debug = false;

    public Broele(int product) {
        this.product = product;
    }

    public void debug(boolean debug) {
        this.debug = debug;
    }

    public boolean check1() {
        if(debug)
            System.out.println("b.check1(" + product + ")");

        List<Integer> prim = Prim.primfaktorzerlegung(product);
        if(prim.size() == 1) {
            if(debug)
                System.out.println("[1, " + prim.get(0) + "];");
            if(product > 1000) {
                System.err.println("Wie kann die Zahl hier größer als 1000 sein?");
            }
            return false;
        } else if(prim.size() == 2 && product > 1000) {
            if(debug)
                System.out.println("[" + prim.get(0) + ", " + prim.get(1) + "];");
            return false;
        } else {
            if(debug)
                System.out.println("[1 ," + prim.get(0) * prim.get(1) + "];[" + prim.get(0) + ", " + prim.get(1) + "];...");
            return true;
        }
    }
    
    public boolean check2() {
        return check2(product);
    }

    public boolean check2(int product) {
        if(debug)
            System.out.println("b.check2(" + product + ")");
        List<Integer> prim = Prim.primfaktorzerlegung(product);
        if(debug)
            System.out.println(prim);
        Set<Pair> possible = new HashSet<Pair>();
        for(ArrayList<Boolean> l : Permute.permutions[prim.size()]) {
            int pro1 = 1, pro2 = 1;
            for(int i = 0; i < l.size(); i++) {
                if(l.get(i)) {
                    pro1 *= prim.get(i);
                } else {
                    pro2 *= prim.get(i);
                }
            }
            if(pro1 <= 1000 && pro2 <= 1000) {
                Pair p = new Pair(pro1, pro2);
                boolean contains = false;
                for(Pair p2 : possible) {
                    if(p.equals(p2)) {
                        contains = true;
                        break;
                    }
                }
                if(!contains) {
                    possible.add(p);
                }

            }
        }
        for(Iterator<Pair> it = possible.iterator(); it.hasNext();) {
            Pair p = it.next();
            int sum = p.sum;
            if(debug)
                System.out.println(p + " " + p.sum);
            out: for(int i = 1; i <= sum / 2; i++) {
                if((Prim.isPrim(i) && Prim.isPrim(sum - i))) {
                    if(debug)
                        System.out.println(p + ": " + i + " " + (sum - i));
                    if(i * (sum - i) > 1000) {
                        it.remove();
                        break out;
                    }
                }
                if((Prim.isPrim(i) && (sum - i == 1)) || (Prim.isPrim(sum - i) && (i == 1))) {
                    it.remove();
                    break out;
                }
            }
        }
        return possible.size() == 1;
    }
    
    public boolean luekingCheck2(int sum) {
        if(debug)
            System.out.println("l.check2(" + sum + ")");
        int count = 0;
        for(int i = 1; i <= sum / 2; i++) {
            if(check2(i * (sum - i))) {
                count++;
            }
            if(count >= 2) {
                return false;
            }
        }
        return count == 1;
    }
}
