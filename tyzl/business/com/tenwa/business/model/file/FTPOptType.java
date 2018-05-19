
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model.file
 * 文件名：         FTPOptType.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-29-下午04:48:42
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.model.file;


 /**
 * 类名称：     FTPOptType
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-3-29 下午04:48:42
 * 修改备注：
 * @version 1.0.0
 **/
/** 
* FTP操作类型 
* 
*/ 
public enum FTPOptType { 
        UP("上传"), 
        DOWN("下载"), 
        LIST("浏览"), 
        DELFILE("删除文件"), 
        DELFOD("删除文件夹"), 
        RENAME("上传"); 

        private String optname; 

        FTPOptType(String optname) { 
                this.optname = optname; 
        } 

        public String getOptname() { 
                return optname; 
        } 
}
