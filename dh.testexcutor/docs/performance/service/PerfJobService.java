package com.ppdai.bdtp.admin.performance.service;

import com.ppdai.bdtp.admin.performance.domain.PerfJobDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author bdtester
 * @email bdtester@ppdai.com
 * @date 2018-08-28 19:18:07
 */
public interface PerfJobService {
	
	PerfJobDO get(Integer id);
	
	List<PerfJobDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerfJobDO perfJob);
	
	int update(PerfJobDO perfJob);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
