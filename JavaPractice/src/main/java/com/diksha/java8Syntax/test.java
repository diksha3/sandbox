package com.diksha.java8Syntax;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<Boolean> getResults(int[][] queries) {
        List<Boolean> result = new ArrayList<>();
        Set<Integer> obstacles = new HashSet<>();
        for (int[] query : queries) {

            int type = query[0];
            int x = query[1];

            if (type == 2) {
                int sz = query[2];
                int i = 0;
                int blockLength = 0;
                if (sz > x) {
                    result.add(false);
                } else {
                    for (i = 0; i <= x; i++) {
                        blockLength++;
                        if (obstacles.contains(i)) {
                            if (blockLength == sz) {
                                result.add(true);
                                break;
                            }
                            blockLength = 0;
                        } else {
                            if (blockLength == sz) {
                                result.add(true);
                                break;
                            }
                        }
                    }
                }

                if (blockLength < sz) result.add(false);

            } else {
                //obstacles.clear() ;
                obstacles.add(x);
            }
        }
        return result;
    }
}