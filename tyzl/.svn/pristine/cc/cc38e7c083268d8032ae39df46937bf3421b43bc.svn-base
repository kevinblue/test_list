package com.reckon.convert;

import java.util.Map;

import com.reckon.bean.ConditionBean;
import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.reckon.entity.contract.reckon.condition.ContractConditionTemp;




public interface ConvertMapToBeanService {
	 
	/**
	 * 
	 * <p>该方法是原本就存在只是做了改写</p>
	 * <p>把传入参数的map对象转化成实体bean数据:</p>
	 * <p>重构的此处传入的是老的租金测算中的商务条件实体bean用于和老的实体类做对接</p>
	 * @author menghaiyang
	 * @param conditionBean 交易结构临时表，正式表数据传值载体
	 * @param modelData 前端租金测算条件构建的JSON字符串转换的MAP集合
	 * @return
	 * @throws Exception
	 */
	public ConditionBean convertContionBean(ConditionBean conditionBean,Map<String,String> modelData)  throws Exception;
	
	
	
	public ContractCondition convertContionBean(ContractCondition conditionBean,Map<String,String> modelData)  throws Exception;
	
	/**
	 * 将condition转换为ConditionBean对象，实现在ConditionBean下作租金测算
	 * 没有的属相无法转换，按ConditionBean中默认值赋值
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public ConditionBean convertContionBean(ContractConditionTemp condition)  throws Exception;
	
	/**
	 * 
	 * <p>我不管其它方法是如何使用去读取对象get、set属性去把map中的值转换为对象操作。
	 * 这个方法永远使用最原始的一个个取数据一个个注入的方式去处理！
	 * 原因：ConditionBean理论上它只是一个大杂烩形式的Bean对象，而不是一个纯粹的ENTITY！
	 * 这个Bean中包含了太多的各种租金测算需要用到的数据，使用控制反转去转换对象经常会因为一些对象无法注入对应的属性进而报错导致后续算法无法运行（有些值实际上后面代码不使用的，但是这里就报错终止了）！
	 * 这严重影响客户的接受度！(实际上我个人认为是最底层的控制反转把MAP转换成对象的方法存在缺陷！目前没空看!)
	 * 因此这里挨个的取值挨个的做空处理，然后再挨个的注入，会为后面的代码节省很多排错的判断!
	 * 注意：因此租金测算增加了字段，那么这里请按照最原始的方式去增加取值和注入值得代码，并且写好注释！
	 * </p>
	 * <p>把传入参数的map对象转化成实体bean数据:</p>
	 * <p>重构的此处传入的是老的租金测算中的商务条件实体bean用于和老的实体类做对接</p>
	 * @author 谢永伦
	 * @param conditionBean
	 * @param modelData
	 * @return
	 * @throws Exception
	 */
	public ConditionBean convertContionBeanByOriginal(ConditionBean conditionBean,Map<String, String> modelData)  throws Exception;
}
