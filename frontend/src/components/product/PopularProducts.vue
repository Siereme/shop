<template>
  <div class="popular-products">
    <h1>Популярные товары</h1>
    <ul class="popular-products-list">
        <ProductSnippet v-for="product in products" :key="product.id" :product="product" />
    </ul>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import ProductSnippetVue from '@/components/product/ProductSnippet.vue'
export default defineComponent({
    name: 'PopularProducts',
    components: {
        ProductSnippet: ProductSnippetVue
    },
    props: {
        products: Array
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
  color: #464b51;
}
h1{
    text-align: left;
    margin: 27px 0 22px;
    font-size: 1.875rem;
    font-weight: 400;
}
.popular-products-list{
    display: flex;
    overflow-y: auto;
}
.popular-products-list::-webkit-scrollbar{
    display: none;
}
</style>