package com.znn.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Consumer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("file:D:/Projects/SolrStudy/DubboClient/dubbo_consumer.xml");
		context.start();
		/*DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
		String hello = demoService.sayHello("world"); // 执行远程方法
		System.out.println(hello);*/

		CommandService commandService = (CommandService)context.getBean("commandService"); // 获取远程服务代理
		String hello = commandService.sendCommand("check status"); // 执行远程方法
		System.out.println(hello);
	}
}
