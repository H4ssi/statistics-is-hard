(ns statistics-is-hard.core
  (:gen-class)
  (:import java.util.Random)
  (:require [clojure.string :as str]))

(def ^:private R (Random.))

(defn random-long [] (.nextLong R))

(defn random-pair [] (let [a (random-long)
                           b (random-long)]
                       [a b]))

(defn naive-strategy [a] :>)

(defn random-strat [a] (if (> (rand) 0.5) :> :<))

(defn magic-strategy [thresh] (fn [a] (if (> a thresh) :> :<)))

(defn simulate
  ([strategy] (simulate strategy 1000))
  ([strategy num] (let [single-round (fn [[a b]] (case (strategy a)
                                                   :> (> a b)
                                                   :< (< a b)))
                        sample-seq (repeatedly num random-pair)
                        result-seq (map single-round sample-seq)
                        num-won    (count (filter identity result-seq))]
                    (double (/ num-won num)))))

(defn whole-run [range]
  (let [magic-strats (map (fn [thresh]
                            [(str "magic " thresh) (magic-strategy thresh)])
                          range)
        all-strats  (-> magic-strats
                        (conj ["random" random-strat])
                        (conj ["naive" naive-strategy]))]
    (str/join \newline
              (map (fn [[label strategy]] (str label ": " (simulate strategy)))
                   all-strats))))
(defn -main
  "Run naive strat against magic fixed num strats presented here: "
  [& args]
  (println (whole-run (range -100000000 100000000 10000000))))
