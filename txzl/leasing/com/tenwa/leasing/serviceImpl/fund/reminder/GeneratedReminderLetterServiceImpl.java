package com.tenwa.leasing.serviceImpl.fund.reminder;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.fund.overdue.OverdueDunningNotice;
import com.tenwa.leasing.service.fund.reminder.GeneratedReminderLetterService;

@Service(value="generatedReminderLetterService")
public class GeneratedReminderLetterServiceImpl
implements GeneratedReminderLetterService {
	@Resource(name = "tableService")
	private TableService tableService;
	@Override
	public void saveGeneratedReminderLetter(Map<String, String> model)throws Exception {
		String datas = model.get("datas");
		this.tableService.saveOrUpdateEntitiesByJSONArrayString(OverdueDunningNotice.class, datas, null, "");
	}

	@Override
	public void removeGeneratedReminderLetter(Map<String, String> model)throws Exception {
		String noticeids = model.get("noticeids");
		String[]  noticeidArrays = noticeids.split(",");
		for (int i = 0; i < noticeidArrays.length; i++) {
			OverdueDunningNotice overduedunningnotice = new OverdueDunningNotice();
			overduedunningnotice.setId(noticeidArrays[i]);
			this.tableService.removeEntity(overduedunningnotice);
		}
	}

	@Override
	public void updateGeneratedReminderLetter(Map<String, String> model)throws Exception {
		String id = model.get("id");
		String status = model.get("status");
		OverdueDunningNotice overduedunningnotice = this.tableService.findEntityByID(OverdueDunningNotice.class, id);
		overduedunningnotice.setStatus(Integer.parseInt(status));
		this.tableService.saveEntity(overduedunningnotice);
	}
}
