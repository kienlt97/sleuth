package com.kienlt.sleuth.sleuth.controller;

import com.kienlt.sleuth.sleuth.entity.DataResponse;
import com.kienlt.sleuth.sleuth.service.ELKService;
import com.kienlt.sleuth.sleuth.service.RestService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.brave.bridge.BraveTracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@RestController
public class SleuthController {

    @GetMapping("/")
    public String helloSleuth() {
        log.info("Hello Sleuth");
        return "success";
    }

    @Autowired
    private Tracer tracer;

    private final ELKService service;

    private final RestService restService;

    public SleuthController(ELKService service, RestService restService) {
        this.service = service;
        this.restService = restService;
    }

    @GetMapping(value = "/hello")
    public DataResponse helloWorld() {
        log.info("Start hello : {}", LocalDateTime.now());
        String response = " Hello World! " + new Date();
        log.info("response: {}",DataResponse.builder().code("success").status(true).traceId(tracer.currentSpan().context().traceId()).data(response).build());

        return DataResponse.builder().code("success").status(true).traceId(tracer.currentSpan().context().traceId()).data(response).build();
    }

    @GetMapping(value = "/Food-Details")
    public JSONArray foodDetails() {
        log.info("Inside Food Detail Function");
        return service.getAllFoodDetails();
    }
}