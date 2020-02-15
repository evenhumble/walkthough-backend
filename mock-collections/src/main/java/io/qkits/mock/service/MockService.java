package io.qkits.mock.service;

import io.qdriven.core.dto.response.QResponse;

/**
 * @author: patrick on 2019/11/27
 * @Description:
 */
public interface MockService {
	public void store(String key,Object value);
	public Object get(String mockKey);
}
