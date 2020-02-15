package io.qkits.mock.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class GenericMockMemStore implements MockService{

    private ConcurrentHashMap<String, Object> mockStore = new ConcurrentHashMap<>();

    @Override
    public void store(String key, Object value) {
        this.mockStore.put(key, value);
    }

	@Override
    public Object get(String key) {
        return this.mockStore.get(key);
    }

}