package alp.highorder.stamp.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStamp is a Querydsl query type for Stamp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStamp extends EntityPathBase<Stamp> {

    private static final long serialVersionUID = 207165388L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStamp stamp = new QStamp("stamp");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final alp.highorder.customer.domain.entity.QCustomer customer;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final alp.highorder.store.domain.entity.QStore store;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QStamp(String variable) {
        this(Stamp.class, forVariable(variable), INITS);
    }

    public QStamp(Path<? extends Stamp> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStamp(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStamp(PathMetadata metadata, PathInits inits) {
        this(Stamp.class, metadata, inits);
    }

    public QStamp(Class<? extends Stamp> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new alp.highorder.customer.domain.entity.QCustomer(forProperty("customer")) : null;
        this.store = inits.isInitialized("store") ? new alp.highorder.store.domain.entity.QStore(forProperty("store")) : null;
    }

}

