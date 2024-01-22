package org.example.hibernatespatial.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLocationTest is a Querydsl query type for LocationTest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocationTest extends EntityPathBase<LocationTest> {

    private static final long serialVersionUID = -261781628L;

    public static final QLocationTest locationTest = new QLocationTest("locationTest");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ComparablePath<org.locationtech.jts.geom.Point> location = createComparable("location", org.locationtech.jts.geom.Point.class);

    public final StringPath name = createString("name");

    public QLocationTest(String variable) {
        super(LocationTest.class, forVariable(variable));
    }

    public QLocationTest(Path<? extends LocationTest> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocationTest(PathMetadata metadata) {
        super(LocationTest.class, metadata);
    }

}

