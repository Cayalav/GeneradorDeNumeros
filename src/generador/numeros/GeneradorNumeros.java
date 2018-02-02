
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generador.numeros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Cayalav
 */
public class GeneradorNumeros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        BufferedWriter out = null;
        long numerosGenerados;
        int numeroCaracteres;
        BigInteger base = new BigInteger("10");
        BigInteger resultadoMaximo = new BigInteger("0");
        BigInteger resultadoMinimo = new BigInteger("0");
        String maximo;
        String minimo;

        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido al Generador de numeros");
        System.out.println("¿Cuantos numeros desea generar?");
        numerosGenerados = sc.nextInt();
        System.out.println("¿Cuantos caracteres desea que contenga cada numero?");
        numeroCaracteres = sc.nextInt();

        //Control de exepciones
        while (numerosGenerados <= 0 || numeroCaracteres <= 0) {
            System.out.println("Los numeros deben ser mayor a 0, intente nuevamente");
            System.out.println("¿Cuantos numeros desea generar?");
            numerosGenerados = sc.nextInt();
            System.out.println("¿Cuantos caracteres desea que contenga cada numero?");
            numeroCaracteres = sc.nextInt();
        }

        resultadoMaximo = base.pow(numeroCaracteres);
        resultadoMinimo = base.pow(numeroCaracteres - 1);

        //control de excepcion en caso base
        if (numeroCaracteres == 1) {
            maximo = "10";
            minimo = "1";
        } else {
            maximo = "" + resultadoMaximo;
            minimo = "" + resultadoMinimo;
        }

        System.out.println("Estos son los " + numerosGenerados + " numeros generados:");

        BigInteger bigInteger = new BigInteger(maximo);// Maximo limite
        BigInteger min = new BigInteger(minimo);// Minimo limite

        BigInteger bigInteger1 = bigInteger.subtract(min);
        Random rnd = new Random();
        int maxNumBitLength = bigInteger.bitLength();

        BigInteger aRandomBigInt;

        String salida;
        for (int i = 0; i < numerosGenerados; i++) {
            aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
            if (aRandomBigInt.compareTo(min) < 0) {
                aRandomBigInt = aRandomBigInt.add(min);
            }
            if (aRandomBigInt.compareTo(bigInteger) >= 0) {
                aRandomBigInt = aRandomBigInt.mod(bigInteger1).add(min);
            }

            //Escritura de datos
            salida = "" + aRandomBigInt;
            try {
                out = new BufferedWriter(new FileWriter("C:\\Users\\Cayalav\\Desktop\\prueba.txt", true));
                out.write(salida);
                out.newLine();
            } catch (IOException e) {
                System.out.println("Hubo un error: "+e.getMessage());
                // error processing code   
            } finally {
                if (out != null) {
                    out.close();
                }
            }

            //consola
            //System.out.println(aRandomBigInt);
        }

    }

}
