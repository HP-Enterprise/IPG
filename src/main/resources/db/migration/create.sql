DROP TABLE IF EXISTS ip_command;
CREATE TABLE ip_command (
  id int(11) NOT NULL AUTO_INCREMENT,
  event_id int(11) NOT NULL,
  command_type SMALLINT  NOT NULL COMMENT '指令类别,对应agent type',
  num int(10)  NOT NULL COMMENT '指令编号',
  action varchar(100)  NOT NULL COMMENT '操作',
  param varchar(200)  NOT NULL COMMENT '参数',
  command_status SMALLINT  NOT NULL COMMENT '指令状态： -3 正在发送 -2 已发 -1 待发送 0 失败 1 成功 2 参数错误',
  action_date datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='控制指令表';
INSERT INTO ip_command VALUES ('1', '1443151834','1', '100', '123','{100,200,300}', '-1', '2016-05-18 11:09:05');


DROP TABLE IF EXISTS ip_data;
CREATE TABLE ip_data (
  id int(11) NOT NULL AUTO_INCREMENT,
  client varchar(50) DEFAULT NULL COMMENT 'from',
  bytes varchar(5000) DEFAULT NULL COMMENT 'bytes',
  action_date datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='报文日志表';

DROP TABLE IF EXISTS ip_client_log;
CREATE TABLE ip_client_log (
  id int(11) NOT NULL AUTO_INCREMENT,
  client varchar(50) DEFAULT NULL COMMENT 'from',
  action varchar(500) DEFAULT NULL COMMENT 'action',
  action_date datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='agent连接日志表';

DROP TABLE IF EXISTS ip_device;
create table ip_device(
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  device_name varchar(100) not null COMMENT '设备名称',
  device_sn varchar(100) not null COMMENT '编号',
  device_type int not null COMMENT '设备类型' ,
  device_locate varchar(500) not null COMMENT '设备位置',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础设备表';

DROP TABLE IF EXISTS ip_device_status;
create table ip_device_status(
id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
device_id int(11) NOT NULL COMMENT '设备id',
device_name	varchar(100) COMMENT '设备名称',
`device_code` VARCHAR(100) NULL DEFAULT NULL COMMENT '设备编号',
`device_location` VARCHAR(100) NULL DEFAULT NULL COMMENT '设备位置',
device_para_name varchar(100) NOT NULL COMMENT '设备参数名称',
device_para_value varchar(100) NOT NULL COMMENT '设备参数值',
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备状态表';

DROP TABLE IF EXISTS ip_device_status_his;
create table ip_device_status_his(
id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
device_id int(11) not null COMMENT '设备id',
device_name	varchar(100) COMMENT '设备名称',
`device_code` VARCHAR(100) NULL DEFAULT NULL COMMENT '设备编号',
`device_location` VARCHAR(100) NULL DEFAULT NULL COMMENT '设备位置',
device_para_name varchar(100) not null COMMENT '设备参数名称',
device_para_value varchar(100) not null COMMENT '设备参数值',
collect_date datetime not null COMMENT '采集时间',
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备状态历史表';

DROP TABLE IF EXISTS ip_agent;
create table ip_agent(
id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
agent_name varchar(100) not null COMMENT '代理名称',
agent_type smallint not null COMMENT '代理类别',
num int(10) not null COMMENT '代理编号',
ip varchar(20) not null COMMENT '代理ip',
port varchar(10) not null COMMENT '代理端口',
contable int(10) not null COMMENT '代理采集周期 * * * * *',
con_protocol int not null COMMENT '代理采集协议',
description varchar(200) not null COMMENT '说明',
PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='agent代理表';
INSERT INTO ip_agent VALUES ('1', 'A000',1,100, '127.0.0.1', '8899', '1000', '8','代理说明信息');

DROP TABLE IF EXISTS ip_alarm;
create table ip_alarm(
id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
device_id int(11) not null COMMENT '设备id',
alarm_device_name	varchar(100) COMMENT '告警设备名称',
alarm_title varchar(200) COMMENT '告警标题',
alarm_content varchar(500) COMMENT '告警正文',
alarm_level int COMMENT '告警级别 1 严重 2 重要 3 一般  4通知',
alarm_date datetime NOT NULL COMMENT '告警时间',
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='告警表';

DROP TABLE IF EXISTS ip_alarm_his;
create table ip_alarm_his(
id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
device_id int(11) not null COMMENT '设备id',
alarm_device_name	varchar(100) COMMENT '告警设备名称',
alarm_title varchar(200) COMMENT '告警标题',
alarm_content varchar(500) COMMENT '告警正文',
alarm_level int COMMENT '告警级别 1 严重 2 重要 3 一般  4通知',
alarm_date  datetime NOT NULL COMMENT '告警时间',
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='告警历史表';

DROP TABLE IF EXISTS ip_alarm_conf;
create table ip_alarm_conf(
id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
device_id int(11) not null COMMENT '设备id',
device_name varchar(100) COMMENT '告警设备名称',
device_para_name varchar(100) not null COMMENT '设备参数名称',
device_para_value varchar(100) not null COMMENT '设备参数值',
alarm_title varchar(200) COMMENT '告警标题',
alarm_content varchar(500) COMMENT '告警正文',
alarm_level int COMMENT '告警级别 1 严重 2 重要 3 一般  4通知',
alarm_date  datetime COMMENT '告警时间',
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='告警配置表';