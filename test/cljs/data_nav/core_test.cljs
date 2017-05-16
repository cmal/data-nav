(ns data-nav.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [data-nav.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
