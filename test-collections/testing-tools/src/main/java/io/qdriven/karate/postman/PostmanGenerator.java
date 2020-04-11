package io.qdriven.karate.postman;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.intuit.karate.Json;
import com.intuit.karate.JsonUtils;
import com.intuit.karate.StringUtils;
import com.intuit.karate.formats.postman.PostmanConverter;
import com.intuit.karate.formats.postman.PostmanItem;
import com.intuit.karate.formats.postman.PostmanUtils;
import io.netty.karate.util.internal.ResourcesUtil;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

public class PostmanGenerator {

    public static void main(String[] args) throws IOException {
        PostmanConverter converter = new PostmanConverter();
        List<String> jsonList = FileUtil.readLines("postman-v2.json",Charset.defaultCharset());
        String jsonStr = StringUtils.join(jsonList,' ');
        System.out.println(jsonStr);
//        PostmanUtils.readPostmanJson();
       List<PostmanItem> postmanItems =  PostmanUtils.readPostmanJson(jsonStr);
        for (PostmanItem postmanItem : postmanItems) {
            System.out.println(postmanItem);
        }

        String karateFeatures = PostmanUtils.toKarateFeature(postmanItems);
        System.out.println(karateFeatures);
        String postManFile = "/Users/patrick/workspace/works/focus/advanced/walkthrough/testing-tools/src/main/resources/postman-v2.json";
        converter.convert(postManFile,"bitex");
    }
}
