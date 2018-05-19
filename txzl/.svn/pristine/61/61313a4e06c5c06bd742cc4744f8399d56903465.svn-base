
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.listener
 * 文件名：         TriggerListenerImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-12-13-下午05:11:17
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.quartz.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;


 /**
 * 类名称：     TriggerListenerImpl
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-12-13 下午05:11:17
 * 修改备注：
 * @version 1.0.0
 **/

public class TriggerListenerImpl implements TriggerListener {
	private Log log = LogFactory.getLog(this.getClass());
	/**
	 * (non-Javadoc)
	 * @see org.quartz.TriggerListener#getName()
	 **/

	@Override
	public String getName() {
		return "QuartzTriggerListener";
	}

	/**
	 * (non-Javadoc)
	 * @see org.quartz.TriggerListener#triggerComplete(org.quartz.Trigger, org.quartz.JobExecutionContext, int)
	 **/

	@Override
	public void triggerComplete(Trigger trigger,
			JobExecutionContext jobExecutionContext, int i) {
	}

	/**
	 * (non-Javadoc)
	 * @see org.quartz.TriggerListener#triggerFired(org.quartz.Trigger, org.quartz.JobExecutionContext)
	 **/

	@Override
	public void triggerFired(Trigger trigger,
			JobExecutionContext jobexecutioncontext) {

	}

	/**
	 * (non-Javadoc)
	 * @see org.quartz.TriggerListener#triggerMisfired(org.quartz.Trigger)
	 **/

	@Override
	public void triggerMisfired(Trigger trigger) {
		// TODO Auto-generated method stub

	}

	/**
	 * (non-Javadoc)
	 * @see org.quartz.TriggerListener#vetoJobExecution(org.quartz.Trigger, org.quartz.JobExecutionContext)
	 **/

	@Override
	public boolean vetoJobExecution(Trigger trigger,
			JobExecutionContext jobexecutioncontext) {
		// TODO Auto-generated method stub
		return false;
	}

}
