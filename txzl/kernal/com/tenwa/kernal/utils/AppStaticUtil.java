package com.tenwa.kernal.utils;

/**
 * 常量字典
 * @author Jason
 * 2013-1-16
 */
public class AppStaticUtil {
	//  如有新增数据要与 base_contract_status保持一致
	
	/**项目立项 */
	public static final Integer PROJ_APPROVAL =  1;

	/**立項否决 */
	public static final Integer PROJ_APPROVAL_2 =  2;
	/**项目信审(审批通过) */
	public static final Integer PROJ_CREDIT = 11;
	/**项目复议 */
	public static final Integer PROJ_REPEAT = 12;
	/** 项目否决 */
	public static final Integer PROJ_DENY = 104;
	/** 项目撤销，项目执行中止 */
	public static final Integer PROJ_CANCEL = 101;
	/** 项目结束*/
	public static final Integer PROJ_END = 105;
	/**项目信审(审批中) */
	public static final Integer PROJ_AUDIT = 6;

	/**合同审批 */
	public static final Integer CONTRACT_APPROVAL = 21;
	/**合同起租 */
	public static final Integer CONTRACT_START = 31;
	/**中途终止 提前结清**/
	public static final Integer CONTRACT_TERMINATE = 100;
	/**合同撤销 ，合同执行中止*/
	public static final Integer CONTRACT_CANCEL = 102;
	/**合同结束 */
	public static final Integer CONTRACT_END = 103;
	/*合同出具*/
	public static final Integer CONTRACT_PRESIGN = 15;
}
