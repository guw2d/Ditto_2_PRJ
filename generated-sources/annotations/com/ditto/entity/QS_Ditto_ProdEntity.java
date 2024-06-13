package com.ditto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QS_Ditto_ProdEntity is a Querydsl query type for S_Ditto_ProdEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QS_Ditto_ProdEntity extends EntityPathBase<S_Ditto_ProdEntity> {

    private static final long serialVersionUID = 760024757L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QS_Ditto_ProdEntity s_Ditto_ProdEntity = new QS_Ditto_ProdEntity("s_Ditto_ProdEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QS_Ditto_CtgEntity ctgCd;

    public final StringPath dlvyAdd = createString("dlvyAdd");

    public final NumberPath<Integer> dlvyCost = createNumber("dlvyCost", Integer.class);

    public final NumberPath<Integer> dlvyCostRe = createNumber("dlvyCostRe", Integer.class);

    public final StringPath dlvyTp = createString("dlvyTp");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> iDt = _super.iDt;

    public final NumberPath<Double> margin = createNumber("margin", Double.class);

    public final NumberPath<Integer> originPrice = createNumber("originPrice", Integer.class);

    public final StringPath pathUrl = createString("pathUrl");

    public final StringPath prodDesc = createString("prodDesc");

    public final NumberPath<Long> prodId = createNumber("prodId", Long.class);

    public final StringPath prodNm = createString("prodNm");

    public final NumberPath<Integer> realCnt = createNumber("realCnt", Integer.class);

    public final NumberPath<Integer> salePrice = createNumber("salePrice", Integer.class);

    public final StringPath saleStatus = createString("saleStatus");

    public final StringPath taxTp = createString("taxTp");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> uDt = _super.uDt;

    public final StringPath userId = createString("userId");

    public QS_Ditto_ProdEntity(String variable) {
        this(S_Ditto_ProdEntity.class, forVariable(variable), INITS);
    }

    public QS_Ditto_ProdEntity(Path<? extends S_Ditto_ProdEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QS_Ditto_ProdEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QS_Ditto_ProdEntity(PathMetadata metadata, PathInits inits) {
        this(S_Ditto_ProdEntity.class, metadata, inits);
    }

    public QS_Ditto_ProdEntity(Class<? extends S_Ditto_ProdEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ctgCd = inits.isInitialized("ctgCd") ? new QS_Ditto_CtgEntity(forProperty("ctgCd")) : null;
    }

}

