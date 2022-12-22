<template>
  <div class="cart-modal-product-item">
      <div class="product-image-wrapper">
        <img :src="cart.imageLink || '/assets/img/plugs/not_found.png'" alt="" class="product-image">
      </div>
      <div class="product-main">
        <div class="product-main__title">
          <a href="#">{{cart.title}}</a>
        </div>
        <div class="product-main__price">
          <div class="product-main__price-actual">
            {{cart.price}} ₽
          </div>
        </div>
      </div>
      <CartCarousel :cart="cart" />
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import CartCarouselVue from '../cart/CartCarousel.vue'

export default defineComponent({
    name: "CartModalProduct",
    props: {
        cart: {
            default: () => { }
        }
    },
    components: { CartCarousel: CartCarouselVue }
})
</script>

<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #464b51;
  text-decoration: none;
}
.cart-modal-product-item{
    display: flex;
    margin-bottom: 15px;
}
.cart-modal-product-item:last-child{
    margin-bottom: 0;
}
.cart-modal-product-item .product-image-wrapper{
    display: flex;
    align-items: center;
    flex: 0 0 auto;
    justify-content: center;
    width: 50px;
    height: 50px;
    margin-right: 10px;
}
.cart-modal-product-item .product-image-wrapper img{
    display: block;
    max-width: 100%;
    height: 100%;
}
.cart-modal-product-item .product-main__title a{
    cursor: pointer;
    transition: all .2s;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    max-height: calc(16px * 2);
    font-size: 12px;
    line-height: 16px;
    text-align: left;
    overflow: hidden;
}
.cart-modal-product-item .product-main__title:hover a{
    color: #0595e6;
}
.cart-modal-product-item .product-main__title{
    margin-bottom: 5px;
}
.cart-modal-product-item .product-main__price-actual{
    font-size: 14px;
    font-weight: 700;
    color: #353d4a;
    text-align: left;
}
</style>