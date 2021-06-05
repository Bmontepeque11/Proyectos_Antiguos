from tkinter import *
from tkinter import messagebox
from tkinter import ttk
import tkinter as tk
#Esta es la cuenta de que jugador está jugando
CuentaJ = 0 #Si la cuenta es = 0 pone una X, si es 1 pone un O

CasillasOcupadas = 0 #Es una cuenta que verifica cuantas casillas estan ocupadas para ver si hay empate

#Esta es la matrix del tablero, contiene 3 filas y 3 espacios por cada fila
Tablero = [['','',''],['','',''],['','','']]

#Crear Metodos para cada boton que contienen la funcionalidad del Juego:

#Boton 1
def ValidarEspacios1():
    global CuentaJ
    global CasillasOcupadas
    if CuentaJ == 0: #Si la cuenta es 0 se pone una X
        #Esto Actualiza el Texto del Boton
        btn1_text.set("X")
        #Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[0][0] = "X"
        #Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 2")
        #Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ + 1
        #Deshabilita el Botón para que no se pueda cambiar la posición
        btn1.config(state = "disabled")

        #AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        #Verificar si Alguien ya Ganó
        if (Tablero[0][0] == "X" and Tablero[0][1] == "X" and Tablero[0][2] == "X") or (Tablero[0][0] == "X" and Tablero[1][0] == "X" and Tablero[2][0] == "X") or (Tablero[0][0] == "X" and Tablero[1][1] == "X" and Tablero[2][2] == "X"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 1 GANO!")
            #Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CuentaJ == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")



    elif CuentaJ == 1: #Si la cuenta es 1 se pone un O
        # Esto Actualiza el Texto del Boton
        btn1_text.set("O")
        # Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[0][0] = "O"
        # Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 1")
        # Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ - 1
        # Deshabilita el Botón para que no se pueda cambiar la posición
        btn1.config(state = "disabled")

        # AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        # Verificar si Alguien ya Ganó
        if (Tablero[0][0] == "O" and Tablero[0][1] == "O" and Tablero[0][2] == "O") or (Tablero[0][0] == "O" and Tablero[1][0] == "O" and Tablero[2][0] == "O") or (Tablero[0][0] == "O" and Tablero[1][1] == "O" and Tablero[2][2] == "O"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 2 GANO")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

#Boton 2
def ValidarEspacios2():
    global CuentaJ
    global CasillasOcupadas
    if CuentaJ == 0: #Si la cuenta es 0 se pone una X
        #Esto Actualiza el Texto del Boton
        btn2_text.set("X")
        #Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[0][1] = "X"
        #Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 2")
        #Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ + 1
        #Deshabilita el Botón para que no se pueda cambiar la posición
        btn2.config(state = "disabled")

        #AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        #Verificar si Alguien ya Ganó
        if (Tablero[0][0] == "X" and Tablero[0][1] == "X" and Tablero[0][2] == "X") or (Tablero[0][1] == "X" and Tablero[1][1] == "X" and Tablero[2][1] == "X"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 1 GANO!")
            #Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")



    elif CuentaJ == 1: #Si la cuenta es 1 se pone un O
        # Esto Actualiza el Texto del Boton
        btn2_text.set("O")
        # Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[0][1] = "O"
        # Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 1")
        # Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ - 1
        # Deshabilita el Botón para que no se pueda cambiar la posición
        btn2.config(state = "disabled")

        # AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        # Verificar si Alguien ya Ganó
        if (Tablero[0][0] == "O" and Tablero[0][1] == "O" and Tablero[0][2] == "O") or (Tablero[0][1] == "O" and Tablero[1][1] == "O" and Tablero[2][1] == "O"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 2 GANO")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

#Boton 3
def ValidarEspacios3():
    global CuentaJ
    global CasillasOcupadas
    if CuentaJ == 0: #Si la cuenta es 0 se pone una X
        #Esto Actualiza el Texto del Boton
        btn3_text.set("X")
        #Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[0][2] = "X"
        #Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 2")
        #Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ + 1
        #Deshabilita el Botón para que no se pueda cambiar la posición
        btn3.config(state = "disabled")

        #AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        #Verificar si Alguien ya Ganó
        if (Tablero[0][0] == "X" and Tablero[0][1] == "X" and Tablero[0][2] == "X") or (Tablero[0][2] == "X" and Tablero[1][2] == "X" and Tablero[2][2] == "X") or (Tablero[0][2] == "X" and Tablero[1][1] == "X" and Tablero[2][0] == "X"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 1 GANO!")
            #Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")



    elif CuentaJ == 1: #Si la cuenta es 1 se pone un O
        # Esto Actualiza el Texto del Boton
        btn3_text.set("O")
        # Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[0][2] = "O"
        # Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 1")
        # Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ - 1
        # Deshabilita el Botón para que no se pueda cambiar la posición
        btn3.config(state = "disabled")

        # AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        # Verificar si Alguien ya Ganó
        if (Tablero[0][0] == "O" and Tablero[0][1] == "O" and Tablero[0][2] == "O") or (Tablero[0][2] == "O" and Tablero[1][2] == "O" and Tablero[2][2] == "O") or (Tablero[0][2] == "O" and Tablero[1][1] == "O" and Tablero[2][0] == "O"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 2 GANO")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

#Boton4
def ValidarEspacios4():
    global CuentaJ
    global CasillasOcupadas
    if CuentaJ == 0: #Si la cuenta es 0 se pone una X
        #Esto Actualiza el Texto del Boton
        btn4_text.set("X")
        #Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[1][0] = "X"
        #Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 2")
        #Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ + 1
        #Deshabilita el Botón para que no se pueda cambiar la posición
        btn4.config(state = "disabled")

        #AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        #Verificar si Alguien ya Ganó
        if (Tablero[1][0] == "X" and Tablero[1][1] == "X" and Tablero[1][2] == "X") or (Tablero[0][0] == "X" and Tablero[1][0] == "X" and Tablero[2][0] == "X"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 1 GANO!")
            #Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")



    elif CuentaJ == 1: #Si la cuenta es 1 se pone un O
        # Esto Actualiza el Texto del Boton
        btn4_text.set("O")
        # Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[1][0] = "O"
        # Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 1")
        # Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ - 1
        # Deshabilita el Botón para que no se pueda cambiar la posición
        btn4.config(state = "disabled")

        # AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        # Verificar si Alguien ya Ganó
        if (Tablero[1][0] == "O" and Tablero[1][1] == "O" and Tablero[1][2] == "O") or (Tablero[0][0] == "O" and Tablero[1][0] == "O" and Tablero[2][0] == "O"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 2 GANO")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

#Boton5
def ValidarEspacios5():
    global CuentaJ
    global CasillasOcupadas
    if CuentaJ == 0: #Si la cuenta es 0 se pone una X
        #Esto Actualiza el Texto del Boton
        btn5_text.set("X")
        #Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[1][1] = "X"
        #Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 2")
        #Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ + 1
        #Deshabilita el Botón para que no se pueda cambiar la posición
        btn5.config(state = "disabled")

        #AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        #Verificar si Alguien ya Ganó
        if (Tablero[1][0] == "X" and Tablero[1][1] == "X" and Tablero[1][2] == "X") or (Tablero[0][1] == "X" and Tablero[1][1] == "X" and Tablero[2][1] == "X") or (Tablero[0][0] == "X" and Tablero[1][1] == "X" and Tablero[2][2] == "X") or (Tablero[0][2] == "X" and Tablero[1][1] == "X" and Tablero[2][0] == "X"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 1 GANO!")
            #Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")



    elif CuentaJ == 1: #Si la cuenta es 1 se pone un O
        # Esto Actualiza el Texto del Boton
        btn5_text.set("O")
        # Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[1][1] = "O"
        # Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 1")
        # Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ - 1
        # Deshabilita el Botón para que no se pueda cambiar la posición
        btn5.config(state = "disabled")

        # AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        # Verificar si Alguien ya Ganó
        if (Tablero[1][0] == "O" and Tablero[1][1] == "O" and Tablero[1][2] == "O") or (Tablero[0][1] == "O" and Tablero[1][1] == "O" and Tablero[2][1] == "O") or (Tablero[0][0] == "O" and Tablero[1][1] == "O" and Tablero[2][2] == "O") or (Tablero[0][2] == "O" and Tablero[1][1] == "O" and Tablero[2][0] == "O"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 2 GANO")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

#Boton6
def ValidarEspacios6():
    global CuentaJ
    global CasillasOcupadas
    if CuentaJ == 0: #Si la cuenta es 0 se pone una X
        #Esto Actualiza el Texto del Boton
        btn6_text.set("X")
        #Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[1][2] = "X"
        #Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 2")
        #Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ + 1
        #Deshabilita el Botón para que no se pueda cambiar la posición
        btn6.config(state = "disabled")

        #AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        #Verificar si Alguien ya Ganó
        if (Tablero[1][0] == "X" and Tablero[1][1] == "X" and Tablero[1][2] == "X") or (Tablero[0][2] == "X" and Tablero[1][2] == "X" and Tablero[2][2] == "X"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 1 GANO!")
            #Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")



    elif CuentaJ == 1: #Si la cuenta es 1 se pone un O
        # Esto Actualiza el Texto del Boton
        btn6_text.set("O")
        # Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[1][2] = "O"
        # Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 1")
        # Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ - 1
        # Deshabilita el Botón para que no se pueda cambiar la posición
        btn6.config(state = "disabled")

        # AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        # Verificar si Alguien ya Ganó
        if (Tablero[1][0] == "O" and Tablero[1][1] == "O" and Tablero[1][2] == "O") or (Tablero[0][2] == "O" and Tablero[1][2] == "O" and Tablero[2][2] == "O"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 2 GANO")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

#Boton 7
def ValidarEspacios7():
    global CuentaJ
    global CasillasOcupadas
    if CuentaJ == 0: #Si la cuenta es 0 se pone una X
        #Esto Actualiza el Texto del Boton
        btn7_text.set("X")
        #Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[2][0] = "X"
        #Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 2")
        #Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ + 1
        #Deshabilita el Botón para que no se pueda cambiar la posición
        btn7.config(state = "disabled")

        #AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        #Verificar si Alguien ya Ganó
        if (Tablero[2][0] == "X" and Tablero[2][1] == "X" and Tablero[2][2] == "X") or (Tablero[0][0] == "X" and Tablero[1][0] == "X" and Tablero[2][0] == "X") or (Tablero[0][2] == "X" and Tablero[1][1] == "X" and Tablero[0][2] == "X"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 1 GANO!")
            #Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")



    elif CuentaJ == 1: #Si la cuenta es 1 se pone un O
        # Esto Actualiza el Texto del Boton
        btn7_text.set("O")
        # Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[2][0] = "O"
        # Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 1")
        # Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ - 1
        # Deshabilita el Botón para que no se pueda cambiar la posición
        btn7.config(state = "disabled")

        # AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        # Verificar si Alguien ya Ganó
        if (Tablero[2][0] == "O" and Tablero[2][1] == "O" and Tablero[2][2] == "O") or (Tablero[0][0] == "O" and Tablero[1][0] == "O" and Tablero[2][0] == "O") or (Tablero[0][2] == "O" and Tablero[1][1] == "O" and Tablero[2][0] == "O"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 2 GANO")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

#Boton 8
def ValidarEspacios8():
    global CuentaJ
    global CasillasOcupadas
    if CuentaJ == 0: #Si la cuenta es 0 se pone una X
        #Esto Actualiza el Texto del Boton
        btn8_text.set("X")
        #Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[2][1] = "X"
        #Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 2")
        #Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ + 1
        #Deshabilita el Botón para que no se pueda cambiar la posición
        btn8.config(state = "disabled")

        #AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        #Verificar si Alguien ya Ganó
        if (Tablero[2][0] == "X" and Tablero[2][1] == "X" and Tablero[2][2] == "X") or (Tablero[0][1] == "X" and Tablero[1][1] == "X" and Tablero[2][2] == "X"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 1 GANO!")
            #Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")



    elif CuentaJ == 1: #Si la cuenta es 1 se pone un O
        # Esto Actualiza el Texto del Boton
        btn8_text.set("O")
        # Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[2][1] = "O"
        # Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 1")
        # Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ - 1
        # Deshabilita el Botón para que no se pueda cambiar la posición
        btn8.config(state = "disabled")

        # AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        # Verificar si Alguien ya Ganó
        if (Tablero[2][0] == "O" and Tablero[2][1] == "O" and Tablero[2][2] == "O") or (Tablero[0][1] == "O" and Tablero[1][1] == "O" and Tablero[2][1] == "O"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 2 GANO")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

#Boton 9
def ValidarEspacios9():
    global CuentaJ
    global CasillasOcupadas
    if CuentaJ == 0: #Si la cuenta es 0 se pone una X
        #Esto Actualiza el Texto del Boton
        btn9_text.set("X")
        #Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[2][2] = "X"
        #Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 2")
        #Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ + 1
        #Deshabilita el Botón para que no se pueda cambiar la posición
        btn9.config(state = "disabled")

        #AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        #Verificar si Alguien ya Ganó
        if (Tablero[2][0] == "X" and Tablero[2][1] == "X" and Tablero[2][2] == "X") or (Tablero[0][2] == "X" and Tablero[1][2] == "X" and Tablero[2][2] == "X") or (Tablero[0][0] == "X" and Tablero[1][1] == "X" and Tablero[2][2] == "X"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 1 GANO!")
            #Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")



    elif CuentaJ == 1: #Si la cuenta es 1 se pone un O
        # Esto Actualiza el Texto del Boton
        btn9_text.set("O")
        # Actualiza EL Boton en la Matriz del Tablero (Para que sea más fácil ver si hay ganador)
        Tablero[2][2] = "O"
        # Esto Actualiza el Texto del Label
        LabelJugador.config(text="Jugador Actual: 1")
        # Aumenta la cuenta del Jugador Actual
        CuentaJ = CuentaJ - 1
        # Deshabilita el Botón para que no se pueda cambiar la posición
        btn9.config(state = "disabled")

        # AumentarCasillasOcupadas
        CasillasOcupadas = CasillasOcupadas + 1

        # Verificar si Alguien ya Ganó
        if (Tablero[2][0] == "O" and Tablero[2][1] == "O" and Tablero[2][2] == "O") or (Tablero[0][2] == "O" and Tablero[1][2] == "O" and Tablero[2][2] == "O") or (Tablero[0][0] == "O" and Tablero[1][1] == "O" and Tablero[2][2] == "O"):
            # ^ Verifica las posiciones en este orden Fila, Columna, Diagonal
            tk.messagebox.showinfo(title="GANASTE!", message="EL JUGADOR 2 GANO")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")

        elif CasillasOcupadas == 9:
            tk.messagebox.showinfo(title="F bros :(", message="Es un empate :'v")
            # Deshabilitar todos los botones
            btn1.config(state="disabled")
            btn2.config(state="disabled")
            btn3.config(state="disabled")
            btn4.config(state="disabled")
            btn5.config(state="disabled")
            btn6.config(state="disabled")
            btn7.config(state="disabled")
            btn8.config(state="disabled")
            btn9.config(state="disabled")


#Crear el GUI
VentanaT = tk.Tk()
VentanaT.geometry("600x600")
VentanaT.title("Totito")

#Agregar el jugador que está jugando actualmente
LabelJugador = Label(VentanaT, text="Jugador Actual: 1")
LabelJugador.place(x=3,y=5, height=30, width=275)

#Agregar Arreglo de 9 Botones
btn1_text = tk.StringVar()
btn1 = Button(VentanaT, textvariable=btn1_text, command=ValidarEspacios1)
btn1.place(x=100, y=100, height=100, width=100)

btn2_text = tk.StringVar()
btn2 = Button(VentanaT, textvariable=btn2_text, command=ValidarEspacios2)
btn2.place(x=100, y=200, height=100, width=100)

btn3_text = tk.StringVar()
btn3 = Button(VentanaT, textvariable=btn3_text, command=ValidarEspacios3)
btn3.place(x=100, y=300, height=100, width=100)

btn4_text = tk.StringVar()
btn4 = Button(VentanaT, textvariable=btn4_text, command=ValidarEspacios4)
btn4.place(x=200, y=100, height=100, width=100)

btn5_text = tk.StringVar()
btn5 = Button(VentanaT, textvariable=btn5_text, command=ValidarEspacios5)
btn5.place(x=200, y=200, height=100, width=100)

btn6_text = tk.StringVar()
btn6 = Button(VentanaT, textvariable=btn6_text, command=ValidarEspacios6)
btn6.place(x=200, y=300, height=100, width=100)

btn7_text = tk.StringVar()
btn7 = Button(VentanaT, textvariable=btn7_text, command=ValidarEspacios7)
btn7.place(x=300, y=100, height=100, width=100)

btn8_text = tk.StringVar()
btn8 = Button(VentanaT, textvariable=btn8_text, command=ValidarEspacios8)
btn8.place(x=300, y=200, height=100, width=100)

btn9_text = tk.StringVar()
btn9 = Button(VentanaT, textvariable=btn9_text, command=ValidarEspacios9)
btn9.place(x=300, y=300, height=100, width=100)

VentanaT.mainloop()
