package com.diksha;

import java.util.ArrayList;
import java.util.List;

class CountTriplets {
    public List<Integer> countTriplets(String[] queries) {
        List<Integer> arr = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int count = 0;

        for (String query : queries) {
            if (query.contains("+")) {
                int val = Integer.parseInt(query.split("\\+")[1]);
                arr.add(val);
            } else if (query.contains("-")) {
                int val = Integer.parseInt(query.split("-")[1]);
                arr.remove(Integer.valueOf(val));
            }

            count = countTripletsHelper(arr);
            result.add(count);
        }

        return result;
    }

    private int countTripletsHelper(List<Integer> arr) {
        int count = 0;
        int n = arr.size();
        if (n < 3) {
            return 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr.get(i) - arr.get(j) == arr.get(j) - arr.get(k)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String[] queries = {"+1", "+2", "+3", "+4", "-2", "+5"};
        CountTriplets solution = new CountTriplets();
        List<Integer> result = solution.countTriplets(queries);
        System.out.println(result);
    }
}

