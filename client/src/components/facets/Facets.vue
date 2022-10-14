<template>
  <div class="facets">
    <FacetCategory v-if="!disableCategory" :mainCategory="mainCategory" :currentCategory="currentCategory" />
    <FicetSlider :handleClick="handleClick"/>
    <FacetCommons :handleClick="handleClick"/>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import FacetCategory from './FacetCategory.vue'
import FacetCommons from './FacetCommons.vue';
import FicetSlider from './FicetSlider.vue';

export default defineComponent({
    name: 'Facets',
    components: {
    FacetCategory,
    FacetCommons,
    FicetSlider
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
        },
        handleClick: Function
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
  color: #464b51;
  font-size: 16px;
}
</style>