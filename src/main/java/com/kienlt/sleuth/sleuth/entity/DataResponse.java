package com.kienlt.sleuth.sleuth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse {

    public String code;
    public boolean status;
    public String traceId;
    public String data;
}
