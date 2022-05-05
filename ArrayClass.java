import java.lang.*;
import java.util.*;
public class ArrayClass{
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        int n = scanner.nextInt();
        int[] A = takeArrayInput(n);
        int m = scanner.nextInt();
       /* int[] B = takeArrayInput(m);
        scanner.close();
        ArrayList<Integer> ans = arrayUnion(A, B);
        printArrayList(ans);
       */
       /* Arrays.sort(A);
        printArray(A);
       */
        int ans = pairSum(A, m);
        System.out.println(ans);
    }
    /* Functions */
    /* function pairSum : find number of pairs in an array that sum to k */
    public static int pairSum(int[] A, int k){
        if(A.length < 2) return 0;
        HashMap<Integer, Integer> map = new HashMap<>(A.length);
        ArrayList<Integer> keys = new ArrayList<>(A.length);
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
                sum     = lokey + hikey;
            }
            else if(sum < k){
                lokey   = keys.get(++lo).intValue();
                sum     = lokey + hikey;
            }
            else{
                hikey   = keys.get(--hi).intValue();
                sum     = lokey + hikey;
            }
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
    /* function arrayUnion : find union of two arrays */
    public static ArrayList<Integer> arrayUnion(int[] A, int[] B){
        HashMap<Integer, Integer> map = new HashMap<>(A.length + B.length);
        ArrayList<Integer> union = new ArrayList<>(A.length + B.length);
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
