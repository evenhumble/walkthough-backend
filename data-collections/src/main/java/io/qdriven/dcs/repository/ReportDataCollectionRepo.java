package io.qdriven.dcs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.qdriven.dcs.domain.ReportDataCollection;

/**
 * @author: patrick on 2019/11/8
 * @Description:
 */
@Repository
public interface ReportDataCollectionRepo extends MongoRepository<ReportDataCollection,String> {

}
