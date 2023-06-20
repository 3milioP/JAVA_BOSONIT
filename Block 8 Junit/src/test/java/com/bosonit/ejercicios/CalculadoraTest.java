package com.bosonit.ejercicios;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    @Test
    void suma() {

        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.suma(32, 94);
        assertThat(resultado, is(126)); //Valor que debería dar, Variable a comparar
    }

    @Test
    void resta() {
    }

    @Test
    void division() {
    }

    @Test
    void multiplicacion() {
    }
}