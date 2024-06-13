package com.ditto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QS_Ditto_DlvyEntity is a Querydsl query type for S_Ditto_DlvyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QS_Ditto_DlvyEntity extends EntityPathBase<S_Ditto_DlvyEntity> {

    private static final long serialVersionUID = 1850490281L;

    public static final QS_Ditto_DlvyEntity s_Ditto_DlvyEntity = new QS_Ditto_DlvyEntity("s_Ditto_DlvyEntity");

    public final StringPath deliveryId = createString("deliveryId");

    public final DateTimePath<java.time.LocalDateTime> exDT = createDateTime("exDT", java.time.LocalDateTime.class);

    public final StringPath exType = createString("exType");

    public final NumberPath<Long> invoiceNo = createNumber("invoiceNo", Long.class);

    public final NumberPath<Long> ordNo = createNumber("ordNo", Long.class);

    public QS_Ditto_DlvyEntity(String variable) {
        super(S_Ditto_DlvyEntity.class, forVariable(variable));
    }

    public QS_Ditto_DlvyEntity(Path<? extends S_Ditto_DlvyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QS_Ditto_DlvyEntity(PathMetadata metadata) {
        super(S_Ditto_DlvyEntity.class, metadata);
    }

}

