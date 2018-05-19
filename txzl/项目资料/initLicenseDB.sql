/***oracle系统初始化必须执行***/
ALTER TABLE JBPM4_DEPLOYMENT MODIFY NAME_ VARCHAR2(300);
ALTER TABLE JBPM4_LOB        MODIFY NAME_ VARCHAR2(300);
drop table T_LICENSE;
-- Create table
create table T_LICENSE
(
  ID_              VARCHAR2(32 CHAR) not null,
  PRIVATE_KEY_     BLOB,
  GRANT_INFO_      BLOB
);
alter table T_LICENSE add primary key (ID_);
insert into T_license(id_) values('07be054d91ec452e8f0f30383bb3e0fa');
commit;

