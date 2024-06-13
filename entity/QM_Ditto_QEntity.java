package com.ditto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QM_Ditto_QEntity is a Querydsl query type for M_Ditto_QEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QM_Ditto_QEntity extends EntityPathBase<M_Ditto_QEntity> {

    private static final long serialVersionUID = 1972764531L;

    public static final QM_Ditto_QEntity m_Ditto_QEntity = new QM_Ditto_QEntity("m_Ditto_QEntity");

    public final StringPath loginId = createString("loginId");

    public final StringPath mQnaAnswer = createString("mQnaAnswer");

    public final DateTimePath<java.time.LocalDateTime> mQnaAnswerDate = createDateTime("mQnaAnswerDate", java.time.LocalDateTime.class);

    public final StringPath mQnaContent = createString("mQnaContent");

    public final DateTimePath<java.time.LocalDateTime> mQnaDate = createDateTime("mQnaDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> mQnaNo = createNumber("mQnaNo", Long.class);

    public final StringPath mQnaStatus = createString("mQnaStatus");

    public final StringPath mQnaTitle = createString("mQnaTitle");

    public QM_Ditto_QEntity(String variable) {
        super(M_Ditto_QEntity.class, forVariable(variable));
    }

    public QM_Ditto_QEntity(Path<? extends M_Ditto_QEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QM_Ditto_QEntity(PathMetadata metadata) {
        super(M_Ditto_QEntity.class, metadata);
    }

}

