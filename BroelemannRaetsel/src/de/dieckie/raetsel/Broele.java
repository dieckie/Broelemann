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
        return !Prim.isPrim(product);
    }

    public boolean check2() {
        List<Integer> prim = Prim.primfaktorzerlegung(product);
        if(product == 4672) {
            System.out.println(prim);
        }
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
            if(debug) {
                System.out.println(p + " " + p.sum);
            }
            out: for(int i = 1; i <= sum / 2; i++) {
                if((Prim.isPrim(i) && Prim.isPrim(sum - i))) {
                    if(debug) {
                        System.out.println(p + ": " + i + " " + (sum - i));
                    }
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

}
