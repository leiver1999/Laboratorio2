package cr.ac.una.myapplication

import android.util.Log

class JuegoService {
    val matriz = Array(3) { CharArray(3) }
    var figura: Char = 'X'

//    init {
//        inicializar()
//    }

    fun jugada(x: Int, y: Int): String {
        matriz[x][y] = figura


        val ganador = verificarGanador()

        Log.i("SERVICE JUGADA", ganador.toString())

        if (ganador) {
            return  "Ha ganado la figura: " + figura

        }

        if (tableroCompleto()) {
            return "El juego ha terminado en empate"
        }

        cambiarFigura()

        return "Es el turno de la figura " + figura
    }

    fun inicializar() {
        matriz[0][0] = ' '
        matriz[0][1] = ' '
        matriz[0][2] = ' '
        matriz[1][0] = ' '
        matriz[1][1] = ' '
        matriz[1][2] = ' '
        matriz[2][0] = ' '
        matriz[2][1] = ' '
        matriz[2][2] = ' '
    }

    private fun tableroCompleto(): Boolean {
        for (fila in matriz) {
            for (celda in fila) {
                if (celda == ' ') {
                    return false
                }
            }
        }
        return true
    }

    private fun cambiarFigura() {
        if (figura == 'X') {
            figura = 'O'
        }
        else {
            figura = 'X'
        }
    }

    private fun verificarGanador(): Boolean {
        // Verificar filas y columnas
        for (i in 0 until 3) {
            if (matriz[i][0] != ' ' && matriz[i][0] == matriz[i][1] && matriz[i][1] == matriz[i][2]) {
                return true
            }
            if (matriz[0][i] != ' ' && matriz[0][i] == matriz[1][i] && matriz[1][i] == matriz[2][i]) {
                return true
            }
        }

        // Verificar diagonales
        if (matriz[0][0] != ' ' && matriz[0][0] == matriz[1][1] && matriz[1][1] == matriz[2][2]) {
            return true
        }
        if (matriz[0][2] != ' ' && matriz[0][2] == matriz[1][1] && matriz[1][1] == matriz[2][0]) {
            return true
        }

        return false;
    }
}