package com.kongfu.business.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.jingling.core.scheduler.AbstractScheduleHandler;

/**
 * 定时任务
 * 
 * @author CNkongfu
 * @date 2017年2月13日
 */
@Component
public class OverdueScheduleHandler extends AbstractScheduleHandler {

	@Override
	protected void doHandle() throws IllegalAccessException, Exception {
		quartz1();
	}

	private void quartz1() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		System.out.println("this is a quartz " + sdf.format(new Date()));
	}

}
