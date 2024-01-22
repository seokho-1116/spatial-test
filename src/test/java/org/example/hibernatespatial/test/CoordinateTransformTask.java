package org.example.hibernatespatial.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;

public class CoordinateTransformTask implements Runnable {
    private final double coordX;
    private final double coordY;

    public CoordinateTransformTask(double coordX, double coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    @Override
    public void run() {
        try {
            CoordinateReferenceSystem sourceCrs = CRS.decode("EPSG:4326");
            CoordinateReferenceSystem targetCrs = CRS.decode("EPSG:3857");
            GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

            Coordinate coordinate = new Coordinate(coordX, coordY);
            Geometry point = geometryFactory.createPoint(coordinate);

            MathTransform transform = CRS.findMathTransform(sourceCrs, targetCrs, true);
            Geometry transformedPoint = JTS.transform(point, transform);

            // 결과를 출력하거나 다른 작업 수행
            System.out.println("Transformed Point: " + transformedPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 덕수궁의 좌표
        double coordX = 126.97476625442985;
        double coordY = 37.565611356905336;
        System.setProperty("org.geotools.referencing.forceXY", "true");


        CoordinateTransformTask task = new CoordinateTransformTask(coordX, coordY);
        List<Thread> threads = new ArrayList<>();
        IntStream.rangeClosed(1, 10)
            .forEach(i -> threads.add(new Thread(task)));

        for (Thread thread : threads) {
            thread.start();
        }

        // 여러 좌표를 변환하려면 추가 스레드 생성
        // 예: new Thread(new CoordinateTransformTask(다른 좌표)).start();
    }
}