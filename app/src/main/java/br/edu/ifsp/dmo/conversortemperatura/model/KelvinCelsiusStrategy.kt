package br.edu.ifsp.dmo.conversortemperatura.model

object KelvinCelsiusStrategy: ConversorTemperatura {
    override fun converter(temperature: Double) = temperature - 273.15
    override fun getScale() = "K"
}