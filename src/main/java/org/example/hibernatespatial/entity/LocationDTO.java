package org.example.hibernatespatial.entity;

import lombok.Getter;

@Getter
public class LocationDTO {

    private final Double latitude;
    private final Double longitude;

    public LocationDTO(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
