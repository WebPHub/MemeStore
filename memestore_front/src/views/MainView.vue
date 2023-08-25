<template>
    <!-- <section class="section">
    <div class="container">
      <h1 class="title">
        나만의 밈 제작 
      </h1>
      <p class="subtitle">
         <strong>나만의 밈을 제작 해보러갈까요?</strong>
      </p>
    </div>
  </section> -->

    <!-- <nav class="level">
    <div class="level-left">
      <div class="level-item">
        <p class="subtitle is-5">
          <strong></strong> 
        </p>
      </div>
    </div>
    <div class="level-right">
      <p class="level-item"><strong>All</strong></p>
      <p class="level-item"><a class="button is-success">New post</a></p>
    </div>
  </nav> -->
    <LogoutNav />
    <div class="container has-text-centered">
        <div class="level-item">
            <div class="field has-addons" style="margin-bottom: 100px">
                <p class="control">
                    <input class="input is-medium" type="text " placeholder="밈구경하러가기" />
                </p>
                <p class="control">
                    <button class="button is-medium">검색</button>
                </p>
            </div>
        </div>

        <p class="title has-text-link">여러분들이 원하는 밈을 제작하여 보세요</p>
        <button class="button is-large" v-on:click="moveCreateMemePage()">생성하기</button>
    </div>
    <hr />

    <div class="container">
        <div class="columns is-multiline" v-for="(a, i) in searchedDataList" :key="i">
            <div class="column">
                <figure class="image is-square"><img :src="a.img" style="border: 1px solid black" /></figure>
                <div class="columns">
                    <div class="column">{{ a.title }}</div>
                    <div class="column" style="text-align: right">♥{{ a.likeCount }}</div>
                </div>
            </div>
            <div class="column">
                <figure class="image is-square">
                    <img
                        src="https://i.pinimg.com/originals/e9/eb/2c/e9eb2c29b012de0706ef2b022b6b1738.jpg"
                        alt="이미지 1"
                    />
                </figure>
                <div class="columns">
                    <div class="column">집가고싶다</div>
                    <div class="column" style="text-align: right">♥201</div>
                </div>
            </div>
            <div class="column">
                <figure class="image is-square">
                    <img
                        src="https://i.pinimg.com/originals/e4/0c/a0/e40ca0836c4a4a477bc6d723dc7d457d.jpg"
                        alt="이미지 2"
                    />
                </figure>
                <div class="columns">
                    <div class="column">노래듣는고양이</div>
                    <div class="column" style="text-align: right">♥304929</div>
                </div>
            </div>
            <div class="column">
                <figure class="image is-square">
                    <img
                        src="https://i.pinimg.com/originals/c9/35/72/c935727e51f18bc7ec4fee7aba8da231.jpg"
                        alt="이미지 3"
                    />
                </figure>
                <div class="columns">
                    <div class="column">귀요미</div>
                    <div class="column" style="text-align: right">♥1004</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
    import LogoutNav from "@/components/LogoutNav.vue";
    import axios from "axios";
    import { onMounted, ref } from "vue";

    const searchedDataList = ref([]);

    onMounted(() => {
        getAllImgData();
    });

    const moveCreateMemePage = () => {
        window.location.href = "/v/creatememe";
    };

    const getAllImgData = () => {
        axios
            .get("/memeimg/find")
            .then((response) => {
                console.log(response);
                if (response.data.msg != undefined) {
                    alert(response.data.msg);
                    return;
                }
                setImgLikeCount(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const setImgLikeCount = (data) => {
        data.forEach((d) => {
            axios
                .get("/like?imgId=" + d.id)
                .then((response) => {
                    console.log(response.data);
                    if (response.data.msg != undefined) {
                        alert(response.data.msg);
                        return;
                    }
                    searchedDataList.value.push({
                        imgId: d.id,
                        title: d.title,
                        img: "data:image/png;base64," + d.img,
                        likeCount: response.data,
                    });
                })
                .catch((error) => {
                    console.log(error);
                });
        });
    };
</script>

<style lang="scss">
    @import "../../node_modules/bulma/bulma.sass";
</style>
