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
    static Map<Integer, HashSet<Pair>> difsP = new HashMap<Integer, HashSet<Pair>>();
    static Map<Integer, Boolean> sums = new HashMap<Integer, Boolean>();
    static Map<Integer, Boolean> pros = new HashMap<Integer, Boolean>();
    static Map<Integer, Boolean> difs = new HashMap<Integer, Boolean>();
    static Pair debug;

    public static void main(String[] args) throws Exception {
        Prim.init();
        Permute.init();
        generatePairs();
        debug();
    }

    public static void debug() {
        Pair p = new Pair(1, 4);
        Broele b = new Broele(p.pro);
        Lueking l = new Lueking(p.sum);
        b.debug(true);
        l.debug(true);
        if(!b.check1()) {
            System.out.println("Removed in b.check1()");
        } else if(!l.check1()) {
            System.out.println("Removed in l.check1()");
        } else if(!b.check2()) {
            System.out.println("Removed in b.check2()");
        } else if(!l.check2()) {
            System.out.println("Removed in l.check2()");
        }
    }

    public static void seach() throws Exception {
        Timer t2 = new Timer();
        t2.start();
        System.out.println("Initphase: " + t2.formatTime());
        t2.restart();
        printSize("Direkt nach generieren", null);
        long time = System.currentTimeMillis();
        int i = 0;
        float teiler = pairs.size() / 100f;
        for(Iterator<Pair> it = pairs.iterator(); it.hasNext();) {
            Pair p = it.next();
            Broele b = new Broele(p.pro);
            Lueking l = new Lueking(p.sum);
            if(p.equals(debug)) {
                b.debug(true);
                l.debug(true);
            }
            if(!b.check1()) {
                it.remove();
            } else if(!l.check1()) {
                it.remove();
            } else if(!b.check2()) {
                it.remove();
            } else if(!l.check2()) {
                it.remove();
            }
            i++;
            if(System.currentTimeMillis() - time > 10000) {
                System.out.println((float) i / teiler + "%");
                time = System.currentTimeMillis();
            }
        }
        generateDifs();
        System.out.println(difsP);
        printSize("Ende", t2);
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
                sums.put(p.sum, true);
            }
            sumsP.get(p.sum).add(p);
        }
    }

    public static void generatePros() {
        for(Pair p : pairs) {
            if(!prosP.containsKey(p.pro)) {
                prosP.put(p.pro, new HashSet<Pair>());
                pros.put(p.pro, true);
            }
            prosP.get(p.pro).add(p);
        }
    }

    public static void generateDifs() {
        for(Pair p : pairs) {
            if(!difsP.containsKey(p.dif)) {
                difsP.put(p.dif, new HashSet<Pair>());
                difs.put(p.dif, true);
            }
            difsP.get(p.dif).add(p);
        }
    }

    public static void refreshPros() {
        long time = System.currentTimeMillis();
        int i = 0;
        for(int p : pros.keySet()) {
            if(!pros.get(p)) {
                // if(prosP.containsKey(p)) {
                for(Pair pa : prosP.get(p)) {
                    pairs.remove(pa);
                }
                // prosP.remove(p);
                // }
            }
            i++;
            if(System.currentTimeMillis() - time > 10000) {
                System.out.println(((float) i / pros.size()) * 100 + "%");
                time = System.currentTimeMillis();
            }
        }
    }

    public static void refreshSums() {
        long time = System.currentTimeMillis();
        int i = 0;
        for(int p : sums.keySet()) {
            if(!sums.get(p)) {
                // if(sumsP.containsKey(p)) {
                for(Pair pa : sumsP.get(p)) {
                    pairs.remove(pa);
                }
                // sumsP.remove(p);
                // }
            }
            i++;
            if(System.currentTimeMillis() - time > 10000) {
                System.out.println(((float) i / sums.size()) * 100 + "%");
                time = System.currentTimeMillis();
            }
        }
    }

    public static void printSize(String message, Timer t) {
        int sumsSize = 0, prosSize = 0;
        int sumsTrue = 0, prosTrue = 0;
        for(int i = 0; i <= 1000000; i++) {
            if(sumsP.containsKey(i)) {
                sumsSize += sumsP.get(i).size();
            }
            if(prosP.containsKey(i)) {
                prosSize += prosP.get(i).size();
            }
        }
        for(Iterator<Integer> it = pros.keySet().iterator(); it.hasNext();) {
            if(pros.get(it.next())) {
                prosTrue++;
            }
        }
        for(Iterator<Integer> it = sums.keySet().iterator(); it.hasNext();) {
            if(sums.get(it.next())) {
                sumsTrue++;
            }
        }
        System.out.println(message);
        System.out.println("\tpairs:" + pairs.size());
        System.out.println("\tsums: " + sumsSize + "/" + sumsP.size() + "(" + sumsTrue + "/" + sums.size() + ")");
        System.out.println("\tpros: " + prosSize + "/" + prosP.size() + "(" + prosTrue + "/" + pros.size() + ")");
        if(t != null) {
            System.out.println("\t" + t.formatTime());
        }
    }
}
