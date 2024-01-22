package org.example.hibernatespatial.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLocation4326 is a Querydsl query type for Location4326
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocation4326 extends EntityPathBase<Location4326> {

    private static final long serialVersionUID = -262785067L;

    public static final QLocation4326 location4326 = new QLocation4326("location4326");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ComparablePath<org.locationtech.jts.geom.Point> location = createComparable("location", org.locationtech.jts.geom.Point.class);

    public final StringPath name = createString("name");

    public QLocation4326(String variable) {
        super(Location4326.class, forVariable(variable));
    }

    public QLocation4326(Path<? extends Location4326> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocation4326(PathMetadata metadata) {
        super(Location4326.class, metadata);
    }

}

