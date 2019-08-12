package io.qkits.rediskv.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import io.qkits.rediskv.repo.RedisDemoRepo;

/**
 * @author: patrick on 2019-08-11
 * @Description:
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {

  @Autowired
  private RedisDemoRepo repo;

  @GetMapping(value = "/{key}")
  public ResponseEntity getValue(@PathVariable String key) {

    return ResponseEntity.ok(repo.get(key));
  }

  @PostMapping
  public ResponseEntity getValue(@RequestBody Map<String, String> req) {

    for (Map.Entry<String, String> entry : req.entrySet()) {
      repo.set(entry.getKey(), entry.getValue());
    }
    return ResponseEntity.ok("SUCCESSFUL");
  }

}
