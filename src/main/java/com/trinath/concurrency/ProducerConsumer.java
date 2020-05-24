package com.trinath.concurrency;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;



public class ProducerConsumer {

    static boolean flag = false;
    static final int NUM_OF_RECORD = 100000;
    static final int CHUNK_SIZE = 1000;
    static ProducerConsumer obj = new ProducerConsumer();
    static long start;

    public static void main(String args[]) throws Exception {
        //PrintStream o = new PrintStream(new File("/Users/tpatri1/test/experiment/yugabyte_ins_delete_read_thread.txt"));//TODO: Give own file path to print log in file
        //System.setOut(o);
        obj.runLoadTest1(flag);
    }


    private   void runLoadTest1(boolean flag) throws Exception {

        ConcurrentHashMap<UUID, Integer> tsBucketMap = new ConcurrentHashMap<UUID, Integer>();
        Thread schedule = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < NUM_OF_RECORD; i++) {
                        System.out.println("Starting Insertion to scheduled_events " );
                        insertToScheduledEvent(flag, tsBucketMap);
                    }

                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());

                }
            }
        });
        Thread insDelete = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < NUM_OF_RECORD; i++) {
                        System.out.println("Starting delete from scheduled_events and insert to inprogress_events "+i);
                        insertAndDelete(flag, tsBucketMap);
                    }


                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());

                }
            }
        });
        schedule.start();
        insDelete.start();
        schedule.join();
        insDelete.join();
    }

    private   void insertToScheduledEvent(boolean flag, Map<UUID, Integer> tsBucketMap) throws Exception {

        List<Future> futures = new ArrayList<>();
        Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        AtomicInteger count = new AtomicInteger(0);
        System.out.println("****Initial: Record count of scheduled_events"+" chunk size= "+CHUNK_SIZE);
        try {

            synchronized (obj){
                System.out.println(" T1 After sync "+this.getClass()+this);
                long chunkStart = System.currentTimeMillis();


                while(tsBucketMap.size()==CHUNK_SIZE){
                    System.out.println("####Waiting to insert to scheduled events table "+Thread.currentThread().getName());
                    obj.wait();

                }

                System.out.println("####Executing to insert to scheduled events table "+Thread.currentThread().getName());
                futures.add(executorService.submit(() -> {
                    int c = count.incrementAndGet();
                    int bucket_id = random.nextInt(10);
                    UUID uuid = UUID.randomUUID();

                    //insertTest("scheduled_events", bucket_id, ts);
                    tsBucketMap.put(uuid, bucket_id);//Use uuid instead of time stamp, so that the map size iis predictable as timestamp can be same when program runs faster



                }));


            for (Future f : futures) {
                //f.get(60, TimeUnit.MINUTES);
                f.get();
            }
            //Muust notify after future get to ensure the bucket has tsBucketMap
            System.out.println("T1 Before notify "+this.getClass()+this);
            obj.notifyAll();
            }

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.print("Error processing scheduled_events"+e.getMessage());
            throw new Exception(e.getMessage());

        }
        finally {
            executorService.shutdown();
            long timeTaken = System.currentTimeMillis() - start;
            // System.out.println(">>>>Done: Record count of scheduled_events"+recordCount("scheduled_events")+" time taken: "+timeTaken);
        }

    }

    private   void insertAndDelete(boolean getNumberOfRecords, ConcurrentHashMap<UUID, Integer> tsBucketMap) throws Exception {

        List<Future> futures = new ArrayList<>();
        Random random = new Random();
        AtomicInteger count1 = new AtomicInteger(0);
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        System.out.println("******Initial: Record count of inprogress_events "+ " chunk size= "+CHUNK_SIZE);//+recordCount("scheduled_events")
        long start1 = System.currentTimeMillis();
        try {
            long chunkStart = System.currentTimeMillis();
            synchronized (obj){
                //System.out.println(" T2 After sync "+this.getClass()+this);

                while(tsBucketMap.size()==0){//tsBucketMap.size()==0
                    System.out.println("@@@Waiting to insert to inprogress_events and delete schduled_events table "+Thread.currentThread().getName());
                    obj.wait();
                }
                System.out.println("@@@Executing to insert to inprogress_events and delete schduled_events table "+Thread.currentThread().getName());
                for(UUID uuid:tsBucketMap.keySet()) {
                    futures.add(executorService1.submit(() -> {
                        int bucket_id = tsBucketMap.get(uuid);
                        tsBucketMap.remove(uuid);
                    }
                    ));
                    break;// If you dont break after one item in the loop, then you will delete faster beofre the run() delete wiill be kept on triggering and program will wait and never finsish
                }



            for (Future f : futures) {
                //f.get(60, TimeUnit.MINUTES);
                f.get();
            }
                System.out.println("T2 Before notify and current bucket size"+tsBucketMap.size());
                obj.notifyAll();

            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.print("Error processing inprogress_events"+e.getMessage());
            throw new Exception(e.getMessage());
        }
        finally {
            executorService1.shutdown();
            long timeTaken = System.currentTimeMillis() - start1;
            System.out.println(">>>>>Done: Record count of inprogress_events time taken: "+timeTaken);
        }
    }

}


