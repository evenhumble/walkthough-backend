CREATE TABLE `project` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) unsigned default 0,
  `project_type` tinyint(4) unsigned NOT NULL,
  `project_name` varchar(255) COLLATE utf8_bin NOT NULL,
   `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(100),
  `updated_by` varchar(100),
  `project_version` varchar(6) COLLATE utf8_bin NOT NULL DEFAULT '1.0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin



CREATE TABLE `connector`.`data_sources` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL COMMENT 'different data sources type, mysql,sql server,data',
  `connection_config` VARCHAR(300) NULL,
  `name` VARCHAR(45) NULL,
  `details` VARCHAR(45) NULL,
  `created_time` DATE NULL,
  `updated_time` DATE NULL,
  `created_by` VARCHAR(45) NULL,
  `updated_by` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
COMMENT = 'data_sources';


CREATE TABLE `api_definition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `api_request` json DEFAULT NULL,
  `api_response` json DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `details` varchar(300) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8