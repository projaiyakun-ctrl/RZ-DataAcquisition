package com.rz.dataacquisition.controller;

import com.rz.dataacquisition.dto.SystemInfoDto;
import com.rz.dataacquisition.service.SystemInfoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api/system")
public class SystemController {

    private final SystemInfoService systemInfoService;

    public SystemController(SystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }

    @GetMapping("/info")
    public SystemInfoDto info() {
        return systemInfoService.getSystemInfo();
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter stream() {
        SseEmitter emitter = new SseEmitter(0L);
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    emitter.send(SseEmitter.event()
                            .name("heartbeat")
                            .data("collector-heartbeat-" + i + " @ " + OffsetDateTime.now()));
                    Thread.sleep(1000);
                }
                emitter.complete();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                emitter.completeWithError(ex);
            } catch (IOException ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }
}
