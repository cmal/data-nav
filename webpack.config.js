var path = require('path')
var webpack = require('webpack')

module.exports = {
  entry: './resources/public/js/compiled/app.js',
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'bundle.js'
  }
};
