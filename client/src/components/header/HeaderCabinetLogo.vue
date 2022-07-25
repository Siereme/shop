<template>
  <div class="header-cabinet-logo">
      <div class="header-nav-icon-wrapper">
        <svg class="icon icon--19x19 " viewBox="0 0 15 15">
            <path d="M7.5 7.60588C8.49456 7.60588 9.44839 7.23382 10.1517 6.57154C10.8549 5.90927 11.25 5.01103 11.25 4.07443C11.25 3.13783 10.8549 2.23959 10.1517 1.57731C9.44839 0.915032 8.49456 0.542969 7.5 0.542969C6.50544 0.542969 5.55161 0.915032 4.84835 1.57731C4.14509 2.23959 3.75 3.13783 3.75 4.07443C3.75 5.01103 4.14509 5.90927 4.84835 6.57154C5.55161 7.23382 6.50544 7.60588 7.5 7.60588ZM10 4.07443C10 4.69883 9.73661 5.29765 9.26777 5.73917C8.79893 6.18069 8.16304 6.42873 7.5 6.42873C6.83696 6.42873 6.20107 6.18069 5.73223 5.73917C5.26339 5.29765 5 4.69883 5 4.07443C5 3.45003 5.26339 2.8512 5.73223 2.40968C6.20107 1.96816 6.83696 1.72012 7.5 1.72012C8.16304 1.72012 8.79893 1.96816 9.26777 2.40968C9.73661 2.8512 10 3.45003 10 4.07443ZM15 13.4916C15 14.6688 13.75 14.6688 13.75 14.6688H1.25C1.25 14.6688 0 14.6688 0 13.4916C0 12.3145 1.25 8.78303 7.5 8.78303C13.75 8.78303 15 12.3145 15 13.4916ZM13.75 13.4869C13.7487 13.1974 13.5575 12.3263 12.71 11.5282C11.895 10.7607 10.3612 9.96019 7.5 9.96019C4.6375 9.96019 3.105 10.7607 2.29 11.5282C1.4425 12.3263 1.2525 13.1974 1.25 13.4869H13.75Z" fill="#0595e6"></path>
        </svg>
      </div>
      <span class="header-nav-title">{{title}}</span>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
export default defineComponent({
    name: 'HeaderCabinetLogo',
    props: {
      title: {
          default: () => {}
      }
    },
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
.header-cabinet-logo {
  position: relative;
}
.header-nav-icon-wrapper{
  display: flex;
  align-items: center;
  justify-content: center;
  width: 28px;
  height: 28px;
}
.header-cabinet-logo svg{
    width: 22px;
    height: 22px;
}
.header-nav-title{
    color: #0595e6;
    font-size: 14px;
    line-height: 20px;
    margin-top: 2px;
    height: 20px;
}
</style>