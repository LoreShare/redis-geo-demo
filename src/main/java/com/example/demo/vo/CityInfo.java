package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityInfo {

    /** 城市 */
    private String city;

    /** 经度 */
    private Double longitude;

    /** 纬度 */
    private Double latitude;
}