import { createRouter, createWebHistory } from "vue-router";

const routes = [
    {
        name: "Home",
        path: "/v/home",
        component: () => import("../views/IndexView.vue"),
    },
    {
        name: "Login",
        path: "/v/login",
        component: () => import("../views/LoginView.vue"),
    },
    {
        name: "SignUp",
        path: "/v/signup",
        component: () => import("../views/SignUpView.vue"),
    },
    {
        name: "Main",
        path: "/v/main",
        component: () => import("../views/MainView.vue"),
    },
    {
        name: "CreateMeme",
        path: "/v/creatememe",
        component: () => import("../views/CreateMemeView.vue"),
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
