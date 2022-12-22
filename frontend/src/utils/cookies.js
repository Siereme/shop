import { useCookies, globalCookiesConfig  } from "vue3-cookies";

globalCookiesConfig ({
    expireTimes: "30d",
    path: "/",
    domain: "",
    secure: true,
    sameSite: "None",
})

const { cookies } = useCookies();

let getCookie = (name) => cookies.get(name)

let deleteCookie = name => {
    cookies.remove(name)
}

let setCookie = (name, value) => {
    deleteCookie(name)
    cookies.set(name, value)
}

export default {
    getCookie,
    deleteCookie,
    setCookie,
}