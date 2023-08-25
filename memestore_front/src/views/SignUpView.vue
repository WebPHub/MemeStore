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
                        <input class="input is-success" type="text" placeholder="너의 별명" value="bulma" />
                        <span class="icon is-small is-left">
                            <i class="fa fa-user"></i>
                        </span>
                        <span class="icon is-small is-right">
                            <i class="fa fa-check"></i>
                        </span>
                    </div>
                    <p class="help is-success">This username is available</p>
                </div>
                <div class="field">
                    <label class="label">비밀번호</label>
                    <div class="control has-icons-left has-icons-right">
                        <input class="input is-danger" type="비밀번호" placeholder="비밀번호" value="*******" />
                        <span class="icon is-small is-left">
                            <i class="fa fa-envelope"></i>
                        </span>
                        <span class="icon is-small is-right">
                            <i class="fa fa-warning"></i>
                        </span>
                    </div>
                    <p class="help is-danger"></p>
                </div>
                <div class="field">
                    <label class="label">비밀번호확인</label>
                    <div class="control">
                        <input class="input" type="text" placeholder="위와 똑같은 비밀번호를 작성하시오 " />
                    </div>
                </div>
                <div class="field">
                    <div class="control">
                        <label class="checkbox">
                            <input type="checkbox" />
                            개인정보수집 및 활용에 동의합니다.
                        </label>
                    </div>
                </div>
                <div class="field is-grouped">
                    <div class="control">
                        <button class="button is-link">가입하기</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script setup>
    import LoginNav from "@/components/LoginNav.vue";

    const checkSignUp = () => {};
</script>

<style lang="scss">
    @import "../../node_modules/bulma/bulma.sass";
    @import url("https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css");
</style>
