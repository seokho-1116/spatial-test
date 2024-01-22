package org.example.hibernatespatial.service;


public record PointDto(
    Long id,
    String name,
    double x,
    double y
) {

}
