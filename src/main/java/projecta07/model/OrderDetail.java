package projecta07.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_detail")
    private Long idOrderDetail;

    @Column(name = "number_product")
    private int numberProduct;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderDetail() {
    }

    public OrderDetail(Long idOrderDetail, int numberProduct) {
        this.idOrderDetail = idOrderDetail;
        this.numberProduct = numberProduct;
    }

    public OrderDetail(int numberProduct, Order order, Product product) {
        this.numberProduct = numberProduct;
        this.order = order;
        this.product = product;
    }

    public OrderDetail(Long idOrderDetail, int numberProduct, Order order, Product product) {
        this.idOrderDetail = idOrderDetail;
        this.numberProduct = numberProduct;
        this.order = order;
        this.product = product;
    }

    public Long getIdOrderDetail() {
        return idOrderDetail;
    }

    public void setIdOrderDetail(Long idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    public int getNumberProduct() {
        return numberProduct;
    }

    public void setNumberProduct(int numberProduct) {
        this.numberProduct = numberProduct;
    }

}
