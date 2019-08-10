package io.hedwig.dh.charts.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthyCheckController {

    @RequestMapping(value = "/ping",method = RequestMethod.GET)
    @ResponseBody
    public String ping() {
        return "pong";
    }
}