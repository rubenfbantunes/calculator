// Ruben Antunes Cloud Upskill

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args){

        CalculatorMethods calculadora = new CalculatorMethods();
        Scanner entrada = new Scanner(System.in);
        int a, b, resultado, opcao;
        boolean op = true;

        while(op) {
            
            System.out.println("\n* Digite o número da opção que pretende:\n");
            System.out.println("1. Somar");
            System.out.println("2. Subtrair");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("0. Sair\n");
            opcao = entrada.nextInt();

            if (opcao == 0) {
                System.out.println("\n* Até breve!\n");
                break;
            } else {
                switch (opcao) {

                    case 1:
                        System.out.println("\nInsira o 1º valor:");
                        a = entrada.nextInt();
                        System.out.println("\nInsira o 2º valor:");
                        b = entrada.nextInt();
                        resultado = calculadora.soma(a, b);
                        System.out.println("     " + a + " + " + b + " = " + resultado + "  ");
                        break;

                    case 2:
                        System.out.println("\nInsira o 1º valor:");
                        a = entrada.nextInt();
                        System.out.println("\nInsira o 2º valor:");
                        b = entrada.nextInt();
                        resultado = calculadora.subtracao(a, b);
                        System.out.println("     " + a + " - " + b + " = " + resultado + "  ");
                        break;

                    case 3:
                        System.out.println("\nInsira o 1º valor:");
                        a = entrada.nextInt();
                        System.out.println("\nInsira o 2º valor:");
                        b = entrada.nextInt();
                        resultado = calculadora.multiplicacao(a, b);
                        System.out.println("     " + a + " * " + b + " = " + resultado + "  ");
                        break;

                    case 4:
                        System.out.println("\nInsira o 1º valor:");
                        a = entrada.nextInt();
                        System.out.println("\nInsira o 2º valor:");
                        b = entrada.nextInt();
                        resultado = calculadora.divisao(a, b);
                        System.out.println("     " + a + " / " + b + " = " + resultado + "  ");
                        break;

                    default:
                        System.out.println(" -- Opção inválida -- ");

                }
            }

        }
    }
}