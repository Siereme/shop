<template>
  <div class="product-item" v-if="product">
      <div class="product-image-wrapper">
        <img :src="product.imageLink || '/assets/img/plugs/not_found.png'" alt="" class="product-image">
      </div>
      <div class="product-main">
        <div class="product-main__title">
          <a href="#">{{product.name}}</a>
        </div>
        <div class="product-main__price">
          <div class="product-main__price-actual">
            {{product.price}} ₽
          </div>
        </div>
        <CartButton :sku="product.sku" />
      </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import CartButton from '../cart/CartButton.vue'
export default defineComponent({
    name: "Product",
    props: {
        product: {
            default: () => { }
        }
    },
    components: { CartButton }
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
  color: #0595e6;
  text-decoration: none;
}
.product-item{
  display: flex;
  flex-direction: column;
  flex-basis: calc(33.33333% - 8px);
  max-width: calc(33.33333% - 8px);
  border: 1px solid #b8ced9;
  border-radius: 4px;
  background: #fff;
  padding: 20px;
  box-sizing: border-box;
  margin: 0 4px;
  margin-bottom: 8px;
}
.product-item:hover{
  box-shadow: 0 0 6px 1px rgba(0, 0, 0, 0.15);
}
@media (min-width: 1268px){
  .product-item{
    flex-basis: calc(25% - 8px);
    max-width: calc(25% - 8px);
  }
}
.product-image-wrapper{
  height: 150px;
  margin: 0 auto;
}
.product-image-wrapper img{
  display: block;
  max-width: 100%;
  height: 100%;
}
.product-main{
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-top: 15px;
}
.product-main__title{
  margin-bottom: 20px;
}
.product-main__title a{
  display: block;
  font-size: 14px;
  line-height: 18px;
  text-align: left;
}
.product-main__price-actual{
  display: flex;
  white-space: nowrap;
  color: #353d4a;
  font-size: 21px;
  min-height: 32px;
}
</style>