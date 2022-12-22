<template>
  <div class="products" v-if="products && products.length">
      <Product v-for="product in products" :key="product.id" :product="product" />
  </div>
</template>

<script>
// import {computed} from 'vue';
import ProductVue from "@/components/product/Product";

export default {
  name: 'Products',
  components: {
    Product: ProductVue
  },
  props: {
      products: {
          default: () => {}
      }
  },
  setup() {

    // let shown = computed(() => products && products.length)

    // return {
    //   shown
    // }
  },
}
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
  color: #42b983;
}
.products{
    display: flex;
    flex-wrap: wrap;
}
</style>