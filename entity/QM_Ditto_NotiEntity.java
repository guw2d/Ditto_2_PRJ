package com.ditto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QM_Ditto_NotiEntity is a Querydsl query type for M_Ditto_NotiEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QM_Ditto_NotiEntity extends EntityPathBase<M_Ditto_NotiEntity> {

    private static final long serialVersionUID = -60384422L;

    public static final QM_Ditto_NotiEntity m_Ditto_NotiEntity = new QM_Ditto_NotiEntity("m_Ditto_NotiEntity");

    public final StringPath mQnaCntt = createString("mQnaCntt");

    public final DateTimePath<java.time.LocalDateTime> mQnaDt = createDateTime("mQnaDt", java.time.LocalDateTime.class);

    public final NumberPath<Long> mQnaNo = createNumber("mQnaNo", Long.class);

    public final StringPath mQnaQAns = createString("mQnaQAns");

    public final DateTimePath<java.time.LocalDateTime> mQnaQIdDt = createDateTime("mQnaQIdDt", java.time.LocalDateTime.class);

    public final StringPath mQnaStatus = createString("mQnaStatus");

    public final StringPath mQnaTitle = createString("mQnaTitle");

    public QM_Ditto_NotiEntity(String variable) {
        super(M_Ditto_NotiEntity.class, forVariable(variable));
    }

    public QM_Ditto_NotiEntity(Path<? extends M_Ditto_NotiEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QM_Ditto_NotiEntity(PathMetadata metadata) {
        super(M_Ditto_NotiEntity.class, metadata);
    }

}

