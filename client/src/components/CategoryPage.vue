<template>
    <div class="category-main" v-if="shown">
        <h1>{{currentCategory.name}}</h1>

        <div class="category-main-container">
            <div class="category-facets-container">
                <Facets :mainCategory="mainCategory" :currentCategory="currentCategory" />
            </div>
            <div class="category-products-container">
                <ProductList :products="products" />
            </div>
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import api from "@/api/backend-api"
import ProductList from './product/ProductList.vue'
import FacetsVue from './facets/Facets.vue'
import {computed, watch, onMounted } from 'vue'
import {useStore} from "vuex"
// import {ref} from 'vue'
// import { useRoute } from 'vue-router'

export default defineComponent({
    name: 'CategoryPage',
    components: {
        ProductList,
        Facets: FacetsVue
    },
    props: {
        categoryId: {
            default: () => {}
        }
    },
    setup(props) {
        const store = useStore();    
            
        let loadData = categoryId => {
            if(token.value){
                api.loadCategoryById(categoryId)
                api.loadCategoryByIdAndDepth(categoryId, 1)
                store.commit('setProducts', [])
                api.loadProductsByCategoryId(categoryId)
            }
        }


        onMounted(() => loadData(props.categoryId))

        watch(
            () => props.categoryId,
            id => loadData(id)
        )

        let token = computed(() => store.getters.getToken())
        watch(
            () => token.value,
            () => loadData(props.categoryId)
        )


        let mainCategory = computed(() => {
            return store.state.category.mainCategory ? store.state.category.mainCategory : null
        })

        let currentCategory = computed(() => {
            return store.state.category.currentCategory ? store.state.category.currentCategory  : null
        })

        let products = computed(() => store.state.product.products)
        
        const shown = computed(() => mainCategory.value !== null && currentCategory.value !== null && products.value !== null)

        return {
            shown,
            products,
            mainCategory,
            currentCategory
        }
    }
})
</script>

<style>
.category-main{
    padding: 0 30px 40px;
}
.category-main-container{
    display: flex;
    flex-direction: row;
}
.category-facets-container{
    display: block;
    width: 304px;
    margin-right: 20px;
}
.category-products-container{
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
