package com.ppdai.bdtp.admin.performance.service.impl;

import com.ppdai.bdtp.admin.performance.dao.PerfJobDao;
import com.ppdai.bdtp.admin.performance.domain.PerfJobDO;
import com.ppdai.bdtp.admin.performance.service.PerfJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PerfJobServiceImpl implements PerfJobService {
	@Autowired
	private PerfJobDao perfJobDao;
	
	@Override
	public PerfJobDO get(Integer id){
		return perfJobDao.get(id);
	}
	
	@Override
	public List<PerfJobDO> list(Map<String, Object> map){
		return perfJobDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return perfJobDao.count(map);
	}
	
	@Override
	public int save(PerfJobDO perfJob){
		return perfJobDao.save(perfJob);
	}
	
	@Override
	public int update(PerfJobDO perfJob){
		return perfJobDao.update(perfJob);
	}
	
	@Override
	public int remove(Integer id){
		return perfJobDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return perfJobDao.batchRemove(ids);
	}
	
}
