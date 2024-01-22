package org.example.hibernatespatial.test;

import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

public class SpatialTest {

    @Test
    void spatialTest() throws TransformException, FactoryException {
        System.setProperty("org.geotools.referencing.forceXY", "true");

        // 덕수궁의 좌표입니다 😎
        String coordX = "126.97476625442985";
        String coordY = "37.565611356905336";

        CoordinateReferenceSystem sourceCrs = CRS.decode("EPSG:4326");
        CoordinateReferenceSystem targetCrs = CRS.decode("EPSG:3857");

        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

        MathTransform transform = CRS.findMathTransform(sourceCrs, targetCrs, true);
        long beforeTime = System.currentTimeMillis();
        Coordinate coordinate = new Coordinate(Double.parseDouble(coordX),
            Double.parseDouble(coordY));
        Geometry point = geometryFactory.createPoint(coordinate);
        Geometry transFormedPoint = JTS.transform(point, transform);
        long afterTime = System.currentTimeMillis();
        long secDiffTime = afterTime - beforeTime;

        System.out.println("시간차이(ms) : " + secDiffTime);

        System.out.println("좌표변경 전(EPSG:4326) Point = " + point);
        System.out.println("좌표변경 후(EPSG:3857) Point = " + transFormedPoint);
    }
}
