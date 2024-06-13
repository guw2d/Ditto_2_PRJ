package com.ditto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QS_Ditto_AEntity is a Querydsl query type for S_Ditto_AEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QS_Ditto_AEntity extends EntityPathBase<S_Ditto_AEntity> {

    private static final long serialVersionUID = 1112983785L;

    public static final QS_Ditto_AEntity s_Ditto_AEntity = new QS_Ditto_AEntity("s_Ditto_AEntity");

    public final StringPath answerContent = createString("answerContent");

    public final DateTimePath<java.time.LocalDateTime> answerDate = createDateTime("answerDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> inquiry = createNumber("inquiry", Long.class);

    public final NumberPath<Long> qnaNo = createNumber("qnaNo", Long.class);

    public QS_Ditto_AEntity(String variable) {
        super(S_Ditto_AEntity.class, forVariable(variable));
    }

    public QS_Ditto_AEntity(Path<? extends S_Ditto_AEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QS_Ditto_AEntity(PathMetadata metadata) {
        super(S_Ditto_AEntity.class, metadata);
    }

}

