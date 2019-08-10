CREATE TABLE `perf_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(200) DEFAULT NULL,
  `perf_scenario` json DEFAULT NULL,
  `api_definition` json DEFAULT NULL,
  `jmeter_script_content` longtext,
  `job_status` varchar(45) DEFAULT NULL,
  `run_machines` varchar(200) DEFAULT NULL,
  `jmeter_file_location` varchar(200) DEFAULT NULL,
  `csv_file_location` varchar(200) DEFAULT NULL,
  `csv_config` varchar(200) DEFAULT NULL,
  `jmeter_param_config` json DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8