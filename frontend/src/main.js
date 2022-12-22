import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import interceptors from './api/interceptors'

interceptors(store)
createApp(App).use(store).use(router).mount('#app')