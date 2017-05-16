(ns data-nav.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [data-nav.core-test]))

(doo-tests 'data-nav.core-test)
