import { createWebHistory, createRouter } from "vue-router"
import Home from "@/components/Home.vue"
import CategoryPage from "@/components/CategoryPage.vue"
import CheckoutPage from '@/components/CheckoutPage.vue'
import OrderPage from '@/components/OrderPage.vue'
import PersonalCabinet from '@/components/PersonalCabinet.vue'
import UserInfo from '@/components/cabinet/UserInfo.vue'
import OrdersHistory from '@/components/cabinet/OrdersHistory.vue'

// import store from "@/store";
// import category from "@/store/modules/category/category";

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
      categoryId: route.params.id
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
});


export default router;