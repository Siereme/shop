<template>
  <div class="facets">
    <FacetCategory v-if="!disableCategory" :mainCategory="mainCategory" :currentCategory="currentCategory" />
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import FacetCategoryVue from './FacetCategory.vue'

export default defineComponent({
    name: 'Facets',
    components: {
      FacetCategory: FacetCategoryVue
    },
    props: {
        disableCategory: {
            default: () => {}
        },
        mainCategory: {
            default: () => {}
        },
        currentCategory: {
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
}
.facets{
  border: 1px solid #b8ced9;
  border-radius: 5px;
  color: #464b51;
  font-size: 16px;
}
</style>