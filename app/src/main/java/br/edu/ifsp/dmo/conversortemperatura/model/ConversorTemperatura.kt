package br.edu.ifsp.dmo.conversortemperatura.model

interface ConversorTemperatura {
    fun converter(temperature: Double): Double
    fun getScale(): String
}
