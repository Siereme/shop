<template>
    <div class="preloader">
        <img :src="require('@/assets/preloaders/preloader_animated.svg')" alt="preloader" />
    </div>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
    name: 'PreloaderIcon',
    props: {}
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
  text-decoration: none;
}
.preloader{
    position: fixed;
    z-index: 99999999999;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    display: flex;
    justify-content: center;
    align-items: center;
}
.preloader img{
    display: block;
    width: 64px;
    height: 64px;
}
</style>