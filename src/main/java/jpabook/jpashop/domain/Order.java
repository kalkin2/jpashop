package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "orders")
@Getter@Setter
public class Order {

    @Id@GeneratedValue
    @Column(name="order_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


    //주문 아이템
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    //배송정보
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    //주문시간
    private LocalDateTime orderDate;

    //주문상태( ORDER, CANCLE)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //연관관계 메서드
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);

    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }



}
