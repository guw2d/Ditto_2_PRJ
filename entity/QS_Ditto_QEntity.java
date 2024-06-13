package com.ditto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QS_Ditto_QEntity is a Querydsl query type for S_Ditto_QEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QS_Ditto_QEntity extends EntityPathBase<S_Ditto_QEntity> {

    private static final long serialVersionUID = -1866826503L;

    public static final QS_Ditto_QEntity s_Ditto_QEntity = new QS_Ditto_QEntity("s_Ditto_QEntity");

    public final StringPath inquiryContent = createString("inquiryContent");

    public final DateTimePath<java.time.LocalDateTime> inquiryDate = createDateTime("inquiryDate", java.time.LocalDateTime.class);

    public final StringPath inquiryStatus = createString("inquiryStatus");

    public final StringPath inquiryTitle = createString("inquiryTitle");

    public final StringPath loginId = createString("loginId");

    public final NumberPath<Long> orderNo = createNumber("orderNo", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath productName = createString("productName");

    public final NumberPath<Long> qnaNo = createNumber("qnaNo", Long.class);

    public QS_Ditto_QEntity(String variable) {
        super(S_Ditto_QEntity.class, forVariable(variable));
    }

    public QS_Ditto_QEntity(Path<? extends S_Ditto_QEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QS_Ditto_QEntity(PathMetadata metadata) {
        super(S_Ditto_QEntity.class, metadata);
    }

}

