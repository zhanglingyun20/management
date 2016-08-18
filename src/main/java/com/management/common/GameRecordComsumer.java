package com.management.common;

import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.management.controller.sync.DataSyncController;
import com.management.service.common.GameRunRecordRequest;
import com.management.service.sync.SyncDataService;

@Component
public class GameRecordComsumer {
	private static Logger logger = Logger.getLogger(GameRecordComsumer.class);
	public static LinkedBlockingQueue<GameRunRecordRequest> gameRecordQueue = new LinkedBlockingQueue<GameRunRecordRequest>();

	@PostConstruct
	public void init() {
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {

				public void run() {
					try {
						while (!Thread.interrupted()) {
							GameRunRecordRequest gameRunRecordRequest = gameRecordQueue.take();
							logger.info("收到上传消息 gameRunRecordRequest="+ gameRunRecordRequest.toString());
							SyncDataService syncDataService = SpringContextUtil.getBean(SyncDataService.class);
							syncDataService.syncGameRunData(gameRunRecordRequest);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}
