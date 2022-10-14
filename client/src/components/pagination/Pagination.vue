<template>
    <div class="pagination-container" v-if="shown">
      <div class="pagination-container__header">
      </div>
      <Paginate
        v-model="currentPage"
        :pageCount="pagesCount"
        :clickHandler="setPage"
        :prevText="'<'"
        :nextText="'>'"
      />
    </div>
  </template>
  
  <script>
  import { defineComponent } from 'vue'
  import { computed, watch, ref, onMounted } from 'vue'
  import { useStore } from "vuex"
  import Paginate from "vuejs-paginate-next"

  export default defineComponent({
      name: 'Pagination',
      components: {
        Paginate
      },
      props: {
        page: Number,
        handleClick: Function
      },
      setup(props) {
        const store = useStore()

        let pagesCount = computed(() => store.state.product.pagesCount)
        let currentPage = ref(1)
        
        onMounted(() => currentPage.value = +props.page)
        watch(
          () => props.page,
          () => currentPage.value = +props.page
        )
        
        let setPage = page => props.handleClick(page)
          
        let shown = computed(() => pagesCount.value && pagesCount.value > 1)

        return {
          pagesCount,
          currentPage,
          shown,
          setPage
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
    margin: 0;
  }
  li {
    display: inline-block;
    margin: 0;
  }
  a {
    color: #0595e6;
  }
  .pagination-container{
    padding: 40px 0 20px;
  }
  .pagination-container .page-link {
    box-shadow: 0 0 0px 0px rgba(0, 0, 0, 0.1);
    background: white !important;
    width: 30px;
    height: 30px;
    border: 1px solid #b8ced9;
    border-radius: 50px;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 6px;
    cursor: pointer;
    color: #353d4a;
  }
  .pagination-container .page-item.active .page-link,
  .pagination-container .page-link:hover{
    border-color: #06a4fd;
    color: #06a4fd;
  }
  </style>