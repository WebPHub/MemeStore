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
    <div id="selectModal" class="modal is-open">
        <div class="modal-background" />
        <div class="modal-content">
            <!-- Any other Bulma elements you want -->
            <div class="content">
                <div id="templateContent" class="white-bg" style="text-align: center">
                    <img id="selectImg" class="is-square" style="border: 1px solid black" />
                    <h2 id="selectTitle"></h2>
                    <button id="likeBtn" class="button" @click="addRemoveLike()">♥</button>
                    <br />
                    <br />
                    <span id="likeCnt"></span>
                </div>
            </div>
        </div>
        <button
            id="closeTemplateBtn"
            class="modal-close is-large"
            aria-label="close"
            v-on:click="closeTemplateModal"
        ></button>
    </div>
    <div class="container has-text-centered">
        <div class="level-item">
            <div class="field has-addons" style="margin-bottom: 100px; width: 500px">
                <input
                    id="searchTitle"
                    class="input is-medium"
                    type="text "
                    placeholder="제목 입력"
                    style="width: 70%"
                />
                <button class="button is-medium" v-on:click="searchMeme()">검색</button>
            </div>
        </div>
        <hr />
        <div style="margin-top: 50px; margin-bottom: 50px">
            <p class="title has-text-link">여러분들이 원하는 밈을 제작하여 보세요</p>
            <button class="button is-large" v-on:click="moveCreateMemePage()">생성하기</button>
        </div>
        <hr />
    </div>

    <div class="container">
        <div class="columns is-multiline">
            <div class="column is-4" v-for="(a, i) in searchedDataList" :key="i">
                <img :src="a.img" class="is-square" style="border: 1px solid black" @click="showDetailImg(a)" />
                <div class="columns">
                    <div class="column">{{ a.title }}</div>
                    <div class="column" style="text-align: right">♥{{ a.likeCount }}</div>
                </div>
            </div>
            <!-- <div class="column">
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
            </div> -->
        </div>
    </div>
</template>

<script setup>
    import LogoutNav from "@/components/LogoutNav.vue";
    import axios from "axios";
    import { onMounted, ref } from "vue";

    const searchedDataList = ref([]);
    const selectData = ref();
    let detailModal;
    let likebtn;
    let detailData;

    onMounted(() => {
        detailModal = document.getElementById("selectModal");
        likebtn = document.getElementById("likeBtn");
        getAllImgData();
    });

    const moveCreateMemePage = () => {
        window.location.href = "/v/creatememe";
    };

    const searchMeme = () => {
        let title = document.getElementById("searchTitle").value;

        searchedDataList.value = [];

        if (title.trim() === "") getAllImgData();
        else {
            axios
                .get("/memeimg/find/title?title=" + title)
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
        }
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
                    //console.log(response.data);
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

    const showDetailImg = (data) => {
        detailData = data;
        detailModal.classList.add("is-active");
        let img = document.getElementById("selectImg");
        img.setAttribute("src", data.img);
        document.getElementById("selectTitle").innerHTML = data.title;
        document.getElementById("likeCnt").innerHTML = "좋아요 : " + data.likeCount;

        axios
            .get("/like/user?userId=" + localStorage.getItem("userid") + "&imgId=" + data.imgId)
            .then((response) => {
                console.log(response);
                //console.log(response.data);
                let data = response.data;

                if (data.id === null) {
                    likebtn.innerHTML = "♡";
                } else {
                    likebtn.innerHTML = "♥";
                }
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const addRemoveLike = () => {
        if (likebtn.innerHTML === "♡") {
            likebtn.innerHTML = "♥";
            addLikeData();
        } else {
            likebtn.innerHTML = "♡";
            deleteLikeData();
        }
    };

    const addLikeData = () => {
        let data = {
            memberid: localStorage.getItem("userid"),
            imgid: detailData.imgId,
        };
        axios
            .post("/like/up", data, {
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

                detailData.likeCount++;
                document.getElementById("likeCnt").innerHTML = "좋아요 : " + detailData.likeCount;
            })
            .catch((error) => {
                alert(error);
            });
    };

    const deleteLikeData = () => {
        let data = {
            memberid: localStorage.getItem("userid"),
            imgid: detailData.imgId,
        };
        axios
            .put("/like/down", data, {
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

                detailData.likeCount--;
                document.getElementById("likeCnt").innerHTML = "좋아요 : " + detailData.likeCount;
            })
            .catch((error) => {
                alert(error);
            });
    };

    const closeTemplateModal = () => {
        detailModal.classList.remove("is-active");
    };
</script>

<style lang="scss">
    @import "../../node_modules/bulma/bulma.sass";

    .columns {
        img {
            cursor: pointer;
        }
    }

    .white-bg {
        background-color: white;
        border-radius: 8px;
        padding: 20px;
    }
</style>
