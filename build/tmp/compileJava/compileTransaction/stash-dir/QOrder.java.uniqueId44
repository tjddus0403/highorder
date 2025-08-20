package alp.highorder.order.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = -957060254L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final alp.highorder.customer.domain.entity.QCustomer customer;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<OrderItem, QOrderItem> items = this.<OrderItem, QOrderItem>createList("items", OrderItem.class, QOrderItem.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> orderedAt = createDateTime("orderedAt", java.time.LocalDateTime.class);

    public final alp.highorder.store.domain.entity.QStore store;

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new alp.highorder.customer.domain.entity.QCustomer(forProperty("customer")) : null;
        this.store = inits.isInitialized("store") ? new alp.highorder.store.domain.entity.QStore(forProperty("store")) : null;
    }

}

