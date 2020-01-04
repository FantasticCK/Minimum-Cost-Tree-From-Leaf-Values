package com.CK;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

// DP
class Solution {
    public int mctFromLeafValues(int[] arr) {
        Result[][] memo = new Result[arr.length][arr.length];
        return dfs(arr, 0, arr.length - 1, memo).sum;
    }

    private Result dfs(int[] arr, int st, int ed, Result[][] memo) {
        if (memo[st][ed] != null)
            return memo[st][ed];

        if (st == ed) {
            memo[st][ed] = new Result(0, arr[st]);
            return memo[st][ed];
        }
        memo[st][ed] = new Result();
        for (int i = st; i < ed; i++) {
            Result left = dfs(arr, st, i, memo), right = dfs(arr, i + 1, ed, memo);
            int tempSum = left.sum + right.sum + left.maxLeaf * right.maxLeaf;
            if (tempSum < memo[st][ed].sum) {
                memo[st][ed].sum = tempSum;
                memo[st][ed].maxLeaf = Math.max(left.maxLeaf, right.maxLeaf);
            }
        }
        return memo[st][ed];
    }

    private class Result {
        int sum;
        int maxLeaf;

        Result() {
            sum = 10000000;
            maxLeaf = 0;
        }

        Result(int _sum, int _maxLeaf) {
            sum = _sum;
            maxLeaf = _maxLeaf;
        }
    }
}

// Monotonous Stack
class Solution {
    public int mctFromLeafValues(int[] A) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int a : A) {
            while (stack.peek() <= a) {
                int mid = stack.pop();
                res += mid * Math.min(stack.peek(), a);
            }
            stack.push(a);
        }
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }
}