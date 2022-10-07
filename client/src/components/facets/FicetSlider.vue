<template>
    <div class="facet-slider" v-if="shown">
      <div class="facet-header">
      </div>

    </div>
  </template>
  
  <script>
  import { defineComponent } from 'vue'
  
  export default defineComponent({
      name: 'FacetSlider',
      setup() {
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
    margin: 0;
  }
  li {
    display: inline-block;
    margin: 0;
  }
  a {
    color: #0595e6;
  }
  .facet-category ul{
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: flex-start;
      padding: 20px 15px;
  }
  .facet-category ul .facet-category-item{
      font-size: 14px;
      line-height: 16px;
      margin-bottom: 16px;
      cursor: pointer;
      text-align: left;
  }
  .facet-category ul .facet-category-item.selected,
  .facet-category ul .facet-category-item:hover{
      color: #0595e6;
  }
  .facet-category ul .facet-category-item.facet-category-item__children{
      padding-left: 15px;
  }
  .facet-category ul .facet-category-item:last-child{
      margin-bottom: 0;
  }
  </style>