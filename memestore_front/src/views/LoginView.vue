<template>
    <LoginNav />
    <div class="hero-body center has-text-centered" style="display: flex">
        <div class="login" style="margin: auto">
            <img src="../assets/memestore_logo.png" width="134" height="34" />
            <div class="field" style="width: 500px">
                <div class="control">
                    <input id="username" class="input is-medium is-rounded" type="text" placeholder="이름" />
                </div>
            </div>
            <div class="field">
                <div class="control">
                    <input
                        id="userpassword"
                        class="input is-medium is-rounded"
                        type="password"
                        placeholder="비밀번호"
                    />
                </div>
            </div>
            <br />
            <button class="button is-link is-fullwidth is-primary is-medium is-rounded" v-on:click="checkLogin()">
                로그인
            </button>
            <br />
            <nav class="level">
                <div class="level-item">
                    <div>
                        <a href="/v/signup">회원가입</a>
                    </div>
                </div>
            </nav>
        </div>
    </div>
</template>

<script setup>
    import LoginNav from "@/components/LoginNav.vue";
    import axios from "axios";

    const checkLogin = () => {
        let loginName = document.getElementById("username").value;
        let loginPw = document.getElementById("userpassword").value;
        // alert(name);

        if (loginName.trim() === "" || loginPw.trim() === "") {
            alert("아이디와 비밀번호를 입력해주세요.");
            return;
        }

        let data = {
            name: loginName,
            password: loginPw,
        };
        axios
            .post("/members/login", data, {
                headers: {
                    "Content-Type": "application/json",
                },
            })
            .then((response) => {
                console.log(response);
                if (response.data.msg != undefined) {
                    alert(response.data.msg);
                    return;
                }

                let user = response.data;
                localStorage.setItem("userid", user.id);
                window.location.href = "/v/main";
            })
            .catch((error) => {
                alert(error);
            });
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
