package com.advert.model;

import java.util.*;

public class Campaign {
    private OptimizationProps optProps;

    private int id;

    private Map<Integer, Integer> blacklist;

    public Campaign(OptimizationProps optProps, int id, Map<Integer, Integer> blacklist) {
        this.optProps = optProps;
        this.id = id;
        this.blacklist = blacklist;
    }

    public OptimizationProps getOptimizationProps() {
        return optProps;
    }
    public Map<Integer, Integer> getBlackList() {
        return blacklist;
    }
    public void saveBlacklist(Map<Integer, Integer> blacklist) {
        // dont implement

//        this.blacklist = blacklist;
    }

    public int getId() {
        return id;
    }
}
