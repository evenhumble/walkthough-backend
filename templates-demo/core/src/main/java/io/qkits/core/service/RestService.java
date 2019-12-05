package io.qkits.core.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import io.qkits.core.util.Pair;

public interface RestService {

    Pair<HttpEntity<String>, ResponseEntity<String>> exchange(String url, HttpMethod method);

    String getLogfile();
}
