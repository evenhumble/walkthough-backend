package io.hedwig.dh.charts.web;

import io.hedwig.dh.charts.service.DBLineageService;
import io.hedwig.dh.charts.utils.MockUtils;
import io.hedwig.dh.charts.web.dto.DataLineageDTO;
import io.hedwig.dh.charts.web.dto.validator.RequestValidator;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Controller
public class DataLineageController {

    @Autowired
    private DBLineageService dbLineageService;
    @Autowired
    private RequestValidator requestValidators;

    @ResponseBody
    @GetMapping(value = "/db/{db}/{table}")
    public DataLineageDTO getLineageData(@PathVariable String db,
                                         @PathVariable String table){
        requestValidators.requireNotNull(db,table);
        return dbLineageService.getDataLineageService(db,table);
    }

    @GetMapping({"/datalineage"})
    public String directToDatalineage(Model mode){

        return "index";
    }

    @GetMapping({"/tree"})
    public String tree(Model mode){

        return "tree";
    }



    @ResponseBody
    @GetMapping(value = "/mock")
    public DataLineageDTO getMock(){

        return dbLineageService.getMockDL("mock","mockTable");
    }

    @ResponseBody
    @GetMapping(value = "/flare",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DataLineageDTO getFlare() throws IOException {

        return MockUtils.mockDataLineageDTO();
//        return MockUtils.flareJSON;
    }

}
