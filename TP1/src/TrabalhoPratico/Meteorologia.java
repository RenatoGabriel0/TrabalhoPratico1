package TrabalhoPratico;

import java.util.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;	

public class Meteorologia {
	static int i , j, k;					/// Contadores											/// 
	static int intervaloDeAnos = 10;																///											
	static int mesesNoAno = 12;																		///	Vari�veis Globais
	static int maximoDiasMes = 31;																	///
	static String[] meses = {"Janeiro","Fevereiro","Mar�o","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};												
	public static double[][][] temperatura = new double[intervaloDeAnos][mesesNoAno][maximoDiasMes];///
	private static DecimalFormat df = new DecimalFormat("0.0");										/// formata os valores decimais para usarem apenas uma casa ap�s a virgula
	
	public static void main(String[] args) {
		float max = 50, min = -50;					/// max e min s�o os valores maximos e minimos das temperaturas de janeiro 2020
		int opcao;
		Scanner ler = new Scanner(System.in);
		inicializaMatriz();
		for(i = 0;i < intervaloDeAnos;i++){
			for(j = 0;j < mesesNoAno; j++) {
				for(k = 0;k < maximoDiasMes;k++) {
					temperatura[i][j][k] = geraRandom(min,max);
				}
			}
		}
		do {
			menu();
			opcao = ler.nextInt();
			escolha(opcao);   
		}while(opcao != 6);
		ler.close();
		System.exit(0);
	}
	
	public static double[][][] inicializaMatriz() {						///preenche a matriz : temperatura com zeros;
		for(i = 0;i < intervaloDeAnos;i++) {
			for(j = 0;j < mesesNoAno;j++) {
				for(k = 0;k < maximoDiasMes;k++) {
					temperatura[i][j][k] = 0;
				}
			}
		}
		return temperatura;
	}
	
	public static void menu() {											///escreve um menu de op��es para o usu�rio
		System.out.println("\t\t\t\t Menu");
		System.out.println("1. Entrada das Temperaturas");
		System.out.println("2. C�lculo da Temperatura M�dia");
		System.out.println("3. C�lculo da Temperatura M�nima");
		System.out.println("4. C�lculo da Temperatura M�xima");
		System.out.println("5. Relat�rio Meteorol�gico");
		System.out.println("6. Finalizar Programa\n");
	}

	public static int escolha(int opcao) {								/// processa a escolha do usu�rio e executa a fun��o competente
		int mes, ano;
		Scanner ler = new Scanner(System.in);
		switch(opcao) {
		case 1: 
			do {
				System.out.println("Digite o M�s e o Ano em que deseja cadastrar as Temperaturas: ");
				mes = ler.nextInt();
				ano = ler.nextInt();					
			}while(validaData(mes,ano) != true);
			temperatura = cadastraTemperaturas(mes,ano);
			return 1;
		case 2: 
			do {
				System.out.println("Digite o M�s e o Ano em que deseja calcular a M�dia das Temperaturas: ");
				mes = ler.nextInt();
				ano = ler.nextInt();					
			}while(validaData(mes,ano) != true);
			temperaturaMedia(mes,ano);
			return 2;
		case 3:
			do {
				System.out.println("Digite o M�s e o Ano em que deseja calcular a Temperatura  M�nima: ");
				mes = ler.nextInt();
				ano = ler.nextInt();					
			}while(validaData(mes,ano) != true);
			temperaturaMinima(mes,ano);
			return 3;
		case 4:
			do {
				System.out.println("Digite o M�s e o Ano em que deseja calcular a Temperatura M�xima: ");
				mes = ler.nextInt();
				ano = ler.nextInt();					
			}while(validaData(mes,ano) != true);
			temperaturaMaxima(mes,ano);
			return 4;
		case 5:
			do {
				System.out.println("Digite o M�s e o Ano de que voc� deseja gerar um Relat�rio Meteorol�gico: ");
				mes = ler.nextInt();
				ano = ler.nextInt();					
			}while(validaData(mes,ano) != true);
			medias(mes,ano);
			temperaturaMedia(mes,ano);
			temperaturaMinima(mes,ano);
			temperaturaMaxima(mes,ano);
			return 5;
		case 6:
			System.out.println("Tenha um bom dia!");
			return 6;
		default:	
			System.out.println("Entrada inv�lida");
			return 7;
		}
	}
	
	public static boolean validaData(int mes,int ano){					/// verifica se a data digitada pelo usu�rio � valida, se o ano 
		if(ano < 2011 || ano > 2020) {									/// est� dentro do intervalo de 2011 a 2020, se os dia est� entre 1 e 31
			return false;												/// e se os m�s est� entre 1 e 12
		}
		else if(mes < 1 || mes > mesesNoAno) {
			return false;
		} 
		else {
			return true;
		}
	}
	
	public static double[][][] cadastraTemperaturas(int mes, int ano) { 	/// cadastra as temperaturas
		int dia, copia_ano, copia_mes;
		Scanner ler = new Scanner(System.in);
		copia_ano = ano - 2011;
		copia_mes = mes - 1;
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			for(dia = 0;dia < 31 ;dia++) {
				System.out.println("Digite a temperatura do dia "+ (dia + 1) + ":");
				temperatura[copia_ano][copia_mes][dia] = ler.nextFloat();
			}
			return temperatura;
		} 
		else if(mes == 2) {
			if(ano % 400 == 0 || ano % 4 == 0 && ano % 100 != 0){
				for(dia = 0;dia < 29;dia++) {
					System.out.println("Digite a temperatura do dia "+ (dia + 1) + ":");
					temperatura[copia_ano][copia_mes][dia] = ler.nextFloat();
				}
				return temperatura;
			}
			else {
				for(dia = 0;dia < 28;dia++) {
					System.out.println("Digite a temperatura do dia "+ (dia + 1) + ":");
					temperatura[copia_ano][copia_mes][dia] = ler.nextFloat();
				}
				return temperatura;
			}
		}
		else {
			for(dia = 0;dia < 30 ;dia++) {
				System.out.println("Digite a temperatura do dia "+ (dia + 1) + ":");
				temperatura[copia_ano][copia_mes][dia] = ler.nextFloat();
			}
			return temperatura;
		}
	}

	public static void temperaturaMedia(int mes, int ano) { 			/// c�lcula a m�dia de temperaturas de um m�s definido pelo usu�rio
		int dia, copia_ano, copia_mes;
		double media, total = 0;
		copia_ano = ano - 2011;
		copia_mes = mes - 1;
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			for(dia = 0;dia < 31 ;dia++) {
				total = total + temperatura[copia_ano][copia_mes][dia];
			}
			media = total/31;
			System.out.println("A m�dia aritim�tica das temperaturas de "+ meses[copia_mes] + " � de "+ df.format(media) +" �C\n");
		} 
		else if(mes == 2) {
			if(ano % 400 == 0 || ano % 4 == 0 && ano % 100 != 0){
				for(dia = 0;dia < 29;dia++) {
					total += temperatura[copia_ano][copia_mes][dia];
				}
				media = total/29;
				System.out.println("A m�dia aritim�tica das temperaturas de "+ meses[copia_mes] + " � de "+ df.format(media) +" �C\n");
			}
			else {
				for(dia = 0;dia < 28;dia++) {
					total += temperatura[copia_ano][copia_mes][dia];
				}
				media = total/28;
				System.out.println("A m�dia aritim�tica das temperaturas de "+ meses[copia_mes] + " � de "+ df.format(media) +" �C\n");
			}
		}
		else {
			for(dia = 0;dia < 30 ;dia++) {
				total += temperatura[copia_ano][copia_mes][dia];
			}
			media = total/30;
			System.out.println("A m�dia aritim�tica das temperaturas de "+ meses[copia_mes] + " � de "+ df.format(media) +" �C\n");
		}
	}
	
	public static void temperaturaMinima(int mes, int ano) {			/// c�lcula a temperatura m�nima de um m�s definido pelo usu�rio
		int dia, copia_ano, copia_mes;
		double minima = 0;
		copia_ano = ano - 2011;
		copia_mes = mes - 1;
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			for(dia = 0;dia < 31 ;dia++) {
				if(dia == 0)
					minima = temperatura[copia_ano][copia_mes][dia];
				if(temperatura[copia_ano][copia_mes][dia] < minima)
					minima = temperatura[copia_ano][copia_mes][dia];
			}
			System.out.println("A temperatura m�nima do m�s de "+ meses[copia_mes] + " foi de "+ df.format(minima) +" �C");
		} 
		else if(mes == 2) {
			if(ano % 400 == 0 || ano % 4 == 0 && ano % 100 != 0){
				for(dia = 0;dia < 29;dia++) {
					if(dia == 0)
						minima = temperatura[copia_ano][copia_mes][dia];
					if(temperatura[copia_ano][copia_mes][dia] < minima)
						minima = temperatura[copia_ano][copia_mes][dia];
				}
				System.out.println("A temperatura m�nima do m�s de "+ meses[copia_mes] + " foi de "+ df.format(minima) +" �C");
			}
			else {
				for(dia = 0;dia < 28;dia++) {
					if(dia == 0)
						minima = temperatura[copia_ano][copia_mes][dia];
					if(temperatura[copia_ano][copia_mes][dia] < minima)
						minima = temperatura[copia_ano][copia_mes][dia];
				}
				System.out.println("A temperatura m�nima do m�s de "+ meses[copia_mes] + " foi de "+ df.format(minima) +" �C");
			}
		}
		else {
			for(dia = 0;dia < 30 ;dia++) {
				if(dia == 0)
					minima = temperatura[copia_ano][copia_mes][dia];
				if(temperatura[copia_ano][copia_mes][dia] < minima)
					minima = temperatura[copia_ano][copia_mes][dia];
			}
			System.out.println("A temperatura m�nima do m�s de "+ meses[copia_mes] + " foi de "+ df.format(minima) +" �C");
		}
	}
	
	public static void temperaturaMaxima(int mes, int ano) {			/// c�lcula a temperatura m�xima de um m�s definido pelo usu�rio
		int dia, copia_ano, copia_mes;
		double maxima = 0;
		copia_ano = ano - 2011;
		copia_mes = mes - 1;
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			for(dia = 0;dia < 31 ;dia++) {
				if(dia == 0)
					maxima = temperatura[copia_ano][copia_mes][dia];
				if(temperatura[copia_ano][copia_mes][dia] > maxima)
					maxima = temperatura[copia_ano][copia_mes][dia];
			}
			System.out.println("A temperatura m�xima do m�s de "+ meses[copia_mes] + " foi de "+ df.format(maxima) +" �C");
		} 
		else if(mes == 2) {
			if(ano % 400 == 0 || ano % 4 == 0 && ano % 100 != 0){
				for(dia = 0;dia < 29;dia++) {
					if(dia == 0)
						maxima = temperatura[copia_ano][copia_mes][dia];
					if(temperatura[copia_ano][copia_mes][dia] > maxima)
						maxima = temperatura[copia_ano][copia_mes][dia];
				}
				System.out.println("A temperatura m�xima do m�s de "+ meses[copia_mes] + " foi de "+ df.format(maxima) +" �C");
			}
			else {
				for(dia = 0;dia < 28;dia++) {
					if(dia == 0)
						maxima = temperatura[copia_ano][copia_mes][dia];
					if(temperatura[copia_ano][copia_mes][dia] > maxima)
						maxima = temperatura[copia_ano][copia_mes][dia];
				}
				System.out.println("A temperatura m�xima do m�s de "+ meses[copia_mes] + " foi de "+ df.format(maxima) +" �C");
			}
		}
		else {
			for(dia = 0;dia < 30 ;dia++) {
				if(dia == 0)
					maxima = temperatura[copia_ano][copia_mes][dia];
				if(temperatura[copia_ano][copia_mes][dia] > maxima)
					maxima = temperatura[copia_ano][copia_mes][dia];
			}
			System.out.println("A temperatura m�xima do m�s de "+ meses[copia_mes] + " foi de "+ df.format(maxima) +" �C");
		}
	}

	public static void medias(int mes,int ano){
		int dia, copia_ano, copia_mes;
		copia_ano = ano - 2011;
		copia_mes = mes - 1;
		System.out.println("\t\t\t Relat�rio Meteorol�gico \n\n");
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			System.out.println(" Temperatura m�dia m�s de "+ meses[copia_mes]+ ":\n");
			for(dia = 0;dia < 31 ;dia++) {
				System.out.println("Temperatura m�dia do "+ (dia + 1) +"� dia foi de " + df.format(temperatura[copia_ano][copia_mes][dia]) +" �C");
			}
			System.out.println();
		} 
		else if(mes == 2) {
			if(ano % 400 == 0 || ano % 4 == 0 && ano % 100 != 0){
				System.out.println(" Temperatura m�dia m�s de "+ meses[copia_mes]+ ":");
				for(dia = 0;dia < 29;dia++) {
					System.out.println("Temperatura m�dia do "+ (dia + 1) +"� dia foi de " + df.format(temperatura[copia_ano][copia_mes][dia]) +" �C");
				}
				System.out.println();
			}
			else {
				System.out.println(" Temperatura m�dia m�s de "+ meses[copia_mes]+ ":");
				for(dia = 0;dia < 28;dia++) {
					System.out.println("Temperatura m�dia do "+ (dia + 1) +"� dia foi de " + df.format(temperatura[copia_ano][copia_mes][dia]) +" �C");
				}
				System.out.println();
			}
		}
		else {
			System.out.println(" Temperatura m�dia m�s de "+ meses[copia_mes]+ ":");
			for(dia = 0;dia < 30 ;dia++) {
				System.out.println("Temperatura m�dia do "+ (dia + 1) +"� dia foi de " + df.format(temperatura[copia_ano][copia_mes][dia]) +" �C");
			}
			System.out.println();
		}
	}
	
	public static double geraRandom(float min, float max){
	    double x = (Math.random()*((max-min)+1))+min;
	    return x;
	}

}

