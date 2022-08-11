package app.model.product;

public interface IProductItem<T extends IProduct> {
    Long getId();

    T getProduct();

    int getCount();

    void setId(Long id);

    void setProduct(T product);

    void setCount(int count);
}
