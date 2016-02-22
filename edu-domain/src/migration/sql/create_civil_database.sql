CREATE DATABASE eduOnline DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;


use mysql;
CREATE USER 'eduAdmin'@'%'IDENTIFIED BY 'welcome2eduOnlineworld';
CREATE USER 'eduAdmin'@'localhost'IDENTIFIED BY 'welcome2eduOnlineworld';
INSERT INTO db (HOST,Db,USER,Select_priv,Insert_priv,Update_priv,Delete_priv,Create_priv,Drop_priv,Index_priv, Alter_priv)
 VALUES('%','eduOnline','eduAdmin','Y','Y','Y','Y','Y','Y','Y','Y');
FLUSH PRIVILEGES;


