<template>
  <div class="header">
        <HeaderTop/>
        <div class="header-middle">
          <HeaderLogo/>
          <HeaderSearchForm />
          <HeaderNavigation/>
        </div>
        <div class="header-bottom">
          <HeaderCategoryList :categories="categories" />
        </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import HeaderTopVue from './HeaderTop.vue'
import HeaderLogoVue from './HeaderLogo.vue'
import HeaderNavigationVue from './HeaderNavigation.vue'
import HeaderCategoryListVue from './HeaderCategoryList.vue'
import HeaderSearchFormVue from './HeaderSearchForm.vue'

export default defineComponent({
    name: 'Header',
    components: {
      HeaderTop: HeaderTopVue,
      HeaderLogo: HeaderLogoVue,
      HeaderNavigation: HeaderNavigationVue,
      HeaderCategoryList: HeaderCategoryListVue,
      HeaderSearchForm: HeaderSearchFormVue
    },
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
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.header{
  box-shadow: 0 0 20px rgb(0 0 0 / 10%);
}
.header-middle{
  height: 90px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 30px;
}
.header-bottom{
  padding: 0 30px;
  border-top: 1px solid #eee;
}
</style>