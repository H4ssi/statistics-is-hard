(defproject statistics-is-hard "0.1.0-SNAPSHOT"
  :description "Simulate to check the counter intuitive success of the guessing strategy explained here: https://www.youtube.com/watch?v=ud_frfkt1t0"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot statistics-is-hard.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
