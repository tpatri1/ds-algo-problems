package com.trinath.dsalgo.lb.Stack_Queue_LinkedList_HashTable;
//https://leetcode.com/problems/gas-station/
public class PetrolPump {
    private int findStartNode(int[] gas, int[] cost){
        int nPumps = gas.length;
        int[] diff = new int[nPumps];
        if(nPumps!=cost.length){
            return -1;
        }

        int i1=0;
        int j=-1; //Potential Start index
        //int k=0;
        int count = 0;
        int i=0;
        int sum=0;

        while(i1<2*nPumps){

            //To make circular
            i=(i1+1)%(nPumps); // i+1 or i+nPumps %nPumps will make ciircular to make sure there is no negative for i-1 to make circular queue;

            if(i1<nPumps) { // Set diff once in the loop for n pumps
                diff[i] = gas[i] - cost[i];
            }

            if(diff[i]>=0 && j==-1){ // start with a pump that has no negative balance or it can go to next pump
                j=i;
                sum=diff[i];
                count++;
            }
            else if(j>=0){// for i!=j ; once set then add up diff and make sure it is >0 for a complete nPumps cycle
                sum += diff[i]; // can be replaced by diff[i] = diff[i] + diff[k]  where k =(i+nPumps-1)%(nPumps); and compare diff[i]<0 ; rolling sum of the diff
                 if(sum<0){
                    j=-1; // Reset the
                    count=0;
                }
                else {
                    count++; // we have to go for one loop complete to declare j as the starting point
                }
            }
            //Complete a positive gas tank loop
            if(count==nPumps) {
                return j;
            }
            i1++;
        }
        return -1;
    }
     void printArray(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
        System.out.println("Done..");
    }

public static void main(String[] args) {

PetrolPump pump = new PetrolPump();
    int[] gas = {1, 2, 3, 4, 5};
    int[] cost = {3, 4, 5, 1, 2};
    int[] gas1 = {3,3, 4};
    int[] cost1 = {3,4, 4};
    System.out.println(pump.findStartNode(gas1, cost1));
    System.out.println(pump.findStartNode(gas, cost));
}

}