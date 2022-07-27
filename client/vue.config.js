const path = require("path");
const vueSrc = "src/assets/";
module.exports = {
  devServer: {
    proxy: {
      '^/api': {
        target: process.env.PROXY_API || "http://localhost:8080/",
        ws: false,
      }
    }
  },
  chainWebpack: config => config.resolve.alias.set('@images', path.resolve(__dirname, vueSrc)),
  configureWebpack: {
    module: {
      exprContextCritical: false
    }
  }
};