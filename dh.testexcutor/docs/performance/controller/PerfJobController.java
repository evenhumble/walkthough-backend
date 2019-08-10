package com.ppdai.bdtp.admin.performance.controller;

import java.util.List;
import java.util.Map;

import com.ppdai.bdtp.admin.performance.domain.PerfJobDO;
import com.ppdai.bdtp.admin.performance.service.PerfJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ppdai.bdtp.admin.common.utils.PageUtils;
import com.ppdai.bdtp.admin.common.utils.Query;
import com.ppdai.bdtp.admin.common.dto.R;

/**
 * 
 * 
 * @author bdtester
 * @email bdtester@ppdai.com
 * @date 2018-08-28 19:18:07
 */
 
@Controller
@RequestMapping("/project/perfJob")
public class PerfJobController {
	@Autowired
	private PerfJobService perfJobService;
	
	@GetMapping()
	@RequiresPermissions("project:perfJob:perfJob")
	String PerfJob(){
	    return "project/perfJob/perfJob";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:perfJob:perfJob")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PerfJobDO> perfJobList = perfJobService.list(query);
		int total = perfJobService.count(query);
		PageUtils pageUtils = new PageUtils(perfJobList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:perfJob:add")
	String add(){
	    return "project/perfJob/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:perfJob:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PerfJobDO perfJob = perfJobService.get(id);
		model.addAttribute("perfJob", perfJob);
	    return "project/perfJob/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:perfJob:add")
	public R save( PerfJobDO perfJob){
		if(perfJobService.save(perfJob)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:perfJob:edit")
	public R update( PerfJobDO perfJob){
		perfJobService.update(perfJob);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:perfJob:remove")
	public R remove( Integer id){
		if(perfJobService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:perfJob:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		perfJobService.batchRemove(ids);
		return R.ok();
	}
	
}
