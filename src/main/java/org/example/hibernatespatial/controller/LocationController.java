package org.example.hibernatespatial.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.hibernatespatial.service.LocationService;
import org.example.hibernatespatial.service.LocationService2;
import org.example.hibernatespatial.service.PointResponse;
import org.opengis.referencing.operation.TransformException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final LocationService2 locationService2;

    @GetMapping("/srid-4326")
    public ResponseEntity<List<PointResponse>> getAllPointIn1kmWith4326(Double x, Double y,
        int distance) {
        return ResponseEntity.ok(locationService.getAllPointIn1kmWith4326(x, y, distance));
    }

    @GetMapping("/srid-3857")
    public ResponseEntity<List<PointResponse>> getAllPointIn1kmWith3857(Double x, Double y,
        int distance)
        throws TransformException {
        return ResponseEntity.ok(locationService.getAllPointIn1kmWith3857(x, y, distance));
    }

    @GetMapping("/test")
    public ResponseEntity<List<PointResponse>> getAllPoint(Double x, Double y, int distance) {
        return ResponseEntity.ok(locationService2.getAllPoint(x, y, distance));
    }
}
