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
import { defineComponent, onMounted } from 'vue'
import auth from "@/utils/authorization"
// import api from "@/api/backend-api"
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
    onMounted(() => {
      auth.handleAuthInit()
      var userDTO = {"id":1,"name":"0cf7015d-c321-48fa-a99f-df3f2ea7853c","surname":"f2231eb5-3fd7-43c2-a673-72811123e750","patronymic":"6f2fef22-c888-489d-8fa0-b8175892ee0e","email":"9cfa8698-c119-4c3e-84f4-9148bc0a2501","password":"$2a$12$gIo6A.9S6WG2P8juBnuYde0SD7UDI39IswflIznzi/A8IuV8BD2E.","phone":"94a8300d-9fce-4de8-b803-d1570d870f3c","role":{"id":3,"name":"ANONYMOUS"},"status":"ANONYMOUS"}
      store.commit('setUser', userDTO)
      // api.loadMain()
      // api.loadCartProducts()
    })


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
