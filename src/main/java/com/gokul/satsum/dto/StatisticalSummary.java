package com.gokul.satsum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticalSummary {

    private String mean;
    private String min;
    private String max;
}
