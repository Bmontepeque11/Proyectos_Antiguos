#include <stdio.h>

int main() {
    
    char c = 'z';
    char *pc = &c;
    char **ppc = &pc;
    char ***pppc = &ppc;
    ***pppc = 'm'; //Cambia el valor de c a m
    printf("El valor del ultimo apuntador es: %c", ***pppc);

    return 0;
}