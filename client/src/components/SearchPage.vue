<template>
    <div class="search-main" v-if="shown">
        <h1>{{term}}</h1>

        <div class="search-main-container">
            <div class="search-facets-container">
                <Facets :disableCategory="true" :handleOptionClick="handleClick"/>
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
import api from "@/api/backend-api"
import ProductList from './product/ProductList.vue'
import Facets from './facets/Facets.vue'
import { computed, watch, onMounted } from 'vue'
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
        query: String,
        page: Number
    },
    setup(props) {
        const store = useStore();   

        let loadData = () => api.search(props.query)
                    .then((res) => res.status === 200 ? store.commit('setIsLoading', false) : null)

        onMounted(() => loadData())
        watch(
            () => props.term,
            () => loadData()
        )
        watch(
            () => props.page,
            () => loadData()
        )

        let products = computed(() => store.state.product.products)

        let priceRange = computed(() => store.state.facet.priceRange)

        let options = computed(() => store.state.facet.options)
        
        const shown = computed(() => products.value)


        let getCheckedOptions = () => {
            let checkedOptions = []

            let optionsList = options.value
            optionsList.forEach(option => {
                let values = option.values.filter(item => item.checked)
                
                if(values.length){
                    checkedOptions.push({
                        id: option.id,
                        type: option.type,
                        values: values
                    })
                }
            })

            return checkedOptions
        }

        let getSearchRequestObject = () => ({
            'query': props.query,
            'options': getCheckedOptions()
        })

        let handleClick = (event, id, value) => {
          store.commit('setOption', {id: id, value: value, checked: event.target.checked})
          api.searchByOptions(getSearchRequestObject())
        }

        return {
            shown,
            products,
            priceRange,
            options,
            handleClick
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
