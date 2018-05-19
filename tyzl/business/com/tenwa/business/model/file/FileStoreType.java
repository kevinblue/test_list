
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model.file
 * 文件名：         FileStoreType.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-31-上午11:23:57
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.model.file;


 /**
 * 类名称：     FileStoreType
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-3-31 上午11:23:57
 * 修改备注：
 * @version 1.0.0
 **/

public enum FileStoreType {
    LOCAL("LOCAL"), 
    FTP("FTP"), 
    SMB("SMB"); 

    private String optname; 

    FileStoreType(String optname) { 
       this.optname = optname; 
    } 

    public String getOptname() { 
      return optname; 
    } 
}
