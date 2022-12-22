<template>
  <div class="banner">
    <a href="#" class="banner-link">
        <img :src="imageLink" alt="" />
    </a>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
export default defineComponent({
    name: 'Banner',
    props: {
        imageLink: {
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
.banner {
    max-width: calc(50% - 23px);
    position: relative;
    width: 100%;
    padding-left: 15px;
    padding-right: 8px;
    margin: 15px auto 0;
}
.banner a{
    height: 330px;
    max-width: 770px;
    display: block;
    border-radius: 10px;
    overflow: hidden;
}
.banner img{
    max-width: auto;
    height: 100%;
}
</style>