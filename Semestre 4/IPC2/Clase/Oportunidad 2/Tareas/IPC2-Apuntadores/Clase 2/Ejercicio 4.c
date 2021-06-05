#include <stdio.h>

int main() {
    int x  = 33;
    int y;
    int *p;
    p = &x;
    printf("El valor de p es %d \n", *p);
    y = *p + 10;
    printf("El valor de y es %d", y);

    return 0;
}