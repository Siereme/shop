<template>
  <div class="product-snippet" v-if="product">
    <div class="image-wrapper">
        <img :src="product.imageLink || '/assets/img/plugs/not_found.png'" alt="">
    </div>
    <div class="product-snippet-main">
        <div class="product-snippet-title">
            <a href="#">{{product.name}}</a>
        </div>
        <div class="product-snippet__main-bottom">
            <SnippetCartButton :sku="product.sku" />
            <div class="product-snippet-price">{{product.price}} ₽</div>
        </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import SnippetCartButtonVue from '@/components/cart/SnippetCartButton.vue'
export default defineComponent({
    name: 'ProductSinppet',
    components: {
        SnippetCartButton: SnippetCartButtonVue
    },
    props: {
        product: {
            default: () => {}
        }
    }
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
.product-snippet{
    display: flex;
    min-width: 355px;
    width: 355px;
    margin-right: 6px;
    border: 1px solid #c2d2e2;
    border-radius: 10px;
    padding: 17px 15px 17px 20px;
    height: 138px;
    text-decoration: none;
    position: relative;
    align-items: center;
    box-sizing: border-box;
}
.image-wrapper{
    height: 100px;
    flex: 0 0 100px;
    margin-right: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
}
.image-wrapper img{
    max-width: 100%;
    max-height: 100%;
}
.product-snippet-main{
    display: flex;
    flex-direction: column;
    overflow: hidden;
    height: 100%;
    justify-content: space-between;
    align-items: flex-start;
}
.product-snippet-title a{
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    max-height: calc(16px * 3);
    font-size: 14px;
    line-height: 16px;
    text-align: left;
    overflow: hidden;
}
.product-snippet__main-bottom{
    display: flex;
    align-items: center;
    width: 100%;
}
.product-snippet__main-bottom .snippet-cart-button{
    margin-right: 15px;
}
</style>