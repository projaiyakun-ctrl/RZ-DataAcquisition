package com.rz.dataacquisition.service;

import com.rz.dataacquisition.dto.SystemInfoDto;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class SystemInfoService {

    public SystemInfoDto getSystemInfo() {
        return new SystemInfoDto(
                "RZ Data Acquisition Platform",
                "0.0.1-SNAPSHOT",
                OffsetDateTime.now().toString()
        );
    }
}
