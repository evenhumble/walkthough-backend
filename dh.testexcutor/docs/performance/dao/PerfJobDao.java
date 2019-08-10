package com.ppdai.bdtp.admin.performance.dao;


import java.util.List;
import java.util.Map;

import com.ppdai.bdtp.admin.performance.domain.PerfJobDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author bdtester
 * @email bdtester@ppdai.com
 * @date 2018-08-28 19:18:07
 */
@Mapper
public interface PerfJobDao {

	PerfJobDO get(Integer id);
	
	List<PerfJobDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerfJobDO perfJob);
	
	int update(PerfJobDO perfJob);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
