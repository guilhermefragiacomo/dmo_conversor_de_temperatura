package br.edu.ifsp.dmo.conversortemperatura.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.conversortemperatura.R
import br.edu.ifsp.dmo.conversortemperatura.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.conversortemperatura.model.CelsiusStrategy
import br.edu.ifsp.dmo.conversortemperatura.model.FahrenheitStrategy
import br.edu.ifsp.dmo.conversortemperatura.model.ConversorTemperatura
import br.edu.ifsp.dmo.conversortemperatura.model.FahrenheitKelvinStrategy
import br.edu.ifsp.dmo.conversortemperatura.model.KelvinCelsiusStrategy
import br.edu.ifsp.dmo.conversortemperatura.model.KelvinFahrenheitStrategy
import br.edu.ifsp.dmo.conversortemperatura.model.KelvinStrategy
import kotlin.NumberFormatException
class MainActivity : AppCompatActivity() {
    private var type: Int = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var converterStrategy: ConversorTemperatura
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListener()
    }
/**
 * Diferente dos projetos anteriores, nesse exemplo o clique nos botões
 * é tratado com a implementação por uma lambda. As duas formas de
 * implementação indicadas são válidas, no botão btnCelsius adotou-se
 * a forma mais direta de implementação, sem especificar qual a
 * interface se está implementando. Já para o btnFahrenheit utiliza-se
 * uma implementação que indica a interface que está sendo implementada.
 *
 * Isso é possível pois é feita a inferência do tipo do argumento do método
 * setClickListener(), que sempre recebe um objeto de View.OnClickListener.
 *
 * Para cada botão é realizada a chamada do método handleConversion() que
 * recebe como argumento qual a estratégia que o sistema deve utilizar
 * para realizar a conversão.
 */
private fun setClickListener() {
    binding.btnCelsius.setOnClickListener {
        if (type == 1) {
            handleConversion(CelsiusStrategy)
        } else {
            if (type == 2) {
                handleConversion(KelvinCelsiusStrategy)
            }
        }
    }
    binding.btnFahrenheit.setOnClickListener(View.OnClickListener {
        if (type == 0) {
            handleConversion(FahrenheitStrategy)
        } else {
            if (type == 2) {
                handleConversion(KelvinFahrenheitStrategy)
            }
        }
    })
    binding.btnKelvin.setOnClickListener {
        if (type == 0) {
            handleConversion(KelvinStrategy)
        } else {
            if (type == 1) {
                handleConversion(FahrenheitKelvinStrategy)
            }
        }
    }
    binding.btnInitial.setOnClickListener {
        if (type < 2) {
            type++
        } else {
            type = 0
        }
        binding.btnInitial.text = when (type) {
            0 -> getString(R.string.celsius)
            1 -> getString(R.string.fahrenheit)
            2 -> getString(R.string.kelvin)
            else -> getString(R.string.type_error)
        }
    }
}
    /**
     * Método responsável por recuperar o valor digitado no edittext
     * e converte-lo para o tipo Double.
     * O método pode lança uma exceção caso não seja possível converter
     * a entrada para Double.
     */
    private fun readTemperature(): Double {
        return try {
            binding.edittextTemperature.text.toString().toDouble()
        } catch (e: NumberFormatException) {
            throw NumberFormatException("Input Error")
        }
    }
    /**
     * O método agrupa a lógica da MainActivity, delegando à TeperatureConverter
     * a lógica de negócio da aplicação, contudo é MainActivity que aplica o
     * resultado da estratégia selecionada,
     */
    private fun handleConversion(strategy: ConversorTemperatura) {
        converterStrategy = strategy
        try {
            val inputValue = readTemperature()
            binding.textviewResultNumber.text = String.format(
                "%.2f %s",
                converterStrategy.converter(inputValue),
                converterStrategy.getScale()
            )
            binding.textviewResultMessage.text = if (this.converterStrategy is
                        CelsiusStrategy) {
                getString(R.string.msgFtoC)
            } else {
                getString(R.string.msgCtoF)
            }
        } catch (e: Exception) {
            Toast.makeText(
                this,
                getString(R.string.error_popup_notify),
                Toast.LENGTH_SHORT
            ).show()
            Log.e("APP_DMO", e.stackTraceToString())
        }
    }
}
