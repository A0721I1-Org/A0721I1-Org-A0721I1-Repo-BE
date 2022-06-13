package projecta07.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_detail")
    private Long idOrderDetail;

    @Column(name = "number_product")
    private int numberProduct;

    @Column(name = "total_product")
    private Double totalProduct;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    public Double getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(Double totalProduct) {
        this.totalProduct = totalProduct;
    }

    public OrderDetail(Long idOrderDetail, int numberProduct, Double totalProduct, Order order, Product product) {
        this.idOrderDetail = idOrderDetail;
        this.numberProduct = numberProduct;
        this.totalProduct = totalProduct;
        this.order = order;
        this.product = product;
    }

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
