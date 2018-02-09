package cn.studyz.concurrent.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangxue on 2018/2/8.
 */
public class CounterDemo {

    private final ConcurrentHashMap<String,Integer> urlVisitTimes = new ConcurrentHashMap<>();


    public synchronized  void  increase1(String url){
        Integer visitTime = urlVisitTimes.get(url);
        visitTime = visitTime == null ? 0 : visitTime+1;
        urlVisitTimes.put(url,visitTime);
    }

    public int getCount(String url){
        Integer visitTime = urlVisitTimes.get(url);
        return visitTime == null ? 0 : visitTime;
    }

    public static void main(String[] args){
        final ExecutorService executor = Executors.newFixedThreadPool(10);
        int callTime = 10000;
        final CountDownLatch counter = new CountDownLatch(callTime);
        final String url = "localhost:8080/hello";

        final CounterDemo counterDemo = new CounterDemo();

        for(int i = 0 ; i <= callTime+100000 ; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    counterDemo.increase1(url);
                    counter.countDown();
                }
            });
        }

        try{
            counter.await();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println("调用次数："+counterDemo.getCount(url));
        System.out.println("counter："+counter.getCount());

    }
}
