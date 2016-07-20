package com.cmos.ipg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * mq 发送给数据线程 Created by Administrator on 2016/6/8.
 */
@Service
public class MqConsumerService{
	//mq服务地址
	private @Value("${com.cmos.mq.nameServerAddress}") String nameServerAddress;
	 @Autowired
	 private CWPCoreService cwpCoreService;
	//告警主题
	public static String TOPIC_ALARM = "TopAlarm";
	public static String TAG_ACCESS ="TagAccess";
	//状态主题
	public static String TOPIC_STATUS = "TopStatus";
	public static String TAG_ENERGY = "TagEnergy";
	public static String TAG_DEVICE = "TagDevice";
	private static MqConsumerService mqClient;
	/*private MqConsumerService() {
	}

	*//**
	 * 得到实例
	 *//*
	public static MqConsumerService getBacnetClient() {
		if (mqClient == null) {
			synchronized (MqConsumerService.class) {
				if (mqClient == null) {
					mqClient = new MqConsumerService();
				}
			}
		}
		return mqClient;
	}*/
	/**
	 * 启动消费
	 */
	public void start(){
		//楼控消费者启动
		ConsumerMq cmqD = new ConsumerMq("deviceConsumerGroupName","deviceInstanceConsumer");
		cmqD.setTopic(TOPIC_STATUS);
		cmqD.setTag(TAG_DEVICE);
		new Thread(cmqD).start();
		//能耗消费者启动
		ConsumerMq cmqE = new ConsumerMq("energyConsumerGroupName","energyInstanceConsumer");
		cmqE.setTopic(TOPIC_STATUS);
		cmqE.setTag(TAG_ENERGY);
		new Thread(cmqE).start();
		//告警消费者启动
		ConsumerMq cmqA = new ConsumerMq("accessConsumerGroupName","accessInstanceConsumer");
		cmqA.setTopic(TOPIC_ALARM);
		cmqA.setTag(TAG_ACCESS);
		Thread alarmThread = new Thread(cmqA);
		alarmThread.setName("告警线程");
		alarmThread.start();
		
	}

	class ConsumerMq implements Runnable {
		DefaultMQPushConsumer consumer = null;
		String topic =null;
		String tag=null;
		int max=100;
		public void createConumer() {
		
		}
		public ConsumerMq(String consmerGroupName, String instanceNameConsmer){
			consumer = new DefaultMQPushConsumer(consmerGroupName);
			consumer.setNamesrvAddr(nameServerAddress);
			consumer.setInstanceName(instanceNameConsmer);
		}
	
		@Override
		public void run() {
			System.out.println("开启线程任务");
			try {
				/**
				 * 订阅指定topic下tags分别等于TagA或TagC或TagD
				 */
//				consumer.subscribe("TopicTest1", "TagA || TagC || TagD");
				/**
				 * 订阅指定topic下所有消息<br>
				 * 注意：一个consumer对象可以订阅多个topic
				 */
//				consumer.subscribe("TopicTest2", "*");
				consumer.subscribe(topic,tag);
				//通过设置consumeMessageBatchMaxSize参数来批量接收消息
				consumer.setConsumeMessageBatchMaxSize(max);
				// 程序第一次启动从消息队列头取数据
				consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
				consumer.registerMessageListener(new MessageListenerConcurrently() {

					/**
					 * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
					 */
					@Override
					public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
							ConsumeConcurrentlyContext context) {
						System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs.size());
						for(MessageExt msg:msgs){
							if (msg.getTopic().equals(TOPIC_STATUS)) {
								// 执行TopicTest1的消费逻辑
								if (msg.getTags() != null && msg.getTags().equals(TAG_DEVICE)) {
									// 执行TagDevice的消费
									System.out.println(msg.getTopic()+msg.getTags());
									cwpCoreService.sendDeviceRecordMessage(new String(msg.getBody()));
								} else if (msg.getTags() != null && msg.getTags().equals(TAG_ENERGY)) {
									// 执行TagEnergy的消费
									System.out.println(msg.getTopic()+msg.getTags());
									cwpCoreService.sendEnergyRecordMessage(new String(msg.getBody()));
								} else if (msg.getTags() != null && msg.getTags().equals("TagD")) {
									// 执行TagD的消费
								}
							} else if (msg.getTopic().equals(TOPIC_ALARM)) {
								if(msg.getTags()!=null&&msg.getTags().equals(TAG_ACCESS)){
									System.out.println(msg.getTopic()+msg.getTags());
									cwpCoreService.sendWarningMessage(new String(msg.getBody()));
								}
								System.out.println(new String(msg.getBody()));
							}
						}

						return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
					}
				});
				/**
				 * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
				 */
				consumer.start();
			} catch (MQClientException e) {
				e.printStackTrace();
			}

		}
		
		public String getTopic() {
			return topic;
		}
		public void setTopic(String topic) {
			this.topic = topic;
		}
		public String getTag() {
			return tag;
		}
		public void setTag(String tag) {
			this.tag = tag;
		}
		
		public int getMax() {
			return max;
		}
		public void setMax(int max) {
			this.max = max;
		}

	}

}
