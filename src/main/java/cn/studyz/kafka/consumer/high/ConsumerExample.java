package cn.studyz.kafka.consumer.high;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangxue on 2018/2/5.
 */
public class ConsumerExample {

    ConsumerConnector consumerConnector;
    String topic;

    ExecutorService executor;

    public ConsumerExample(String zks, String group, String topic) {
        consumerConnector = Consumer.createJavaConsumerConnector(createConsumerConfig(zks,group));
        this.topic = topic;
    }

    public void shutdown(){
        if(consumerConnector == null) consumerConnector.shutdown();

        if(executor == null ) executor.shutdown();

        try{
            if(!executor.awaitTermination(5000, TimeUnit.SECONDS)){
                System.out.println("consumerConnector and executor shutdown time out ,exiting uncleanly...");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted during shutdown,exiting uncleanly...");
        }
    }

    public void consume(int thread){
        Map<String,Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic,new Integer(thread));

        Map<String, List<KafkaStream<byte[], byte[]>>> streamMaps = consumerConnector.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = streamMaps.get(topic);

        executor = Executors.newFixedThreadPool(thread);

        int threadSeq = 0;
        for(KafkaStream<byte[],byte[]> stream:streams){
            executor.submit(new ConsumerRunnable(threadSeq,stream));
            threadSeq++;
        }
    }

    private static ConsumerConfig createConsumerConfig(String zks, String group){
        Properties properties = new Properties();
        properties.put("zookeeper.connect",zks);
        properties.put("group.id",group);
        properties.put("zookeeper.session.timeout.ms", "400");
        properties.put("zookeeper.sync.time.ms", "200");
        properties.put("auto.commit.interval.ms", "1000");
        return new ConsumerConfig(properties);
    }
}
