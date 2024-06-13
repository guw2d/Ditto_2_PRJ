package com.ditto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QS_Ditto_OrderEntity is a Querydsl query type for S_Ditto_OrderEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QS_Ditto_OrderEntity extends EntityPathBase<S_Ditto_OrderEntity> {

    private static final long serialVersionUID = -158171242L;

    public static final QS_Ditto_OrderEntity s_Ditto_OrderEntity = new QS_Ditto_OrderEntity("s_Ditto_OrderEntity");

    public final StringPath expectYn = createString("expectYn");

    public final StringPath memberId = createString("memberId");

    public final DateTimePath<java.time.LocalDateTime> orderDate = createDateTime("orderDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> orderNo = createNumber("orderNo", Long.class);

    public final NumberPath<Double> orderPrice = createNumber("orderPrice", Double.class);

    public final NumberPath<Integer> orderQuantity = createNumber("orderQuantity", Integer.class);

    public final StringPath orderStatus = createString("orderStatus");

    public final NumberPath<Long> prodId = createNumber("prodId", Long.class);

    public final StringPath prodNm = createString("prodNm");

    public QS_Ditto_OrderEntity(String variable) {
        super(S_Ditto_OrderEntity.class, forVariable(variable));
    }

    public QS_Ditto_OrderEntity(Path<? extends S_Ditto_OrderEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QS_Ditto_OrderEntity(PathMetadata metadata) {
        super(S_Ditto_OrderEntity.class, metadata);
    }

}

