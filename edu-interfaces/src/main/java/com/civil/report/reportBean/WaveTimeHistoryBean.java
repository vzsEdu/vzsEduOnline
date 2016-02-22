package com.civil.report.reportBean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by leodavinci on 2015/5/17.
 */
@Data
@AllArgsConstructor
public class WaveTimeHistoryBean {

    private String wave;
    private Integer angel;
    private Integer floor;
    private BigDecimal shear;


    @Override
    public String toString() {
        return "WaveTimeHistoryBean{" +
                "wave='" + wave + '\'' +
                ", angel=" + angel +
                ", floor=" + floor +
                ", shear=" + shear +
                '}';
    }
}
