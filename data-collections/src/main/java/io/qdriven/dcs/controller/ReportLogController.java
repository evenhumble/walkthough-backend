package io.qdriven.dcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import io.qdriven.dcs.service.ReportDataCollectionService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: patrick on 2019/11/8
 * @Description:
 */
@RestController
@Slf4j
public class ReportLogController {

	@Autowired
	private ReportDataCollectionService rdcService;
	@PostMapping
	public ResponseEntity recordLog(@RequestBody Map reportData) {
		log.info("incoming data :"+reportData);
		rdcService.saveReportData(reportData);
		return ResponseEntity.ok("collected!");
	}
}
