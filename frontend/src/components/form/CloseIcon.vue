<template>
    <div class="close-icon">
        <svg fill="#0595e6" xmlns="http://www.w3.org/2000/svg"  viewBox="0 0 24 24" width="24px" height="24px"><path d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z"/></svg>
    </div>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
    name: 'CloseIcon',
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
.close-icon{
    display: block;
    width: 19px;
    height: 19px;
    position: absolute;
    right: 0;
    top: 0;
    margin: 24px;
    cursor: pointer;
}
</style>