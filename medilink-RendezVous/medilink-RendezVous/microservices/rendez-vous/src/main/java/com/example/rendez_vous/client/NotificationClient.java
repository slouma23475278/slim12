package com.example.rendez_vous.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "notification")
public interface NotificationClient {
    @GetMapping(value = "/notification/ping", produces = MediaType.TEXT_PLAIN_VALUE)
    String ping();
}
