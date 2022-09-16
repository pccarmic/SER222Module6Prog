package edu.ser222.m02_02;
import java.util.Random;

/**
 * Implements various divide and conquer algorithms.
 *
 * Last updated 4/2/2022.
 *
 * Completion time: (your completion time)
 *
 * @author Paul Carmichael, Acuna, Sedgewick and Wayne
 * @verison 1.0
 */
import java.util.Random;

public class CompletedMerging implements MergingAlgorithms {

    //TODO: implement interface methods.
    @Override
    public <T extends Comparable> Queue<T> mergeQueues(Queue<T> q1, Queue<T> q2) {
        Queue<T> fin_queue = new ListQueue<>();

        while(!q1.isEmpty() && !q2.isEmpty()){
            T peek_q1 = q1.peek();
            T peek_q2 = q2.peek();

            if(less(peek_q1, peek_q2)){
                fin_queue.enqueue(q1.dequeue());
            } else {
                fin_queue.enqueue(q2.dequeue());
            }
        }

        while(!q1.isEmpty()){
            fin_queue.enqueue(q1.dequeue());
        }

        while(!q2.isEmpty()){
            fin_queue.enqueue(q2.dequeue());
        }

        return fin_queue;
    }

    @Override
    public void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        aux = mergesort(a);
        assert isSorted(aux);
        for(int i = 0; i < a.length; i++){
            a[i] = aux[i];
        }
    }

    @Override
    public Comparable[] mergesort(Comparable[] a) {
        if(a.length < 2){
            return a;
        }
        int mid = a.length/2;
        int len2 = a.length-mid;
        int k = 0, j = 0;

        Comparable[] aux1 = new Comparable[mid];
        Comparable[] aux2 = new Comparable[len2];

        for(int i = 0; i < a.length; i++){
            if(i < mid && k < mid){
                aux1[k++] = a[i];
            }else{
                aux2[j++] = a[i];
            }
        }

        aux1 = mergesort(aux1);
        aux2 = mergesort(aux2);

        return merge(aux1, aux2);
    }

    @Override
    public Comparable[] merge(Comparable[] a, Comparable[] b) {
        assert isSorted(a);
        assert isSorted(b);

        Comparable[] fin_arr = new Comparable[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while(i < a.length && j < b.length){
            if(less(a[i], b[j])){
                fin_arr[k++] = a[i++];
            } else {
                fin_arr[k++] = b[j++];
            }
        }
        while(i < a.length){
            fin_arr[k++] = a[i++];
        }
        while(j < b.length){
            fin_arr[k++] = b[j++];
        }


        assert isSorted(fin_arr);
        return fin_arr;
    }

    @Override
    public void shuffle(Object[] a) {
        Random rand = new Random();
        for(int i = a.length-1; i >= 0; i--){
            int ran_index = rand.nextInt(i+1);
            Object temp = a[ran_index];
            a[ran_index] = a[i];
            a[i] = temp;
        }
    }
     
    /**
     * entry point for sample output.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Queue<String> q1 = new ListQueue<>(); q1.enqueue("E"); q1.enqueue("L"); q1.enqueue("O"); q1.enqueue("R"); q1.enqueue("T");
        Queue<String> q2 = new ListQueue<>(); q2.enqueue("A"); q2.enqueue("E"); q2.enqueue("M"); q2.enqueue("P"); q2.enqueue("S"); q2.enqueue("X");
        Queue<Integer> q3 = new ListQueue<>(); q3.enqueue(5); q3.enqueue(12); q3.enqueue(15); q3.enqueue(17); q3.enqueue(20);
        Queue<Integer> q4 = new ListQueue<>(); q4.enqueue(1); q4.enqueue(4); q4.enqueue(12); q4.enqueue(13); q4.enqueue(16); q4.enqueue(18);

        MergingAlgorithms ma = new CompletedMerging();

        //Q1 - sample test cases
        Queue merged1 = ma.mergeQueues(q1, q2);
        System.out.println(merged1.toString());
        Queue merged2 = ma.mergeQueues(q3, q4);
        System.out.println(merged2.toString());
        
        //Q2 - sample test cases
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        ma.sort(a);
        assert isSorted(a);
        show(a);
        
        //Q3 - sample test cases
        String[] b = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        ma.shuffle(b);

        show(b);
        
        ma.shuffle(b);
        show(b);
    }

    //below are utilities functions, please do not change them.
        
    //sorting helper from text
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //sorting helper from text
    private static void show(Comparable[] a) {
        for (Comparable a1 : a)
            System.out.print(a1 + " ");

        System.out.println();
    }
    
    //sorting helper from text
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        
        return true;
    }
}