package com.trinath.dsalgo.lb.intervals;

import java.util.*;
import java.util.stream.Collectors;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
};

public class InsertInterval {

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> mergedIntervals = new ArrayList<>();
        if(intervals==null || intervals.size()==0){
            return Arrays.asList(newInterval);
        }
        int i=0;
        while(i<intervals.size() && intervals.get(i).end<newInterval.start){ // compare with end
            mergedIntervals.add(intervals.get(i));
            i++;
        }
        //now have the index for insert
        //take care of the left
        int idx = i;
        System.out.println("i "+i+" intervals "+intervals);
        int start = newInterval.start;
        int end = newInterval.end;
        while(i<intervals.size() && intervals.get(i).start<=newInterval.end){//this next elemt is getting merged
            newInterval.start = Math.min(intervals.get(i).start,newInterval.end );
            newInterval.end = Math.max(intervals.get(i).start,newInterval.end );
            i++;
        }
        mergedIntervals.add(new Interval(start,end));

        //rest all
        while(i<intervals.size()){
            mergedIntervals.add(intervals.get(i));
            i++;
        }

        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        input.add(new Interval(18, 22));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(4, 16)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(4, 10)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(2, 3));
        input.add(new Interval(5, 6));
        input.add(new Interval(6, 7));
        input.add(new Interval(16, 17));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(2, 8)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(4, (a,b)->Integer.compare(a,b));
        minHeap.offer(2);
        minHeap.offer(3);
        minHeap.offer(4);
        List<Integer> l1 = minHeap.stream().collect(Collectors.toList());
        System.out.println("heap");
        l1.stream().forEach(l->System.out.println(l));
        System.out.println("heap end");
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(-5);
        list.add(4);
       //list  = list.stream().sorted((a,b)->a.compareTo(b)).collect(Collectors.toList());
        list.sort(Comparator.reverseOrder());
        list.sort((a,b)->b-a);
        //Collections.sort(list, Comparator.reverseOrder());
       list.stream().forEach(l->System.out.println(l));

       LinkedList<Integer> ll =  new LinkedList<>();
       ll.add(1);
       ll.add(2);
       ll.add(3);
       ll.addLast(4);
       ll.remove();
       ll.removeFirst();
        ll.stream().forEach(l->System.out.println(l));

        System.out.println(Arrays.asList("yes".toCharArray()).get(0));
        List<Character> characterList = new ArrayList<>();
        characterList.add('c');
        characterList.add('a');

        System.out.println(characterList.stream().map(c->String.valueOf(c)).collect(Collectors.joining()));

    }
}
