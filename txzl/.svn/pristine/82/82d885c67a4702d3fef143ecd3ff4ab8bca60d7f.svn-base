/***oracle系统初始化必须执行***/
/****license授权信息****/
-- Create table
create table T_LICENSE
(
  ID_              VARCHAR2(32 CHAR) not null,
  PRIVATE_KEY_     BLOB,
  GRANT_INFO_      BLOB
);
alter table T_LICENSE add primary key (ID_);
insert into T_license(id_) values('07be054d91ec452e8f0f30383bb3e0fa');
/****JBPM4 oracle需要更正某些字段的类型***/
ALTER TABLE JBPM4_DEPLOYMENT MODIFY NAME_ VARCHAR2(300);
ALTER TABLE JBPM4_LOB        MODIFY NAME_ VARCHAR2(300);
/***初始化超级管理员用户****/
insert into t_users (ID_, USERNAME_, CODE_, PASSWORD_, REALNAME_, TELEPHONE_, EMAIL_, LASTUPDATEPASSWORDDATE_, ENABLED_, CREATE_DATE_, MODIFY_DATE_, CREATOR_, MODIFICATOR_)
values ('Administrator', 'administrator', 'Administrator', '74892abe03708f2244f1f48de08f17a4', '超级管理员', '1', '1', '2013-11-27 12:25:50', 1, '2013-08-19', '2013-08-19', '', '');
/****初始化组织机构*****/
insert into t_depts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, MANAGER_USER_ID_, CREATOR_, MODIFICATOR_)
values ('0', '组织架构', 'CODE_0', '', 0, '2013-08-19', '', '', '', 'Administrator', '');
insert into t_depts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, MANAGER_USER_ID_, CREATOR_, MODIFICATOR_)
values ('admin_dept', '系统管理部', 'admin_dept', '', 0, '2013-08-19', '', '0', '', 'Administrator', '');
/***初始化系统管理部门人员****/
insert into t_users_depts (ID_, CREATE_DATE_, MODIFY_DATE_, USER_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('Administrator', '2013-08-19', '', 'Administrator', 'admin_dept', 'Administrator', '');
/*****初始化系统菜单*****/
insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('0', '系统菜单', 'SystemMenus', '', 'base.gif', '', '', '', 0, 1, '2013-08-19', '', '', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A2', '我的工作', 'MyWork', '', 'money.png', '', '', '', 33, 1, '2013-08-19', '2013-09-03 16:57:51', '0', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A21', '待办事宜', 'WorkToDo', 'workflow/jbpm-core/pendingRequest.bi', 'case.png', '', '', '', 33, 1, '2013-08-19', '', 'A2', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A22', '已办事宜', 'WorkTerminative', 'workflow/jbpm-core/completedRequest.bi', 'inbox.png', '', '', '', 34, 1, '2013-08-19', '', 'A2', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A23', '我的草稿', 'WorkDraft', 'workflow/jbpm-core/draftRequest.bi', 'layers.png', '', '', '', 35, 1, '2013-08-19', '', 'A2', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A24', '流程实例跟踪', 'WorkFlowTrack', 'workflow/jbpm-core/processInstanceRequest.bi', 'pin.png', '', '', '', 36, 1, '2013-08-19', '', 'A2', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A25', '可启动的流程', 'WorkStart', 'workflow/jbpm-core/authWorkflowStart.bi', 'flag.png', '', '', '', 37, 1, '2013-08-19', '', 'A2', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A26', '可查看的流程', 'WorkSelect', 'workflow/jbpm-core/authWorkflowView.bi', 'money.png', '', '', '', 38, 1, '2013-08-19', '', 'A2', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A27', '上报', 'SubmitMessage', 'leasing/publishloadinfo/appear.bi', 'flag.png', '', '', '', 39, 1, '2013-08-19', '', 'A2', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A28', '发布', 'PublishMessage', 'leasing/publishloadinfo/publish.bi', 'flag.png', '', '', '', 40, 1, '2013-08-19', '', 'A2', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A3', '客户管理', 'Custs', '', 'users.png', '', '', '', 42, 1, '2013-08-19', '', '0', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A31', '客户管理', 'CustManage', '', 'users.png', '', '', '', 42, 1, '2013-08-19', '', 'A3', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A311', '自然人客户', 'CustPerson', 'leasing/cust_info/cust_person/cust_person_list.bi', 'users.png', '', '', '', 43, 1, '2013-08-19', '', 'A31', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A312', '法人客户', 'CustCompany', 'leasing/cust_info/cust_company/cust_company_list.bi', 'users.png', '', '', '', 44, 1, '2013-08-19', '', 'A31', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A4', '经销商管理', 'DealerManage', '', 'contact.png', '', '', '', 46, 1, '2013-08-19', '', '0', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A41', '经销商登记', 'DealerRegister', 'leasing/cust_info/cust_dealer/cust_dealer_list.bi', 'contact.png', '', '', '', 46, 1, '2013-08-19', '', 'A4', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A42', '经销商准入申请', 'DealerApproval', 'workflow/forms/distributor/distributor_manager/flow_openlist.bi', 'bubble.png', '', '', '', 47, 1, '2013-08-19', '', 'A4', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A43', '经销商准入评审', 'DealerReview', 'workflow/forms/distributor/distributor_review/flow_openlist.bi', 'flag.png', '', '', '', 48, 1, '2013-08-19', '', 'A4', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A44', '经销商合同管理', 'DealerContract', 'workflow/forms/distributor/distributor_contract/flow_openlist.bi', 'doc.png', '', '', '', 49, 1, '2013-08-19', '', 'A4', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A45', '经销商账号管理', 'DealerAccount', 'leasing/cust_info/cust_dealer/dealer_account_manager.bi', 'contact.png', '', '', '', 50, 1, '2013-08-19', '', 'A4', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A46', '经销商额度管理', 'DealerCredit', 'workflow/forms/distributor/distributor_limitmanager/flow_openlist.bi', 'balance.png', '', '', '', 51, 1, '2013-08-19', '', 'A4', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A47', '提前放款监控表', 'DealerLoanList', 'leasing/special_function_manager/condition_beforeloan.bi', 'clipboard.png', '', '', '', 52, 1, '2013-08-19', '', 'A4', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A48', '经销商抵押合同', 'DealerDoc', 'workflow/forms/distributor/distributor_mortgage_contract/flow_open.bi', 'contact.png', '', '', '', 53, 1, '2013-08-19', '', 'A4', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('297e7def410190e7014102bffe5600da', '抵押备案信息', 'diyabeian', 'workflow/forms/other/mortgage_record/flow_openlist.bi', 'flag.png', '', '', '', 55, 1, '2013-09-09 20:43:58', '2013-09-10 15:45:26', 'A4', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A49', '经销商保证金收款', 'DealerGuaranceCollection', 'workflow/forms/distributor/distributor_collection/flow_openlist.bi', 'compass.png', '', '', '', 56, 1, '2013-08-19', '', 'A4', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A50', '经销商保证金退款', 'DealerGuarancereBack', 'workflow/forms/distributor/distributor_drawback/flow_openlist.bi', 'cogs-disable.png', '', '', '', 57, 1, '2013-08-19', '', 'A4', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('297e7def40cd4afc0140cd9ce9f7001a', '经销商保证金抵扣', 'disguarandedution', 'workflow/forms/distributor/distributor_deduction/flow_openlist.bi', 'db.png', '', '', '', 61, 1, '2013-08-30 13:05:47', '2013-08-31 16:20:47', 'A4', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A5', '租前业务', 'BusinessApply', '', 'star.png', '', '', '', 58, 1, '2013-08-19', '2013-09-06 18:09:39', '0', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A51', '项目立项管理', 'ProjApplyManage', '', 'pencil.png', '', '', '', 57, 1, '2013-08-19', '', 'A5', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A511', '项目立项', 'ProjApply', 'workflow/forms/project/proj_approval/flowopen_list.bi', 'bubble.png', '', '', '', 59, 1, '2013-08-19', '2013-08-28 17:28:57', 'A51', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A512', '项目变更', 'ProjChange', 'workflow/forms/project/proj_change/flowopen_list.bi', 'reload.png', '', '', '', 60, 1, '2013-08-19', '', 'A51', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A513', '项目撤销', 'ProjCancel', 'workflow/forms/project/proj_cancel/flowopen_list.bi', 'delete.png', '', '', '', 61, 1, '2013-08-19', '', 'A51', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A52', '项目审核', 'ProjReviewManage', '', 'layers.png', '', '', '', 61, 1, '2013-08-19', '', 'A5', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A521', '项目信审', 'ProjReview', 'workflow/forms/project/proj_credit/flowopen_list.bi', 'globe.png', '', '', '', 62, 1, '2013-08-19', '', 'A52', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A522', '项目复议', 'DuplicateReview', 'workflow/forms/project/proj_reconsider/flowopen_list.bi', 'clipboard.png', '', '', '', 63, 1, '2013-08-19', '', 'A52', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A523', '项目资料补充', 'ProjDocAdd', '/workflow/forms/project/proj_file_upload/flowopen_list.bi', 'shield.png', '', '', '', 66, 1, '2013-08-19', '2013-08-26 12:54:49', 'A52', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A53', '合同管理', 'ContractManage', '', 'notepad.png', '', '', '', 65, 1, '2013-08-19', '', 'A5', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A531', '合同审批', 'ContractReview', 'workflow/forms/contract/contract_approval/flow_openlist.bi', 'case.png', '', '', '', 68, 1, '2013-08-19', '', 'A53', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('4028829540de2eef0140de4df5170002', '合同额度金额管理', 'contractCreditMoney', '', 'key.png', '', '', '', 70, 1, '2013-09-02 18:53:05', '2013-09-02 18:55:14', 'A53', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('4028814940c29db00140c2fcc227000f', '设备验收', 'equip_acceptance', 'workflow/forms/contract/equip_acceptance/flow_openlist.bi', 'zoom.png', '', '', '', 71, 1, '2013-08-28 11:34:39', '', 'A53', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A532', '合同起租', 'ContractStar', 'workflow/forms/contract/contract_onhire/flow_openlist.bi', 'check.png', '', '', '', 72, 1, '2013-08-19', '', 'A53', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A533', '合同多次起租', 'ContractStarMore', 'workflow/forms/contract/contract_many_onhire/flow_open.bi', 'check.png', '', '', '', 73, 0, '2013-08-19', '', 'A53', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A534', '合同变更', 'ContractChange', 'workflow/forms/contract/contract_change/flow_openlist.bi', 'reload.png', '', '', '', 74, 1, '2013-08-19', '', 'A53', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A535', '合同撤销', 'ContractCancel', 'workflow/forms/contract/contract_cancel/flow_openlist.bi', 'delete.png', '', '', '', 75, 1, '2013-08-19', '', 'A53', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A6', '资产管理', 'AssetsManage', '', 'balance.png', '', '', '', 76, 1, '2013-08-19', '', '0', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A61', '合同租金管理', 'ContractRentManage', '', 'case.png', '', '', '', 75, 1, '2013-08-19', '', 'A6', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A611', '租金回笼', 'RentIncome', 'workflow/forms/assets/fund/fund_income/flow_openlist.bi', 'balance.png', '', '', '', 76, 1, '2013-08-19', '', 'A61', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A612', '期末保证金抵扣', 'CautionDeductible', 'workflow/forms/final_deposit/deduction/flow_open.bi', 'balance.png', '', '', '', 77, 1, '2013-08-19', '', 'A61', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A613', '经销商租金回笼', 'RentIncomeDealer', 'workflow/forms/assets/fund/fund_translation/flow_openlist.bi', 'balance.png', '', '', '', 79, 1, '2013-08-19', '2013-08-30 15:35:59', 'A61', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A62', '资金管理', 'FundsManage', '', 'balance.png', '', '', '', 79, 1, '2013-08-19', '', 'A6', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A621', '实际投放', 'PutFunds', 'workflow/forms/fund/fund_payequipment/flow_openlist.bi', 'balance.png', '', '', '', 80, 1, '2013-08-19', '', 'A62', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('4028813f41befe650141bff55c6a0023', '投放额度申请', 'PUTLIMITAPP', 'workflow/forms/fund/fund_limit_app/flow_openlist.bi', 'chart_pie.png', '', '', '额度申请', 81, 1, '2013-10-16 14:30:30', '', 'A62', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A622', '项目投放', 'PutFundsDealer', 'workflow/forms/fund/fund_payequipment_new/flow_open.bi', 'sum.png', '', '', '', 82, 1, '2013-08-19', '', 'A62', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A623', '付款', 'PayFunds', 'workflow/forms/fund/fund_payment/flow_openlist.bi', 'money.png', '', '', '', 83, 1, '2013-08-19', '', 'A62', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A624', '经销商资金收款', 'FundsIncomeDealer', 'workflow/forms/fund/fund_money_collection/flow_openlist.bi', 'balance.png', '', '', '', 85, 1, '2013-08-19', '2013-08-26 16:47:53', 'A62', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A625', '收款', 'FundsIncome', 'workflow/forms/fund/fund_collection/flow_openlist.bi', 'money.png', '', '', '', 86, 1, '2013-08-19', '', 'A62', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A626', '设备验收', 'EuipAccepting', 'workflow/forms/contract/equip_acceptance/flow_open.bi', 'check.png', '', '', '', 87, 1, '2013-08-19', '', 'A62', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A627', '资金计划变更', 'FundsPlanChange', 'workflow/forms/fund/fund_plan_change/flow_openlist.bi', 'box.png', '', '', '', 89, 1, '2013-08-19', '2013-08-23 10:14:11', 'A62', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A63', '保险管理', 'InsuranceManage', '', 'box.png', '', '', '', 87, 1, '2013-08-19', '', 'A6', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A631', '保险清单', 'InsuranceList', 'leasing/insure_manage/insurance_info.bi', 'calendar.png', '', '', '', 90, 1, '2013-08-19', '2013-09-09 15:00:44', 'A63', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('4028814a41008a1d0141017cbd200024', '保险询报价', 'InsurAskMoney', 'workflow/forms/insurance/insurance_price/flow_openlist.bi', 'calc.png', '', '', '', 96, 1, '2013-09-09 14:50:54', '2013-09-09 16:18:13', 'A63', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('402809814149717401414a6199f60017', '保险续保提醒', 'insurance_xb_remind', 'leasing/insure_manage/insurance_xb_remind.bi', 'calendar.png', '', '', '', 98, 1, '2013-09-23 18:33:32', '2013-09-23 18:33:53', 'A63', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A64', '设备管理', 'EquipManage', '', 'box.png', '', '', '', 89, 1, '2013-08-19', '', 'A6', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A641', '设备管理', 'ManageEquip', 'leasing/lease_management/equip_management.bi', 'track.png', '', '', '', 92, 1, '2013-08-19', '2013-09-01 17:29:09', 'A64', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A642', '收车登记', 'EquipRevoke', 'leasing/lease_management/equip_revoke.bi', 'flag.png', '', '', '', 98, 1, '2013-08-19', '2013-09-02 20:19:09', 'A64', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A644', '设备登记', 'EquipAcceptance', 'leasing/base_equipment/equip_registration.bi', 'filter.png', '', '', '', 102, 1, '2013-08-19', '2013-09-02 18:41:00', 'A64', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A643', '收车评估', 'EquipEvaluate', 'workflow/forms/other/vehicle_evaluation/flow_openlist.bi', 'clipboard.png', '', '', '', 104, 1, '2013-08-19', '2013-09-02 18:23:09', 'A64', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A7', '租后管理', 'LeasingLastManage', '', 'chart_line.png', '', '', '', 96, 1, '2013-08-19', '', '0', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A71', '租后变更', 'LeasingChange', '', 'case.png', '', '', '', 95, 1, '2013-08-19', '', 'A7', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('4028815640e6bbe90140e6f93a0f0006', '央行基准利率调整表', 'AdjustmentOfInterestRate', 'leasing/regulating_of_breathing/pranayama_information_list.bi', 'chart_bar.png', '', '', '', 3, 1, '2013-09-04 11:17:07', '2013-09-04 11:18:36', 'A71', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A711', '起租后合同变更', 'ContractStarChange', 'workflow/forms/contract/contact_onhirechange/flow_openlist.bi', 'clipboard.png', '', '', '', 104, 1, '2013-08-19', '2013-08-22 17:11:53', 'A71', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A712', '租金计划变更', 'ContractPlanChange', 'workflow/forms/rent_plan/plan_change/flow_openlist.bi', 'notepad.png', '', '', '', 106, 1, '2013-08-19', '2013-08-20 15:56:48', 'A71', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A713', '租金计划修改', 'ContractPlanUpdate', 'workflow/forms/rent_plan/plan_update/flow_openlist.bi', 'notepad.png', '', '', '', 108, 1, '2013-08-19', '2013-08-20 15:56:59', 'A71', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A714', '调息', 'RateChange', 'workflow/forms/transfer_interest/flow_openlist.bi', 'chart_line.png', '', '', '', 110, 1, '2013-08-19', '2013-08-27 09:39:56', 'A71', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A715', '调息回滚', 'RateChangeBack', 'workflow/forms/rollback/flow_openlist.bi', 'chart_line.png', '', '', '', 112, 1, '2013-08-19', '2013-08-27 09:53:03', 'A71', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A72', '租金催收', 'RentUrgeManage', '', 'flag.png', '', '', '', 101, 1, '2013-08-19', '', 'A7', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A721', '租金催收', 'RentUrge', 'leasing/fund_overduerent/overdue_rent_info.bi', 'case.png', '', '', '', 102, 1, '2013-08-19', '', 'A72', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A722', '租金催款函', 'RentUrgeDoc', 'leasing/fund_overduerent/overdue_rent_letter_info.bi', 'doc.png', '', '', '', 103, 1, '2013-08-19', '', 'A72', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A73', '租后巡视', 'ContractCensorManage', '', 'layers.png', '', '', '', 104, 1, '2013-08-19', '', 'A7', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A731', '租后检查', 'ContractCensor', 'leasing/rent_aftercheck/rent_aftercheck.bi', 'flag.png', '', '', '', 105, 1, '2013-08-19', '', 'A73', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A732', '客户寻访', 'CustCensor', 'workflow/forms/contract/cust_view/flow_openlist.bi', 'users.png', '', '', '', 108, 1, '2013-08-19', '2013-09-02 18:53:34', 'A73', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A733', '五级分类', 'CustFiveLevel', 'workflow/forms/contract/five_category_contract/flow_openlist.bi', 'flag.png', '', '', '', 112, 1, '2013-08-19', '2013-09-02 21:13:34', 'A73', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A74', '合同结束管理', 'ContractEndManage', '', 'layers.png', '', '', '', 108, 1, '2013-08-19', '', 'A7', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A741', '合同结束', 'ContractEnd', 'workflow/forms/contract/contract_finish/flowopen_list.bi', 'delete.png', '', '', '', 109, 1, '2013-08-19', '', 'A74', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A742', '合同中途终止', 'ContractDiscontinuity', 'workflow/forms/contract/contract_terminate/flowopen_list.bi', 'delete.png', '', '', '', 110, 1, '2013-08-19', '', 'A74', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A75', '其他功能', 'ContractOther', '', 'layers.png', '', '', '', 111, 1, '2013-08-19', '', 'A7', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A751', '合同登记', 'ContractDocReg', 'leasing/other/contract_file/list.bi', 'pencil.png', '', '', '', 112, 1, '2013-08-19', '', 'A75', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A752', '合同归档', 'ContractDocpigeonhole', 'leasing/other/contract_archives/contract_archives.bi', 'layers.png', '', '', '', 113, 1, '2013-08-19', '', 'A75', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A753', '合同借阅', 'ContractDocBorrow', 'workflow/forms/other/borrow/flow_openlist.bi', 'search.png', '', '', '', 115, 1, '2013-08-19', '2013-09-02 22:18:27', 'A75', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A754', '合同归还', 'ContractDocBack', 'leasing/special_function_manager/contract_revert.bi', 'home.png', '', '', '', 116, 1, '2013-08-19', '', 'A75', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A755', '权证登记', 'ContractCertificateReg', 'leasing/other/document_reg/list.bi', 'clipboard.png', '', '', '', 117, 1, '2013-08-19', '', 'A75', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('4028829540d251360140d30fee8e0034', '风险管理', 'list2', '', 'chart_line.png', '', '', '', 114, 1, '2013-08-31 14:29:31', '', 'A7', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('4028829540d251360140d3109f9e0035', '风险处置', 'list', 'leasing/risk_management/risk_management.bi', 'chart_line.png', '', '', '', 2, 1, '2013-08-31 14:30:16', '2013-08-31 14:43:53', '4028829540d251360140d30fee8e0034', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A54', '风险预警流程', 'RiskManage', 'workflow/forms/nocar/risk_warning/flow_openlist.bi', 'department.png', '', '', '', 3, 1, '2013-08-19', '2013-08-29 17:53:33', '4028829540d251360140d30fee8e0034', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A8', '发票管理', 'InvoiceManage', '', 'money.png', '', '', '', 120, 1, '2013-08-19', '2013-10-24 16:42:49', '0', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A81', '进项发票', 'VATInvoice', '', 'clip.png', '', '', '', 118, 1, '2013-08-19', '', 'A8', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A811', '进项发票统计', 'VATCount', '/report/showReport.action?reportId=4028813141d8d4380141da2d22b20023', 'calc.png', '', '', '', 122, 0, '2013-08-19', '2013-10-21 17:12:24', 'A81', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A815', '新建合同未采购清单', 'Nopurchaselist', 'leasing/invoice_manage/vat_invoice/no_purchase_list.bi', 'calc.png', '', '', '', 125, 1, '2013-09-17', '2013-10-11 17:03:47', 'A81', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A813', '进项发票登记', 'VATReg', 'leasing/invoice_manage/vat_invoice/vat_invoice_record.bi', 'db.png', '', '', '', 127, 1, '2013-08-19', '2013-10-12 13:27:09', 'A81', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A816', '进项发票确认', 'receiptsconfirm', 'leasing/invoice_manage/vat_invoice/vat_invoice_confirm.bi', 'calc.png', '', '', '', 128, 1, '2013-09-17', '2013-10-12 13:28:23', 'A81', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A812', '发票认证结果导入系统', 'VATAuthenticate', 'leasing/invoice_manage/vat_invoice/vat_authentication_imp.bi', 'calc.png', '', '', '', 130, 1, '2013-08-19', '2013-10-12 13:36:48', 'A81', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A82', '资金发票', 'FundInvoice', '', 'clip.png', '', '', '', 122, 1, '2013-08-19', '', 'A8', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A821', '资金发票计划开票', 'FundInvoiceConfirm', 'leasing/invoice_manage/fund_invoice/fund_invoice_plan.bi', 'contact.png', '', '', '', 4, 1, '2013-08-19', '2013-10-14 09:41:17', 'A82', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A822', '资金发票实收开票', 'FundIncomeInvoice', 'leasing/invoice_manage/fund_invoice/fund_invoice_charge.bi', 'contact.png', '', '', '', 6, 1, '2013-08-19', '2013-10-14 09:41:43', 'A82', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('4028813141ed553d0141eda25c290013', '资金实收开票（收据）', 'Contract_Fund_Fund_Receipt', 'leasing/invoice_manage/fund_invoice/contract_fund_fund_receipt.bi', 'clip.png', '', '', '', 8, 1, '2013-10-25 11:22:22', '2013-10-25 11:23:08', 'A82', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A829', '资金发票计划开票确认', 'conllectinvoiceplanconfirm', 'leasing/invoice_manage/fund_invoice/fund_invoice_plan_confirm.bi', 'calc.png', '', '', '', 10, 1, '2013-09-17', '2013-10-14 09:42:01', 'A82', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A828', '资金发票实收开票确认', 'conllectinvoicefirm', 'leasing/invoice_manage/fund_invoice/fund_invoice_charge_confirm.bi', 'calc.png', '', '', '', 131, 1, '2013-09-17', '2013-10-14 09:42:38', 'A82', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A823', '资金开票红冲', 'FundIncomeBack', 'leasing/invoice_manage/fund_invoice/fund_invoice_hc.bi', 'contact.png', '', '', '', 139, 1, '2013-08-19', '2013-10-14 09:43:00', 'A82', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A824', '资金开票数据回导', 'FundInvoiceUp', 'leasing/invoice_manage/fund_invoice/fund_invoice_import.bi', 'contact.png', '', '', '', 141, 1, '2013-08-19', '2013-10-14 09:43:26', 'A82', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A825', '资金开票清单统计表', 'FundInvoiceList', '/report/showReport.action?reportId=4028813141d8d4380141da2eb85b0026', 'contact.png', '', '', '', 145, 1, '2013-08-19', '2013-10-21 17:10:31', 'A82', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A83', '租金发票', 'RentInvoice', '', 'clip.png', '', '', '', 129, 1, '2013-08-19', '', 'A8', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A835', '租金计划应开票提醒清单（本金）', 'CorpusPlanList', 'leasing/invoice_manage/rent_invoice/rent_invoice_corpus_once.bi', 'clip.png', '', '', '', 2, 1, '2013-08-19', '2013-10-16 14:33:20', 'A83', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A837', '租金计划应开票清单（本金）', 'CorpusInvoiceList', 'leasing/rent_translation_plan/rent_translation_plan.bi', 'db.png', '', '', '', 4, 1, '2013-08-19', '2013-10-16 14:24:10', 'A83', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A836', '租金开票提醒清单（每期租金）', 'BeforeRentList', 'leasing/invoice_manage/rent_invoice/rent_invoice_plan.bi', 'clip.png', '', '', '', 7, 1, '2013-08-19', '2013-10-18 16:47:48', 'A83', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A838', '租金开票数据（每期租金）', 'BeforeRentInvoiceList', 'leasing/exp_rent_translation_open/exp_rent_translation_open.bi', 'db.png', '', '', '', 13, 1, '2013-08-19', '2013-10-16 14:24:51', 'A83', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A83N', '租金计划开票确认', 'renttranslation', 'leasing/invoice_manage/rent_invoice/rent_invoice_plan_confirm.bi', 'calc.png', '', '', '', 16, 1, '2013-09-17', '2013-10-21 11:21:14', 'A83', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('4028813041dddc3c0141dde432260002', '租金开票提醒清单（收据）', 'rent_invoice_plan_receipt', 'leasing/invoice_manage/rent_invoice/rent_invoice_plan_receipt.bi', 'calc.png', '', '', '', 17, 1, '2013-10-22 10:00:21', '', 'A83', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A83M', '租金计划开票确认（收据）', 'renttranslationlist', 'leasing/invoice_manage/rent_invoice/rent_invoice_plan_receipt_confirm.bi', 'calc.png', '', '', '', 19, 1, '2013-09-17', '2013-10-21 10:25:31', 'A83', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A83G', '租金/罚息实收应开票数据提醒清单', 'rentdefaulttranslation', 'leasing/invoice_manage/rent_invoice/rent_invoice_income.bi', 'calc.png', '', '', '', 21, 1, '2013-09-17', '2013-10-21 10:25:55', 'A83', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A83X', '租金实收开票确认', 'paidconfirm', 'leasing/invoice_manage/rent_invoice/rent_invoice_income_confirm.bi', 'calc.png', '', '', '', 23, 1, '2013-09-17', '2013-10-21 10:26:11', 'A83', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('4028813041dddc3c0141dde5c3cf0003', '租金实收开票提醒清单（收据）', 'rent_invoice_income_receipt', 'leasing/invoice_manage/rent_invoice/rent_invoice_income_receipt.bi', 'calc.png', '', '', '', 26, 1, '2013-10-22 10:02:04', '2013-10-22 10:17:42', 'A83', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A83Z', '租金实收开票确认（收据)', 'paidconfirmlist', 'leasing/invoice_manage/rent_invoice/rent_invoice_income_receipt_confirm.bi', 'calc.png', '', '', '', 151, 1, '2013-09-17', '2013-10-21 10:26:41', 'A83', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A834', '租金开票数据回导', 'RentInvoiceUp', 'leasing/invoice_manage/rent_invoice/rent_invoice_import.bi', 'clip.png', '', '', '', 155, 1, '2013-08-19', '2013-10-21 10:26:57', 'A83', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A833', '租金开票红冲', 'RentInvoiceBack', 'leasing/invoice_manage/rent_invoice/rent_invoice_hc.bi', 'clip.png', '', '', '', 157, 1, '2013-08-19', '2013-10-21 10:27:12', 'A83', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A9', '接口', 'InterfaceManage', '', 'clip.png', '', '', '', 140, 1, '2013-08-19', '', '0', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A91', '财务接口', 'FinanceInterface', '', 'clip.png', '', '', '', 138, 1, '2013-08-19', '', 'A9', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A911', '网银导入', 'EbankImport', 'leasing/fund_ebank/cust_ebank_info.bi', 'flag.png', '', '', '', 139, 1, '2013-08-19', '', 'A91', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A912', '合同扣款卡号维护', 'ContractCard', 'leasing/other/contract_debit/list.bi', 'attention.png', '', '', '', 140, 1, '2013-08-19', '', 'A91', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A913', '卡扣回笼数据导出', 'CarRentDown', 'leasing/fund_bankcarddown/bankcarddown_info.bi', 'case.png', '', '', '', 141, 1, '2013-08-19', '', 'A91', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A914', '卡扣回笼数据核销', 'CarRentIncome', 'leasing/fund_bankcardload/bankcardload_info.bi', 'compass.png', '', '', '', 142, 1, '2013-08-19', '', 'A91', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('402881614115436d014115468d1e0003', '凭证信息', 'voucher', 'leasing/voucher/voucher_list.bi', 'chart_bar.png', '', '', '', 145, 1, '2013-09-13 11:04:07', '2013-09-13 11:25:03', 'A91', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A92', 'GPS接口导入', 'GPSManage', '', 'attention.png', '', '', '', 143, 1, '2013-08-19', '', 'A9', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A921', '设备相关GPS信息', 'GPSEquipInfo', 'leasing/other/interface_import/list3.bi', 'attention.png', '', '', '', 144, 1, '2013-08-19', '', 'A92', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A922', 'GPS接口信息', 'GPSInterface', 'leasing/other/interface_import/list1.bi', 'attention.png', '', '', '', 145, 1, '2013-08-19', '', 'A92', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A923', 'GPS接口信息汇总', 'GPSGroupInfo', 'leasing/other/interface_import/list2.bi', 'attention.png', '', '', '', 146, 1, '2013-08-19', '', 'A92', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A93', '租金计算器', 'RentCalculate', '', 'attention.png', '', '', '', 147, 1, '2013-08-19', '', 'A9', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A931', '车辆业务', 'CarBusiness', 'workflow/forms/rent_reckon/rent_reckon/rentCal.bi', 'track.png', '', '', '', 148, 1, '2013-08-19', '', 'A93', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A932', '设备业务', 'EquipBusiness', 'workflow/forms/rent_reckon/rent_reckon/rentCal_4_5.bi', 'monitor.png', '', '', '', 149, 1, '2013-08-19', '', 'A93', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('B1', '报表中心', 'Report', '', 'chart_bar.png', '', '', '', 155, 1, '2013-08-19', '2013-10-24 16:26:39', '0', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('4028813141d8d4380141da2d22ba0024', '进项发票统计', '4028813141d8d4380141da2d22b20023', '/report/showReport.action?reportId=4028813141d8d4380141da2d22b20023', 'chart_bar.png', '', '', '', 0, 1, '', '', 'B1', '', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('4028813141d8d4380141da2eb8640027', '资金发票统计', '4028813141d8d4380141da2eb85b0026', '/report/showReport.action?reportId=4028813141d8d4380141da2eb85b0026', 'chart_bar.png', '', '', '', 0, 1, '', '', 'B1', '', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('402881344129ad6e014129b21c160001', '客户信息', '402881344129ad6e014129b21c080000', '/report/showReport.action?reportId=402881344129ad6e014129b21c080000', 'chart_bar.png', '', '', '', 0, 1, '', '', 'B1', '', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('40288134414955510141499f33a70003', '保险续保提醒', '40288134414955510141499f33840002', '/report/showReport.action?reportId=40288134414955510141499f33840002', 'chart_bar.png', '', '', '', 1, 1, '', '', 'B1', '', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('B11', '报表配置', 'ReportConfig', '', 'cogs.png', '', '', '', 152, 1, '2013-08-19', '', 'B1', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('B111', '数据源配置', 'RpeortDataSource', 'report/datasource.bi', 'box.png', '', '', '', 152, 1, '2013-08-19', '', 'B11', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('B112', '报表配置', 'ReprotCreate', 'report/reportconfig.bi?reportMenuRoot={parent[2]}', 'chart_bar.png', '', '', '', 154, 1, '2013-08-19', '2013-08-22 19:26:46', 'B11', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('B21', '基础报表', 'ReportConfig1', '', 'cogs.png', '', '', '', 155, 1, '2013-08-19', '', 'B1', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('B211', '项目信息查询', 'ProjQuery', 'leasing/query/proj_info_query.bi', 'filter.png', '', '', '', 155, 1, '2013-08-19', '', 'B21', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('B212', '合同信息查询', 'ContractQuery', 'leasing/query/contract_info_query.bi', 'filter.png', '', '', '', 156, 1, '2013-08-19', '', 'B21', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('B213', '租赁物件信息', 'EquipQuery', 'report/equip_info.bi', 'doc.png', '', '', '', 157, 1, '2013-08-19', '', 'B21', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('B214', '项目租金计划', 'ProjRentPlanQuery', 'report/fund_plan_info.bi', 'doc.png', '', '', '', 158, 1, '2013-08-19', '', 'B21', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('B215', '项目资金计划', 'ContractRentPlanQuery', 'report/rent_plan_info.bi', 'doc.png', '', '', '', 159, 1, '2013-08-19', '', 'B21', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('402881824094f54f0140956b4c790071', '合同租金计划', 'B216', 'report/contract_fund_rent_plan_info.bi', 'db.png', '', '', '', 160, 1, '2013-08-19 15:12:51', '', 'B21', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('402881824094f54f0140956f822d007b', '合同资金计划', 'B217', 'report/contract_fund_fund_plan_info.bi', 'flag.png', '', '', '', 161, 1, '2013-08-19 15:17:27', '', 'B21', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A0', '系统管理', 'SystemManage', '', 'wrench.png', '', '', '', 1006, 1, '2013-08-19', '2013-09-06 18:11:28', '0', 'Administrator', 'Administrator');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A11', '用户管理', 'Users', '', 'users.png', '', '', '', 2, 1, '2013-08-19', '', 'A0', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A111', '用户管理', 'UserManage', 'permission/user.bi', 'users.png', '', '', '', 3, 1, '2013-08-19', '', 'A11', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A112', '部门管理', 'DepartmentManage', 'permission/department.bi', 'home.png', '', '', '', 4, 1, '2013-08-19', '', 'A11', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A113', '角色管理', 'RoleManage', 'permission/role.bi', 'contact.png', '', '', '', 5, 1, '2013-08-19', '', 'A11', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A114', '群组管理', 'GroupManage', 'permission/group.bi', 'contact.png', '', '', '', 6, 1, '2013-08-19', '', 'A11', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A115', '用户权限', 'UserAuthority', 'permission/userPermission.bi', 'contact.png', '', '', '', 7, 1, '2013-08-19', '', 'A11', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A12', '系统数据管理', 'SystemDataManage', '', 'db.png', '', '', '', 8, 1, '2013-08-19', '', 'A0', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A122', '公告管理', 'Notice', 'acl/notice.bi', 'clipboard.png', '', '', '', 9, 1, '2013-08-19', '', 'A12', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A123', '数据字典', 'SystemDict', 'permission/dictionary.bi', 'db.png', '', '', '', 10, 1, '2013-08-19', '', 'A12', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A124', '模板管理', 'TemplateDoc', 'leasing/common/templateManager/FileTemplate.bi', 'notepad.png', '', '', '', 11, 1, '2013-08-19', '', 'A12', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('297e7def40e35cbc0140e3a44e4f000f', '文件上传下载管理', 'systemdocmanager', 'leasing/common/templateManager/list.bi', 'filter.png', '', '', '', 12, 1, '2013-09-03 19:45:30', '', 'A12', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A125', '本方信息', 'OwnInfo', 'leasing/own/owninfo.bi', 'bubble.png', '', '', '', 13, 1, '2013-08-19', '', 'A12', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A126', '本方账户信息', 'OwnAccount', 'leasing/own/ownaccountinfo.bi', 'bubble.png', '', '', '', 14, 1, '2013-08-19', '', 'A12', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('4028816340f0ee2c0140f10636c3000b', '业务企划区域', 'ManageDistrict', '', 'bubble.png', '', '', '', 15, 1, '2013-09-06 10:07:31', '', 'A12', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A127', '树形转表格配置管理', 'DocumentManage', 'permission/tree2Table.bi', 'doc.png', '', '', '', 16, 1, '2013-08-19', '', 'A12', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A13', '系统设置', 'SystemSet', '', 'wrench.png', '', '', '', 15, 1, '2013-08-19', '', 'A0', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A131', '菜单管理', 'MenusManage', 'permission/menu.bi', 'layers.png', '', '', '', 16, 1, '2013-08-19', '', 'A13', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A132', '流程设计', 'WorkFlow', 'workflow/jbpm-core/listDesignedDeployments.bi', 'money.png', '', '', '', 17, 1, '2013-08-19', '', 'A13', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A133', '流程状态', 'ProcessStatus', 'base/process_status.bi', 'connect.png', '', '', '', 18, 1, '2013-08-19', '', 'A13', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A134', '流程互斥', 'WorkFlowLock', 'workflow/jbpm-core/workflowStartRejectConfig.bi', 'box.png', '', '', '', 19, 1, '2013-08-19', '', 'A13', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A135', '流程发起权限', 'WorkflowStartPermission', 'permission/menu.bi?currentFlag=WorkflowStart', 'layers.png', '', '', '', 20, 1, '2013-08-19', '', 'A13', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A136', '流程查看权限', 'WorkflowViewPermission', 'permission/menu.bi?currentFlag=WorkflowView', 'layers.png', '', '', '', 21, 1, '2013-08-19', '', 'A13', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A137', '操作管理', 'ActionManage', 'permission/menu.bi?currentFlag=Action', 'layers.png', '', '', '', 22, 1, '2013-08-19', '', 'A13', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A138', '资源管理', 'ResourcesManage', 'permission/menu.bi?currentFlag=Resource', 'layers.png', '', '', '', 23, 1, '2013-08-19', '', 'A13', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A139', '定时调度', 'Quartz', '', 'dashboard.png', '', '', '', 24, 1, '2013-08-19', '', 'A13', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A1391', '任务管理', 'QuartzTask', 'quartz/quartz_job_manager.bi', 'contact.png', '', '', '', 25, 1, '2013-08-19', '', 'A139', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A1392', '任务触发', 'TaskTrigger', 'quartz/quartz_trigger_manager.bi', 'clock.png', '', '', '', 26, 1, '2013-08-19', '', 'A139', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A13A', '通用表单', 'CommonMenus', 'test1.jsp', 'box.png', '', '', '', 28, 1, '2013-08-19', '', 'A13', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A14', '系统高级管理', 'SystemParamount', '', 'money.png', '', '', '', 29, 1, '2013-08-19', '', 'A0', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A141', '流程数据清除', 'WorkDataClear', 'workflow/jbpm-core/removeAllProcessInstance.bi', 'delete.png', '', '', '', 30, 1, '2013-08-19', '', 'A14', 'Administrator', '');

insert into t_menus (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('A142', '后台管理', 'BackstageManage', 'admin/manager.bi', 'box.png', '', '', '', 31, 1, '2013-08-19', '', 'A14', 'Administrator', '');

/****将系统管理所有权限赋值给管理员***/
insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e6ffbb30009c', '2013-09-04 11:24:14', '', 'A112', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e6ffc76e009d', '2013-09-04 11:24:17', '', 'A113', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e6ffcffe009e', '2013-09-04 11:24:19', '', 'A114', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e6ffeae200a0', '2013-09-04 11:24:26', '', 'A122', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e6ffeae900a1', '2013-09-04 11:24:26', '', 'A12', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e6fff2df00a2', '2013-09-04 11:24:28', '', 'A123', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e70003ab00a4', '2013-09-04 11:24:32', '', '297e7def40e35cbc0140e3a44e4f000f', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e7000b6800a5', '2013-09-04 11:24:34', '', 'A125', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e7002cc400a8', '2013-09-04 11:24:43', '', 'A131', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e7002ccb00a9', '2013-09-04 11:24:43', '', 'A13', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e70033a500aa', '2013-09-04 11:24:44', '', 'A132', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e7003bea00ab', '2013-09-04 11:24:46', '', 'A133', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e70043d100ac', '2013-09-04 11:24:48', '', 'A134', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e7004bcc00ad', '2013-09-04 11:24:51', '', 'A135', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e70052d100ae', '2013-09-04 11:24:52', '', 'A136', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e7005c1400af', '2013-09-04 11:24:55', '', 'A137', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e70064f000b0', '2013-09-04 11:24:57', '', 'A138', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e7007b4400b1', '2013-09-04 11:25:03', '', 'A1391', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e7007b4b00b2', '2013-09-04 11:25:03', '', 'A139', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e7008b4a00b3', '2013-09-04 11:25:07', '', 'A1392', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e700953b00b4', '2013-09-04 11:25:09', '', 'A13A', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e700a44600b5', '2013-09-04 11:25:13', '', 'A141', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e700a44d00b6', '2013-09-04 11:25:13', '', 'A14', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e700abe500b7', '2013-09-04 11:25:15', '', 'A142', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e6ffb0460099', '2013-09-04 11:24:11', '', 'A111', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e6ffb04d009a', '2013-09-04 11:24:11', '', 'A11', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e6ffb054009b', '2013-09-04 11:24:11', '', 'A0', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e6ffd8fe009f', '2013-09-04 11:24:21', '', 'A115', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e6fffa6a00a3', '2013-09-04 11:24:30', '', 'A124', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e700137b00a6', '2013-09-04 11:24:36', '', 'A126', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340e6c8bb0140e7001cb600a7', '2013-09-04 11:24:38', '', 'A127', 'admin_dept', 'Administrator', '');

insert into t_menus_depts (ID_, CREATE_DATE_, MODIFY_DATE_, MENU_ID_, DEPT_ID_, CREATOR_, MODIFICATOR_)
values ('4028816340f0ee2c0140f1064689000c', '2013-09-06 10:07:35', '', '4028816340f0ee2c0140f10636c3000b', 'admin_dept', 'Administrator', '');
/***初始化数据字典目录****/
insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('0', '数据字典', 'root', '', 1, '', '', '', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('documentColumnType', '资料子项类别', 'documentColumnType', '', 3, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.cust_type', '客户', 'root.cust_type', '', 3, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('cust_info_type', '客户性质类别', 'cust_info_type', '', 1, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('five_class', '五级分类', 'five_class', '', 2, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('credit_rating', '信用等级', 'credit_rating', '', 21, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('cust_kind', '内部行业', 'cust_kind', '', 37, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('cust_type', '客户类别', 'cust_type', '', 138, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('marital_status', '婚姻状况', 'marital_status', '', 142, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('school', '学历', 'school', '', 146, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('ownership', '企业性质', 'ownership', '', 160, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('relationship', '与客户关系', 'relationship', '', 164, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('relation_ship', '关联关系', 'relation_ship', '', 165, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('cust_jobposition', '职务', 'cust_jobposition', '', 166, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('contact_type', '交往记录类型', 'contact_type', '', 171, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('reditor_right', '债权性质', 'reditor_right', '', 299, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('assuror_right', '担保性质', 'assuror_right', '', 308, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('cust_scale', '客户规模', 'cust_scale', '', 320, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('tax_level_name', '纳税类别', 'tax_level_name', '', 321, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.cust_type.dealer', '经销商', 'root.cust_type.dealer', '', 322, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('dealer_district', '所属区域', 'dealer_district', '', 1, '', '', 'root.cust_type.dealer', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('owner_office', '办事处', 'owner_office', '', 2, '', '', 'root.cust_type.dealer', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('dealer_symbiosis', '合作状态', 'dealer_symbiosis', '', 3, '', '', 'root.cust_type.dealer', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('dealer_shop_type', '店面类型', 'dealer_shop_type', '', 4, '', '', 'root.cust_type.dealer', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('dealer_intoStatue', '准入申请状态', 'dealer_intoStatue', '', 5, '', '', 'root.cust_type.dealer', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('dealerApprovalstatus', '经销商状态', 'dealerApprovalstatus', '', 7, '', '', 'root.cust_type.dealer', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('dealer_creditStatus', '经销商评审状态', 'dealer_creditStatus', '', 8, '2013-09-03 11:17:09', '', 'root.cust_type.dealer', 'Administrator', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('dealer_mortgage', '抵押备案', 'dealer_mortgage', '', 9, '2013-09-10 16:36:46', '', 'root.cust_type.dealer', 'Administrator', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root_ethnic', '民族', 'root_ethnic', '', 323, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('visit_mode', '走访方式', 'visit_mode', '', 324, '', '', 'root.cust_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('document_type', '证件类型', 'document_type', '证件类型，身份证，护照等', 325, '2013-09-12 14:28:47', '', 'root.cust_type', 'Administrator', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('fund_fee', '资金费用项', 'fund_fee', '', 4, '2013-08-31 13:52:44', '', '0', 'Administrator', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('currency_type', '货币类型', 'currency_type', '', 1, '', '', 'fund_fee', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('FeeType', '费用类型', 'FeeType', '', 14, '', '', 'fund_fee', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('pay_type', '收付方向', 'pay_type', '', 34, '', '', 'fund_fee', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('PayFund', '结算方式', 'PayFund', '', 35, '', '', 'fund_fee', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('Payment', '支付方式', 'Payment', '', 49, '', '', 'fund_fee', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('loan_nature', '借款性质', 'loan_nature', '', 50, '', '', 'fund_fee', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.evaluate', '评估模型数据', 'root.evaluate', '', 98, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.vocation', '行业类型', 'root.vocation', '', 93, '', '', 'root.evaluate', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.evaluate.category', '模型类别', 'root.evaluate.category', '', 96, '', '', 'root.evaluate', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.evaluate.category.credit', '信用评估', 'root.evaluate.category.credit', '', 101, '', '', 'root.evaluate.category', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.evaluate.category.credit.equan', '定量评估', 'root.evaluate.category.credit.equan', '', 102, '', '', 'root.evaluate.category.credit', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.evaluate.category.credit.equal', '定性评估', 'root.evaluate.category.credit.equal', '', 103, '', '', 'root.evaluate.category.credit', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.evaluate.category.credit.espec', '特例评估', 'root.evaluate.category.credit.espec', '', 104, '', '', 'root.evaluate.category.credit', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.evaluate.category.credit.leasetrisk', '租赁物风险', 'root.evaluate.category.credit.leasetrisk', '', 116, '', '', 'root.evaluate.category.credit', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.evaluate.category.zixin', '资信评估', 'root.evaluate.category.zixin', '', 121, '', '', 'root.evaluate.category', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.evaluate.group', '模型分组', 'root.evaluate.group', '', 97, '', '', 'root.evaluate', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.evaluate.targettype', '评估对象类型', 'root.evaluate.targettype', '', 105, '', '', 'root.evaluate', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('rent_reckon', '租金测算', 'rent_reckon', '', 176, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('settle_method', '租金测算方式', 'settle_method', '', 172, '', '', 'rent_reckon', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('period_type', '付租方式', 'period_type', '', 174, '', '', 'rent_reckon', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('income_number_year', '付租类型', 'income_number_year', '', 177, '', '', 'rent_reckon', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('pena_rate', '罚息利率', 'pena_rate', '', 182, '', '', 'rent_reckon', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('adjust_style', '调息方式 ', 'adjust_style', '', 183, '', '', 'rent_reckon', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('rate_float_type', '利率浮动类型 ', 'rate_float_type', '', 191, '', '', 'rent_reckon', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('insure_type', '投保方式', 'insure_type', '', 192, '', '', 'rent_reckon', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('project', '项目', 'project', '', 202, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('leas_type', '固定资产分类', 'leas_type', '', 1, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('leas_form', '租赁形式', 'leas_form', '', 6, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('proj_scale', '项目规模', 'proj_scale', '', 11, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('proj_type', '项目类型', 'proj_type', '', 151, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('proj_source', '项目来源', 'proj_source', '', 154, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('relation', '关系', 'relation', '', 156, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('guarantee_type', '抵押方式', 'guarantee_type', '', 159, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('assure_method', '担保类型', 'assure_method', '', 202, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('repeal_type', '撤销类型', 'repeal_type', '', 210, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('industry_type', '行业', 'industry_type', '', 229, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('rent_invoice_type', '发票类型', 'rent_invoice_type', '', 391, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('contract_lease_type', '租赁合同类型', 'contract_lease_type', '', 392, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('EQUIP_ACCEPTANCE', '租赁物验收类型', 'EQUIP_ACCEPTANCE', '', 394, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('bussiness_type', '业务类别', 'bussiness_type', '', 395, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('bussiness_mode', '业务模式', 'bussiness_mode', '', 396, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('CreditType', '信审通过类型', 'CreditType', '', 397, '', '', 'project', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('equip_manage', '租赁物', 'equip_manage', '', 203, '2013-08-31 14:38:42', '', '0', 'Administrator', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('equip_class', '设备分类', 'equip_class', '', 2, '2013-08-31 14:39:38', '2013-09-01 19:28:16', 'equip_manage', 'Administrator', 'Administrator');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('equip_type', '设备类别', 'equip_type', '', 7, '2013-08-31 14:39:10', '2013-09-01 19:33:30', 'equip_manage', 'Administrator', 'Administrator');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('device_type', '设备类型', 'device_type', '', 9, '', '2013-09-01 19:32:24', 'equip_manage', '', 'Administrator');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('file_status', '上传或导出文件状态', 'file_status', '', 217, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('assets', '资产分类', 'assets', '', 326, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('dept', '部门名称', 'dept', '', 400, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('paperTypes', '纸质文档类型', 'paperTypes', '', 404, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('attachbigtype', '附件大类', 'attachbigtype', '', 408, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('attachbigtype01', '附件大类1', 'attachbigtype01', '', 403, '', '', 'attachbigtype', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('attachbigtype01midtype01', '附件中类1', 'attachbigtype01midtype01', '', 405, '', '', 'attachbigtype01', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('attachbigtype02', '附件大类02', 'attachbigtype02', '', 404, '', '', 'attachbigtype', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('meettype', '会议方式', 'meettype', '', 423, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('vottype', '表决意见', 'vottype', '', 424, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('PLANMANYSTATUS', '收款状态', 'PLANMANYSTATUS', '', 425, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('table_for_his', '历史表配置', 'table_for_his', '', 426, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('table_for_his_status', '历史状态', 'table_for_his_status', '', 1, '', '', 'table_for_his', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('table_for_his_type', '历史变更类型', 'table_for_his_type', '', 2, '', '', 'table_for_his', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('finish_type', '合同结束类型', 'finish_type', '', 427, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('JbpmCommonAdvise', '流程常用意见', 'JbpmCommonAdvise', '', 428, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.FileType', '附件文档类型', 'root.FileType', '', 429, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('cust_company_list', '法人客户资料', 'cust_company_list', '法人客户资料', 1, '2013-10-25 10:51:42', '2013-10-25 11:55:16', 'root.FileType', 'Administrator', 'Administrator');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.FileType.TYPE1', '类型1', 'root.FileType.TYPE1', '', 4, '', '', 'root.FileType', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.FileType.TYPE2', '类型2', 'root.FileType.TYPE2', '', 5, '', '', 'root.FileType', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('dealer_credit_doc_list', '经销商评审资料清单', 'dealer_credit_doc_list', '', 6, '2013-09-04 10:28:02', '', 'root.FileType', 'Administrator', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('rent_change_agreement', '租金变更协议', 'rent_change_agreement', '租金变更协议', 7, '2013-10-24 10:54:30', '', 'root.FileType', 'Administrator', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('rent_update_agreement', '租金修改协议', 'rent_update_agreement', '租金修改协议', 8, '2013-10-24 14:22:43', '', 'root.FileType', 'Administrator', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.', '融资管理', 'root.', '', 430, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('crediter_type', '授信机构属性', 'crediter_type', '', 1, '', '', 'root.', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('ContRateStand', '合同利率基准', 'ContRateStand', '', 2, '', '', 'root.', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('FinancingAssurer', '担保机构', 'FinancingAssurer', '', 3, '', '', 'root.', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('credit_type', '授信种类', 'credit_type', '', 4, '', '', 'root.', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root.workflowType', '流程分类', 'root.workflowType', '', 431, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('root_null_ini', '待初始化自定义', 'root_null_ini', '', 432, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('document_borrow', '文档借阅方式', 'document_borrow', '', 433, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('wordtempleType', 'Word模板分类', 'wordtempleType', '', 434, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('wordtempletypefirst', '一级分类', 'wordtempletypefirst', '', 1, '', '', 'wordtempleType', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('wordtempletypetwo', '二级分类', 'wordtempletypetwo', '', 2, '', '', 'wordtempleType', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('wordtempletypethree', '三级分类', 'wordtempletypethree', '', 3, '', '', 'wordtempleType', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('wordtempletypefour', '四级分类', 'wordtempletypefour', '', 4, '', '', 'wordtempleType', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('wordtempletypefive', '五类分类', 'wordtempletypefive', '', 5, '', '', 'wordtempleType', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('message_type', '信息发布与上报', 'message_type', '', 435, '', '', '0', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('message_readstatus', '状态', 'message_readstatus', '', 1, '', '', 'message_type', '', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('msgtype', '类型', 'msgtype', '', 2, '2013-08-26 14:07:00', '', 'message_type', 'Administrator', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('msggroup', '消息接受组类型', 'msggroup', '', 3, '2013-09-10 16:13:34', '', 'message_type', 'Administrator', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('contract_register', '合同登记', 'contract_register', '登记合同信息', 436, '2013-09-09 11:19:50', '', '0', 'Administrator', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('insurance', '保险信息', 'insurance', '', 437, '2013-09-09 19:34:54', '', '0', 'Administrator', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('ThirdInsurTpye', '第三者责任险', 'ThirdInsurTpye', '', 1, '2013-09-09 19:36:24', '', 'insurance', 'Administrator', '');

insert into t_dicts (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('downloadModel', '上传下载模块名', 'downloadModel', '', 438, '2013-09-11 16:59:06', '', '0', 'Administrator', '');

/******数据字典数据数据****/
insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('message_readstatus_1', '未读', 'message_readstatus_1', 1, '', 4, '', '', 'message_readstatus', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('message_readstatus_2', '已读', 'message_readstatus_2', 1, '', 6, '', '', 'message_readstatus', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('Limited', '有限责任公司', 'Limited', 1, '', 159, '', '', 'ownership', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('shares', '股份有限公司', 'shares', 1, '', 160, '', '', 'ownership', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('state_owned', '国有独资公司', 'state_owned', 1, '', 161, '', '', 'ownership', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('creditresulttype', '信审结论类型', 'creditresulttype', 1, '', 0, '', '', '0', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('ContRateStand1', '1年期基准利率', 'ContRateStand1', 1, '', 1, '', '', 'ContRateStand', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('ContRateStand2', '1-3年期基准利率', 'ContRateStand2', 1, '', 2, '', '', 'ContRateStand', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('ContRateStand3', '3-5年期基准利率', 'ContRateStand3', 1, '', 3, '', '', 'ContRateStand', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('ContRateStand4', '5年以上基准利率', 'ContRateStand4', 1, '', 4, '', '', 'ContRateStand', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('ContRateStand5', '固定利率', 'ContRateStand5', 1, '', 5, '', '', 'ContRateStand', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type_@11', '审批通过', 'credit_type_@11', 1, '', 244, '', '', 'CreditType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type_@12', '审批复议', 'credit_type_@12', 1, '', 248, '', '', 'CreditType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type_@103', '审批否决', 'credit_type_@103', 1, '', 251, '', '', 'CreditType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('EQUIP_ACCEPTANCE_part', '部分验收', 'EQUIP_ACCEPTANCE_part', 1, '', 0, '', '', 'EQUIP_ACCEPTANCE', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('EQUIP_ACCEPTANCE_all', '完全验收', 'EQUIP_ACCEPTANCE_all', 1, '', 0, '', '', 'EQUIP_ACCEPTANCE', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype1', '手续费', 'feetype1', 1, '', 30, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype2', '保证金', 'feetype2', 1, '', 31, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype3', '管理费', 'feetype3', 1, '', 32, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype5', '首付款', 'feetype5', 1, '', 33, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype6', '厂商返利', 'feetype6', 1, '', 34, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype7', '保险费', 'feetype7', 1, '', 35, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype10', '设备款', 'feetype10', 1, '', 36, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype9', '租前息', 'feetype9', 1, '', 37, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype4', '名义货价', 'feetype4', 1, '', 38, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype8', '其它收入', 'feetype8', 1, '', 39, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype11', '其它支出', 'feetype11', 1, '', 40, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype12', '期末余值', 'feetype12', 1, '', 41, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype13', '违约手续费', 'feetype13', 1, '', 42, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype14', '委托租赁资金', 'feetype14', 1, '', 43, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('feetype15', 'GPS费用', 'feetype15', 1, '', 44, '', '', 'FeeType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('FinancingAssurer1', '担保机构1', 'FinancingAssurer1', 1, '', 1, '', '', 'FinancingAssurer', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('FinancingAssurer2', '担保机构2', 'FinancingAssurer2', 1, '', 2, '', '', 'FinancingAssurer', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('FinancingAssurer3', '无担保机构', 'FinancingAssurer3', 1, '', 3, '', '', 'FinancingAssurer', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('FinancingAssurer4', '集团担保', 'FinancingAssurer4', 1, '', 4, '', '', 'FinancingAssurer', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('JbpmCommonAdvise_alreadyRead', '已阅', 'JbpmCommonAdvise_alreadyRead', 1, '', 1, '', '', 'JbpmCommonAdvise', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('JbpmCommonAdvise_agree', '同意', 'JbpmCommonAdvise_agree', 1, '', 2, '', '', 'JbpmCommonAdvise', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('JbpmCommonAdvise_assure', '确认', 'JbpmCommonAdvise_assure', 1, '', 3, '', '', 'JbpmCommonAdvise', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('JbpmCommonAdvise_quickDeal', '请速办理', 'JbpmCommonAdvise_quickDeal', 1, '', 4, '', '', 'JbpmCommonAdvise', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('JbpmCommonAdvise_conditionAgree', '附条件同意', 'JbpmCommonAdvise_conditionAgree', 1, '', 5, '', '', 'JbpmCommonAdvise', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('JbpmCommonAdvise_disagree', '不同意', 'JbpmCommonAdvise_disagree', 1, '', 6, '', '', 'JbpmCommonAdvise', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('equip_type_4', '自卸车', 'equip_type_4', 1, '', 11, '2013-08-31 14:54:49', '2013-09-01 19:24:56', 'equip_type', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('PLANMANYSTATUSOVER', '超额回笼', 'PLANMANYSTATUSOVER', 1, '', 0, '', '', 'PLANMANYSTATUS', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('PLANMANYSTATUSNO', '未收款', 'PLANMANYSTATUSNO', 1, '', 0, '', '', 'PLANMANYSTATUS', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('PLANMANYSTATUSPART', '部份收款', 'PLANMANYSTATUSPART', 1, '', 0, '', '', 'PLANMANYSTATUS', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('PLANMANYSTATUSALL', '已结清', 'PLANMANYSTATUSALL', 1, '', 0, '', '', 'PLANMANYSTATUS', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('PayFund1', '银行汇票', 'PayFund1', 1, '', 44, '', '', 'PayFund', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('PayFund2', '银行承兑汇票', 'PayFund2', 1, '', 45, '', '', 'PayFund', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('PayFund3', '商业承兑汇票', 'PayFund3', 1, '', 46, '', '', 'PayFund', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('PayFund4', '支票', 'PayFund4', 1, '', 47, '', '', 'PayFund', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('PayFund5', '信用证', 'PayFund5', 1, '', 48, '', '', 'PayFund', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('PayFund6', '电汇', 'PayFund6', 1, '', 49, '', '', 'PayFund', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('PayFund7', '保证金抵扣', 'PayFund7', 1, '', 219, '', '', 'PayFund', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('Payment1', '网银', 'Payment1', 1, '', 131, '', '', 'Payment', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('Payment2', '非网银', 'Payment2', 1, '', 132, '', '', 'Payment', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('next_list', '次期', 'next_list', 1, '', 186, '', '', 'adjust_style', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('next_month', '次月', 'next_month', 1, '', 187, '', '', 'adjust_style', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('next_day', '次日', 'next_day', 1, '', 188, '', '', 'adjust_style', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('next_year', '次年', 'next_year', 1, '', 189, '', '', 'adjust_style', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('assets_loss', '贷款损失准备', 'assets_loss', 1, '', 321, '', '', 'assets', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('assets_Offered ', '拆放同业和买入返售资产', 'assets_Offered ', 1, '', 322, '', '', 'assets', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('assets_Storage', '存放同业款项', 'assets_Storage', 1, '', 323, '', '', 'assets', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('assets_Investment ', '银行账户债券投资', 'assets_Investment ', 1, '', 324, '', '', 'assets', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('assets_Interest', '应收利息', 'assets_Interest', 1, '', 325, '', '', 'assets', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('assets_Accounts ', '其它应收款', 'assets_Accounts ', 1, '', 326, '', '', 'assets', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('assets_Liabilities', '不可撤销的承诺及或有负债', 'assets_Liabilities', 1, '', 327, '', '', 'assets', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('assure_method1', '全额担保', 'assure_method1', 1, '', 199, '', '', 'assure_method', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('meettype', '会议方式', 'meettype', 1, '', 0, '', '', '0', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('assure_method2', '部分担保', 'assure_method2', 1, '', 200, '', '', 'assure_method', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('assuror_right1', '中央政府投资的公用企业提供的保证', 'assuror_right1', 1, '', 305, '', '', 'assuror_right', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('assuror_right2', '其它公用企业提供的保证', 'assuror_right2', 1, '', 306, '', '', 'assuror_right', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('assuror_right3', '商业银行提供的保证', 'assuror_right3', 1, '', 307, '', '', 'assuror_right', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('assuror_right4', '其它金融机构提供的保证', 'assuror_right4', 1, '', 308, '', '', 'assuror_right', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('assuror_right5', '其他企业和个人提供的保证', 'assuror_right5', 1, '', 309, '', '', 'assuror_right', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('attachbigtype01midtype01smart01', '附件小类1', 'attachbigtype01midtype01smart01', 1, '', 407, '', '', 'attachbigtype01midtype01', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('attachbigtype01midtype01smart02', '附件小类2', 'attachbigtype01midtype01smart02', 1, '', 408, '', '', 'attachbigtype01midtype01', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('bussiness_mode_dealer', '经销', 'bussiness_mode_dealer', 1, '', 1, '', '', 'bussiness_mode', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('bussiness_mode_cust', '直销', 'bussiness_mode_cust', 1, '', 2, '', '', 'bussiness_mode', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('bussiness_type_car', '车辆', 'bussiness_type_car', 1, '', 1, '', '', 'bussiness_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('bussiness_type_equip', '设备', 'bussiness_type_equip', 1, '', 2, '', '', 'bussiness_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contact_type_phone', '电话联系', 'contact_type_phone', 1, '', 168, '', '', 'contact_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contact_type_home', '上门拜访', 'contact_type_home', 1, '', 169, '', '', 'contact_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contact_type_mail', '电子邮件', 'contact_type_mail', 1, '', 170, '', '', 'contact_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_lease_type1', '正常合同', 'contract_lease_type1', 1, '', 3, '', '', 'contract_lease_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_lease_type2', '变更合同', 'contract_lease_type2', 1, '', 4, '', '', 'contract_lease_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_lease_type3', '回寄合同', 'contract_lease_type3', 1, '', 5, '', '', 'contract_lease_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_lease_type4', '结束合同', 'contract_lease_type4', 1, '', 6, '', '', 'contract_lease_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_lease_type5', '担保合同', 'contract_lease_type5', 1, '', 7, '', '', 'contract_lease_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_lease_type6', '保证书', 'contract_lease_type6', 1, '', 8, '', '', 'contract_lease_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_rating_A3', 'AAA', 'credit_rating_A3', 1, '', 20, '', '', 'credit_rating', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_rating_A2', 'AA', 'credit_rating_A2', 1, '', 21, '', '', 'credit_rating', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_rating_A1', 'A', 'credit_rating_A1', 1, '', 22, '', '', 'credit_rating', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_rating_b3', 'BBB', 'credit_rating_b3', 1, '', 23, '', '', 'credit_rating', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_rating_b2', 'BB', 'credit_rating_b2', 1, '', 24, '', '', 'credit_rating', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_rating_c3', 'CCC', 'credit_rating_c3', 1, '', 25, '', '', 'credit_rating', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_rating_c2', 'CC', 'credit_rating_c2', 1, '', 26, '', '', 'credit_rating', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_rating_c1', 'C', 'credit_rating_c1', 1, '', 27, '', '', 'credit_rating', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_rating_b1', 'B', 'credit_rating_b1', 1, '', 28, '', '', 'credit_rating', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type1', '保理业务', 'credit_type1', 1, '', 1, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type2', '项目贷款', 'credit_type2', 1, '', 2, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type3', '一般短期流动资金贷款', 'credit_type3', 1, '', 3, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type4', '中长期流动资金贷款', 'credit_type4', 1, '', 4, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type5', '项目贷款', 'credit_type5', 1, '', 5, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type6', '国内信用证押汇', 'credit_type6', 1, '', 6, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type7', '国内信用证议付', 'credit_type7', 1, '', 7, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type8', '国内信用证开立', 'credit_type8', 1, '', 8, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type9', '非融资性保函', 'credit_type9', 1, '', 9, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type10', '融资性保函', 'credit_type10', 1, '', 10, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type11', '贴现', 'credit_type11', 1, '', 11, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type12', '银行承兑汇票', 'credit_type12', 1, '', 12, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type13', '商业承兑汇票', 'credit_type13', 1, '', 13, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type14', '委托贷款', 'credit_type14', 1, '', 14, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_type15', '综合', 'credit_type15', 1, '', 15, '', '', 'credit_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('crediter_type1', '银行', 'crediter_type1', 1, '', 1, '', '', 'crediter_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('crediter_type2', '非银行金融机构', 'crediter_type2', 1, '', 2, '', '', 'crediter_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('crediter_type3', '集团', 'crediter_type3', 1, '', 3, '', '', 'crediter_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('currency_type1', '人民币', 'currency_type1', 1, 'BB01', 11, '', '', 'currency_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('currency_type2', '美元', 'currency_type2', 1, 'BB02', 12, '', '', 'currency_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('CUST_INFO_PERSON', '自然人', 'CUST_INFO_PERSON', 1, '', 0, '', '', 'cust_info_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('CUST_INFO_COMPANY', '法人', 'CUST_INFO_COMPANY', 1, '', 1, '', '', 'cust_info_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('CUST_INFO_DEALER', '经销商', 'CUST_INFO_DEALER', 1, '', 2, '', '', 'cust_info_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('cust_jobposition1', '经理', 'cust_jobposition1', 1, '', 2, '', '', 'cust_jobposition', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('cust_jobposition2', '主管', 'cust_jobposition2', 1, '', 3, '', '', 'cust_jobposition', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('cust_jobposition3', '总监', 'cust_jobposition3', 1, '', 4, '', '', 'cust_jobposition', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('Education', '教育', 'Education', 1, '', 36, '', '', 'cust_kind', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('machinery', '工程机械', 'machinery', 1, '', 37, '', '', 'cust_kind', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('medical', '医疗', 'medical', 1, '', 50, '', '', 'cust_kind', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('communications', '交通运输', 'communications', 1, '', 65, '', '', 'cust_kind', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('newenergy', '新能源', 'newenergy', 1, '', 66, '', '', 'cust_kind', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('economize', '节能减排', 'economize', 1, '', 67, '', '', 'cust_kind', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('industry', '工业装备', 'industry', 1, '', 68, '', '', 'cust_kind', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('Coalmine', '煤矿', 'Coalmine', 1, '', 311, '', '', 'cust_kind', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('Coalindustry', '煤化工', 'Coalindustry', 1, '', 312, '', '', 'cust_kind', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('Agriculture', '农业', 'Agriculture', 1, '', 313, '', '', 'cust_kind', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('Logistics', '物流', 'Logistics', 1, '', 314, '', '', 'cust_kind', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values (' chemical', '化工', ' chemical', 1, '', 365, '', '', 'cust_kind', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('catering industry', '餐饮服务', 'catering industry', 1, '', 372, '', '', 'cust_kind', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('other', '其它', 'other', 1, '', 376, '', '', 'cust_kind', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('cust_scale1', '大型', 'cust_scale1', 1, '', 317, '', '', 'cust_scale', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('cust_scale2', '中型', 'cust_scale2', 1, '', 318, '', '', 'cust_scale', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('cust_scale3', '小型', 'cust_scale3', 1, '', 319, '', '', 'cust_scale', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('cust_type.cust', '承租人', 'cust_type.cust', 1, '', 137, '', '', 'cust_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('cust_type.assuror', '担保人', 'cust_type.assuror', 1, '', 138, '', '', 'cust_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('cust_type.vndr', '供应商', 'cust_type.vndr', 1, '', 139, '', '', 'cust_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('cust_type.manufacturer', '制造商', 'cust_type.manufacturer', 1, '', 208, '', '', 'cust_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('cust_type.guarantor', '抵押人', 'cust_type.guarantor', 1, '', 220, '', '', 'cust_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealerApprovalstatus1', '资料填写', 'dealerApprovalstatus1', 1, '', 3, '', '', 'dealerApprovalstatus', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealerApprovalstatus2', '准入通过', 'dealerApprovalstatus2', 1, '', 4, '', '', 'dealerApprovalstatus', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealerApprovalstatus3', '准入失败', 'dealerApprovalstatus3', 1, '', 5, '', '', 'dealerApprovalstatus', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealerApprovalstatus4', '评审通过', 'dealerApprovalstatus4', 1, '', 6, '', '', 'dealerApprovalstatus', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealerApprovalstatus5', '评审失败', 'dealerApprovalstatus5', 1, '', 7, '', '', 'dealerApprovalstatus', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealerApprovalstatus6', '签订合同', 'dealerApprovalstatus6', 1, '', 8, '', '', 'dealerApprovalstatus', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district1', '华东', 'dealer_district1', 0, '', 1, '', '', 'dealer_district', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district3', '鄂', 'dealer_district3', 1, '', 4, '2013-08-23 10:27:44', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('agreeApply', '同意申请', 'agreeApply', 1, '', 2, '', '', 'dealer_intoStatue', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('noagreeApply', '不同意申请', 'noagreeApply', 1, '', 3, '', '', 'dealer_intoStatue', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_shop_type1', '4S店', 'dealer_shop_type1', 1, '', 1, '', '', 'dealer_shop_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_shop_type2', '旗舰店', 'dealer_shop_type2', 1, '', 2, '', '', 'dealer_shop_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_symbiosis1', '启用', 'dealer_symbiosis1', 1, '', 1, '', '', 'dealer_symbiosis', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_symbiosis2', '禁用', 'dealer_symbiosis2', 1, '', 2, '', '', 'dealer_symbiosis', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('businessdept', '业务部', 'businessdept', 0, '', 396, '', '', 'dept', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('approvedept', '信审部', 'approvedept', 0, '', 397, '', '', 'dept', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('documentColumnType_money', '金额', 'documentColumnType_money', 1, '', 4, '', '', 'documentColumnType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('otherLimited', '其它有限责任公司', 'otherLimited', 1, '', 310, '', '', 'ownership', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('paperType1', '租赁合同', 'paperType1', 1, '', 399, '', '', 'paperTypes', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('paperType2', '保证合同', 'paperType2', 1, '', 400, '', '', 'paperTypes', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('paperType3', '抵押合同', 'paperType3', 1, '', 401, '', '', 'paperTypes', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('paperType4', 'XX', 'paperType4', 1, '', 410, '', '', 'paperTypes', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('pay_type_out', '付款', 'pay_type_out', 1, '', 0, '', '', 'pay_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('pay_type_in', '收款', 'pay_type_in', 1, '', 1, '', '', 'pay_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('pena_rate_6', '6.66', 'pena_rate_6', 1, '', 183, '', '', 'pena_rate', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('pena_rate_60', '6', 'pena_rate_60', 1, '', 184, '', '', 'pena_rate', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('period_type_0', '期初', 'period_type_0', 1, '', 175, '', '', 'period_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('period_type_1', '期末', 'period_type_1', 1, '', 176, '', '', 'period_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('proj_scale1', '500万以下', 'proj_scale1', 1, '', 8, '', '', 'proj_scale', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('proj_scale2', '500-1000万', 'proj_scale2', 1, '', 9, '', '', 'proj_scale', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('proj_scale3', '1000万-3000万', 'proj_scale3', 1, '', 51, '', '', 'proj_scale', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('proj_scale4', '3000万-1亿', 'proj_scale4', 1, '', 52, '', '', 'proj_scale', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('proj_scale5', '1亿以上', 'proj_scale5', 1, '', 53, '', '', 'proj_scale', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('proj_source1', '自营', 'proj_source1', 1, '', 151, '', '', 'proj_source', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('proj_source2', '推荐', 'proj_source2', 1, '', 328, '', '', 'proj_source', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('proj_type1', '关联', 'proj_type1', 1, '', 148, '', '', 'proj_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('proj_type2', '非关联', 'proj_type2', 1, '', 149, '', '', 'proj_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('fixed', '固定利率', 'fixed', 1, '', 191, '', '', 'rate_float_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('proportion', '按央行浮动比率', 'proportion', 1, '', 192, '', '', 'rate_float_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('add', '按央行利率加点', 'add', 1, '', 193, '', '', 'rate_float_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('reditor_right1', '对我国政府的债权', 'reditor_right1', 1, '', 296, '', '', 'reditor_right', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('reditor_right2', '对中国人民银行的债权', 'reditor_right2', 1, '', 297, '', '', 'reditor_right', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('reditor_right3', '对评级AA-级及以上国家和地区的中央政府和中央银行的债权', 'reditor_right3', 1, '', 298, '', '', 'reditor_right', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('reditor_right4', '对评级AA-级以下国家和地区的中央政府和中央银行的债权', 'reditor_right4', 1, '', 299, '', '', 'reditor_right', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('reditor_right5', '对评级AA-级及以上国家和地区中央政府投资的公用企业的债权', 'reditor_right5', 1, '', 300, '', '', 'reditor_right', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('reditor_right6', '对评级AA-级以下国家和地区中央政府投资的公用企业的债权', 'reditor_right6', 1, '', 301, '', '', 'reditor_right', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('reditor_right7', '对我国中央政府投资的公用企业的债权', 'reditor_right7', 1, '', 302, '', '', 'reditor_right', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('reditor_right8', '对其他公用企业的债权', 'reditor_right8', 1, '', 303, '', '', 'reditor_right', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('relation1', '股东', 'relation1', 1, '', 153, '', '', 'relation', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('relation2', '关联企业', 'relation2', 1, '', 154, '', '', 'relation', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('relation_ship1', '主要股东', 'relation_ship1', 1, '', 1, '', '', 'relation_ship', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('relation_ship2', '股东', 'relation_ship2', 1, '', 2, '', '', 'relation_ship', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('relation_ship.', '集团', 'relation_ship.', 1, '', 3, '', '', 'relation_ship', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('relationship_chairman', '董事长', 'relationship_chairman', 1, '', 2, '', '', 'relationship', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('relationship_ceo', '总经理', 'relationship_ceo', 1, '', 3, '', '', 'relationship', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('relationship_cfo', '财务负责人', 'relationship_cfo', 1, '', 5, '', '', 'relationship', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('relationship_spouse', '配偶', 'relationship_spouse', 1, '', 168, '', '', 'relationship', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('relationship_children', '子女', 'relationship_children', 1, '', 169, '', '', 'relationship', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('relationship_friends', '朋友', 'relationship_friends', 1, '', 170, '', '', 'relationship', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('relationship_parents', '父母', 'relationship_parents', 1, '', 171, '', '', 'relationship', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('invoice_type01', '本金一次性开收据，利息分次开发票', 'invoice_type01', 1, '', 388, '', '2013-08-26 14:38:21', 'rent_invoice_type', '', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('invoice_type02', '本金一次性开发票，利息分次开发票', 'invoice_type02', 1, '', 391, '', '2013-08-26 14:38:28', 'rent_invoice_type', '', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('invoice_type03', '发票分期开具，按每期租金开票', 'invoice_type03', 1, '', 394, '', '2013-08-26 14:38:34', 'rent_invoice_type', '', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('invoice_type04', '发票分期开具,按每期本息分开开票', 'invoice_type04', 1, '', 396, '', '2013-08-26 14:37:26', 'rent_invoice_type', '', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('invoice_type05', '营业税发票', 'invoice_type05', 1, '', 397, '', '', 'rent_invoice_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('invoice_type06', '每期开具利息发票和本金收据（不开具本金发票）', 'invoice_type06', 1, '', 401, '', '2013-08-26 14:37:49', 'rent_invoice_type', '', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('repeal_type1', '承租人违约', 'repeal_type1', 1, '', 206, '', '', 'repeal_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('repeal_type2', '初评会否决', 'repeal_type2', 1, '', 207, '', '', 'repeal_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('repeal_type3', '项目评审会议否决', 'repeal_type3', 1, '', 367, '', '', 'repeal_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('repeal_type4', '风险控制委员会以否决', 'repeal_type4', 1, '', 368, '', '', 'repeal_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('repeal_type5', '董事会否决', 'repeal_type5', 1, '', 369, '', '', 'repeal_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.FileType.TYPE1.TEST', '测试', 'root.FileType.TYPE1.TEST', 1, '', 2, '', '', 'root.FileType.TYPE1', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.FileType.TYPE2.TEST02', '测试02', 'root.FileType.TYPE2.TEST02', 1, '', 2, '', '', 'root.FileType.TYPE2', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_equal_com', '定性指标', 'credit_equal_com', 1, '', 113, '', '', 'root.evaluate.category.credit.equal', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.credit.equal.analysis', '定性分析', 'root.evaluate.category.credit.equal.analysis', 1, '', 134, '', '', 'root.evaluate.category.credit.equal', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('manager', '管理水平', 'manager', 1, '', 227, '', '', 'root.evaluate.category.credit.equal', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('competition', '竞争实力', 'competition', 1, '', 228, '', '', 'root.evaluate.category.credit.equal', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('environment', '环境', 'environment', 1, '', 229, '', '', 'root.evaluate.category.credit.equal', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('AssetsOperation', '资产运营', 'AssetsOperation', 1, '', 238, '', '', 'root.evaluate.category.credit.equal', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('FRControl', '财务风险控制', 'FRControl', 1, '', 239, '', '', 'root.evaluate.category.credit.equal', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('bankR', '与银行评级相对应', 'bankR', 1, '', 240, '', '', 'root.evaluate.category.credit.equal', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.credit.equan.earning', '盈利能力', 'root.evaluate.category.credit.equan.earning', 1, '', 108, '', '', 'root.evaluate.category.credit.equan', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.credit.equan.asset', '资产质量', 'root.evaluate.category.credit.equan.asset', 1, '', 109, '', '', 'root.evaluate.category.credit.equan', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.credit.equan.debt', '债务风险', 'root.evaluate.category.credit.equan.debt', 1, '', 110, '', '', 'root.evaluate.category.credit.equan', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.credit.equan.grow', '成长能力', 'root.evaluate.category.credit.equan.grow', 1, '', 111, '', '', 'root.evaluate.category.credit.equan', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.credit.equan.pay', '偿债能力', 'root.evaluate.category.credit.equan.pay', 1, '', 112, '', '', 'root.evaluate.category.credit.equan', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.credit.equan.scale', '规模', 'root.evaluate.category.credit.equan.scale', 1, '', 133, '', '', 'root.evaluate.category.credit.equan', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('credit_espec_com', '特例指标', 'credit_espec_com', 1, '', 114, '', '', 'root.evaluate.category.credit.espec', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.credit.leasetrisk.lease', '租赁物风险度评估', 'root.evaluate.category.credit.leasetrisk.lease', 1, '', 119, '', '', 'root.evaluate.category.credit.leasetrisk', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.credit.leasetrisk.guarantee', '抵质押物风险度测评', 'root.evaluate.category.credit.leasetrisk.guarantee', 1, '', 120, '', '', 'root.evaluate.category.credit.leasetrisk', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.zixin.base', '公司基本情况', 'root.evaluate.category.zixin.base', 1, '', 122, '', '', 'root.evaluate.category.zixin', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.zixin.cash', '现金支付', 'root.evaluate.category.zixin.cash', 1, '', 123, '', '', 'root.evaluate.category.zixin', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.zixin.proj', '工程', 'root.evaluate.category.zixin.proj', 1, '', 124, '', '', 'root.evaluate.category.zixin', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.zixin.guaran', '信用记录', 'root.evaluate.category.zixin.guaran', 1, '', 125, '', '', 'root.evaluate.category.zixin', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.zixin.prepay', '首付比例', 'root.evaluate.category.zixin.prepay', 1, '', 126, '', '', 'root.evaluate.category.zixin', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.zixin.equipType', '设备类型', 'root.evaluate.category.zixin.equipType', 1, '', 127, '', '', 'root.evaluate.category.zixin', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.zixin.dprnl', '担保人的实力', 'root.evaluate.category.zixin.dprnl', 1, '', 128, '', '', 'root.evaluate.category.zixin', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.zixin.tgdyw', '提供抵押物', 'root.evaluate.category.zixin.tgdyw', 1, '', 129, '', '', 'root.evaluate.category.zixin', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.zixin.xjl', '月现金流分析', 'root.evaluate.category.zixin.xjl', 1, '', 222, '', '', 'root.evaluate.category.zixin', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.category.zixin.chnl', '偿付能力分析', 'root.evaluate.category.zixin.chnl', 1, '', 223, '', '', 'root.evaluate.category.zixin', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.group.quanti', '定量类', 'root.evaluate.group.quanti', 1, '', 98, '', '', 'root.evaluate.group', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.group.qualti', '定性类', 'root.evaluate.group.qualti', 1, '', 99, '', '', 'root.evaluate.group', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.group.special', '特例类', 'root.evaluate.group.special', 1, '', 100, '', '', 'root.evaluate.group', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.group.leaserisk', '租赁物风险类', 'root.evaluate.group.leaserisk', 1, '', 115, '', '', 'root.evaluate.group', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.targettype.person', '个人用户', 'root.evaluate.targettype.person', 1, '', 106, '', '', 'root.evaluate.targettype', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.evaluate.targettype.enteruser', '公司客户', 'root.evaluate.targettype.enteruser', 1, '', 107, '', '', 'root.evaluate.targettype', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.vocation.common', '一般企业', 'root.vocation.common', 1, '', 94, '', '', 'root.vocation', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.vocation.trafic', '交通运输', 'root.vocation.trafic', 1, '', 117, '', '', 'root.vocation', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.vocation.machinery', '工程机械', 'root.vocation.machinery', 1, '', 118, '', '', 'root.vocation', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.vocation.medical', '医疗器械', 'root.vocation.medical', 1, '', 135, '', '', 'root.vocation', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.vocation.madeIn', '制造业', 'root.vocation.madeIn', 1, '', 230, '', '', 'root.vocation', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.vocation.power', '能源业', 'root.vocation.power', 1, '', 231, '', '', 'root.vocation', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.vocation.saved', '仓储业', 'root.vocation.saved', 1, '', 232, '', '', 'root.vocation', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.vocation.posted', '邮政业', 'root.vocation.posted', 1, '', 233, '', '', 'root.vocation', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.vocation.WRTrade', '批发零售业', 'root.vocation.WRTrade', 1, '', 234, '', '', 'root.vocation', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.vocation.server', '其他服务业', 'root.vocation.server', 1, '', 235, '', '', 'root.vocation', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.vocation.builder', '建筑业', 'root.vocation.builder', 1, '', 236, '', '', 'root.vocation', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root.vocation.familary', '房地产业', 'root.vocation.familary', 1, '', 237, '', '', 'root.vocation', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('WorkflowType.ProjBefore', '租前业务', 'WorkflowType.ProjBefore', 1, '', 6, '', '', 'root.workflowType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('WorkflowType.ProjAfter', '租后管理', 'WorkflowType.ProjAfter', 1, '', 8, '', '', 'root.workflowType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic1', '阿昌族', 'root_ethnic1', 1, '', 1, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic2', '白族', 'root_ethnic2', 1, '', 2, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic3', '保安族', 'root_ethnic3', 1, '', 3, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic4', '布朗族', 'root_ethnic4', 1, '', 4, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic5', '布依族', 'root_ethnic5', 1, '', 5, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic8', '达斡尔族', 'root_ethnic8', 1, '', 8, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic9', '傣族', 'root_ethnic9', 1, '', 9, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic10', '德昂族', 'root_ethnic10', 1, '', 10, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic11', '东乡族', 'root_ethnic11', 1, '', 11, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic12', '侗族', 'root_ethnic12', 1, '', 12, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic13', '独龙族', 'root_ethnic13', 1, '', 13, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic14', '俄罗斯族', 'root_ethnic14', 1, '', 14, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic15', '鄂伦春族', 'root_ethnic15', 1, '', 15, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic16', '鄂温克族', 'root_ethnic16', 1, '', 16, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic17', '高山族', 'root_ethnic17', 1, '', 17, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic18', '哈尼族', 'root_ethnic18', 1, '', 18, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic19', '哈萨克族', 'root_ethnic19', 1, '', 19, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic20', '汉族', 'root_ethnic20', 1, '', 20, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic21', '赫哲族', 'root_ethnic21', 1, '', 21, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic22', '回族', 'root_ethnic22', 1, '', 22, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic23', '基诺族', 'root_ethnic23', 1, '', 23, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic24', '京族', 'root_ethnic24', 1, '', 24, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic25', '景颇族', 'root_ethnic25', 1, '', 25, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic26', '柯尔克孜族', 'root_ethnic26', 1, '', 26, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic27', '拉祜族', 'root_ethnic27', 1, '', 27, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic28', '黎族', 'root_ethnic28', 1, '', 28, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic29', '傈僳族', 'root_ethnic29', 1, '', 29, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic30', '珞巴族', 'root_ethnic30', 1, '', 30, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic31', '满族', 'root_ethnic31', 1, '', 31, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic32', '毛南族', 'root_ethnic32', 1, '', 32, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic33', '门巴族', 'root_ethnic33', 1, '', 33, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic34', '蒙古族', 'root_ethnic34', 1, '', 34, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic35', '苗族', 'root_ethnic35', 1, '', 35, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic36', '仫佬族', 'root_ethnic36', 1, '', 36, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic37', '纳西族', 'root_ethnic37', 1, '', 37, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic38', '怒族', 'root_ethnic38', 1, '', 38, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic39', '普米族', 'root_ethnic39', 1, '', 39, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic40', '羌族', 'root_ethnic40', 1, '', 40, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic41', '撒拉族', 'root_ethnic41', 1, '', 41, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic42', '畲族', 'root_ethnic42', 1, '', 42, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic43', '水族', 'root_ethnic43', 1, '', 43, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic44', '塔吉克族', 'root_ethnic44', 1, '', 44, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic45', '塔塔尔族', 'root_ethnic45', 1, '', 45, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic46', '土家族', 'root_ethnic46', 1, '', 46, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic47', '土族', 'root_ethnic47', 1, '', 47, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic48', '佤族', 'root_ethnic48', 1, '', 48, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic49', '维吾尔族', 'root_ethnic49', 1, '', 49, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic50', '乌兹别克族', 'root_ethnic50', 1, '', 50, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic51', '锡伯族', 'root_ethnic51', 1, '', 51, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic52', '瑶族', 'root_ethnic52', 1, '', 52, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic53', '彝族', 'root_ethnic53', 1, '', 53, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic54', '仡佬族', 'root_ethnic54', 1, '', 54, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic55', '裕固族', 'root_ethnic55', 1, '', 55, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic56', '壮族', 'root_ethnic56', 1, '', 56, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_null_ini_1', '自定义数据1', 'root_null_ini_1', 1, '', 1, '', '', 'root_null_ini', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_null_ini_2', '自定义数据2', 'root_null_ini_2', 1, '', 7, '', '', 'root_null_ini', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_null_ini_3', '自定义数据3', 'root_null_ini_3', 1, '', 8, '', '', 'root_null_ini', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('High_school', '高中', 'High_school', 1, '', 145, '', '', 'school', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('university', '大学', 'university', 1, '', 146, '', '', 'school', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('equip_class_1', '拖车', 'equip_class_1', 1, '', 2, '2013-08-31 14:39:57', '2013-09-01 19:14:16', 'equip_class', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('equip_class_2', '挂车', 'equip_class_2', 1, '', 4, '2013-08-31 14:40:05', '2013-09-01 19:14:23', 'equip_class', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('equip_type_1', '水泥搅拌车', 'equip_type_1', 1, '', 3, '2013-08-31 14:40:18', '2013-09-01 19:24:33', 'equip_type', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('equip_type_2', '散装水泥罐车', 'equip_type_2', 1, '', 6, '2013-08-31 14:41:44', '2013-09-01 19:24:42', 'equip_type', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('PayFund10', '经销商保证金抵扣', 'PayFund10', 1, '', 222, '2013-08-31 13:25:25', '', 'PayFund', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('visit_mode1', '上门', 'visit_mode1', 1, '', 1, '2013-08-31 13:56:14', '', 'visit_mode', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('equip_type_3', '危险品运输车', 'equip_type_3', 1, '', 9, '2013-08-31 14:42:13', '2013-09-01 19:24:49', 'equip_type', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('degdept', '风控部', 'degdept', 0, '', 409, '', '', 'dept', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('xxxxdept2', '法务部', 'xxxxdept2', 0, '', 413, '', '', 'dept', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('documentColumnType_null', '无操作', 'documentColumnType_null', 1, '', 1, '', '', 'documentColumnType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('documentColumnType_text', '文本域', 'documentColumnType_text', 1, '', 2, '', '', 'documentColumnType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('documentColumnType_double', '数字', 'documentColumnType_double', 1, '', 3, '', '', 'documentColumnType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district6', '吉', 'dealer_district6', 1, '', 7, '2013-08-23 10:29:19', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('WorkflowType.ProjIng', '租中管理', 'WorkflowType.ProjIng', 1, '', 7, '2013-08-28 14:37:32', '', 'root.workflowType', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district5', '黑', 'dealer_district5', 1, '', 6, '2013-08-23 10:29:03', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district7', '辽', 'dealer_district7', 1, '', 8, '2013-08-23 10:29:32', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district8', '晋', 'dealer_district8', 1, '', 9, '2013-08-23 10:29:53', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district21', '宁', 'dealer_district21', 1, '', 23, '2013-08-23 10:36:17', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('his_fund_plan_change', '资金计划变更', 'his_fund_plan_change', 1, '', 10, '2013-08-20 14:36:08', '', 'table_for_his_type', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district2', '皖', 'dealer_district2', 1, '', 3, '2013-08-23 10:27:15', '2013-08-23 10:28:23', 'dealer_district', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district4', '赣', 'dealer_district4', 1, '', 5, '2013-08-23 10:28:44', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('msgtype.2', '发布', 'msgtype.2', 1, '', 3, '2013-08-26 14:07:34', '', 'msgtype', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('PayFund8', '退款', 'PayFund8', 1, '', 221, '2013-08-26 19:39:19', '', 'PayFund', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('owner_office_1', '上海办事处', 'owner_office_1', 1, '', 2, '2013-08-19 14:49:44', '', 'owner_office', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('his_status_before', '前', 'his_status_before', 1, '', 1, '', '', 'table_for_his_status', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('his_status_after', '后', 'his_status_after', 1, '', 3, '', '', 'table_for_his_status', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('his_proj_change', '项目变更', 'his_proj_change', 1, '', 1, '', '', 'table_for_his_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('his_contract_change', '合同变更', 'his_contract_change', 1, '', 2, '', '', 'table_for_his_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('his_contract_onhire', '合同起租', 'his_contract_onhire', 1, '', 3, '', '', 'table_for_his_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('his_contract_onhire_more', '合同多次起租', 'his_contract_onhire_more', 1, '', 4, '', '', 'table_for_his_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('his_plan_change', '租金计划变更', 'his_plan_change', 1, '', 5, '', '', 'table_for_his_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('his_plan_mod', '租金计划修改', 'his_plan_mod', 1, '', 6, '', '', 'table_for_his_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('his_rate_change', '调息', 'his_rate_change', 1, '', 7, '', '', 'table_for_his_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('his_rate_change_back', '调息回滚', 'his_rate_change_back', 1, '', 8, '', '', 'table_for_his_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('his_contract_end', '合同中途终止', 'his_contract_end', 1, '', 9, '', '', 'table_for_his_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('tax_level_name.1', '一般纳税人', 'tax_level_name.1', 1, '', 1, '', '', 'tax_level_name', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('tax_level_name.2', '非一般纳税人', 'tax_level_name.2', 1, '', 2, '', '', 'tax_level_name', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('visit_mode2', '电话', 'visit_mode2', 1, '', 376, '', '', 'visit_mode', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('vottype1', '同意', 'vottype1', 1, '', 0, '', '', 'vottype', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('vottype3', '附条件同意', 'vottype3', 1, '', 0, '', '', 'vottype', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('irregular_rent', '不规则', 'irregular_rent', 1, '', 1, '', '', 'settle_method', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('even_interest', '均息法', 'even_interest', 1, '', 1, '', '', 'settle_method', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('even_rent', '等额租金', 'even_rent', 1, '', 1, '', '', 'settle_method', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('documentColumnType_date', '时间', 'documentColumnType_date', 1, '', 5, '', '', 'documentColumnType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('documentColumnType_textarea', '富文本域', 'documentColumnType_textarea', 1, '', 6, '', '', 'documentColumnType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('documentColumnType_radio', '单选框', 'documentColumnType_radio', 1, '', 7, '', '', 'documentColumnType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('documentColumnType_checkbox', '复选框', 'documentColumnType_checkbox', 1, '', 8, '', '', 'documentColumnType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('documentColumnType_hide', '隐藏文本域', 'documentColumnType_hide', 2, '', 10, '', '', 'documentColumnType', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('document_borrow_1', '借阅', 'document_borrow_1', 1, '', 1, '', '', 'document_borrow', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('document_borrow_2', '寄送', 'document_borrow_2', 1, '', 2, '', '', 'document_borrow', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('device_type_1', '全新', 'device_type_1', 1, '', 3, '', '2013-09-01 19:34:12', 'device_type', '', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('device_type_2', '二手', 'device_type_2', 1, '', 7, '', '2013-09-01 19:34:17', 'device_type', '', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('device_type_3', '翻新', 'device_type_3', 1, '', 10, '', '2013-09-01 19:34:32', 'device_type', '', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('normal', '正常', 'normal', 1, '', 212, '', '', 'file_status', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('error', '读取出错', 'error', 1, '', 213, '', '', 'file_status', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('delete', '已删除', 'delete', 1, '', 214, '', '', 'file_status', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('renege', '数据不符合要求', 'renege', 1, '', 215, '', '', 'file_status', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('revoked', '撤销', 'revoked', 1, '', 216, '', '', 'file_status', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('down_error', '生成文件出现错误', 'down_error', 1, '', 245, '', '', 'file_status', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('finish_typenormal', '正常结束', 'finish_typenormal', 1, '', 1, '', '', 'finish_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('finish_typesettle', '提前结清', 'finish_typesettle', 1, '', 2, '', '', 'finish_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('finish_typetermination', '中途解约', 'finish_typetermination', 1, '', 3, '', '', 'finish_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('normal_class', '正常类', 'normal_class', 1, '', 14, '', '', 'five_class', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('about_class', '关注类', 'about_class', 1, '', 15, '', '', 'five_class', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('sub_class', '次级类', 'sub_class', 1, '', 16, '', '', 'five_class', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('loss_class', '可疑类', 'loss_class', 1, '', 17, '', '', 'five_class', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('danger_class', '损失类', 'danger_class', 1, '', 18, '', '', 'five_class', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('guarantee_type1', '房屋和其他地上定着物', 'guarantee_type1', 1, '', 156, '', '', 'guarantee_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('guarantee_type2', '机器、交通运输工具和其他财产', 'guarantee_type2', 1, '', 157, '', '', 'guarantee_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('guarantee_type3', '国有土地使用权、房屋和其他地上定着物', 'guarantee_type3', 1, '', 246, '', '', 'guarantee_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('guarantee_type4', '国有机器、交通运输工具和其他财产', 'guarantee_type4', 1, '', 247, '', '', 'guarantee_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('guarantee_type5', '依法可以抵押的其他财产', 'guarantee_type5', 1, '', 248, '', '', 'guarantee_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('guarantee_type6', '动产质押', 'guarantee_type6', 1, '', 249, '', '', 'guarantee_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('guarantee_type7', '汇票、支票、本票、债券、存款单、仓单、提单', 'guarantee_type7', 1, '', 250, '', '', 'guarantee_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('guarantee_type8', '股票、股份', 'guarantee_type8', 1, '', 251, '', '', 'guarantee_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('guarantee_type9', '商标专用权，专利权、著作权中的财产权', 'guarantee_type9', 1, '', 252, '', '', 'guarantee_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('guarantee_type10', '依法可以质押的其他权利', 'guarantee_type10', 1, '', 253, '', '', 'guarantee_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('income_1', '月  付', 'income_1', 1, '', 178, '', '', 'income_number_year', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('income_3', '季  付', 'income_3', 1, '', 179, '', '', 'income_number_year', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('income_6', '半年付', 'income_6', 1, '', 180, '', '', 'income_number_year', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('income_12', '年  付', 'income_12', 1, '', 181, '', '', 'income_number_year', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('income_2', '双月付', 'income_2', 1, '', 217, '', '', 'income_number_year', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('industry_type.Engineering ', '工程机械', 'industry_type.Engineering ', 1, '', 225, '', '', 'industry_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('industry_type.medical', '医疗', 'industry_type.medical', 1, '', 226, '', '', 'industry_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('insure_type1', '本司付款', 'insure_type1', 1, '', 1, '', '', 'insure_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('insure_type2', '本司代付', 'insure_type2', 1, '', 2, '', '', 'insure_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('insure_type3', '客户自保', 'insure_type3', 1, '', 3, '', '', 'insure_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('insure_type4', '客户代付', 'insure_type4', 1, '', 4, '', '', 'insure_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('insure_type5', '不投保', 'insure_type5', 1, '', 5, '', '', 'insure_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_form1', '直租', 'lease_form1', 1, '', 4, '', '', 'leas_form', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_form2', '回租', 'lease_form2', 1, '', 5, '', '', 'leas_form', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_form3', '委托(直租)', 'lease_form3', 1, '', 6, '', '', 'leas_form', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_form4', '委托(回租)', 'lease_form4', 1, '', 329, '', '', 'leas_form', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type1', '锅炉及原动机', 'lease_type1', 1, '', 254, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type2', '金属加工设备', 'lease_type2', 1, '', 255, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type3', '起重设备', 'lease_type3', 1, '', 256, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type4', '装卸设备', 'lease_type4', 1, '', 257, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type5', '其它通用设备', 'lease_type5', 1, '', 258, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type6', '工程机械', 'lease_type6', 1, '', 259, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type7', '电力设备', 'lease_type7', 1, '', 260, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type8', '纺织设备', 'lease_type8', 1, '', 261, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type9', '印刷设备', 'lease_type9', 1, '', 262, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type10', '医疗器械', 'lease_type10', 1, '', 263, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type11', '环保设备', 'lease_type11', 1, '', 264, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type12', '农林牧渔机械', 'lease_type12', 1, '', 265, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type13', '矿采设备', 'lease_type13', 1, '', 266, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type14', '钢铁', 'lease_type14', 1, '', 267, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type15', '有色金属', 'lease_type15', 1, '', 268, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type16', '冶炼设备', 'lease_type16', 1, '', 269, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type17', '炼油化工设备', 'lease_type17', 1, '', 270, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type18', '其它专用设备', 'lease_type18', 1, '', 271, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type19', '通用航空飞机', 'lease_type19', 1, '', 272, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type20', '运输机', 'lease_type20', 1, '', 273, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type21', '直升机', 'lease_type21', 1, '', 274, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type22', '其它飞机', 'lease_type22', 1, '', 275, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type23', '货船', 'lease_type23', 1, '', 276, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type24', '客船', 'lease_type24', 1, '', 277, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type25', '工程船', 'lease_type25', 1, '', 278, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type26', '其它船舶', 'lease_type26', 1, '', 279, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type27', '轨道交通', 'lease_type27', 1, '', 280, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type28', '汽车', 'lease_type28', 1, '', 281, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type29', '其他交通设备', 'lease_type29', 1, '', 282, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type30', '铁路运输设备', 'lease_type30', 1, '', 283, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type31', '其它交通运输设备', 'lease_type31', 1, '', 284, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type32', '通信设备', 'lease_type32', 1, '', 285, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type33', '计算机', 'lease_type33', 1, '', 286, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type34', '电信设备', 'lease_type34', 1, '', 287, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type35', '电气设备', 'lease_type35', 1, '', 288, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type36', '土地', 'lease_type36', 1, '', 289, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type37', '房屋', 'lease_type37', 1, '', 290, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type38', '道路', 'lease_type38', 1, '', 291, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type39', '桥梁', 'lease_type39', 1, '', 292, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type40', '构筑物', 'lease_type40', 1, '', 293, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('lease_type41', '其它租赁物', 'lease_type41', 1, '', 294, '', '', 'leas_type', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('loan_nature1', '同业借款', 'loan_nature1', 1, '', 357, '', '', 'loan_nature', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('loan_nature2', '流贷', 'loan_nature2', 1, '', 358, '', '', 'loan_nature', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('loan_nature3', '同业拆借', 'loan_nature3', 1, '', 359, '', '', 'loan_nature', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('loan_nature4', '股东存款', 'loan_nature4', 1, '', 360, '', '', 'loan_nature', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('married', '已婚', 'married', 1, '', 141, '', '', 'marital_status', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('unmarried', '未婚', 'unmarried', 1, '', 142, '', '', 'marital_status', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('widowed', '丧偶', 'widowed', 1, '', 143, '', '', 'marital_status', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('meettype1', '会议审核', 'meettype1', 1, '', 0, '', '', 'meettype', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('meettype2', '会签审核', 'meettype2', 1, '', 0, '', '', 'meettype', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('msggroup_group', '群组', 'msggroup_group', 1, '', 3, '2013-09-10 16:14:25', '', 'msggroup', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_mortgage3', '解除抵押登记', 'dealer_mortgage3', 1, '', 4, '2013-09-10 16:37:42', '', 'dealer_mortgage', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_mortgage5', '其他', 'dealer_mortgage5', 1, '', 6, '2013-09-10 16:38:12', '', 'dealer_mortgage', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.', '法人客户现场考察报告（德银）', 'wordtempletypetwo.', 0, '法人客户现场考察报告（德银）', 41, '2013-09-13 12:57:49', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.21', '合同编号测试', 'wordtempletypetwo.21', 1, '', 57, '2013-09-17 10:13:25', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.26', '合同-租赁物回购合同(经销商适用)', 'wordtempletypetwo.26', 1, '', 19, '2013-09-17 13:47:08', '2013-09-17 13:52:09', 'wordtempletypetwo', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('rentinvoiceconfirm', '租金发票开票', 'rentinvoiceconfirm', 1, '租金发票开票', 9, '2013-10-21 11:39:51', '', 'downloadModel', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.89', '合同-融资租赁业务合作协议(经销商适用)', 'wordtempletypetwo.89', 1, '', 73, '2013-10-10 10:23:48', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('taxvatuploadinfo', '进项发票', 'taxvatuploadinfo', 1, '', 5, '2013-10-16 17:25:21', '', 'downloadModel', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('rent_change_agreement01', '租金变更协议', 'rent_change_agreement01', 1, '租金变更协议', 2, '2013-10-24 10:55:01', '', 'rent_change_agreement', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('rent_update_agreement01', '租金修改协议', 'rent_update_agreement01', 1, '租金修改协议', 2, '2013-10-24 14:22:58', '', 'rent_update_agreement', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_register.guarantee', '担保函', 'contract_register.guarantee', 1, '1', 3, '2013-09-09 11:24:04', '', 'contract_register', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_register.ownership', '车辆所有权确认协议', 'contract_register.ownership', 1, '1', 4, '2013-09-09 11:24:40', '', 'contract_register', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_register.copy', '复印件', 'contract_register.copy', 1, '2', 8, '2013-09-09 11:27:31', '2013-09-09 16:28:00', 'contract_register', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_register.tenant_info', '承租人基本资料', 'contract_register.tenant_info', 1, '3', 11, '2013-09-09 11:28:50', '2013-09-09 16:28:08', 'contract_register', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('overduenotice', '催收通知书', 'overduenotice', 1, '', 3, '2013-09-11 17:01:57', '', 'downloadModel', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.34', '法人客户信审报告（10台以上）', 'wordtempletypetwo.34', 1, '法人客户信审报告（10台以上）', 56, '2013-09-17 15:51:03', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('fundinvoiceconfirm', '资金发票开票', 'fundinvoiceconfirm', 1, '资金发票开票', 8, '2013-10-18 15:49:16', '', 'downloadModel', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.81', '股东会决议', 'wordtempletypetwo.81', 1, '', 76, '2013-10-10 17:06:57', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_register.contract', '租赁物买卖合同', 'contract_register.contract', 1, '1', 2, '2013-09-09 11:23:25', '', 'contract_register', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_register.transportation_cost', '物流运输费用结算合同', 'contract_register.transportation_cost', 1, '1', 5, '2013-09-09 11:25:38', '', 'contract_register', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_register.guarantee_original', '保单原件', 'contract_register.guarantee_original', 1, '1', 6, '2013-09-09 11:27:13', '', 'contract_register', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_register.guarantee_info', '担保人基本资料', 'contract_register.guarantee_info', 1, '1', 12, '2013-09-09 11:29:16', '', 'contract_register', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_register.car_invoice', '车辆发票', 'contract_register.car_invoice', 1, '1', 13, '2013-09-09 11:30:01', '', 'contract_register', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('ThirdInsurTpye5', '责任限额30万', 'ThirdInsurTpye5', 1, '', 6, '2013-09-09 19:38:30', '', 'ThirdInsurTpye', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('ThirdInsurTpye9', '责任限额200万', 'ThirdInsurTpye9', 1, '', 10, '2013-09-09 19:39:25', '', 'ThirdInsurTpye', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_mortgage1', '抵押备案', 'dealer_mortgage1', 1, '', 2, '2013-09-10 16:37:11', '', 'dealer_mortgage', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_mortgage4', '公证', 'dealer_mortgage4', 1, '', 5, '2013-09-10 16:37:58', '', 'dealer_mortgage', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('fundchargeinvoiceconfirm', '资金实收开票', 'fundchargeinvoiceconfirm', 1, '资金实收开票', 79, '2013-10-23 09:24:06', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.33', '法人客户信审报告（6-10台）', 'wordtempletypetwo.33', 1, '法人客户信审报告（6-10台）', 55, '2013-09-17 09:17:36', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.24', '合同-抵押合同（主合同为还款协议）', 'wordtempletypetwo.24', 1, '', 26, '2013-09-17 13:29:22', '2013-09-17 13:51:45', 'wordtempletypetwo', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.25', '合同-抵押合同（主合同为融资租赁合同）', 'wordtempletypetwo.25', 1, '', 22, '2013-09-17 13:46:39', '2013-09-17 13:51:56', 'wordtempletypetwo', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.27', '合同-保证担保函（法人担保）', 'wordtempletypetwo.27', 1, '', 17, '2013-09-17 13:48:10', '2013-09-17 13:52:19', 'wordtempletypetwo', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contract_register.car_certificate', '车辆证书', 'contract_register.car_certificate', 1, '1', 9, '2013-09-09 11:28:03', '', 'contract_register', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('msggroup_district', '区域', 'msggroup_district', 1, '', 6, '2013-09-10 16:15:47', '', 'msggroup', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('cardupload', '卡扣上传', 'cardupload', 1, '', 4, '2013-09-11 17:26:05', '', 'downloadModel', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypefirst.', '项目信审流程', 'wordtempletypefirst.', 1, '项目信审流程', 7, '2013-09-13 12:57:07', '', 'wordtempletypefirst', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('creditreport', '信审文档', 'creditreport', 1, '信审文档', 6, '2013-10-17 11:29:31', '', 'downloadModel', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealercontract', '经销商合同', 'dealercontract', 1, '', 7, '2013-10-17 19:17:57', '', 'downloadModel', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypefirst4', '文件下载模板', 'wordtempletypefirst4', 1, '', 6, '2013-09-05 09:52:56', '', 'wordtempletypefirst', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo11', '租金催收通知函', 'wordtempletypetwo11', 1, '', 40, '2013-09-05 09:53:33', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('conform', '符合', 'conform', 1, '', 2, '2013-09-03 11:18:26', '', 'dealer_creditStatus', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_2', '基本资料-组织机构代码证', 'dealer_credit_doc_list_2', 1, '', 2, '2013-09-04 10:30:20', '2013-09-04 10:33:51', 'dealer_credit_doc_list', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_3', '基本资料-税务登记证', 'dealer_credit_doc_list_3', 1, '', 3, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_4', '基本资料-法定代表人身份证', 'dealer_credit_doc_list_4', 1, '', 4, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_5', '基本资料-银行开户许可证', 'dealer_credit_doc_list_5', 1, '', 5, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_6', '基本资料-公司章程', 'dealer_credit_doc_list_6', 1, '', 6, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_7', '基本资料-贷款卡（含密码）', 'dealer_credit_doc_list_7', 1, '', 7, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_8', '基本资料-验资报告', 'dealer_credit_doc_list_8', 1, '', 8, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_9', '基本资料-公司介绍', 'dealer_credit_doc_list_9', 1, '', 9, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_10', '基本资料-陕汽重卡品牌经销协议', 'dealer_credit_doc_list_10', 1, '', 10, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_11', '基本资料-公司主要领导个人简历', 'dealer_credit_doc_list_11', 1, '', 11, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_12', '基本资料-经销商联系信息登记表', 'dealer_credit_doc_list_12', 1, '', 12, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_13', '财务类资料-前年/去年度审计报告', 'dealer_credit_doc_list_13', 1, '', 13, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_14', '财务类资料-去年上月财务报表及，对应二级科目余额表', 'dealer_credit_doc_list_14', 1, '', 14, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_15', '财务类资料-去年公司所有银行对账单', 'dealer_credit_doc_list_15', 1, '', 15, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_16', '财务类资料-去年每月电子缴税付款凭证', 'dealer_credit_doc_list_16', 1, '', 16, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_17', '财务类资料-固定资产证明', 'dealer_credit_doc_list_17', 1, '', 17, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_18', '股东信息-身份证', 'dealer_credit_doc_list_18', 1, '', 18, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_19', '股东信息-夫妻双方户口簿', 'dealer_credit_doc_list_19', 1, '', 19, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_20', '股东信息-夫妻双方婚姻证明', 'dealer_credit_doc_list_20', 1, '', 20, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_21', '股东信息-固定资产证明', 'dealer_credit_doc_list_21', 1, '', 21, '', '', 'dealer_credit_doc_list', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_credit_doc_list_1', '基本资料-营业执照', 'dealer_credit_doc_list_1', 1, '', 1, '2013-09-04 10:29:12', '2013-09-04 10:33:29', 'dealer_credit_doc_list', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('noconform', '不符合', 'noconform', 1, '', 3, '2013-09-03 11:18:44', '', 'dealer_creditStatus', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic6', '藏族', 'root_ethnic6', 1, '', 6, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('root_ethnic7', '朝鲜族', 'root_ethnic7', 1, '', 7, '', '', 'root_ethnic', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('vottype2', '不同意', 'vottype2', 1, '', 0, '', '', 'vottype', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypefirst2', '额度通知函', 'wordtempletypefirst2', 1, '', 2, '', '2013-09-05 09:52:21', 'wordtempletypefirst', '', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypefirst1', '授信协议', 'wordtempletypefirst1', 1, '', 4, '', '', 'wordtempletypefirst', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypefirst3', '上传模板', 'wordtempletypefirst3', 1, '', 5, '', '', 'wordtempletypefirst', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo1', '融资租赁业务合作协议', 'wordtempletypetwo1', 1, '', 30, '', '', 'wordtempletypetwo', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo2', '股东会决议', 'wordtempletypetwo2', 1, '', 31, '', '', 'wordtempletypetwo', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo3', '最高额保证担保函(经销商)', 'wordtempletypetwo3', 1, '', 32, '', '', 'wordtempletypetwo', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo4', '最高额保证担保函(股东)', 'wordtempletypetwo4', 1, '', 33, '', '', 'wordtempletypetwo', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo5', '三方代付协议', 'wordtempletypetwo5', 1, '', 34, '', '', 'wordtempletypetwo', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo6', '综合服务协议', 'wordtempletypetwo6', 1, '', 35, '', '', 'wordtempletypetwo', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo7', '中止业务通知函', 'wordtempletypetwo7', 1, '', 36, '', '', 'wordtempletypetwo', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo8', '经销商授信额度调减通知函', 'wordtempletypetwo8', 1, '', 37, '', '', 'wordtempletypetwo', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo9', '经销商授信额度调增通知函', 'wordtempletypetwo9', 1, '', 38, '', '', 'wordtempletypetwo', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo10', '网银上传模板', 'wordtempletypetwo10', 1, '', 39, '', '', 'wordtempletypetwo', '', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('owner_office_2', '江苏办事处', 'owner_office_2', 1, '', 3, '2013-08-19 14:50:05', '', 'owner_office', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('owner_office_3', '湖南办事处', 'owner_office_3', 1, '', 4, '2013-08-19 14:50:20', '', 'owner_office', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('msgtype.1', '上报', 'msgtype.1', 1, '', 2, '2013-08-26 14:07:21', '', 'msgtype', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('PayFund9', '红冲', 'PayFund9', 1, '', 220, '2013-08-26 19:39:00', '', 'PayFund', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district9', '豫', 'dealer_district9', 1, '', 10, '2013-08-23 10:30:25', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district13', '京', 'dealer_district13', 1, '', 14, '2013-08-23 10:31:51', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district14', '津', 'dealer_district14', 1, '', 15, '2013-08-23 10:32:10', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district17', '苏', 'dealer_district17', 1, '', 18, '2013-08-23 10:33:12', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district18', '沪', 'dealer_district18', 1, '', 19, '2013-08-23 10:33:30', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district19', '浙', 'dealer_district19', 1, '', 20, '2013-08-23 10:33:47', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district10', '疆', 'dealer_district10', 1, '', 11, '2013-08-23 10:30:54', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district15', '冀', 'dealer_district15', 1, '', 16, '2013-08-23 10:32:27', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district16', '鲁', 'dealer_district16', 1, '', 17, '2013-08-23 10:32:52', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district22', '渝及其他区域', 'dealer_district22', 1, '', 24, '2013-08-23 10:37:12', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('his_onhire_contract_change', '起租后合同变更', 'his_onhire_contract_change', 1, '', 11, '2013-08-20 17:17:42', '', 'table_for_his_type', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district11', '蒙', 'dealer_district11', 1, '', 12, '2013-08-23 10:31:10', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district12', '陕', 'dealer_district12', 1, '', 13, '2013-08-23 10:31:33', '', 'dealer_district', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_district20', '甘', 'dealer_district20', 1, '', 22, '2013-08-23 10:35:48', '2013-08-23 10:36:01', 'dealer_district', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('contractmanager', '合同管理', 'contractmanager', 1, '', 2, '2013-09-11 17:00:09', '', 'downloadModel', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('document_type4', '学生证', 'document_type4', 1, '', 5, '2013-09-12 14:29:56', '', 'document_type', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('document_type5', '驾驶证', 'document_type5', 1, '', 6, '2013-09-12 14:30:14', '', 'document_type', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('ThirdInsurTpye6', '责任限额50万', 'ThirdInsurTpye6', 1, '', 7, '2013-09-09 19:38:46', '', 'ThirdInsurTpye', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('ThirdInsurTpye7', '责任限额100万', 'ThirdInsurTpye7', 1, '', 8, '2013-09-09 19:39:00', '', 'ThirdInsurTpye', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('ThirdInsurTpye8', '责任限额150万', 'ThirdInsurTpye8', 1, '', 9, '2013-09-09 19:39:13', '', 'ThirdInsurTpye', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.13', '推荐承诺函(经销商承担担保义务适用)', 'wordtempletypetwo.13', 1, '推荐承诺函(经销商承担担保义务适用)', 42, '2013-09-13 14:36:23', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.14', '租赁物明细', 'wordtempletypetwo.14', 1, '', 43, '2013-09-16 11:13:33', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo20', '建行卡扣下载', 'wordtempletypetwo20', 1, '建行卡扣下载', 53, '2013-10-16 15:29:19', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo19', '农行卡扣下载', 'wordtempletypetwo19', 1, '', 52, '2013-09-16 16:39:49', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.77', '合同-最高额保证担保函(经销商自然人股东适用)', 'wordtempletypetwo.77', 1, '', 66, '2013-10-09 14:29:42', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('cust_company_list01', '法人客户资料', 'cust_company_list01', 1, '法人客户资料', 2, '2013-10-25 10:52:08', '2013-10-25 11:56:02', 'cust_company_list', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.45', '合同-专用车企模式-租赁物回购合同(专用车企业适用)', 'wordtempletypetwo.45', 1, '', 2, '2013-10-10 10:50:55', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.80', '股东会成员签章样本', 'wordtempletypetwo.80', 1, '', 75, '2013-10-10 15:42:23', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.46', '资金发票计划开票', 'wordtempletypetwo.46', 1, '资金发票计划开票', 77, '2013-10-11 10:17:29', '2013-10-18 15:56:39', 'wordtempletypetwo', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('ThirdInsurTpye1', '责任限额5万', 'ThirdInsurTpye1', 1, '', 2, '2013-09-09 19:37:41', '', 'ThirdInsurTpye', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('ThirdInsurTpye2', '责任限额10万', 'ThirdInsurTpye2', 1, '', 3, '2013-09-09 19:37:53', '', 'ThirdInsurTpye', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('ThirdInsurTpye3', '责任限额15万', 'ThirdInsurTpye3', 1, '', 4, '2013-09-09 19:38:04', '', 'ThirdInsurTpye', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('ThirdInsurTpye4', '责任限额20万', 'ThirdInsurTpye4', 1, '', 5, '2013-09-09 19:38:15', '', 'ThirdInsurTpye', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('msggroup_user', '人员', 'msggroup_user', 1, '', 2, '2013-09-10 16:13:50', '', 'msggroup', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('msggroup_dept', '部门', 'msggroup_dept', 1, '', 4, '2013-09-10 16:14:43', '', 'msggroup', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('msggroup_roles', '角色', 'msggroup_roles', 1, '', 5, '2013-09-10 16:15:08', '', 'msggroup', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('dealer_mortgage2', '抵押登记', 'dealer_mortgage2', 1, '', 3, '2013-09-10 16:37:26', '', 'dealer_mortgage', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.17', '租金计划', 'wordtempletypetwo.17', 1, '', 50, '2013-09-16 12:10:42', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.22', '合同-还款协议', 'wordtempletypetwo.22', 1, '', 28, '2013-09-17 10:35:21', '2013-09-17 13:51:30', 'wordtempletypetwo', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypefirst.5', '发票管理', 'wordtempletypefirst.5', 1, '', 8, '2013-10-16 17:44:21', '', 'wordtempletypefirst', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('fundchargeconfirmreceipt', '资金实收开票收据', 'fundchargeconfirmreceipt', 1, '资金实收开票收据', 80, '2013-10-25 16:54:11', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.79', '合同-法定代表人证明书', 'wordtempletypetwo.79', 1, '', 74, '2013-10-10 13:31:13', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('document_type1', '身份证', 'document_type1', 1, '', 2, '2013-09-12 14:29:09', '', 'document_type', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('document_type2', '军官证', 'document_type2', 1, '', 3, '2013-09-12 14:29:31', '', 'document_type', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('document_type3', '士兵证', 'document_type3', 1, '', 4, '2013-09-12 14:29:43', '', 'document_type', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.28', '合同-保证担保函（自然人担保）', 'wordtempletypetwo.28', 1, '', 15, '2013-09-17 13:48:28', '2013-09-17 13:52:29', 'wordtempletypetwo', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.29', '合同-法定代表人授权委托书（非法定代表人签署合同时适用）', 'wordtempletypetwo.29', 1, '', 13, '2013-09-17 13:48:48', '2013-09-17 13:52:40', 'wordtempletypetwo', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.30', '合同-法定代表人证明书', 'wordtempletypetwo.30', 1, '', 11, '2013-09-17 13:49:05', '2013-09-17 13:52:48', 'wordtempletypetwo', 'Administrator', 'Administrator', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo21', '通用卡扣下载', 'wordtempletypetwo21', 1, '通用卡扣下载', 54, '2013-10-16 17:56:07', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('wordtempletypetwo.78', '合同-最高额保证担保函（经销商适用）', 'wordtempletypetwo.78', 1, '', 65, '2013-10-09 14:19:38', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

insert into t_dicts_datas (ID_, NAME_, CODE_, ENABLED_, DESCRIPTION_, POSITION_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, CHARACTER_, GRADE_LEVEL_, IS_MUST_)
values ('rentchargeinvoiceconfirm', '租金实收开票', 'rentchargeinvoiceconfirm', 1, '租金实收开票', 78, '2013-10-21 15:07:47', '', 'wordtempletypetwo', 'Administrator', '', '', '', 0);

/****初始化树转表*******/
insert into base_document_config (ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, ENABLED_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_, IS_STATISTIC_, WIDTH_, HEIGHT_)
values ('0', '资料文档根节点', 'DOCUMENT', '', 1, 1, '', '', '', '', '', 0, '', '');
/***初始化系统资源****/
insert into t_resources (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('0', '系统资源', 'code_0', '', 'base.gif', '', '', '', 0, 1, '2013-08-19', '', '', 'Administrator', '');
/***初始化操作权限****/
insert into t_actions (ID_, NAME_, CODE_, URL_, ICON_, ICONCLOSE_, ICONOPEN_, DESCRIPTION_, POSITION_, IS_RELATIVED_PATH_, CREATE_DATE_, MODIFY_DATE_, PID_, CREATOR_, MODIFICATOR_)
values ('0', '系统操作', 'code_0', '', 'base.gif', '', '', '', 0, 1, '', '', '', 'Administrator', '');
/***报表初始化****/
--INSERT INTO REPORT_REPORT(ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, REPORTTYPE) VALUES ('0', '报表', 'RPROOT', '报表', '0', 'FOLDER');

/***提交***/
commit;
