package br.edu.ifsp.dmo.conversortemperatura.model

object FahrenheitKelvinStrategy: ConversorTemperatura {
    override fun converter(temperature: Double) = (temperature - 32) * 5/9 + 273.15
    override fun getScale() = "K"
}