(require 'cljs.build.api)

(cljs.build.api/build "server_src"
  {:main 'data-nav-server.core
   :output-to "main.js"
   :target :nodejs})
