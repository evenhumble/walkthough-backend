package io.qkits.mock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import io.qdriven.core.dto.response.QResponse;
import io.qkits.mock.service.GenericMockMemStore;

/**
 * @author: patrick on 2019/11/27
 * @Description:
 */
@RestController("/mock")
public class MockController {

  @RestController
  @RequestMapping("/mock")
  public class GenericMockController {

    @Autowired
    private GenericMockMemStore mockMemStore;

    @RequestMapping("/{productName}/setup")
    @PostMapping
    public QResponse setupMock(@RequestBody Object setupResponse,
                               @PathVariable String productName,
                               HttpServletRequest request) {

      mockMemStore.store(productName, setupResponse);
      return QResponse.success();
    }

    /**
     * todo: change the path parameter mapping
     *
     * @param productName
     * @param request
     * @return
     */
    @RequestMapping(value = "/{productName}/getMock", method = {RequestMethod.GET,
                                                                RequestMethod.POST,
                                                                RequestMethod.PUT})
    public Object getMock(@PathVariable String productName,
                          HttpServletRequest request) {

      return mockMemStore.get(productName);
    }
  }
}
