
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_client_log`
-- ----------------------------
DROP TABLE IF EXISTS t_client_log;
CREATE TABLE t_client_log (
  id int(11) NOT NULL AUTO_INCREMENT,
  client varchar(50) DEFAULT NULL COMMENT 'from',
  action varchar(500) DEFAULT NULL COMMENT 'action',
  create_by varchar(50) DEFAULT NULL,
  create_date date DEFAULT NULL,
  last_modified_by varchar(50) DEFAULT NULL,
  last_modified_date date DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

