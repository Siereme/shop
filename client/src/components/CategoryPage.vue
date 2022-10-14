<template>
    <div class="category-main" v-if="shown">
        <h1>{{currentCategory.name}}</h1>

        <div class="category-main-container">
            <div class="category-facets-container">
                <Facets :mainCategory="mainCategory" :currentCategory="currentCategory" :handleClick="handleClick" />
            </div>
            <div class="category-products-container">
                <ProductList :products="products" />
                <Pagination :page="page" :handleClick="handleClick" />
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
import { useRoute, useRouter } from 'vue-router'
import Pagination from './pagination/Pagination.vue'

export default defineComponent({
    name: 'CategoryPage',
    components: {
        ProductList,
        Facets,
        Pagination
    },
    props: {
        categoryId: {
            default: () => {}
        },
        page: {
            default: () => {}
        }
    },
    setup(props) {
        const store = useStore()   
        const router = useRouter()
        const route = useRoute()
        
        let token = computed(() => store.getters.getAccessToken())

        
        let getRequestDTO = () => {
            var request = {}
            request['category'] = {
                'id': props.categoryId,
                'withParent': Object.keys(mainCategory.value).length === 0
            }
            props.page ? request.page = props.page : null
            return request
        }
            
        let loadData = () => api.searchCategory(getRequestDTO())
                    .then((res) => res.status === 200 ? store.commit('setIsLoading', false) : null)


        onMounted(() => loadData(props.categoryId))
        watch(
            () => props.categoryId,
            () => loadData()
        )
        watch(
            () => props.page,
            () => !store.state.main.isLoading ? loadData() : null
        )
        watch(
            () => token.value,
            () => loadData(props.categoryId)
        )



        let mainCategory = computed(() => store.state.category.mainCategory ? store.state.category.mainCategory : null)

        let currentCategory = computed(() => store.state.category.currentCategory ? store.state.category.currentCategory  : null)

        let products = computed(() => store.state.product.products)

        let options = computed(() => store.state.facet.options)
        
        const shown = computed(() => mainCategory.value !== null && currentCategory.value !== null)



        let getCheckedOptions = () => {
            let checkedOptions = []     
            options.value.forEach(option => {
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

        let getSearchRequestObject = (page) => ({
            'category': {
                'path': currentCategory.value.path,
                'depth': currentCategory.value.depth
            },
            'rangePrice': store.state.facet.price,
            'options': getCheckedOptions(),
            'page': page
        })

        let handleClick = (page = 1) => {
            store.commit('setIsLoading', true)
            router.replace({path: route.path, params: {id: route.params.id}, query: {page: page}})
            api.searchCategoryByOptions(getSearchRequestObject(page))
                .then(() => store.commit('setIsLoading', false))
        }

        return {
            shown,
            products,
            mainCategory,
            currentCategory,
            handleClick
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
