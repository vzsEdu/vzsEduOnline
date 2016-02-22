CREATE DATABASE civil DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;


use mysql;
CREATE USER 'byao'@'%'IDENTIFIED BY 'welcome2civilworld';
CREATE USER 'byao'@'localhost'IDENTIFIED BY 'welcome2civilworld';
INSERT INTO db (HOST,Db,USER,Select_priv,Insert_priv,Update_priv,Delete_priv,Create_priv,Drop_priv,Index_priv, Alter_priv)
 VALUES('%','civil','byao','Y','Y','Y','Y','Y','Y','Y','Y');
FLUSH PRIVILEGES;


