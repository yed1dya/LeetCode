import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

public class Easy {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
        long end = System.currentTimeMillis();
        double time = (double) (end - start)/1000;
        System.out.println("time: "+time+" seconds");
    }
    public static int[] twoSum(int[] nums, int target) {
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        for(int i=0; i<nums.length; i++){
            hashtable.put(nums[i], i);
        }
        for(int i=0; i<nums.length; i++){
            Integer t = hashtable.get(target-nums[i]);
            if(t!=null && t!=i){
                return new int[]{i,t};
            }
        }
        return new int[]{0,0};
    }

    public boolean isMonotonic(int[] nums) {
        int length = nums.length;
        if(length==1) return true;
        int j=0;
        while(j<length-2 && nums[j]==nums[j+1]){
            j++;
        }
        if(nums[j]<nums[j+1]){
            for(int i=j; i<length-1; i++){
                if(nums[i]>nums[i+1]) return false;
            }
        }
        if(nums[j]>nums[j+1]){
            for(int i=j; i<length-1; i++){
                if(nums[i]<nums[i+1]) return false;
            }
        }
        return true;
    }
    public static boolean canBeIncreasing(int[] nums) {
        if(nums.length==2) return true;
        boolean a = false;
        for(int i=1; i<nums.length; i++){
            if(nums[i]<nums[i-1]){
                if(a) return false;
                a = true;
            }
        }
        return true;
    }
    public static int distMoney(int money, int children) {
        if(money<children) return -1;
        int[] dist = new int[children];
        money -= children;
        int count = 0;
        for(int i=0; i<children-1; i++){
            if(money>=7){
                dist[i] += 7;
                money -= 7;
                count++;
            }
            if(money < 7 && money!=3) return count;
        }
        if(money==3){
            if(count<children-1) return count;
            if(count==0) return 0;
            return count-1;
        }
        dist[count] += money;
        if(dist[count] == 7) count++;
        return count;
    }
    public static int[] sortArrayByParity(int[] nums) {
        int[] ans = new int[nums.length];
        int evens = 0, odds = nums.length-1, n;
        for (int num : nums) {
            n = num;
            if (n % 2 == 0) ans[evens++] = n;
            else ans[odds--] = n;
        }
        return ans;
    }
    public static long tribonacci(int n){
        if(n==0) return 0;
        if(n<=2) return 1;
        int a = 0, b = 1, c = 1, t;
        for(int i=3; i <= n; i++){
            t = c;
            c = c + b + a;
            a = b;
            b = t;
        }
        return c;
    }

    public static long fibonacci(int n){
        int a = 0, b = 1, t;
        for(int i=1; i<n; i++){
            t = a;
            a += b;
            b = t;
        }
        return a;
    }

    public static int fibRec(int n){
        if(n<=0) return -1;
        if(n==1) return 0;
        if(n==2) return 1;
        return fibRec(n-1)+fibRec(n-2);
    }

    public int minCostClimbingStairs(int[] cost) {
        int length = cost.length;
        int[] a = new int[length];
        a[0] = cost[0];
        a[1] = cost[1];
        if(length == 2) return Math.min(a[0], a[1]);
        for(int i=2; i<length; i++){
            a[i] = Math.min(a[i-2]+cost[i], a[i-1]+cost[i]);
        }
        return Math.min(a[length-1], a[length-2]);
    }

    public static boolean equalFrequency(String word) {
        int[] freq = new int[26];
        int length = word.length();
        int types = 0;
        for(int i=0; i<length; i++){
            char c = word.charAt(i);
            int index = ((int)c)-97;
            if(freq[index]==0) types++;
            freq[index]++;
        }
        System.out.println(Arrays.toString(freq));
        if(types==1){
            System.out.println("types==1");
            return true;
        }
        if(types==length){
            System.out.println("types==length");
            return true;
        }
        int min = freq[0];
        int max = freq[0];
        int c = 0;
        for(int i : freq){
            if(i==1) c++;
            if(i==0) continue;
            if(min == 0) min = i;
            else min = Math.min(min, i);
            max = Math.max(max, i);
        }
        int secMin = max;
        for(int i : freq){
            if(i<secMin && i>min) secMin = i;
        }
        System.out.println("max: "+max+", min: "+min+", secMin: "+secMin);
        if(c==1 && types==2) return true;
        if(length%types==0){
            System.out.println("length%types==0");
            return false;
        }
        if(min!=1 && max-min>1){
            System.out.println("min!=1 && max-min>1");
            return false;
        }
        if(min==1 && max!=secMin){
            System.out.println("min==1 && max!=secMin");
            return false;
        }
        if((length-1) % types == 0){
            System.out.println("(length-1) % types == 0");
            return true;
        }
        if((length-1) % (types-1) == 0 && min==1){
            System.out.println("(length-1) % (types-1) == 0");
            return true;
        }
        return false;
    }
}
