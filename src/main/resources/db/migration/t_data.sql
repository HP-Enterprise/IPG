
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_data`
-- ----------------------------
DROP TABLE IF EXISTS t_data;
CREATE TABLE t_data (
  id int(11) NOT NULL AUTO_INCREMENT,
  client varchar(50) DEFAULT NULL COMMENT 'from',
  bytes varchar(500) DEFAULT NULL COMMENT 'bytes',
  create_by varchar(50) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  last_modified_by varchar(50) DEFAULT NULL,
  last_modified_date datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

