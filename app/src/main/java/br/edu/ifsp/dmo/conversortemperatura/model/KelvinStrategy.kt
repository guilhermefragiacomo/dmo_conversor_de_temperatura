package br.edu.ifsp.dmo.conversortemperatura.model

object KelvinStrategy: ConversorTemperatura {
    override fun converter(temperature: Double) = temperature + 273.15
    override fun getScale() = "K"
}