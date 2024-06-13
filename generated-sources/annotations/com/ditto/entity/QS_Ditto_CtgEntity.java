package com.ditto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QS_Ditto_CtgEntity is a Querydsl query type for S_Ditto_CtgEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QS_Ditto_CtgEntity extends EntityPathBase<S_Ditto_CtgEntity> {

    private static final long serialVersionUID = -2140196450L;

    public static final QS_Ditto_CtgEntity s_Ditto_CtgEntity = new QS_Ditto_CtgEntity("s_Ditto_CtgEntity");

    public final NumberPath<Long> ctgCd = createNumber("ctgCd", Long.class);

    public final StringPath ctgNm = createString("ctgNm");

    public final DateTimePath<java.time.LocalDateTime> iDt = createDateTime("iDt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> idx = createNumber("idx", Integer.class);

    public QS_Ditto_CtgEntity(String variable) {
        super(S_Ditto_CtgEntity.class, forVariable(variable));
    }

    public QS_Ditto_CtgEntity(Path<? extends S_Ditto_CtgEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QS_Ditto_CtgEntity(PathMetadata metadata) {
        super(S_Ditto_CtgEntity.class, metadata);
    }

}

