<template>
    <LoginNav />
    <div class="hero-body center has-text-centered" style="display: flex">
        <div class="login" style="margin: auto">
            <img src="../assets/memestore_logo.png" width="134" height="34" />
            <form style="width: 500px">
                <div class="field">
                    <div class="control">
                        <input class="input is-medium is-rounded" type="text" placeholder="이름" required />
                    </div>
                </div>
                <div class="field">
                    <div class="control">
                        <input
                            class="input is-medium is-rounded"
                            type="password"
                            placeholder="비밀번호"
                            autocomplete="current-password"
                            required
                        />
                    </div>
                </div>
                <br />
                <button class="button is-link is-fullwidth is-primary is-medium is-rounded" v-on:click="checkLogin()">
                    로그인
                </button>
            </form>
            <br />
            <nav class="level">
                <div class="level-item">
                    <div>
                        <a href="/signup">회원가입</a>
                    </div>
                </div>
            </nav>
        </div>
    </div>
</template>

<script setup>
    import LoginNav from "@/components/LoginNav.vue";

    const checkLogin = () => {
            
        window.location.href = "/v/main";
    };
</script>

<style lang="scss">
    @import "../../node_modules/bulma/bulma.sass";
    .center {
        display: flex;
        justify-content: center; /* 수평 가운데 정렬 */
        align-items: center; /* 수직 가운데 정렬 */
    }
</style>
