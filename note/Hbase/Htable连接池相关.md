## Htable 连接池相关

#### 连接池的作用

在多线程环境下，每个线程要保持独立的连接来完成自己处理逻辑，这时需要连接池

#### HTable需要用到的连接

htabe需要连接到hbase集群(HConnection)，还需要连接到zookeeper集群，但是一个jvm里面的所有HTable可以共享一份连接资源，他并不需要一个独立的连接来完成自己的操作

#### 到底是怎么处理的

HTable类的注释这样说道

![Hbase类的注释](https://github.com/Saber-Altria/note/raw/master/images/Hbase/Hbase%E8%BF%9E%E6%8E%A5%E6%B1%A0%E7%9B%B8%E5%85%B3/Htable%E6%B3%A8%E9%87%8A.jpg)

所有通过相同Configuration对象创建的Htable都共享zookeeper connection等其他资源</br>

即是说zk connection和HConnection这些连接资源是和configuration对象一一对应的</br>

所以在创建HTable对象时只要保证configuration对象相同，就不会反复创建zk连接和Hbase连接，也就没有使用连接池的必要</br>

