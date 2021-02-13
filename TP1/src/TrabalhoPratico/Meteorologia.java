package TrabalhoPratico;

import java.util.*; //adciona todas as classes utilitarias;

public class Meteorologia {
		
	public static void main(String[] args) {
		int opcao, mes, ano;
		float temperatura[][][];
		temperatura = new float[10][12][31];
		Scanner ler = new Scanner(System.in);
		do {
			menu();
			opcao = ler.nextInt();
			switch(opcao) {
			case 1: 
				do {
					System.out.println("Digite o mês e o ano em que deseja cadastrar as temperaturas: ");
					mes = ler.nextInt();
					ano = ler.nextInt();					
				}while(validaData(mes,ano) != true);
				temperatura = cadastraTemperaturas(mes,ano);
				break;
			case 2: 	
				break;
			case 3:
				break;
			case 4: 
				break;
			case 5: 
				break;
			case 6:
				System.out.println("Tenha um bom dia!");
				ler.close();
				break;
			default:	
				System.out.println("Entrada inválida");
			 	break;
			}
		}while(opcao != 6);
	}
	
	public static void menu() {
		System.out.println("\t\t\t\t Menu");
		System.out.println("1. Entrada das Temperaturas");
		System.out.println("2. Cálculo da Temperatura Média");
		System.out.println("3. Cálculo da Temperatura Mínima");
		System.out.println("4. Cálculo da Temperatura Máxima");
		System.out.println("5. Relátorio Meteorológico");
		System.out.println("6. Finalizar Programa\n");
	}
	
	public static float[][][] cadastraTemperaturas(int mes, int ano) {
		int dia, copia_ano, copia_mes;
		float temp[][][];
		Scanner ler = new Scanner(System.in);
		temp = new float[10][12][31];
		copia_ano = ano - 2011;
		copia_mes = mes - 1;
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			for(dia = 0;dia < 30 ;dia++) {
				temp[copia_ano][copia_mes][dia] = ler.nextFloat();
			}
			return temp;
		} 
		else if(mes == 2) {
			if(ano % 400 == 0 || ano % 4 == 0 && ano % 100 != 0){
				for(dia = 0;dia < 28;dia++) {
					temp[copia_ano][copia_mes][dia] = ler.nextFloat();
					
				}
				return temp;
			}
			else {
				for(dia = 0;dia < 27;dia++) 
					temp[copia_ano][copia_mes][dia] = ler.nextFloat();
				return temp;
			}
		}
		else {
			for(dia = 0;dia < 29 ;dia++) 
				temp[copia_ano][copia_mes][dia] = ler.nextFloat();
			return temp;
		}
	}
	
	public static boolean validaData(int mes,int ano){
		if(ano < 2011 || ano > 2020) {
			return false;
		}
		else if(mes < 1 || mes > 12) {
			return false;
		} 
		else {
			return true;
		}
	}
}
