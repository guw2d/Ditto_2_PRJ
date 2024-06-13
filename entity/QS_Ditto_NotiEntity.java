package com.ditto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QS_Ditto_NotiEntity is a Querydsl query type for S_Ditto_NotiEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QS_Ditto_NotiEntity extends EntityPathBase<S_Ditto_NotiEntity> {

    private static final long serialVersionUID = -1747851244L;

    public static final QS_Ditto_NotiEntity s_Ditto_NotiEntity = new QS_Ditto_NotiEntity("s_Ditto_NotiEntity");

    public final StringPath sNotiCntt = createString("sNotiCntt");

    public final DateTimePath<java.time.LocalDateTime> sNotiIDt = createDateTime("sNotiIDt", java.time.LocalDateTime.class);

    public final StringPath sNotiNm = createString("sNotiNm");

    public final NumberPath<Long> sNotiSeq = createNumber("sNotiSeq", Long.class);

    public final StringPath sNotiTitle = createString("sNotiTitle");

    public final DateTimePath<java.time.LocalDateTime> sNotiUDt = createDateTime("sNotiUDt", java.time.LocalDateTime.class);

    public QS_Ditto_NotiEntity(String variable) {
        super(S_Ditto_NotiEntity.class, forVariable(variable));
    }

    public QS_Ditto_NotiEntity(Path<? extends S_Ditto_NotiEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QS_Ditto_NotiEntity(PathMetadata metadata) {
        super(S_Ditto_NotiEntity.class, metadata);
    }

}

