package cn.studyz.kafka.consumer.high;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

import java.util.Properties;

/**
 * Created by zhangxue on 2018/2/5.
 */
public class ConsumerRunnable implements Runnable {

    int threadId;
    KafkaStream stream;

    public ConsumerRunnable(int threadId, KafkaStream stream) {
        this.threadId = threadId;
        this.stream = stream;
    }

    @Override
    public void run() {
        ConsumerIterator<byte[],byte[]> iterator = stream.iterator();
        while (iterator.hasNext()){
            MessageAndMetadata<byte[],byte[]> next = iterator.next();
            System.out.println("Thread"+threadId+":"+new String(next.message()));
        }
        System.out.println("Shutdown thread : " +threadId);
    }

}
