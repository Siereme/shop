<template>
  <div class="header-navbar">
        <ul class="header-category-list">
            <a v-for="category in categories" :key="category.id" @click="handleClick(category.id)">
              {{category.name}}
            </a>
        </ul>
  </div>
</template>

<script>
import api from "@/api/backend-api"
import { defineComponent } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default defineComponent({
    name: 'HeaderCategoryList',
    props: {
        categories: {
            default: () => {}
        }
    },
    setup(){
      const store = useStore()
      const router = useRouter()

      let getRequestDTO = (categoryId) => {
            var request = {}
            request.categoryId = categoryId
            request.withParent = true
            request.withProducts = true
            return request
      }

      let handleClick = (categoryId) => {
        store.commit('setIsLoading', true)
        api.searchCategory(getRequestDTO(categoryId))
        .then((res) => {
          if(res.status === 200){
            router.push({name: 'CategoryPage', params: {id: categoryId}})
            store.commit('setIsLoading', false)
          }
        })
      }
      
      return{
        handleClick
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