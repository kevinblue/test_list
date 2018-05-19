/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jbpm.pvm.internal.history.model;

import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.pvm.internal.model.ExecutionImpl;

import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;


/**
 * @author Tom Baeyens modify by tracywindy
 */
public class HistoryTaskInstanceImpl extends HistoryActivityInstanceImpl {

  private static final long serialVersionUID = 1L;
  
  HistoryTaskImpl historyTask;
  private JBPMWorkflowHistoryInfo activedProcessInstanceHistoryInfo;
  private JBPMWorkflowHistoryInfo processInstanceHistoryInfo;
  private JBPMWorkflowHistoryInfo taskHistoryInfo;
  private JBPMWorkflowHistoryInfo firstedProcessInstanceHistoryInfo;
  private JBPMWorkflowHistoryInfo lastToProcessInstanceHistoryInfo;
  
  public HistoryTaskInstanceImpl() {
  }

  public HistoryTaskInstanceImpl(HistoryProcessInstance historyProcessInstanceImpl, ExecutionImpl execution, HistoryTaskImpl historyTask) {
    super(historyProcessInstanceImpl, execution);
    this.historyTask = historyTask;
  }

  public HistoryTaskImpl getHistoryTask() {
    return historyTask;
  }

	public void setTaskHistoryInfo(JBPMWorkflowHistoryInfo taskHistoryInfo) {
		this.taskHistoryInfo = taskHistoryInfo;
	}
	
	public JBPMWorkflowHistoryInfo getTaskHistoryInfo() {
		return taskHistoryInfo;
	}
	
	public void setLastToProcessInstanceHistoryInfo(
			JBPMWorkflowHistoryInfo lastToProcessInstanceHistoryInfo) {
		this.lastToProcessInstanceHistoryInfo = lastToProcessInstanceHistoryInfo;
	}
	
	public JBPMWorkflowHistoryInfo getLastToProcessInstanceHistoryInfo() {
		return lastToProcessInstanceHistoryInfo;
	}
	
	public void setFirstedProcessInstanceHistoryInfo(
			JBPMWorkflowHistoryInfo firstedProcessInstanceHistoryInfo) {
		this.firstedProcessInstanceHistoryInfo = firstedProcessInstanceHistoryInfo;
	}
	
	public JBPMWorkflowHistoryInfo getFirstedProcessInstanceHistoryInfo() {
		return firstedProcessInstanceHistoryInfo;
	}
	
	public void setActivedProcessInstanceHistoryInfo(
			JBPMWorkflowHistoryInfo activedProcessInstanceHistoryInfo) {
		this.activedProcessInstanceHistoryInfo = activedProcessInstanceHistoryInfo;
	}
	
	public JBPMWorkflowHistoryInfo getActivedProcessInstanceHistoryInfo() {
		return activedProcessInstanceHistoryInfo;
	}
	
	
	public void setProcessInstanceHistoryInfo(JBPMWorkflowHistoryInfo processInstanceHistoryInfo) {
		this.processInstanceHistoryInfo = processInstanceHistoryInfo;
	}
	
	
	public JBPMWorkflowHistoryInfo getProcessInstanceHistoryInfo() {
		return processInstanceHistoryInfo;
	}
}
