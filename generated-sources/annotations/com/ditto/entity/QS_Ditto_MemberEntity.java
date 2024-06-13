package com.ditto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QS_Ditto_MemberEntity is a Querydsl query type for S_Ditto_MemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QS_Ditto_MemberEntity extends EntityPathBase<S_Ditto_MemberEntity> {

    private static final long serialVersionUID = -699958024L;

    public static final QS_Ditto_MemberEntity s_Ditto_MemberEntity = new QS_Ditto_MemberEntity("s_Ditto_MemberEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> iDt = _super.iDt;

    public final StringPath loginId = createString("loginId");

    public final StringPath loginPw = createString("loginPw");

    public final StringPath memberAddr = createString("memberAddr");

    public final StringPath memberDt = createString("memberDt");

    public final StringPath memberEmail = createString("memberEmail");

    public final StringPath memberNm = createString("memberNm");

    public final NumberPath<Long> memberNo = createNumber("memberNo", Long.class);

    public final StringPath memberTel = createString("memberTel");

    public final StringPath memberZipcd = createString("memberZipcd");

    public final SetPath<com.ditto.model.Role, EnumPath<com.ditto.model.Role>> roles = this.<com.ditto.model.Role, EnumPath<com.ditto.model.Role>>createSet("roles", com.ditto.model.Role.class, EnumPath.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> uDt = _super.uDt;

    public QS_Ditto_MemberEntity(String variable) {
        super(S_Ditto_MemberEntity.class, forVariable(variable));
    }

    public QS_Ditto_MemberEntity(Path<? extends S_Ditto_MemberEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QS_Ditto_MemberEntity(PathMetadata metadata) {
        super(S_Ditto_MemberEntity.class, metadata);
    }

}

