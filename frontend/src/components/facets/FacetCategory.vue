<template>
  <div class="facet-category" v-if="shown">
    <div class="facet-header">
    </div>
    <ul>
        <li class="facet-category-item" :class="{'selected': mainCategory.id === currentCategory.id}" @click="handleSelectCategory(mainCategory)">
            {{mainCategory.name}}
        </li>
        <li class="facet-category-item" v-for="category in getParentCategories(mainCategory, currentCategory)" :class="{'selected': category.id === currentCategory.id}" :key="category.id" @click="handleSelectCategory(category)">
            {{category.name}}
        </li>
        <li class="facet-category-item facet-category-item__children" v-for="children in current.categories" :key="children.id" @click="handleSelectCategory(children)">
            {{children.name}}
        </li>
    </ul>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { computed, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

export default defineComponent({
    name: 'FacetCategory',
    props: {
        mainCategory: {
            default: () => {}
        },
        currentCategory: {
            default: () => {}
        }
    },
    setup(props) {
        const store = useStore()
        const router = useRouter()

        let handleSelectCategory = category => {
            store.commit('setIsLoading', true)
            router.push({name: 'CategoryPage', params: {id: category.id}, query: {}})
        }

        let current = ref(props.currentCategory)

        let getParentCategories = (category, currentCategory) => {
            if (category.id === currentCategory.id) {
                current.value = category
                return []
            } else if (category.categories) {
                for (var i = 0; i < category.categories.length; i++) {
                    var path = getParentCategories(category.categories[i], currentCategory)
                    if (path !== null) {
                        path.unshift(category.categories[i])
                        return path
                    }        
                }
            }
            return null;
        }

        const shown = computed(() => props.mainCategory.value !== null && props.currentCategory.value !== null)

        return {
            shown,
            current,
            handleSelectCategory,
            getParentCategories
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
  margin: 0;
}
li {
  display: inline-block;
  margin: 0;
}
a {
  color: #0595e6;
}
.facet-category {
    border: 1px solid #b8ced9;
    border-radius: 15px;
    padding: 20px 15px;
    margin-bottom: 30px;
}
.facet-category ul{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
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