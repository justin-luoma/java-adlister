package com.justinluoma.adlister.controllers.util;

public class Paging {
    public static int doPaging(int current, int range, int pages) {
        return range - 1 + Math.min(pages + 1 - range, Math.max(1, current - (range / 2)));
    }
}
