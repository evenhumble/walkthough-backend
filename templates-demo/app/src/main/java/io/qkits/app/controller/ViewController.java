/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.qkits.app.controller;

import com.github.sgoeschl.freemarker.demo.app.form.UserForm;
import com.github.sgoeschl.freemarker.sample.model.User;
import com.github.sgoeschl.freemarker.sample.service.RestService;
import com.github.sgoeschl.freemarker.sample.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static org.springframework.http.HttpMethod.GET;

@Controller
public class ViewController {

    private final RestService restService;

    public ViewController(RestService restService) {
        this.restService = restService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/ui/exception")
    public String exception() {
        throw new RuntimeException("You clicked on the 'Exception' link to simulate an error ...");
    }

    @GetMapping("/ui/actuator")
    public String logfile(
            Map<String, Object> model,
            @RequestParam("endpoint") String endpoint) {
        final String url = "http://localhost:8080/actuator/" + endpoint;
        final Pair<HttpEntity<String>, ResponseEntity<String>> pair;
        pair = restService.exchange(url, GET);

        final HttpEntity<String> request = pair.getLeft();
        final ResponseEntity<String> response = pair.getRight();
        final HttpStatus statusCode = response.getStatusCode();
        final String title = ("Invoke Spring Boot Actuator Endpoint");
        final String subtitle = format("HTTP GET %s - %s - %s", url, statusCode, response.getHeaders().get("Date"));

        model.put("title", title);
        model.put("subtitle", subtitle);
        model.put("request", request);
        model.put("response", response);
        model.put("body", response.getBody());

        return "actuator";
    }

    @GetMapping("/ui/user-form")
    public String requestUserForm(
            Map<String, Object> model) {
        model.put("user", new UserForm());
        model.put("groups", createUserGroups());
        return "user-form";
    }

    @PostMapping("/ui/user-form")
    public String createUser(
            Map<String, Object> model,
            @Valid @ModelAttribute("user") UserForm userForm,
            BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                final ObjectError error = bindingResult.getAllErrors().get(0);
                throw new IllegalArgumentException(error.getDefaultMessage());
            }
            final User user = new User(
                    userForm.getId(),
                    userForm.getName(),
                    userForm.getGroup());
            model.put("user", user);
            return "user";
        } catch (RuntimeException e) {
            model.put("errorMessage", e.getMessage());
            model.put("groups", createUserGroups());
            return "user-form";
        }
    }

    private Map<String, String> createUserGroups() {
        final HashMap<String, String> result = new HashMap<>();
        result.put("ADMIN", "Adminstrator");
        result.put("USER", "Standard User");
        return result;
    }
}
