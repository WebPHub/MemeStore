<template>
    <nav class="navbar" role="navigation" aria-label="main navigation">
        <div class="navbar-brand">
            <a class="navbar-item" href="/v/main">
                <img src="../assets/memestore_logo.png" width="100" height="40" />
            </a>

            <a
                role="button"
                class="navbar-burger"
                aria-label="menu"
                aria-expanded="false"
                data-target="navbarBasicExample"
            >
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
            </a>
        </div>

        <div id="navbarBasicExample" class="navbar-menu">
            <!-- <div class="navbar-start">
                <a class="navbar-item"> Home </a>

                <a class="navbar-item"> Documentation </a>

                <div class="navbar-item has-dropdown is-hoverable">
                    <a class="navbar-link"> More </a>

                    <div class="navbar-dropdown">
                        <a class="navbar-item"> About </a>
                        <a class="navbar-item"> Jobs </a>
                        <a class="navbar-item"> Contact </a>
                        <hr class="navbar-divider" />
                        <a class="navbar-item"> Report an issue </a>
                    </div>
                </div>
            </div> -->

            <div class="navbar-end">
                <div class="navbar-item">
                    <button class="button has-background-link is-primary" @click="removeUserData()">Log Out</button>
                </div>
            </div>
        </div>
    </nav>
</template>

<script setup>
    const removeUserData = () => {
        localStorage.removeItem("userid");
        window.location.href = "/v/home";
    };
</script>

<style lang="scss">
    @import "../../node_modules/bulma/bulma.sass";
</style>
