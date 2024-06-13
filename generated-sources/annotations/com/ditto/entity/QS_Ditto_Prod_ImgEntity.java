package com.ditto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QS_Ditto_Prod_ImgEntity is a Querydsl query type for S_Ditto_Prod_ImgEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QS_Ditto_Prod_ImgEntity extends EntityPathBase<S_Ditto_Prod_ImgEntity> {

    private static final long serialVersionUID = 1578170777L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QS_Ditto_Prod_ImgEntity s_Ditto_Prod_ImgEntity = new QS_Ditto_Prod_ImgEntity("s_Ditto_Prod_ImgEntity");

    public final DateTimePath<java.time.LocalDateTime> imgIDt = createDateTime("imgIDt", java.time.LocalDateTime.class);

    public final StringPath imgName = createString("imgName");

    public final NumberPath<Long> imgNo = createNumber("imgNo", Long.class);

    public final DateTimePath<java.time.LocalDateTime> imgUDt = createDateTime("imgUDt", java.time.LocalDateTime.class);

    public final StringPath path = createString("path");

    public final QS_Ditto_ProdEntity product;

    public final StringPath uuid = createString("uuid");

    public QS_Ditto_Prod_ImgEntity(String variable) {
        this(S_Ditto_Prod_ImgEntity.class, forVariable(variable), INITS);
    }

    public QS_Ditto_Prod_ImgEntity(Path<? extends S_Ditto_Prod_ImgEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QS_Ditto_Prod_ImgEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QS_Ditto_Prod_ImgEntity(PathMetadata metadata, PathInits inits) {
        this(S_Ditto_Prod_ImgEntity.class, metadata, inits);
    }

    public QS_Ditto_Prod_ImgEntity(Class<? extends S_Ditto_Prod_ImgEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QS_Ditto_ProdEntity(forProperty("product"), inits.get("product")) : null;
    }

}

