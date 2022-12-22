<template>
  <div class="main">
      <Header :categories="categories" />
      <router-view></router-view>
      <Footer/>
      <AuthorizationModal/>
      <Preloader v-if="isLoading" />
  </div>
</template>

<script>
import { defineComponent } from 'vue'
// import auth from "@/utils/authorization"
import api from "@/api/backend-api"
import {useStore} from "vuex"
import {computed} from 'vue'
import Header from "./components/header/Header.vue"
import Footer from './components/footer/Footer.vue'
import AuthorizationModal from './components/auth/AuthorizationModal.vue'
import Preloader from './components/form/Preloader.vue'

export default defineComponent({
  name: 'App',
  components: {
    Header,
    Footer,
    AuthorizationModal,
    Preloader
},
  setup() {
    // onMounted(() => {
      // auth.handleAuthInit()
    // })

    api.loadMain()
    api.loadCartProducts()

    const store = useStore();
    let categories = computed(() => store.state.category.categories)
    let isLoading = computed(() => store.state.main.isLoading)

    return {
      categories,
      isLoading
    }
  }
})
</script>

<style>
html, body{
  height: 100%;
  width: 100%;
  margin: 0;
  overflow-x: hidden;
}
.icon--20x20{
  width: 20px;
  height: 20px;
}
.icon--24x24{
  width: 24px;
  height: 24px;
}
.icon--18x18{
  width: 18px;
  height: 18px;
}
.icon--17x17{
  width: 17px;
  height: 17px;
}
#app {
  font-family: 'PT Sans',sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#app, .main{
  height: 100%;
  min-height: 100%;
}
a:hover{
  color: #06a4fd;
}
</style>
