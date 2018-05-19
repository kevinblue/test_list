--创建临时表空间  
create temporary tablespace tls_temp  
tempfile 'D:\oracle\product\10.2.0\oradata\tls\tls_temp.dbf' 
size 50m  
autoextend on  
next 50m maxsize 20480m  
extent management local;  
--创建数据表空间  
create tablespace tls_data  
logging  
datafile 'D:\oracle\product\10.2.0\oradata\tls\tls_data.dbf' 
size 50m  
autoextend on  
next 50m maxsize 20480m  
extent management local;  
--创建用户并指定表空间  
create user tls identified by "123"  
default tablespace tls_data  
temporary tablespace tls_temp;  
--给用户授予权限  
grant dba to tls;