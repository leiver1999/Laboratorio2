package cr.ac.una.myapplication

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment


class MainActivity : AppCompatActivity() {
    lateinit var jugar :Button
    lateinit var pos1: ImageButton
    lateinit var pos2: ImageButton
    lateinit var pos3: ImageButton
    lateinit var pos4: ImageButton
    lateinit var pos5: ImageButton
    lateinit var pos6: ImageButton
    lateinit var pos7: ImageButton
    lateinit var pos8: ImageButton
    lateinit var pos9: ImageButton
    var juegoService = JuegoService()

    private var ListaBotones = mutableListOf<ImageButton>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jugar = findViewById(R.id.jugar)

        pos1 = findViewById(R.id.pos1)
        pos2 = findViewById(R.id.pos2)
        pos3 = findViewById(R.id.pos3)
        pos4 = findViewById(R.id.pos4)
        pos5 = findViewById(R.id.pos5)
        pos6 = findViewById(R.id.pos6)
        pos7 = findViewById(R.id.pos7)
        pos8 = findViewById(R.id.pos8)
        pos9 = findViewById(R.id.pos9)

        jugar.setOnClickListener() {
//            enableDisableButton()
            dialogoInicio()
//            iniciarJuego()
        }
        pos1.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo(juegoService.jugada(0,0))

        }
        pos2.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo(juegoService.jugada(0,1))

        }

        pos3.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo(juegoService.jugada(0,2))

        }

        pos4.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(1,0))

        }


        pos5.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(1,1))

        }
        pos6.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(1,2))

        }
        pos7.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(2,0))

        }
        pos8.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(2,1))

        }
        pos9.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(2,2))

        }
        enableDisableButton()

        ListaBotones.add(pos1)
        ListaBotones.add(pos2)
        ListaBotones.add(pos3)
        ListaBotones.add(pos4)
        ListaBotones.add(pos5)
        ListaBotones.add(pos6)
        ListaBotones.add(pos7)
        ListaBotones.add(pos8)
        ListaBotones.add(pos9)
    }



    private fun dialogoInicio() {



        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle("Aviso")
            .setMessage("¡Bienvenido al juego de gato! \n\nEl juego consiste en que el jugador que " +
                    "logre alinear 3 figuras en línea horizontal, vertical o" +
                    " diagonal gana. \n\n¿Listo para jugar?")
            .setPositiveButton("¡Entendido!") { dialog, which ->
                // Do something.
//                juegoService.inicializar()
//
//                enableDisableButton()
//
//                jugar.isEnabled = false

                iniciarJuego()

            }




        val dialog: AlertDialog = builder.create()
        dialog.show()


    }

    private fun enableDisableButton(){
        pos1.isEnabled =  !pos1.isEnabled
        pos2.isEnabled =  !pos2.isEnabled
        pos3.isEnabled =  !pos3.isEnabled
        pos4.isEnabled =  !pos4.isEnabled
        pos5.isEnabled =  !pos5.isEnabled
        pos6.isEnabled =  !pos6.isEnabled
        pos7.isEnabled =  !pos7.isEnabled
        pos8.isEnabled =  !pos8.isEnabled
        pos9.isEnabled =  !pos9.isEnabled
    }

    private fun seleccionafigura(imageButton: ImageButton) {


        if (juegoService.figura == 'O')
            imageButton.setBackgroundResource(R.drawable.circulo)
        else
            imageButton.setBackgroundResource(R.drawable.cruz)
        imageButton.isEnabled = false

    }



    private fun iniciarJuego() {
        juegoService.inicializar()

//        for(button in ListaBotones){
//            button.setBackgroundResource(R.drawable.limpio)
//
//        }

        pos1.setBackgroundResource(R.drawable.limpio)
        pos2.setBackgroundResource(R.drawable.limpio)
        pos3.setBackgroundResource(R.drawable.limpio)
        pos4.setBackgroundResource(R.drawable.limpio)
        pos5.setBackgroundResource(R.drawable.limpio)
        pos6.setBackgroundResource(R.drawable.limpio)
        pos7.setBackgroundResource(R.drawable.limpio)
        pos8.setBackgroundResource(R.drawable.limpio)
        pos9.setBackgroundResource(R.drawable.limpio)

        enableDisableButton()
        jugar.isEnabled = false
    }


    private fun cambiarARojo(fig: Char) {


        if (fig == 'X') {
            for (i in 0..2) {
                for (j in 0..2) {
                    if (juegoService.matriz[i][j] == 'X') {
                        ListaBotones[i * 3 + j].setBackgroundResource(R.drawable.cruz_roja)
                    }
                }
            }
        } else {
            for (i in 0..2) {
                for (j in 0..2) {
                    if (juegoService.matriz[i][j] == 'O') {
                        ListaBotones[i * 3 + j].setBackgroundResource(R.drawable.circulo_rojo)
                    }
                }
            }
        }
    }



    private fun muestraDialogo(mensaje: String) {

        if (mensaje.contains("Ha ganado la figura") or mensaje.contains("El juego ha terminado en empate")){
            cambiarARojo(juegoService.figura)

            jugar.isEnabled = true
            //cambiar 'jugar' por reiniciar
        }


        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle("Aviso")
            .setMessage(mensaje)
            .setPositiveButton("¡Entendido!") { dialog, which ->

            }


        val dialog: AlertDialog = builder.create()
        dialog.show()
    }



}
