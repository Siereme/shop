package app.consrtructor;

import app.model.category.Category;
import app.model.product.Product;
import app.model.product.option.ProductOption;

import java.util.*;
import java.util.stream.Collectors;

public class TestProductConstructor {

    private final List<Product> productList = new ArrayList<>();
    private final List<Category> categoryList = new ArrayList<>();

    public TestProductConstructor() {
        TestCategoryConstructor constructor = new TestCategoryConstructor();
        categoryList.addAll(constructor.getAll());
        construct();
    }

    private void construct() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setArticle(412235L);
        product1.setName("Смартфон Vivo Y53s 128Gb 6Gb глубокое море");
        product1.setPrice(19599d);
        product1.setImageLink("/assets/img/products/smartphones/product_7_412235.jpg");
        product1.setCategories(Set.of(categoryList.get(1)));

        Product product2 = new Product();
        product2.setId(2L);
        product2.setArticle(359195L);
        product2.setName("Смартфон Samsung Galaxy S21 SM-G991 256Gb 8Gb белый");
        product2.setPrice(77990d);
        product2.setImageLink("/assets/img/products/smartphones/product_7_359195.jpg");
        product2.setCategories(Set.of(categoryList.get(1)));

        Product product3 = new Product();
        product3.setId(3L);
        product3.setArticle(392210L);
        product3.setName("Планшет Huawei MatePad Pro 8 128 Gb WiFi Grey M-Pencil");
        product3.setPrice(49990d);
        product3.setImageLink("/assets/img/products/tablets/product_8_392210.jpg");
        product3.setCategories(Set.of(categoryList.get(2)));

        Product product4 = new Product();
        product4.setId(4L);
        product4.setArticle(403373L);
        product4.setName("Планшет Xiaomi Mi Pad 5 RU 6 128 Pearl White");
        product4.setPrice(36990d);
        product4.setImageLink("/assets/img/products/tablets/product_8_403373.jpg");
        product4.setCategories(Set.of(categoryList.get(2)));

        Product product5 = new Product();
        product5.setId(5L);
        product5.setArticle(379476L);
        product5.setName("Фитнес-браслет Xiaomi Mi Smart Band 6 XMSH15HM");
        product5.setPrice(3099d);
        product5.setImageLink("/assets/img/products/smart_watches_and_bracelets/product_9_379476.jpg");
        product5.setCategories(Set.of(categoryList.get(3)));
        product5.setOptions(Set.copyOf(Collections.singletonList(new ProductOption("Вес", "200г"))));

        Product product6 = new Product();
        product6.setId(6L);
        product6.setArticle(325810L);
        product6.setName("Часы-телефон JET PHONE SP1 черный");
        product6.setPrice(1499d);
        product6.setImageLink("/assets/img/products/smart_watches_and_bracelets/product_9_325810.jpg");
        product6.setCategories(Set.of(categoryList.get(3)));

        Product product7 = new Product();
        product7.setId(7L);
        product7.setArticle(374331L);
        product7.setName("Электронная книга PocketBook 628 Ink Ruby Red (PB628-R-CIS)");
        product7.setPrice(11490d);
        product7.setImageLink("/assets/img/products/e-books/product_10_374331.jpg");
        product7.setCategories(Set.of(categoryList.get(4)));

        Product product8 = new Product();
        product8.setId(8L);
        product8.setArticle(411175L);
        product8.setName("Электронная книга PocketBook 970 Mist Grey (PB970-M-CIS)");
        product8.setPrice(19490d);
        product8.setImageLink("/assets/img/products/e-books/product_10_411175.jpg");
        product8.setCategories(Set.of(categoryList.get(4)));

        productList.addAll(List.of(
                product1,
                product2,
                product3,
                product4,
                product5,
                product6,
                product7,
                product8
        ));
    }

    public List<Product> getAll() {
        return productList;
    }

    public Product getById(Long id) {
        return productList.stream()
                .filter(product -> Objects.equals(product.getId(), id))
                .findFirst().orElseGet(Product::new);
    }

    public List<Product> getAllById(List<Long> ids) {
        return productList.stream()
                .filter(product -> ids.stream()
                        .anyMatch(id -> Objects.equals(product.getId(), id)))
                .collect(Collectors.toList());
    }

    public List<Product> getByCategoryId(Long id) {
        return productList.stream()
                .filter(product -> product.getCategories().stream()
                        .anyMatch(category -> Objects.equals(category.getId(), id)))
                .collect(Collectors.toList());
    }
}
