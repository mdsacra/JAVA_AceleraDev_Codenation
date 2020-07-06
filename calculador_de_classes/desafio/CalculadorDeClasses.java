package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object c) {
        return calcularAtributos(c, Somar.class);
    }


    @Override
    public BigDecimal subtrair(Object c) {
        return calcularAtributos(c, Subtrair.class);
    }


    @Override
    public BigDecimal totalizar(Object c){
        return somar(c).subtract(subtrair(c));
    }

    public BigDecimal calcularAtributos(Object c, Class<? extends Annotation> anotacao){

        BigDecimal total = new BigDecimal(0);
        int cont = 0;

        for (Field field: c.getClass().getDeclaredFields()){
            field.setAccessible(true);
            if (field.getType().equals(BigDecimal.class) && field.isAnnotationPresent(anotacao)){
                try{
                    cont++;
                    total = total.add((BigDecimal) field.get(c));
                } catch (IllegalAccessException e){
                    e.getStackTrace();
                }
            }
        }
        if (cont == 0){
            total = BigDecimal.ZERO;
        }
        return total;
    }

}
