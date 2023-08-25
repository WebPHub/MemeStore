<template>
    <LoginNav />
    <section class="section">
        <div class="columns">
            <div class="column is-4 is-offset-4">
                <div class="field">
                    <!-- <label class="label">Name</label>
                  <div class="control">
                     <input class="input" type="text" placeholder="Text input">
                  </div> -->
                </div>
                <div class="field">
                    <label class="label">닉네임</label>
                    <div class="control has-icons-left has-icons-right">
                        <input id="newname" class="input" type="text" placeholder="닉네임 입력" />
                        <span class="icon is-small is-left">
                            <i class="fa fa-user"></i>
                        </span>
                    </div>
                    <!-- <p class="help is-success">This username is available</p> -->
                </div>
                <div class="field">
                    <label class="label">비밀번호</label>
                    <div class="control has-icons-left has-icons-right">
                        <input id="userpassword" class="input" type="password" placeholder="비밀번호" />
                        <span class="icon is-small is-left">
                            <i class="fa fa-envelope"></i>
                        </span>
                    </div>
                    <!-- <p class="help is-danger"></p> -->
                </div>
                <div class="field">
                    <label class="label">비밀번호확인</label>
                    <div class="control">
                        <input id="checkpassword" class="input" type="password" placeholder="비밀번호 확인" />
                    </div>
                </div>
                <div class="field is-grouped">
                    <div class="control">
                        <button class="button is-link" @click="checkSignUp()">가입하기</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script setup>
    import LoginNav from "@/components/LoginNav.vue";
    import axios from "axios";

    const checkSignUp = () => {
        let username = document.getElementById("newname").value;
        let userpassword = document.getElementById("userpassword").value;
        let checkpassword = document.getElementById("checkpassword").value;

        if (username.trim() === "" || userpassword.trim() === "" || checkpassword.trim() === "") {
            alert("공백이 존재합니다.");
            return;
        }

        if (userpassword.trim() !== checkpassword.trim()) {
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }

        let data = {
            name: username,
            password: userpassword,
        };

        axios
            .post("/members/new", data, {
                headers: {
                    "Content-Type": "application/json",
                },
            })
            .then((response) => {
                if (response.data.msg != undefined) {
                    alert(response.data.msg);
                    return;
                }
                alert("회원가입이 완료되었습니다!");
                window.location.href = "/v/home";
            })
            .catch((error) => {
                alert(error);
            });
    };
</script>

<style lang="scss">
    @import "../../node_modules/bulma/bulma.sass";
    @import url("https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css");
</style>
