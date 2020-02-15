package io.qdriven.dcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import io.qdriven.dcs.domain.ReportDataCollection;
import io.qdriven.dcs.repository.ReportDataCollectionRepo;
import io.qdriven.dcs.service.exception.ObjectNotFoundException;

/**
 * @author: patrick on 2019/11/8
 * @Description:
 */
@Service
public class ReportDataCollectionService {

	@Autowired
	private ReportDataCollectionRepo rdcRepo;

	public ReportDataCollection findById(String id){
		Optional<ReportDataCollection> obj = rdcRepo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(id + "is not found"));
	}

	public ReportDataCollection saveReportData(Map reportData){
		ReportDataCollection rdc = new ReportDataCollection();
		rdc.setData(reportData);
		rdcRepo.save(rdc);
		return rdc;
	}
}
