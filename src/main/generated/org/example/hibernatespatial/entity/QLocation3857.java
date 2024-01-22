package org.example.hibernatespatial.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLocation3857 is a Querydsl query type for Location3857
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocation3857 extends EntityPathBase<Location3857> {

    private static final long serialVersionUID = -262809959L;

    public static final QLocation3857 location3857 = new QLocation3857("location3857");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ComparablePath<org.locationtech.jts.geom.Point> location = createComparable("location", org.locationtech.jts.geom.Point.class);

    public final StringPath name = createString("name");

    public QLocation3857(String variable) {
        super(Location3857.class, forVariable(variable));
    }

    public QLocation3857(Path<? extends Location3857> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocation3857(PathMetadata metadata) {
        super(Location3857.class, metadata);
    }

}

