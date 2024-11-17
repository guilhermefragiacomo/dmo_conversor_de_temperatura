package br.edu.ifsp.dmo.conversortemperatura.model

object KelvinFahrenheitStrategy: ConversorTemperatura {
    override fun converter(temperature: Double) = (temperature - 273.15) * 1.8 + 32
    override fun getScale() = "K"
}