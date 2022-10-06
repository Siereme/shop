<template>
    <div class="search-main" v-if="shown">
        <h1>{{term}}</h1>

        <div class="search-main-container">
            <div class="search-facets-container">
                <Facets :disableCategory="true"/>
            </div>
            <div class="search-products-container">
                <ProductList :products="products" />
                <Pagination :page="page" />
            </div>
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
// import api from "@/api/backend-api"
import ProductList from './product/ProductList.vue'
import Facets from './facets/Facets.vue'
import { computed } from 'vue'
import { useStore } from "vuex"
import Pagination from './pagination/Pagination.vue'

export default defineComponent({
    name: 'SearchPage',
    components: {
        ProductList,
        Facets,
        Pagination
    },
    props: {
        term: String,
        page: Number
    },
    setup() {
        const store = useStore();   

        let products = computed(() => store.state.product.products)

        let priceRange = computed(() => store.state.facet.priceRange)

        let options = computed(() => store.state.facet.options)
        
        const shown = computed(() => products.value)

        return {
            shown,
            products,
            priceRange,
            options
        }
    }
})
</script>

<style>
.search-main{
    padding: 0 30px 40px;
}
.search-main-container{
    display: flex;
    flex-direction: row;
}
.search-facets-container{
    display: block;
    width: 304px;
    margin-right: 20px;
}
.search-products-container{
    flex: 1;
}
h1{
    display: block;
    font-size: 1.675rem;
    text-align: left;
    margin: 22px 0;
    font-weight: 400;
}
</style>
