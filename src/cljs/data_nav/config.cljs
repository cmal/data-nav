(ns data-nav.config)

(def debug? 
   ^boolean goog.DEBUG)

(def token "execute接口使用的token，确保不被外部调用"
  "91827fc3227c5ba631ba53435327374273c45fa0")

#_(def url-prefix
  "https://www.joudou.com/stockinfogate")

#_(def execution-url
  "https://www.joudou.com/stockinfogate/execute")

#_(def execution-url
  "/p/www/execution")

(def execution-url
  "http://localhost:6787/p/beta/stockinfogate/execute")
