package de.dieckie.raetsel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

    static List<Pair> pairs = new ArrayList<Pair>();
    static Map<Integer, HashSet<Pair>> sumsP = new HashMap<Integer, HashSet<Pair>>();
    static Map<Integer, HashSet<Pair>> prosP = new HashMap<Integer, HashSet<Pair>>();
    static Map<Integer, Boolean> sums = new HashMap<Integer, Boolean>();
    static Map<Integer, Boolean> pros = new HashMap<Integer, Boolean>();
    static Pair debug;

    public static void main(String[] args) throws Exception {
        Prim.init();
        generatePairs();
        // generateSums();
        // generatePros();
        printSize("Direkt nach generieren");
        for(Iterator<Pair> iterator = pairs.iterator(); iterator.hasNext();) {
            Pair p = iterator.next();
            Broele b = new Broele(p.pro);
            Lueking l = new Lueking(p.sum);
            if(!b.check1()) {
                iterator.remove();

            } else if(!l.check1()) {
                iterator.remove();
            }
        }
        printSize("Ende");
        if(!pairs.contains(debug)) {
            throw new Exception("Das richtige Zahlenpaar war am Ende nicht dabei!");
        }
    }

    public static void generatePairs() {
        for(int i = 1; i <= 1000; i++) {
            for(int i1 = i; i1 <= 1000; i1++) {
                if(i == 64 && i1 == 73) {
                    debug = new Pair(i, i1);
                    pairs.add(debug);
                } else {
                    pairs.add(new Pair(i, i1));
                }

            }
        }
    }

    public static void generateSums() {
        for(Pair p : pairs) {
            if(!sumsP.containsKey(p.sum)) {
                sumsP.put(p.sum, new HashSet<Pair>());
            }
            sumsP.get(p.sum).add(p);
        }
    }

    public static void generatePros() {
        for(Pair p : pairs) {
            if(!prosP.containsKey(p.pro)) {
                prosP.put(p.pro, new HashSet<Pair>());
            }
            prosP.get(p.pro).add(p);
        }
    }

    public static void printSize(String message) {
        // int sumsSize = 0, prosSize = 0;
        // for(int i = 0; i <= 1000000; i++) {
        // if(sums.containsKey(i)) {
        // sumsSize += sums.get(i).size();
        // }
        // if(pros.containsKey(i)) {
        // prosSize += pros.get(i).size();
        // }
        // }
        System.out.println(message);
        System.out.println("\tpairs:" + pairs.size());
        // System.out.println("\tsums: " + sumsSize + "/" + sums.size());
        // System.out.println("\tpros: " + prosSize + "/" + pros.size());
    }
}
