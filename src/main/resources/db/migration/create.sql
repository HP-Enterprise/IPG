DROP TABLE IF EXISTS t_data;
CREATE TABLE t_data (
  id int(11) NOT NULL AUTO_INCREMENT,
  client varchar(50) DEFAULT NULL COMMENT 'from',
  bytes varchar(5000) DEFAULT NULL COMMENT 'bytes',
  create_by varchar(50) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  last_modified_by varchar(50) DEFAULT NULL,
  last_modified_date datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t_client_log;
CREATE TABLE t_client_log (
  id int(11) NOT NULL AUTO_INCREMENT,
  client varchar(50) DEFAULT NULL COMMENT 'from',
  action varchar(500) DEFAULT NULL COMMENT 'action',
  create_by varchar(50) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  last_modified_by varchar(50) DEFAULT NULL,
  last_modified_date datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
device_para_name varchar(100) NOT NULL COMMENT '设备参数名称',
device_para_value varchar(100) NOT NULL COMMENT '设备参数值',
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备状态表';

DROP TABLE IF EXISTS ip_device_status_his;
create table ip_device_status_his(
id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
device_id int(11) not null COMMENT '设备id',
device_para_name varchar(100) not null COMMENT '设备参数名称',
device_para_value varchar(100) not null COMMENT '设备参数值',
collect_date datetime not null COMMENT '采集时间',
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备状态历史表';

DROP TABLE IF EXISTS ip_agent;
create table ip_agent(
id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
agent_name varchar(100) not null COMMENT '代理名称',
ip varchar(20) not null COMMENT '代理ip',
port varchar(10) not null COMMENT '代理端口',
contable varchar(50) not null COMMENT '代理采集周期 * * * * *',
PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='agent代理表';

DROP TABLE IF EXISTS ip_alarm;
create table ip_alarm(
id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
alarm_devicename	varchar(100) COMMENT '告警设备名称',
alarm_title varchar(200) COMMENT '告警标题',
alarm_content varchar(500) COMMENT '告警正文',
alarm_level int COMMENT '告警级别 1 严重 2 重要 3 一般  4通知',
alarm_time datetime NOT NULL COMMENT '告警时间',
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='告警表';

DROP TABLE IF EXISTS ip_alarm_his;
create table ip_alarm_his(
id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
alarm_device_name	varchar(100) COMMENT '告警设备名称',
alarm_title varchar(200) COMMENT '告警标题',
alarm_content varchar(500) COMMENT '告警正文',
alarm_level int COMMENT '告警级别 1 严重 2 重要 3 一般  4通知',
alarm_time  datetime NOT NULL COMMENT '告警时间',
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='告警历史表';

DROP TABLE IF EXISTS ip_alarm_conf;
create table ip_alarm_conf(
id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
device_name varchar(100) COMMENT '告警设备名称',
device_para_name varchar(100) not null COMMENT '设备参数名称',
device_para_value varchar(100) not null COMMENT '设备参数值',
alarm_title varchar(200) COMMENT '告警标题',
alarm_content varchar(500) COMMENT '告警正文',
alarm_level int COMMENT '告警级别 1 严重 2 重要 3 一般  4通知',
alarm_time  datetime COMMENT '告警时间',
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='告警配置表';