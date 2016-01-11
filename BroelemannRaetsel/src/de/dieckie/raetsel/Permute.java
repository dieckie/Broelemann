package de.dieckie.raetsel;

import java.util.ArrayList;

public class Permute {

    @SuppressWarnings("unchecked")
    static ArrayList<ArrayList<Boolean>>[] permutions = new ArrayList[20];

    public static void init() {
        for(int i = 0; i < permutions.length; i++) {
            permutions[i] = new ArrayList<ArrayList<Boolean>>();
        }
        ArrayList<Boolean> l = new ArrayList<Boolean>();
        permute(0, 9, l);
    }

    @SuppressWarnings("unchecked")
    public static void permute(int level, int maxDepth, ArrayList<Boolean> l) {
        if(level == maxDepth) {
            return;
        } else {
            l.add(false);
            permutions[level + 1].add((ArrayList<Boolean>)l.clone());
            permute(level + 1, maxDepth, (ArrayList<Boolean>)l.clone());
            l.set(level, true);
            permutions[level + 1].add((ArrayList<Boolean>)l.clone());
            permute(level + 1, maxDepth, (ArrayList<Boolean>)l.clone());
        }
    }

}
