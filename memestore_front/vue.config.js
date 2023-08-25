const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
    transpileDependencies: true,
    outputDir: "../memestore_back/hello-spring/src/main/resources/static", // Build Directory
    devServer: {
        proxy: {
            "/": {
                target: "http://localhost:8080",
                changeOrigin: true,
            },
        },
    },
});
