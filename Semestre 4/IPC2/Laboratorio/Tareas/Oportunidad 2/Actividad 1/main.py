import os
# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
print("Bienvenido, ingrese un numero impar y este programa le mostrará una piramide con esa cantidad de lineas :)")
User = os.getlogin()
#Crear Archivo
Archivo = open(f'C:\\Users\\{User}\\Desktop\\Piramides.txt', 'a')
Archivo.write('Inicio del Registro :)\n')
#Variable para que se repita
NumeroPruebas = 0
while int(NumeroPruebas) < 5:
    try:
        NLineas = int(input("Ingrese el Número Impar que desea: "))
        Archivo.write('--------------------------------------------------\n')
        if NLineas % 2 == 0:
            raise Exception ("No maje >:v")
    except:
        Archivo.write("Metió un número par el niño >:( (" + str(NLineas) + ')\n')
        print("Lee intrucciones mano, solo impares >:v")
        NumeroPruebas = NumeroPruebas + 1
    else:
        print("Vos muy bien! ;v")
        Archivo.write('Piramide de: ' + str(NLineas)+'\n')
        Mitad = int((NLineas/2)+0.5)
        for i in range (Mitad):
            for j in range(0, i+1):
                Archivo.write('!')
            Archivo.write('\n')
        for k in range(Mitad-1,0,-1):
            for l in range(k,0,-1):
                Archivo.write('!')
            Archivo.write('\n')
        Archivo.write('\n')
    NumeroPruebas = NumeroPruebas + 1

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
