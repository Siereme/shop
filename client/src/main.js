import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import interceptors from './api/interceptors'

interceptors(store)
createApp(App).use(store).use(router).mount('#app')