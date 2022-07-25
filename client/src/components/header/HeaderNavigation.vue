<template>
  <div class="header-navigation" @mouseleave="handleModalShown(false)">
    <CartLogo @mouseover="handleModalShown(true)" />
    <CabinetLogo :title="title" @mouseover="handleModalShown(false)" @click="handleCabinetClick()"/>
    <CartModal v-if="shown" />
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import {useStore} from "vuex"
import { useRouter } from 'vue-router'
import {computed} from 'vue'
import HeaderCabinetLogoVue from "./HeaderCabinetLogo"
import HeaderCartLogoVue from "./HeaderCartLogo"
import CartModal from '../cart/CartModal.vue'
export default defineComponent({
    name: 'HeaderNavigation',
    components: {
      CabinetLogo: HeaderCabinetLogoVue,
      CartLogo: HeaderCartLogoVue,
      CartModal
    },
    setup() {
      const store = useStore()
      const router = useRouter()

      const shown = computed(() => store.getters.getCartModalShown())

      let handleModalShown = (shown) => store.commit('setCartModalShown', shown) 


      let title = computed(
        () => store.getters.getUserStatus() === 'ACTIVE'
        ? store.getters.getUserName()
        : 'Войти'
      )

      let showAuthModal = () => store.commit('setShownAuthModal', 'login')

      let handleCabinetClick = () => title.value === 'Войти' ? showAuthModal() : router.replace({path: '/cabinet/user'})

      return {
        shown,
        title,
        handleModalShown,
        showAuthModal,
        handleCabinetClick
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
.header-navigation{
    display: flex;
    position: relative;
    margin-bottom: -10px;
}
.header-navigation .header-cart-logo{
    margin-right: 25px;
}
.header-navigation div {
    display: flex;
    align-items: center;
    cursor: pointer;
    flex-direction: column;
}
</style>