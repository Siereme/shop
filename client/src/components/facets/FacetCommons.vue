<template>
    <div class="facet-commons">
      <div class="facet-header">
      </div>
      <FacetCommon v-for="option in options" :key="option.id" :option="option" :handleClick="handleClick"/>
    </div>
  </template>
  
  <script>
  import { defineComponent, computed } from 'vue'
  import { useStore } from "vuex"
  import FacetCommon from './FacetCommon.vue'
  export default defineComponent({
    name: "FacetCommons",
    props: {
      handleClick: Function
    },
    setup() {
        const store = useStore();

        let options = computed(() => store.state.facet.options)

        return {
            options
        }
    },
    components: { FacetCommon }
})
  </script>
  
  <style scoped>
  h3 {
    margin: 40px 0 0;
  }
  ul {
    list-style-type: none;
    padding: 0;
    margin: 0;
  }
  li {
    display: inline-block;
    margin: 0;
  }
  a {
    color: #0595e6;
  }
  .facet-commons {
    border: 1px solid #b8ced9;
    border-radius: 5px;
    padding: 20px 15px;
  }
  </style>