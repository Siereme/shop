<template>
  <div class="banners-wrapper">
    <Banner v-for="(imageLink, index) in imageLinks" :key="index" :imageLink="imageLink" />
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import BannerVue from './Banner.vue'

export default defineComponent({
    name: 'BannersWrapper',
    components: {
      Banner: BannerVue
    },
    setup() {
        let imageLinks = [
                require('@/assets/home/banner_1.jpg'),
                require('@/assets/home/banner_2.jpg'),
                require('@/assets/home/banner_3.jpg'),
                require('@/assets/home/banner_4.jpg')
        ]
        return {
            imageLinks
        }
    }
})
</script>

<style>
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
.banners-wrapper{
    display: flex;
    flex-wrap: wrap;
    margin: 0 auto;
    max-width: 1500px;
    padding: 10px 30px 40px;
}
</style>