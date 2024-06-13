package com.ditto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGet_ProdEntiy is a Querydsl query type for Get_ProdEntiy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGet_ProdEntiy extends EntityPathBase<Get_ProdEntiy> {

    private static final long serialVersionUID = 205785665L;

    public static final QGet_ProdEntiy get_ProdEntiy = new QGet_ProdEntiy("get_ProdEntiy");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath ctgCd = createString("ctgCd");

    public final StringPath dlvyAdd = createString("dlvyAdd");

    public final NumberPath<Integer> dlvyCost = createNumber("dlvyCost", Integer.class);

    public final NumberPath<Integer> dlvyCostRe = createNumber("dlvyCostRe", Integer.class);

    public final StringPath dlvyTp = createString("dlvyTp");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> iDt = _super.iDt;

    public final StringPath imgName = createString("imgName");

    public final NumberPath<Double> margin = createNumber("margin", Double.class);

    public final NumberPath<Integer> originPrice = createNumber("originPrice", Integer.class);

    public final StringPath prodDesc = createString("prodDesc");

    public final NumberPath<Long> prodId = createNumber("prodId", Long.class);

    public final StringPath prodNm = createString("prodNm");

    public final NumberPath<Integer> realCnt = createNumber("realCnt", Integer.class);

    public final NumberPath<Integer> salePrice = createNumber("salePrice", Integer.class);

    public final StringPath saleStatus = createString("saleStatus");

    public final StringPath taxTp = createString("taxTp");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> uDt = _super.uDt;

    public final StringPath uuid = createString("uuid");

    public final StringPath wholesaleNm = createString("wholesaleNm");

    public QGet_ProdEntiy(String variable) {
        super(Get_ProdEntiy.class, forVariable(variable));
    }

    public QGet_ProdEntiy(Path<? extends Get_ProdEntiy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGet_ProdEntiy(PathMetadata metadata) {
        super(Get_ProdEntiy.class, metadata);
    }

}

