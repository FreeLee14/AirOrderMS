module.exports = {
  configureWebpack: {
    resolve: {
      alias: {
        assets: '@/assets',
        components: '@/components',
        network: '@/network',
        views: '@/views'
      }
    },
    module: {
      rules: [
        {
          test: /\.less$/,
          use: [
            'vue-style-loader',
            'css-loader',
            'less-loader'
          ]
        }
      ]
    }
  }
}
