package org.example.hibernatespatial.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.hibernatespatial.entity.Direction;
import org.example.hibernatespatial.entity.LocationDTO;
import org.example.hibernatespatial.repository.LocationRepository;
import org.example.hibernatespatial.util.GeometryUtil;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocationService2 {

    private final LocationRepository locationRepository;
    private final GeometryFactory geometryFactory4326;

    public List<PointResponse> getAllPoint(Double latitude, Double longitude,
        double distance) {
        LocationDTO northEast = GeometryUtil
            .calculate(latitude, longitude, distance, Direction.NORTHEAST.getBearing());
        LocationDTO southWest = GeometryUtil
            .calculate(latitude, longitude, distance, Direction.SOUTHWEST.getBearing());

        List<PointDto> dtos = locationRepository.findAllPoint(
            getCoordinate(northEast, southWest));

        return dtos.stream()
            .map(PointResponse::fromDto)
            .toList();
    }

    private Polygon getCoordinate(LocationDTO northEast, LocationDTO southWest) {

        double maxX = northEast.getLatitude();
        double maxY = northEast.getLongitude();
        double minX = southWest.getLatitude();
        double minY = southWest.getLongitude();

        return geometryFactory4326.createPolygon(new Coordinate[]{
            new Coordinate(minX, minY),
            new Coordinate(minX, maxY),
            new Coordinate(maxX, minY),
            new Coordinate(maxX, maxY),
            new Coordinate(minX, minY)
        });
    }

}
