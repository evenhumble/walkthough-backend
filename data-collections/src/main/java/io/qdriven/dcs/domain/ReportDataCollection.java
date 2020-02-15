package io.qdriven.dcs.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

import lombok.Data;

/**
 * @author: patrick on 2019/11/8
 * @Description:
 */
@Document(collection = "report_data")
@Data
public class ReportDataCollection implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private Map data;
	private LocalDate createTime = LocalDate.now();
}
