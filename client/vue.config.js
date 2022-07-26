const path = require("path");
const vueSrc = "../server/src/main/resources/assets/img";
module.exports = {
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://localhost:8080',
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