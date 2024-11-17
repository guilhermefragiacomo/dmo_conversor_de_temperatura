package br.edu.ifsp.dmo.conversortemperatura.model

object CelsiusStrategy: ConversorTemperatura {
    override fun converter(temperature: Double) = (temperature - 32) / 1.8
    override fun getScale() = "ºC"
}