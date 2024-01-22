package org.example.hibernatespatial.service;

public record PointResponse(
    Long id,
    String name,
    double x,
    double y
) {
    public static PointResponse fromDto(PointDto dto) {
        return new PointResponse(dto.id(), dto.name(), dto.x(), dto.y());
    }
}
