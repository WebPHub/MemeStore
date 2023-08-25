<template>
    <LogoutNav />
    <div id="selectModal" class="modal is-open">
        <div class="modal-background" />
        <div class="modal-content">
            <!-- Any other Bulma elements you want -->
            <div class="content">
                <h1 style="margin: 0; padding: 40px; padding-bottom: 20px">템플릿 사진 선택하기</h1>
                <div class="columns">
                    <div class="column is-half">
                        <!-- <input id="topicinput" class="input" type="text" placeholder="주제 입력" /> -->
                    </div>
                    <div class="column dropdown modalorientation" v-on:click="openCloseDropDown('modalorientation')">
                        <div class="dropdown-trigger">
                            <button
                                class="button modalorientation"
                                aria-haspopup="true"
                                aria-controls="dropdown-menu"
                                v-on:blur="openCloseDropDown('modalorientation')"
                            >
                                <span>{{ orientationType }}</span>
                                <span class="icon is-small"> > </span>
                            </button>
                            <div class="dropdown-menu is-absolute" id="dropdown-menu" role="menu">
                                <div class="dropdown-content">
                                    <a class="dropdown-item" href="#" @mousedown="changeOrientation('가로')">가로</a>
                                    <hr class="dropdown-divider" />
                                    <a class="dropdown-item" href="#" @mousedown="changeOrientation('세로')">세로</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="column">
                        <button class="button" v-on:click="getRandomImg()">검색하기</button>
                    </div>
                </div>
                <div id="templateContent" class="white-bg columns is-multiline"></div>
            </div>
        </div>
        <button
            id="closeTemplateBtn"
            class="modal-close is-large"
            aria-label="close"
            v-on:click="closeTemplateModal"
        ></button>
    </div>
    <div class="container is-widescreen">
        <div class="buttons is-right">
            <label for="uploadBtn">
                <p class="button is-responsive">사진 불러오기</p>
            </label>
            <input id="uploadBtn" type="file" style="display: none" accept="image/*" v-on:change="uploadImg" />
            <button id="openTemplateBtn" class="button is-responsive" v-on:click="openTemplateModal">
                템플릿 사진 선택하기
            </button>
            <button class="button is-responsive" @click="addTextField()">텍스트 추가하기</button>
            <button class="button is-responsive" @click="saveMemeImage()">저장하기</button>
        </div>
        <div class="box myfont">
            <div class="dropdown boxfontname" v-on:click="openCloseDropDown('boxfontname')">
                <div class="dropdown-trigger">
                    <button
                        class="button boxfontname"
                        aria-haspopup="true"
                        aria-controls="dropdown-menu"
                        v-on:blur="openCloseDropDown('boxfontname')"
                    >
                        <span>{{ nowFontName }}</span>
                        <span class="icon is-small"> > </span>
                    </button>
                    <div class="dropdown-menu" id="dropdown-menu" role="menu">
                        <div class="dropdown-content">
                            <a class="dropdown-item" href="#" @mousedown="changeFontName('나눔고딕', $event)"
                                >나눔고딕</a
                            >
                            <a class="dropdown-item" href="#" @mousedown="changeFontName('궁서', $event)">궁서</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="dropdown boxfontsize" v-on:click="openCloseDropDown('boxfontsize')">
                <div class="dropdown-trigger">
                    <button
                        class="button boxfontsize"
                        aria-haspopup="true"
                        aria-controls="dropdown-menu"
                        v-on:blur="openCloseDropDown('boxfontsize')"
                    >
                        <span>{{ nowFontSize }}</span>
                        <span class="icon is-small"> > </span>
                    </button>
                    <div class="dropdown-menu" id="dropdown-menu" role="menu">
                        <div class="dropdown-content">
                            <a
                                class="dropdown-item"
                                v-for="(k, i) in 10"
                                :key="i"
                                @mousedown="changeFontSize(20 + k * i, $event)"
                                >{{ 20 + k * i }}</a
                            >
                        </div>
                    </div>
                </div>
            </div>

            <input id="userTextColor" type="color" class="button" value="#000000" v-on:input="setTextFieldColor()" />

            <button
                id="styleBoldBtn"
                class="button is-responsive"
                style="font-weight: bold"
                v-on:click="clickFontStyle($event, 0)"
            >
                T
            </button>
            <button
                id="styleItalicBtn"
                class="button is-responsive"
                style="font-style: italic"
                v-on:click="clickFontStyle($event, 1)"
            >
                T
            </button>
            <button
                id="styleUnderBtn"
                class="button is-responsive"
                style="text-decoration: underline"
                v-on:click="clickFontStyle($event, 2)"
            >
                T
            </button>
        </div>
        <div id="memepaintbg">
            <div id="memepaint" ref="memecapture"></div>
        </div>
        <div id="drawncomponents" class="box">
            <div v-for="(k, i) in uploadedElements" :key="i">
                <div class="columns">
                    <span class="column is-four-fifths">{{ k }}</span>
                    <div class="column">
                        <button class="button" v-on:click="frontComponent(i)">앞으로</button>
                    </div>
                    <div class="column">
                        <button class="button" v-on:click="backComponent(i)">뒤로</button>
                    </div>
                    <div class="column">
                        <button class="button is-danger" v-on:click="removeComponent(i)">지우기</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
    import LogoutNav from "@/components/LogoutNav.vue";
    import axios from "axios";
    import html2canvas from "html2canvas";
    import { ref, onMounted } from "vue";

    //그림판 Background 관련 변수
    let memePaint;
    let memePaintBg;

    //업로드 버튼
    let uploadBtn;

    //폰트 색상 변경 버튼
    let colorPickBtn;

    //사용자가 화면에 그린 Elements ID를 저장할 리스트
    const uploadedElements = ref([]);
    //고유 id 카운팅 변수
    let imgIdCount = 1;
    let textIdCount = 1;

    //템플릿 Modal 관련 변수
    let selectModal;
    let templateContent;
    const orientationType = ref("가로");

    //Unspalsh API 데이터 변수
    const accessKey = "Cx18IXqrM-Q0N_8X4fj-CvzsHouR-w0FEFeFvx_BlFY";
    const proxyUrl = "https://cors-anywhere.herokuapp.com/";
    const apiUrl = "https://api.unsplash.com/";

    onMounted(() => {
        //컴포넌트들이 다 들어온 경우
        //HTML 요소 읽어들이기
        uploadBtn = document.getElementById("uploadBtn");
        memePaint = document.getElementById("memepaint");
        memePaintBg = document.getElementById("memepaintbg");
        selectModal = document.getElementById("selectModal");
        templateContent = document.getElementById("templateContent");
        colorPickBtn = document.getElementById("userTextColor");
    });

    //이미지 업로드
    const uploadImg = () => {
        const files = uploadBtn.files;

        // 파일 타입 검사
        [...files].forEach((file) => {
            if (!file.type.match("image/*")) {
                alert("이미지 파일만 업로드가 가능합니다.");
                return;
            }
            const reader = new FileReader();
            reader.onload = (e) => {
                let img = new Image();
                img.src = e.target.result;
                img.onload = () => {
                    let result = createPaintImgElement(img);
                    memePaint.appendChild(result);
                    addActiveObj(result);
                };
            };
            reader.readAsDataURL(file);
        });
    };

    //오브젝트 이동 관련 변수
    let targetObj = null;
    let targetKey = 0;
    let originX, originY;
    let originLeft, originTop;

    //오브젝트 이미지 조정 관련 변수
    let objResizeType = "none";
    let objStartWidth = 0,
        objStartHeight = 0;

    //업로드 한 이미지 추가하기
    const createPaintImgElement = (resultImg) => {
        const img = document.createElement("img");
        img.setAttribute("id", "paintimg" + imgIdCount);
        img.setAttribute("class", "user-paint");
        img.setAttribute("src", resultImg.src);
        img.setAttribute("width", resultImg.width);
        img.setAttribute("height", resultImg.height);
        img.addEventListener("mousedown", (e) => {
            addActiveObj(img);
            startDrag(e, img, 1);
        });

        uploadedElements.value.push("paintimg" + imgIdCount++);
        return img;
    };

    // 드래그 시작
    function startDrag(e, obj, key) {
        //움직일 오브젝트 객체 변수 저장
        targetObj = obj;

        //움직일 오브젝트 타입 저장
        //1 - 이미지
        //2 - 텍스트박스
        targetKey = key;

        //이미 텍스트박스에 포커스가 있는 경우 이동 사용 막기
        if (targetKey == 2 && targetObj == document.activeElement) return;

        //현재 마우스 위치 저장
        originX = e.clientX;
        originY = e.clientY;
        originLeft = targetObj.offsetLeft;
        originTop = targetObj.offsetTop;

        //이벤트 활성화
        document.onmousemove = moveDrag;
        document.onmouseup = stopDrag;

        const objRect = targetObj.getBoundingClientRect();
        const rightBoundary = objRect.left + objRect.width;
        const bottomBoundary = objRect.top + objRect.height;
        const boundPadding = 20;

        if (targetKey == 1) {
            objStartWidth = targetObj.width;
            objStartHeight = targetObj.height;
        } else if (targetKey == 2) {
            objStartWidth = objRect.width;
            objStartHeight = objRect.height;
        }

        //크기 증가 or 단순 이동인지 확인
        if (originX >= rightBoundary - boundPadding && originY >= bottomBoundary - boundPadding) {
            objResizeType = "bottom-right";
        } else if (originX >= rightBoundary - boundPadding) {
            objResizeType = "right";
        } else if (originY >= bottomBoundary - boundPadding) {
            objResizeType = "bottom";
        } else {
            objResizeType = "none"; //단순 이동
        }

        //활성화 된 오브젝트 테두리 설정
        addActiveObj(targetObj);
    }

    // 이미지 움직이기
    function moveDrag(e) {
        var e_obj = window.event ? window.event : e;

        const { width: containerWidth, height: containerHeight } = memePaintBg.getBoundingClientRect();
        const { width: imgWidth, height: imgHeight } = targetObj.getBoundingClientRect();

        const diffX = e_obj.clientX - originX;
        const diffY = e_obj.clientY - originY;

        let resizeObj = targetObj;
        let resizeWidth = objStartWidth + diffX;
        let resizeHeight = objStartHeight + diffY;

        if (targetKey == 2) {
            resizeObj = targetObj.style;
            resizeWidth = resizeWidth + "px";
            resizeHeight = resizeHeight + "px";
        }

        if (objResizeType === "bottom-right") {
            resizeObj.width = resizeWidth;
            //텍스트박스가 아닌 경우에는 하단도 이동
            if (targetKey == 1) resizeObj.height = resizeHeight;
        } else if (objResizeType === "right") {
            resizeObj.width = resizeWidth;
        } else if (objResizeType === "bottom" && targetKey == 1) {
            resizeObj.height = resizeHeight;
        } else {
            //위치 이동
            //단, 특정 범위를 넘어가지 않도록 강제 조정
            const endOfXPoint = containerWidth - imgWidth;
            const endOfYPoint = containerHeight - imgHeight;
            targetObj.style.left = Math.min(Math.max(0, originLeft + diffX), endOfXPoint) + "px";
            targetObj.style.top = Math.min(Math.max(0, originTop + diffY), endOfYPoint) + "px";
        }

        return false;
    }

    // 드래그 멈추기
    function stopDrag(e) {
        //이벤트 비활성화
        objResizeType = "none";
        document.onmousemove = null;
        document.onmouseup = null;

        //텍스트박스 위치가 안바뀌었다면 포커스 걸기 + 데이터 수정
        if (targetKey == 2) {
            let isNotMove = targetObj.style.left === originLeft + "px" && targetObj.style.top === originTop + "px";
            let isNotChange =
                targetObj.style.width === objStartWidth + "px" && targetObj.style.height === objStartHeight + "px";
            //console.log(targetObj.style.height + "/" + objStartHeight);
            if (isNotMove && isNotChange) {
                setTextFontData(targetObj);
                targetObj.focus();
            }
        }
        //활성화 된 오브젝트 테두리 삭제
        removeActiveObj();
    }

    //클릭한 오브젝트 테두리 설정
    const addActiveObj = (obj) => {
        if (targetKey == 1) {
            obj.classList.add("active-img");
        } else if (targetKey == 2) {
            obj.style.border = "1px solid black";
        }
    };

    const removeActiveObj = () => {
        if (targetObj == null) return;
        if (targetKey == 1) {
            targetObj.classList.remove("active-img");
        } else if (targetKey == 2) {
            targetObj.style.border = "0";
        }
    };

    //모달 창 열기
    const openTemplateModal = () => {
        selectModal.classList.add("is-active");
        getRandomImg();
    };

    //모달 이미지 타입 DropDown Show/Hide 처리
    const openCloseDropDown = (key) => {
        var btnDiv = document.querySelector("." + key);
        btnDiv.classList.toggle("is-active");
    };

    const changeOrientation = (data) => {
        console.log(data);
        orientationType.value = data;
    };

    //모달 창 닫기
    const closeTemplateModal = () => {
        selectModal.classList.remove("is-active");
    };

    //Unsplash API 호출
    const getRandomImg = () => {
        templateContent.innerHTML = ""; //기존 사진 데이터 초기화

        let getURL = "photos/random?count=28";

        // let topics = document.querySelector("#topicinput");

        // if (topics.value.trim().length != 0) {
        //     console.log(topics.value);
        //     getURL = getURL + "&topics=" + topics.value;
        // }

        if (orientationType.value === "가로") {
            getURL += "&orientation=landscape";
        } else {
            getURL += "&orientation=portrait";
        }

        axios
            .get(proxyUrl + apiUrl + getURL + "&client_id=" + accessKey)
            .then((response) => {
                console.log(response);
                const imgData = response.data;
                [...imgData].forEach((data) => {
                    const preview = createTemplateImg(data);
                    templateContent.appendChild(preview);
                });
            })
            .catch((error) => {
                console.log(error);
            });
    };

    //Unsplash에서 불러온 이미지 추가하기
    const createTemplateImg = (data) => {
        const img = document.createElement("img");
        img.setAttribute("src", data.urls.small);
        img.setAttribute("class", "column is-one-quarter");
        img.setAttribute("style", "cursor: pointer;");
        img.addEventListener("click", () => {
            closeTemplateModal();
            let tmpImg = new Image();
            tmpImg.src = data.urls.small;
            tmpImg.onload = () => {
                const preview = createPaintImgElement(tmpImg);
                memePaint.appendChild(preview);
            };
        });
        return img;
    };

    let textFieldList = [];
    let lastTextField;

    //텍스트박스 추가
    const addTextField = () => {
        const textField = document.createElement("textarea");
        textField.setAttribute("id", "textField" + textIdCount);
        textField.setAttribute("class", "user-paint");
        textField.setAttribute(
            "style",
            "background-color: transparent; border: 0; resize: none; width: 200px; overflow: hidden;"
        );
        textField.addEventListener("mousedown", (e) => {
            startDrag(e, textField, 2);
        });
        textField.addEventListener("focusin", (e) => {
            lastTextField = textField;
            setTextFontData(lastTextField);
        });
        textField.addEventListener("focusout", (e) => {
            textField.style.border = "0";
        });
        textField.addEventListener("keyup", (e) => {
            textField.style.height = "auto";
            textField.style.height = textField.scrollHeight + "px";
        });
        uploadedElements.value.push("textField" + textIdCount);
        textFieldList.push({
            id: "textField" + textIdCount++,
            fontName: nowFontName.value,
            fontSize: nowFontSize.value,
            fontColor: colorPickBtn.value,
            isStyleOn: [false, false, false],
        });
        memePaint.appendChild(textField);
        textField.focus();
        changeTextFontStyle();
    };

    const nowFontName = ref("나눔고딕");
    const nowFontSize = ref(20);

    const changeFontName = (name, e) => {
        nowFontName.value = name;
        if (lastTextField != null) {
            let targetTb = getTargetData(lastTextField);
            targetTb.fontName = name;
            changeTextFontStyle();
        }
    };

    const changeFontSize = (size, e) => {
        nowFontSize.value = size;
        if (lastTextField != null) {
            let targetTb = getTargetData(lastTextField);
            targetTb.fontSize = size;
            changeTextFontStyle();
        }
    };

    const clickFontStyle = (e, ind) => {
        let nowColor = e.target.style.backgroundColor;
        if (nowColor.toLowerCase() == "lightgray") e.target.style.backgroundColor = "white";
        else e.target.style.backgroundColor = "lightgray";

        if (lastTextField != null) {
            let targetTb = getTargetData(lastTextField);
            targetTb.isStyleOn[ind] = !targetTb.isStyleOn[ind];
            changeTextFontStyle();
        }
    };

    const setTextFontData = (obj) => {
        let targetTb = getTargetData(obj);
        nowFontName.value = targetTb.fontName;
        nowFontSize.value = targetTb.fontSize;
        //Bold, Italic, Under
        setFontStyleBtn("Bold", targetTb.isStyleOn[0]);
        setFontStyleBtn("Italic", targetTb.isStyleOn[1]);
        setFontStyleBtn("Under", targetTb.isStyleOn[2]);
    };

    const setFontStyleBtn = (type, val) => {
        let btn = document.getElementById("style" + type + "Btn");
        let nowColor = btn.style.backgroundColor;
        if (val) {
            if (nowColor.toLowerCase() == "white") {
                btn.style.backgroundColor = "lightgray";
            }
        } else {
            if (nowColor.toLowerCase() == "lightgray") {
                btn.style.backgroundColor = "white";
            }
        }
    };

    const setTextFieldColor = () => {
        let color = colorPickBtn.value;
        if (targetObj != null) {
            let targetObj = getTargetData(lastTextField);
            targetObj.fontColor = color;
            changeTextFontStyle();
        }
    };

    //현재 포커스가 맞춰진 텍스트필드에 속성 적용
    const changeTextFontStyle = () => {
        let myFontFamily = "";
        if (nowFontName.value == "나눔고딕") {
            myFontFamily = "'Nanum Gothic','sans-serif'";
        } else {
            myFontFamily = "'Noto Serif KR','serif'";
        }

        if (lastTextField != null) {
            let targetTb = textFieldList.filter((x) => x.id == lastTextField.id)[0];
            lastTextField.style.fontFamily = myFontFamily;
            lastTextField.style.fontSize = nowFontSize.value + "px";
            lastTextField.style.color = targetTb.fontColor;

            targetTb.isStyleOn[0] ? (lastTextField.style.fontWeight = "bold") : (lastTextField.style.fontWeight = "");
            targetTb.isStyleOn[1] ? (lastTextField.style.fontStyle = "italic") : (lastTextField.style.fontStyle = "");
            targetTb.isStyleOn[2]
                ? (lastTextField.style.textDecoration = "underline")
                : (lastTextField.style.textDecoration = "");

            startCustomEvent(lastTextField, "keyup");
        }
    };

    const getTargetData = (obj) => {
        return textFieldList.filter((x) => x.id == obj.id)[0];
    };

    //수동 이벤트 활성화
    const startCustomEvent = (obj, name) => {
        const tempEvt = new CustomEvent(name);
        obj.dispatchEvent(tempEvt);
    };

    //컴포넌트 앞으로 보내기
    const frontComponent = (ind) => {
        if (uploadedElements.value.length === 1 || ind === 0) return;
        let data = uploadedElements.value[ind];
        let tmp = uploadedElements.value[ind - 1];
        uploadedElements.value[ind - 1] = data;
        uploadedElements.value[ind] = tmp;
        setDrawnComponents();
    };

    //컴포넌트 뒤로 보내기
    const backComponent = (ind) => {
        if (uploadedElements.value.length === ind + 1) return;
        let data = uploadedElements.value[ind];
        let tmp = uploadedElements.value[ind + 1];
        uploadedElements.value[ind + 1] = data;
        uploadedElements.value[ind] = tmp;
        setDrawnComponents();
    };

    //컴포넌트 삭제
    const removeComponent = (ind) => {
        uploadedElements.value.splice(ind, 1);
        setDrawnComponents();
    };

    //정렬 순서대로 다시 그리기
    const setDrawnComponents = () => {
        let tmpList = [];

        uploadedElements.value.forEach((data) => {
            tmpList.push(document.getElementById(data));
        });

        memePaint.innerHTML = "";

        tmpList.forEach((data) => {
            memePaint.appendChild(data);
        });
    };

    //밈 이미지 저장
    const saveMemeImage = () => {
        if (uploadedElements.value.length === 0) {
            alert("사진을 제작하여 주세요");
            return;
        }

        let userInputTitle = prompt("제목을 입력해주세요");
        if (userInputTitle.trim() === "") {
            alert("제목은 공백으로 둘 수 없습니다.");
            return;
        }
        html2canvas(memePaint, {
            useCORS: true,
            allowTaint: true,
        }).then((canvas) => {
            //document.body.appendChild(canvas);

            //blob 형태로 전환
            let myImg = canvas.toDataURL("image/png");
            myImg = myImg.replace("data:image/png;base64,", "");
            // console.log(myImg);
        });
    };
</script>

<style lang="scss">
    @import "../../node_modules/bulma/bulma.sass";

    //regular, bold, extrabold
    @import url("https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap");
    //regular, semibold, bold
    @import url("https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@400;600;700&display=swap");

    $modal-content-width: 1200px;
    $textfield-font-name: "Nanum Gothic";
    $textfield-font-type: "san-serif";
    $textfield-font-size: 10px;
    $textfield-font-style: normal;

    .modal-content {
        width: $modal-content-width;
        background-color: white;
        overflow-x: hidden;
        overflow-y: auto;
    }

    .columns {
        margin-left: 20px;
        margin-right: 20px;
        .button {
            width: 100%;
        }
    }

    .myfont {
        input {
            margin-right: 50px;
        }
        button {
            margin-right: 10px;

            #styleUnderBtn {
                margin-right: 50px;
            }
        }
    }

    .white-bg {
        height: 1000px;
        border-radius: 8px;
        padding: 20px;
    }

    #memepaintbg {
        width: 100%;
        height: 1100px;
        position: relative;
        background-color: lightgray;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    #memepaint {
        width: 900px;
        height: 900px;
        background-color: white;
    }

    .user-paint {
        // max-height: inherit;
        position: absolute;
        left: 0px;
        top: 0px;
        min-width: 50px;
        min-height: 50px;
        cursor: pointer;
    }

    .active-img {
        border: 1px solid black;
    }

    #drawncomponents {
        margin-top: 40px;
    }

    #myBestColor {
        border-radius: 50%;
        width: 50px;
        height: 50px;
        border: none;
        outline: none;
        cursor: pointer;
    }
    #myBestColor::-webkit-color-swatch-wrapper {
        padding: 10;
    }
    #myBestColor::-webkit-color-swatch {
        border: none;
        border-radius: 50%;
    }
</style>
