package com.ditto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QS_Ditto_CartEntity is a Querydsl query type for S_Ditto_CartEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QS_Ditto_CartEntity extends EntityPathBase<S_Ditto_CartEntity> {

    private static final long serialVersionUID = 1930308542L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QS_Ditto_CartEntity s_Ditto_CartEntity = new QS_Ditto_CartEntity("s_Ditto_CartEntity");

    public final NumberPath<Integer> cartCount = createNumber("cartCount", Integer.class);

    public final NumberPath<Long> cartId = createNumber("cartId", Long.class);

    public final QS_Ditto_MemberEntity loginNo;

    public final QS_Ditto_ProdEntity prodId;

    public QS_Ditto_CartEntity(String variable) {
        this(S_Ditto_CartEntity.class, forVariable(variable), INITS);
    }

    public QS_Ditto_CartEntity(Path<? extends S_Ditto_CartEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QS_Ditto_CartEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QS_Ditto_CartEntity(PathMetadata metadata, PathInits inits) {
        this(S_Ditto_CartEntity.class, metadata, inits);
    }

    public QS_Ditto_CartEntity(Class<? extends S_Ditto_CartEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.loginNo = inits.isInitialized("loginNo") ? new QS_Ditto_MemberEntity(forProperty("loginNo")) : null;
        this.prodId = inits.isInitialized("prodId") ? new QS_Ditto_ProdEntity(forProperty("prodId"), inits.get("prodId")) : null;
    }

}

