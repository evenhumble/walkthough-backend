package io.hedwig.modules.esper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.hedwig.modules.esper.client.EsperSender;
import io.hedwig.modules.esper.entity.MarketData;

/**
 * 1. author: patrick
 */
@Controller
@RequestMapping("/epl")
public class EPLController {

    private static Logger logger = LoggerFactory.getLogger(EPLController.class);
    @Autowired
    EsperSender sender;

    @RequestMapping(value = "/select",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity selectEval(@RequestBody MarketData data) {
        logger.info("start send epl.....");
        sender.send(data);
        return ResponseEntity.ok("ok!");
    }
}
