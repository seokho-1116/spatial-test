package org.example.hibernatespatial.config;

import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeoToolsConfig {

    @Bean
    public MathTransform mathTransform4326To3857() throws FactoryException {
        CoordinateReferenceSystem sourceCrs = CRS.decode("EPSG:4326");
        CoordinateReferenceSystem targetCrs = CRS.decode("EPSG:3857");

        return CRS.findMathTransform(sourceCrs, targetCrs, true);
    }

    @Bean
    public MathTransform mathTransform3857To4326() throws FactoryException {
        CoordinateReferenceSystem sourceCrs = CRS.decode("EPSG:3857");
        CoordinateReferenceSystem targetCrs = CRS.decode("EPSG:4326");

        return CRS.findMathTransform(sourceCrs, targetCrs, true);
    }

    @Bean
    public GeometryFactory geometryFactory4326() {
        return new GeometryFactory(new PrecisionModel(), 4326);
    }

    @Bean
    public GeometryFactory geometryFactory3857() {
        return new GeometryFactory(new PrecisionModel(), 3857);
    }
}
