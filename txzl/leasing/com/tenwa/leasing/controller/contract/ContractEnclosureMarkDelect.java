package com.tenwa.leasing.controller.contract;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.file.BaseFile;
@Controller(value = "ContractEnclosureMarkDelect")
@RequestMapping(value = "/**/acl")
public class ContractEnclosureMarkDelect {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@RequestMapping(value = "/ContractEnclosureMarkDelect.acl")
	@ResponseBody
	public boolean ContractEnclosureMarkDelect(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			String uafdi = request.getParameter("uploadAttachmentFileDetailId");
			BaseFile bf = this.tableService.findEntityByID(BaseFile.class, uafdi);
			bf.setInvalid("否");
			this.tableService.updateEntity(bf);
			return true;
		}catch (Exception e) {
			return false;
		}
		
		
		
	}
	
}
