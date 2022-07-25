<template>
  <div class="header-navbar">
        <ul class="header-category-list">
            <router-link v-for="category in categories" :key="category.id" :to="'/category/' + category.id">
              {{category.name}}
            </router-link>
        </ul>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
export default defineComponent({
    name: 'HeaderCategoryList',
    props: {
        categories: {
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
a {
  display: inline-block;
  margin: 0 10px;
  text-decoration: none;
  color: #2c3e50;
}
.header-navbar {
  justify-content: center;
  display: flex;
}
.header-category-list{
    height: 56px;
    display: flex;
    align-items: center;
    margin: 0;
    overflow: auto;
}
.header-category-list::-webkit-scrollbar{
  display: none;
}
.header-category-list a{
  cursor: pointer;
  white-space: nowrap;
}
</style>