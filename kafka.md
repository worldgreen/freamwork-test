1. 什么是kafka
    
    发布订阅消息系统，分布式，分区，可重复的日志服务

2. 传统消息传递方式
   - 队列：一组用户可以从队列中取消息，每条消息只发给一个人
   - 发布-订阅：消息被广播给所有用户
   
3. Kafka优势
   - 快速
   - 可伸缩性
   - 持久性，消息在集群被复制
   
4. zookeeper 作用
   - 集群不同节点通信
   - leader选举
   - 存储偏移量
   
5. 消息队列的使用场景
   - 异步处理（把不太重要的东西放到队列中）
      - 用户注册时，发短信，发邮件。
      - 串行  50 - 50 - 50   150 
      - 消息队列  50
   - 应用解耦
      - 订单系统 -> 库存系统
      - 传统  订单系统调用库存系统API，耦合
      - 消息队列： 中间加入消息队列
   - 流量削峰
      - 秒杀，或团抢
      - 用户请求 -> 消息队列 -> 秒杀业务处理
      秒杀活动，一般会应为流量过大，导致流量暴增，应用垮掉，加入消息队列，控制人数
   - 日志处理
      - 日志采集客户端 -> Kafka -> 日志处理应用
   - 消息通信
      - 点对点通信： 客户端A和B使用同一个队列
      - 聊天室通信： 客户端A到N订阅同一个主题进行消息发送和接受