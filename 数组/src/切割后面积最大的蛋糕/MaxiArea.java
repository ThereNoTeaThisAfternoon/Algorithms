package 切割后面积最大的蛋糕;

public class MaxiArea {
    public static void main(String[] args) {
        int h = 50;
        int w = 15;
        int[] horizontalCuts = new int[]{
                37, 40, 41, 30, 27, 10, 31
        };
        int[] verticalCuts = new int[]{
                2, 1, 9, 5, 4, 12, 6, 13, 11
        };
        int result = new Solution().maxArea(h, w, horizontalCuts, verticalCuts);
        String out = String.valueOf(result);
        System.out.println(out);
    }
}

class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long maxArea = 0;
        sort(horizontalCuts);
        sort(verticalCuts);
        int x, y;
        for (int i = 0; i <= horizontalCuts.length; i++) {
            if (i == 0) {
                y = horizontalCuts[i];
            } else if (i == horizontalCuts.length)
                y = h - horizontalCuts[i - 1];
            else {
                y = horizontalCuts[i] - horizontalCuts[i - 1];
            }
            for (int j = 0; j <= verticalCuts.length; j++) {
                if (j == 0) {
                    x = verticalCuts[j];
                } else if (j == verticalCuts.length) {
                    x = w - verticalCuts[j - 1];
                } else {
                    x = verticalCuts[j] - verticalCuts[j - 1];
                }
                maxArea = Math.max(maxArea, x * y);
            }
        }

        return (int) maxArea % (1000000000 + 7);
    }

    //不稳定的选择排序
    private void sort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            int minI = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[minI] > nums[j]) {
                    minI = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[minI];
            nums[minI] = temp;
        }
    }
}