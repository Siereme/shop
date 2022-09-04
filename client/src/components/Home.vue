<template>
    <div class="main-conteiner">
        <BannersWrapper />
        <div class="snippets">
            <PopularProducts :products="products" />
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import {useStore} from "vuex"
import {computed} from 'vue'
import BannersWrapperVue from './banners/BannersWrapper.vue'
import PopularProductsVue from './product/PopularProducts.vue'

export default defineComponent({
    name: 'App',
    components: {
    BannersWrapper: BannersWrapperVue,
    PopularProducts: PopularProductsVue,
},
    setup() {
        const store = useStore();

        let products = computed(() => store.state.product.popularProducts)

        return {
            products
        }
    }
})
</script>

<style>
.snippets {
    padding: 0 30px 30px;
}
</style>
