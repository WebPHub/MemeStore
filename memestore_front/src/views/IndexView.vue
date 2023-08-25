<template>
    <LoginNav />
    <!-- 수치화통계바 -->
    <nav class="level">
        <div class="level-item has-text-centered">
            <div>
                <p class="heading">Tweets</p>
                <p class="title has-text-link">3,456</p>
            </div>
        </div>
        <div class="level-item has-text-centered">
            <div>
                <p class="heading">Following</p>
                <p class="title has-text-link">123</p>
            </div>
        </div>
        <div class="level-item has-text-centered">
            <div>
                <p class="heading">Followers</p>
                <p class="title has-text-link">456K</p>
            </div>
        </div>
        <div class="level-item has-text-centered">
            <div>
                <p class="heading">Likes</p>
                <p class="title has-text-link">789</p>
            </div>
        </div>
    </nav>
    <!-- 선두 웹 아이덴티티 소개글 -->
    <div>
        <section class="section is-medium">
            <h1 class="title has-text-link has-text-weight-bold is-size-3" style="text-align: center">
                자신의 개성을 드러내는 가장 완벽한 플랫폼
            </h1>
            <h2 class="subtitle" style="text-align: center">
                Show your personality on our site. <br />
                Unfold my own personality on the MemeStore stage.
            </h2>
        </section>
    </div>
    <div class="container">
        <div class="columns">
            <div class="column">
                <figure class="image is-square">
                    <img
                        src="https://i.pinimg.com/originals/f5/73/12/f57312adca2f5c9c50037bb743709990.jpg"
                        alt="이미지 1"
                    />
                </figure>
            </div>
            <div class="column">
                <figure class="image s-square">
                    <img
                        src="https://i.pinimg.com/originals/8e/0d/99/8e0d99336b486c4f8b2a9e81735aee93.jpg"
                        alt="이미지 2"
                    />
                </figure>
            </div>
            <div class="column">
                <figure class="image is-square">
                    <img
                        src="https://i.pinimg.com/originals/4a/6b/c5/4a6bc50e3a1fc78c255697e821fd1930.jpg"
                        alt="이미지 3"
                    />
                </figure>
            </div>
        </div>
    </div>
    <!-- 웹 제공서비스 소개 -->
    <div class="content is-normal has-text-centered" style="height: 500px; display: flex">
        <div style="margin: auto">
            <p class="has-text-link has-text-weight-bold is-size-2">Meme Store</p>
            <p>
                자기주도적 MZ세대의 필수 트렌드 마케팅! <br />
                자신의 개성을 드러내는 MZ세대의 욕망! <br />
                대중과 공유하며 전국적·세계적으로 확산!
                <br />
            </p>
            <ul style="display: inline-block; text-align: left">
                <li>50+ 무료 템플릿 제공</li>
                <li>자신의 개성에 맞는 폰트 디자인까지</li>
                <li>쉽고 간편한 밈 커스터마이징</li>
                <li>월간 추천수를 통계하여 소정의 상품 제공</li>
            </ul>
        </div>
    </div>

    <!-- 웹 제공서비스 소개 2 -->
    <div
        class="has-text-white has-background-link p-4"
        style="line-height: 18px; font-weight: 750; height: 400px; display: flex"
    >
        <div class="content" style="font-size: 32px; margin: auto; text-align: center">
            <p>밈 플레이스는</p>
            <p>다양한 리소스와</p>
            <p>커뮤니티 공간 제공으로</p>
            <p>고객의 개성과</p>
            <p>사회의 유행을</p>
            <p>선도하는 기업으로 자리합니다</p>
        </div>
    </div>
    <!-- 맨 밑 하단 간략 정보 -->
    <hr />
    <div class="centered" style="margin-bottom: 20px">
        <div class="item">
            <p style="font-size: 14px">
                (주) Meme Project Place <br />
                사업자 등록번호 : 123 <br />
                만든이 : 강경령 김도현 김예림 이상준 <br />
                주최자 : Project Club <br />
                고객센터 : Project 4 Team <br />
            </p>
        </div>
    </div>
</template>

<script setup>
    import LoginNav from "@/components/LoginNav.vue";
</script>

<style lang="scss">
    @import "../../node_modules/bulma/bulma.sass";

    .centered {
        display: table;
        margin-left: auto;
        margin-right: auto;
    }
</style>
