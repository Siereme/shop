import { createWebHistory, createRouter } from "vue-router"
import Home from "@/components/Home.vue"
import CategoryPage from "@/components/CategoryPage.vue"
import SearchPage from "@/components/SearchPage.vue"
import CheckoutPage from '@/components/CheckoutPage.vue'
import OrderPage from '@/components/OrderPage.vue'
import PersonalCabinet from '@/components/PersonalCabinet.vue'
import UserInfo from '@/components/cabinet/UserInfo.vue'
import OrdersHistory from '@/components/cabinet/OrdersHistory.vue'
import store from "@/store"
import { computed } from "vue"

const userRole = computed(() => store.getters.getUserRole())

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/category/:id",
    name: "CategoryPage",
    component: CategoryPage,
    props: route => ({
      categoryId: route.params.id,
      page: route.query.page
    })
  },
  {
    path: "/search/?query=:term",
    name: "SearchPage",
    component: SearchPage,
    props: route => ({
      term: route.params.term,
      page: route.query.page
    })
  },
  {
    path: "/checkout",
    name: "CheckoutPage",
    component: CheckoutPage
  },
  {
    path: "/order-success",
    name: "OrderPage",
    component: OrderPage
  },
  {
    path: "/cabinet",
    name: "PersonalCabinet",
    component: PersonalCabinet,
    children: [
      {
        path: "user",
        components: {
          CabinetContainer: UserInfo
        }
      },
      {
        path: "orders-history",
        components: {
          CabinetContainer: OrdersHistory
        }
      }
    ]
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior () {
    return { top: 0 }
  }
});

const getUserRole = () => new Promise(resolve => {
  if (userRole.value === undefined) {
    const unwatch = store.watch(
      () => userRole.value,
      (value) => {
        unwatch()
        resolve(value)
      }
    )
  } else {
    resolve(userRole.value)
  }
})

router.beforeEach(async (to) => {
  const checkPages = ['/cabinet'];
  const userRole = await getUserRole()
  const authRequired = checkPages.some(page => to.path.includes(page)) &&  userRole === 'ANONYMOUS';

  if (authRequired) {
      return '/';
  }
});


export default router;