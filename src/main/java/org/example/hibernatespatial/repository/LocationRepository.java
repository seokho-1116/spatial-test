package org.example.hibernatespatial.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.hibernatespatial.service.PointDto;
import org.locationtech.jts.geom.Polygon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LocationRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<PointDto> findAllPointIn1kmWith4326(Polygon rectangle) {
        String query = """
            SELECT id, name, ST_X(location) as x, ST_Y(location) as y 
            FROM location4326 
            WHERE ST_Contains(ST_PolygonFromText(?, 4326), location)
            """;

        return jdbcTemplate.query(
            query,
            (resultSet, rowNum) -> new PointDto(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDouble("x"),
                resultSet.getDouble("y")
            ),
            rectangle.toText()
        );
    }

    public List<PointDto> findAllPointIn1kmWith3857(Polygon rectangle) {
        String query = "SELECT id, name, ST_X(location) as x, ST_Y(location) as y FROM location3857 WHERE ST_Contains(ST_PolygonFromText(?, 3857), location)";

        return jdbcTemplate.query(
            query,
            (resultSet, rowNum) -> new PointDto(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDouble("x"),
                resultSet.getDouble("y")
            ),
            rectangle.toText()
        );
    }

    public List<PointDto> findAllPoint(Polygon rectangle) {
        String query = "SELECT id, name, ST_X(location) as x, ST_Y(location) as y FROM location4326 WHERE MBRContains(ST_GeomFromText(?, 4326), location)";

        return jdbcTemplate.query(
            query,
            new Object[]{rectangle.toText()},
            (resultSet, rowNum) -> new PointDto(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDouble("x"),
                resultSet.getDouble("y")
            )
        );
    }


}
