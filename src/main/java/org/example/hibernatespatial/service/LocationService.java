package org.example.hibernatespatial.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.hibernatespatial.repository.LocationRepository;
import org.geotools.geometry.jts.JTS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocationService {

    private static final double KM_TO_DEGREE =  1 / 110.574;

    private final LocationRepository locationRepository;
    private final GeometryFactory geometryFactory4326;
    private final GeometryFactory geometryFactory3857;
    private final MathTransform mathTransform3857To4326;
    private final MathTransform mathTransform4326To3857;

    public List<PointResponse> getAllPointIn1kmWith4326(Double x, Double y, double distance) {
        Point point = geometryFactory4326.createPoint(new Coordinate(x, y));

        List<PointDto> dtos = locationRepository.findAllPointIn1kmWith4326(
            getDistanceMBR4326(point, distance));

        return dtos.stream()
            .map(PointResponse::fromDto)
            .toList();
    }

    public List<PointResponse> getAllPointIn1kmWith3857(Double x, Double y, double distance)
        throws TransformException {
        Point point = geometryFactory4326.createPoint(new Coordinate(x, y));

        Point transform = (Point) JTS.transform(point, mathTransform4326To3857);

        Polygon distanceMBR4326 = getDistanceMBR3857(transform, distance);

        List<PointDto> dtos = locationRepository.findAllPointIn1kmWith3857(distanceMBR4326);

        return dtos.stream()
            .map(r -> {
                Point p = change3857To4326(r.x(), r.y());
                return new PointResponse(r.id(), r.name(), p.getX(), p.getY());
            })
            .toList();
    }

    private Polygon getDistanceMBR4326(Point p, double distance) {
        double x = p.getX();
        double y = p.getY();

        double v_deltaLon = distance / Math.abs(Math.cos(Math.toRadians(y)) * 111.32);
        double v_Lat1 = y - (distance * KM_TO_DEGREE);
        double v_Lat2 = y + (distance * KM_TO_DEGREE);
        double v_Lon1 = x - v_deltaLon;
        double v_Lon2 = x + v_deltaLon;

        return geometryFactory4326.createPolygon(new Coordinate[]{
            new Coordinate(v_Lon1, v_Lat1),
            new Coordinate(v_Lon2, v_Lat1),
            new Coordinate(v_Lon2, v_Lat2),
            new Coordinate(v_Lon1, v_Lat2),
            new Coordinate(v_Lon1, v_Lat1)
        });
    }

    private Polygon getDistanceMBR3857(Point p, double distance) {
        double x = p.getX();
        double y = p.getY();

        double minX = x - distance * 1000;
        double minY = y - distance * 1000;
        double maxX = x + distance * 1000;
        double maxY = y + distance * 1000;

        return geometryFactory3857.createPolygon(new Coordinate[]{
            new Coordinate(minX, minY),
            new Coordinate(maxX, minY),
            new Coordinate(maxX, maxY),
            new Coordinate(minX, maxY),
            new Coordinate(minX, minY)
        });
    }

    private Point change3857To4326(double x, double y) {
        Point point = geometryFactory3857.createPoint(new Coordinate(x, y));

        try {
            return (Point) JTS.transform(point, mathTransform3857To4326);
        } catch (TransformException e) {
            throw new RuntimeException(e);
        }
    }
}
