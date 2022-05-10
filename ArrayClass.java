import java.lang.*;
import java.util.*;
public class ArrayClass{
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        int n = scanner.nextInt();
        int[] A = takeArrayInput(n);
        sort01(A);
       /* int[] B = takeArrayInput(m);
        scanner.close();
        ArrayList<Integer> ans = arrayUnion(A, B);
        printArrayList(ans);
       */
       /* Arrays.sort(A); */
        printArray(A);
       /* int ans = pairSum4(A, m);
        System.out.println(ans);
        */
    }
    /* Functions */
    /* function sort01 : sort an array of only zeros and ones */
    public static void sort01(int[] A){
        if(A.length < 2) return;
        int lo = 0,
            hi = A.length - 1,
            lokey = A[lo],
            hikey = A[hi];
        while(lo < hi){
            if(lokey == 0){         // lo = 0; incr lo
                lo++;
                lokey = A[lo];
                if(hikey == 1){     // lo = 0, hi = 1; incr lo and decr hi
                    hi--;
                    hikey = A[hi];
                }
            }
            else{                   // lo = 1
                if(hikey == 1){     // lo = 1, hi = 1; keep lo and decr hi
                    hi--;
                    hikey = A[hi];
                }
                else{               // lo = 1, hi = 0; swap
                    A[lo] = 0;
                    A[hi] = 1;
                    lo++;
                    hi--;
                    lokey = A[lo];
                    hikey = A[hi];
                }
            }
        }
    }
    /* function tripletSum : find number of triplets in an array that sum to k */
    /* function tripletSum2 : O(n^2) time; O(n) space; takes help of pairSum */
    public static int tripletSum2(int[] A, int k){
        if(A.length < 3) return 0;
        HashMap<Integer, Integer> map   = new HashMap<>(A.length);
        ArrayList<Integer> keys         = new ArrayList<>(A.length);
        createFrequencyMapAndSortedListOfKeys(A, map, keys);
        int lo          = 0,
            hi          = keys.size() - 1,
            triplets   = 0;
        while(lo < hi){
            int lokey   = keys.get(lo++).intValue(),
                freq    = map.get(lokey).intValue();
            if(3 * lokey == k){
                triplets   += freq * (freq - 1) * (freq - 2) / 6;
            }
            else{
                triplets   += freq * pairSumHelperToTripletSum(map, keys, lo, hi, k - lokey);
                int newKey  = k - 2 * lokey;
                if(newKey > lokey && map.containsKey(newKey)){
                    triplets += (freq * (freq - 1) / 2) * map.get(newKey).intValue();
                }
            }
        }
        int hikey = keys.get(hi).intValue();
        if(3 * hikey == k){
            int freq = map.get(hikey).intValue();
            triplets += freq * (freq - 1) * (freq - 2) / 6;
        }
        return triplets;
    }
    /* function pairSumHelperToTripletSum */
    public static int pairSumHelperToTripletSum(HashMap<Integer, Integer> map, ArrayList<Integer> keys, int lo, int hi, int k){
        int lokey   = keys.get(lo).intValue(),
            hikey   = keys.get(hi).intValue(),
            sum     = lokey + hikey,
            pairs   = 0;
        while(hi > lo){
            if(sum == k){
                pairs   += map.get(lokey).intValue() * map.get(hikey).intValue();
                lokey   = keys.get(++lo).intValue();
                hikey   = keys.get(--hi).intValue();
            }
            else if(sum < k){
                lokey   = keys.get(++lo).intValue();
            }
            else{
                hikey   = keys.get(--hi).intValue();
            }
            sum         = lokey + hikey;
        }
        if(hi == lo && sum == k){
            int freq    = map.get(lokey).intValue();
            pairs       += freq * (freq - 1) / 2;
        }
        return pairs;
    }
    /* function tripletsum1 : nested loops; brute force; O(n^3) time */
    public static int tripletSum1(int[] A, int k){
        if(A.length < 3) return 0;
        int n   = A.length,
            nm1 = n - 1,
            nm2 = n - 2,
            triplets = 0;
        for(int i = 0; i < nm2; i++){
            int key1 = A[i];
            for(int j = i + 1; j < nm1; j++){
                int key2 = A[j];
                for(int l = j + 1; l < n; l++){
                    if(key1 + key2 + A[l] == k){
                        triplets++;
                    }
                }
            }
        }
        return triplets;
    }
    /* triplet sum end */
    /* function pairSum : find number of pairs in an array that sum to k */
    /* function pairSum4 : create frequency map; iterate over array elements and find the counterpart */
    /*                      O(n) time; O(n) space */
    public static int pairSum4(int[] A, int k){
        if(A.length < 2) return 0;
        HashMap<Integer, Integer> map = createFrequencyMapOfArrayElements(A);
        int pairs = 0;
        for(int i = 0; i < A.length; i++){
            int key = A[i];
            if(map.containsKey(key)){
                int otherkey = k - key;
                if(key == otherkey){
                    int freq = map.get(key).intValue();
                    pairs += freq * (freq - 1) / 2;
                    map.remove(key);
                }
                else if(map.containsKey(otherkey)){
                    pairs += map.get(key).intValue() * map.get(otherkey).intValue();
                    map.remove(key);
                    map.remove(otherkey);
                }
                else map.remove(key);
            }
        }
        return pairs;
    }
    /* function pairSum3 : Create frequency map and sorted list of keys; apply classical pairSum algorithm */
    /*                     O(nlogn) time in worst case; O(n) time in best case */
    public static int pairSum3(int[] A, int k){
        if(A.length < 2) return 0;
        HashMap<Integer, Integer> map   = new HashMap<>(A.length);
        ArrayList<Integer> keys         = new ArrayList<>(A.length);
        createFrequencyMapAndSortedListOfKeys(A, map, keys);
        int lo      = 0,
            hi      = keys.size() - 1,
            lokey   = keys.get(lo).intValue(),
            hikey   = keys.get(hi).intValue(),
            sum     = lokey + hikey,
            pairs   = 0;
        while(hi > lo){
            if(sum == k){
                pairs   += map.get(lokey).intValue() * map.get(hikey).intValue();
                lokey   = keys.get(++lo).intValue();
                hikey   = keys.get(--hi).intValue();
            }
            else if(sum < k){
                lokey   = keys.get(++lo).intValue();
            }
            else{
                hikey   = keys.get(--hi).intValue();
            }
            sum         = lokey + hikey;
        }
        if(hi == lo && sum == k){
            int freq    = map.get(lokey).intValue();
            pairs       += freq * (freq - 1) / 2;
        }
        return pairs;
    }
    /* function createFrequencyMapAndSortedListOfKeys */
    public static void createFrequencyMapAndSortedListOfKeys(int[] A, HashMap<Integer, Integer> map, ArrayList<Integer> keys){
        for(int i = 0; i < A.length; i++){
            int key = A[i];
            if(map.containsKey(key)){
                map.replace(key, map.get(key).intValue() + 1);
            }
            else{
                map.put(key, 1);
                keys.add(key);
            }
        }
        keys.sort(null);
    }
    /* function pairSum2 : use sorting; O(nlogn) time */
    public static int pairSum2(int[] A, int k){
        Arrays.sort(A);
        int lo      = 0,
            hi      = A.length - 1,
            lokey   = A[lo],
            hikey   = A[hi],
            sum     = lokey + hikey,
            pairs   = 0;
        while(lo < hi){
            if(sum == k){
                if(lokey == hikey){
                    int freq = hi - lo + 1;
                    pairs += freq * ( freq - 1) / 2;
                    break;
                }
                lo++;
                int nextkey = A[lo],
                    lofreq  = 1,
                    hifreq  = 1;
                while(nextkey == lokey){
                    lofreq++;
                    lo++;
                    nextkey = A[lo];
                }
                lokey = nextkey;
                hi--;
                nextkey = A[hi];
                while(nextkey == hikey){
                    hifreq++;
                    hi--;
                    nextkey = A[hi];
                }
                hikey = nextkey;
                pairs += lofreq * hifreq;
            }
            else if(sum < k){
                lo++;
                lokey = A[lo];
            }
            else{
                hi--;
                hikey = A[hi];
            }
            sum = lokey + hikey;
        }
        return pairs;
    }
    /* function pairSum1 : nested loops; brute force; O(n^2) time */
    public static int pairSum1(int[] A, int k){
        int elem,
            n = A.length,
            nm1 = n - 1,
            pairs = 0;
        for(int i = 0; i < nm1; i++){
            elem = A[i];
            for(int j = i + 1; j < n; j++){
                if(elem + A[j] == k){
                    pairs++;
                }
            }
        }
        return pairs;
    }
    /* pairSum varients end */
    /* function arrayUnion : find union of two arrays */
    public static ArrayList<Integer> arrayUnion(int[] A, int[] B){
        HashMap<Integer, Integer> map   = new HashMap<>(A.length + B.length);
        ArrayList<Integer> union        = new ArrayList<>(A.length + B.length);
        addUniqueElemetsToUnion(A, map, union);
        addUniqueElemetsToUnion(B, map, union);
        return union;
    }
    /* function addUniqueElemetsToUnion */
    public static void addUniqueElemetsToUnion(int[] A, HashMap<Integer, Integer> map, ArrayList<Integer> union){
        for(int i = 0; i < A.length; i++){
            int key = A[i];
            if( ! map.containsKey(key)){
                map.put(key, 1);
                union.add(key);
            }
        }
    }
    /* function arrayIntersections : find intersections between two arrays */
    public static ArrayList<Integer> arrayIntersections(int[] A, int[] B){
        HashMap<Integer, Integer> map = createFrequencyMapOfArrayElements(B);
        ArrayList<Integer> intersections = new ArrayList<>(A.length);
        for(int i = 0; i < A.length; i++){
            int key = A[i];
            if(map.containsKey(key)){
                int freq = map.get(key);
                if(freq > 0){
                    intersections.add(key);
                    map.replace(key, freq - 1);
                }
            }
        }
        return intersections;
    }
    /* function findDuplicate : finds duplicate element in an Array (assumes there is only one duplicate) */
    public static int findDuplicate(int[] A){
        HashMap<Integer, Integer> map = new HashMap<>(A.length);
        for(int i = 0; i < A.length; i++){
            int key = A[i];
            if(map.containsKey(key)){
                return key;
            }
            else{
                map.put(key, 1); 
            }
        }
        return -1;
    }
    /* function findUnique : finds the only unique element in an array (assumes there is only one unique) */
    public static int findUnique(int[] A){
        HashMap<Integer, Integer> map = createFrequencyMapOfArrayElements(A);
        for(int i = 0; i < A.length; i++){
            int key = A[i];
            if(map.get(key).intValue() == 1) return key;
        }
        return -1;
    }
    /* function createFrequencyMapOfArrayElements */
    public static HashMap<Integer, Integer> createFrequencyMapOfArrayElements(int[] A){
        HashMap<Integer, Integer> map = new HashMap<>(A.length);
        for(int i = 0; i < A.length; i++){
            int key = A[i];
            if(map.containsKey(key)){
                map.replace(key, map.get(key).intValue() + 1);
            }
            else{
                map.put(key, 1);
            }
        }
        return map;
    }
    /* function takeArrayInput : takes 1D array as input and returns */
    public static int[] takeArrayInput(int n){
        int[] A = new int[n];
        for(int i = 0; i < n; i++){
            A[i] = scanner.nextInt();
        }
        return A;
    }
    /* function printArray : prints 1D Array */
    public static void printArray(int[] A){
        System.out.println("*** Printing Array ***");
        for(int i = 0; i < A.length; i++){
            System.out.print(A[i] + " "); 
        }
        System.out.println("\n***");
    } 
    /* function printArrayList : prints an ArrayList */
    public static void printArrayList(ArrayList<Integer> A){
        System.out.println("*** Printing ArrayList ***");
        int n = A.size();
        for(int i = 0; i < n; i++){
            System.out.print(A.get(i) + " "); 
        }
        System.out.println("\n***");
    }
}
