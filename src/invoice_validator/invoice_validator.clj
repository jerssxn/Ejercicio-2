(ns invoice-validator
  (:require [clojure.spec.alpha :as s]
            [clojure.data.json :as json]))

; Importando las especificaciones
(load-file "invoice-spec.clj")

(defn load-json [file]  ;Esta función toma un archivo como entrada, lo carga como recurso, lo lee como una cadena y luego lo analiza como JSON
  (-> file
      io/resource
      slurp
      (json/parse-string true)))

(defn validate-invoice [file]   ; Esta función toma un archivo como entrada, lo carga como un objeto JSON utilizando la función load-json y luego utiliza la función s/valid? de Clojure.spec para validar si el objeto JSON cumple con las especificaciones definidas
  (let [invoice (load-json file)]
    (s/valid? ::invoice invoice)))

; Ejemplo de uso
(validate-invoice "invoice.json")