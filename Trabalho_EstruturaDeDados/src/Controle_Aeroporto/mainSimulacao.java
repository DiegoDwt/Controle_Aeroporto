package Controle_Aeroporto;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class mainSimulacao {

	public static void main(String[] args) throws InterruptedException  {
	
		

        Random random = new Random();
		
		Fila fila1Aterrissagem = new Fila(10);
		
		Fila fila2Aterrissagem = new Fila(10);
		
		Fila fila1Decolagem = new Fila(10);
		
		Fila fila2Decolagem = new Fila(10);
		
		Fila filaPousoEmergencial = new Fila(10);
		
		Aviao aeronave = new Aviao();
		
		Aviao aeronave2 = new Aviao();
		
		ArrayList<Aviao> pista1 = new ArrayList<Aviao>();
		
		ArrayList<Aviao> pista2 = new ArrayList<Aviao>();
		
		ArrayList<Aviao> pousoEmergencial = new ArrayList<Aviao>();

		boolean simulacaoTerminou = false;
		
		int idPouso = 1;
		
		int idDecolagem = 2; 
		
		boolean emergencia = false;
		
		int numPousosEmergenciais = 0 ;
		
		int ciclosDeTempo = 1;
		
		double tempoMedioDecolagens = 0;
		
		double tempoMedioAterrissagens = 0;
		
		int somaTotalDecolagens = 0;
		int somaTotalAterrissagens = 0;
		
		double somaProdutoDecolagensEspera = 0;
		double somaProdutoAterrissagensEspera = 0;
		
		int ciclosDeSimulacao = 0;
		
		
		while (!simulacaoTerminou) {            //   Simulação
			
		
			
			if(ciclosDeTempo == 1) {
				Scanner entrada = new Scanner(System.in);
				
				System.out.println("***************Controle de Aeroporto***************");
				System.out.println();
				System.out.print("Informe o número de ciclos de tempo da Simulaçao: ");
				
				ciclosDeSimulacao = entrada.nextInt();
				entrada.close();
			}
			
	
			
			int numAvioesAterrissagem = random.nextInt(3); // aviões para Aterrissagem / aleatório entre 0 e 2		    
			 
			 for(int i = 0; i < numAvioesAterrissagem; i++) {       // Aviões chegando nas filas de aterrissagem
				int combustivel;
				do {
					 combustivel = random.nextInt(20);	
				} while(combustivel < 3);   
				
				boolean prioridade = false;
				if (combustivel <= 3) {        
					prioridade = true;
				}
			    aeronave = new Aviao(idPouso, combustivel, prioridade);
				idPouso += 2;  
				 
				if(aeronave.isPrioridade()) {        // Filas Pouso Emergencial e aterrissgem
			    	emergencia = true;
				 	filaPousoEmergencial.inserir(aeronave);;
				 } else if(fila1Aterrissagem.temEspaco() 
			    	&& (fila1Aterrissagem.getTamanho() <= fila2Aterrissagem.getTamanho())) {           
			    	fila1Aterrissagem.inserir(aeronave);	
				 } else if (fila2Aterrissagem.temEspaco()) {    
			    		fila2Aterrissagem.inserir(aeronave);
				 }
		    }		    
			
			System.out.println("Fila Pouso Emergencial: ");       // Imprime as filas de Aterrissagens atualizadas
		    filaPousoEmergencial.mostraFila();    
		    System.out.println("Fila Aterrissagem 1: ");    
		    fila1Aterrissagem.mostraFila();
		    System.out.println("Fila Aterrissagem 2: ");
	        fila2Aterrissagem.mostraFila();    
	        
	
		   //------------------------------------ Decolagens
			
			
		   int numAvioesDecolagem = random.nextInt(3); // aviões para decolagem / aleatório entre 0 e 2		    
		   
		   for(int i = 0; i < numAvioesDecolagem; i++) {        // Aviões chegando nas filas de decolagem
		    	
			   int combustivel;
				do {
					 combustivel = random.nextInt(20);	
				} while(combustivel <= 5);   
				
			    aeronave = new Aviao(idDecolagem, combustivel);
					    		
		    	idDecolagem += 2;
		    	
		    	if(fila1Decolagem.temEspaco() 
				    	&& (fila1Decolagem.getTamanho() <= fila2Decolagem.getTamanho())) {           
		    	 	fila1Decolagem.inserir(aeronave);	
		    	} else if (fila2Decolagem.temEspaco()) {    
		    		fila2Decolagem.inserir(aeronave);
		    	} else {                
		    		System.out.print("");
		    	}
		   }
		    
		    
		    System.out.println("Fila Decolagem 1: ");     // Imprime as filas de decolagem atualizadas
			fila1Decolagem.mostraFila();
			System.out.println("Fila Decolagem 2: ");
			fila2Decolagem.mostraFila();    
			
			System.out.println("_________________________________________________________________________________________________"); 

			
			//____________________   Gerenciamento das Pistas
		    	 
			
			 // Controle/Impressão decolagens e pousos
			
			int filaTotalAterrissagem = fila1Aterrissagem.getTamanho() + fila2Aterrissagem.getTamanho();
			int filaTotalDecolagem = fila1Decolagem.getTamanho() + fila2Decolagem.getTamanho();
			
			int decolagensCiclo = 0;
			int aterrissagensCiclo = 0;
			
			int somaEsperaDecolagensCiclo = 0;
			int somaEsperaAterrissagensCiclo = 0;
			
			if(emergencia) { 
				if(filaPousoEmergencial.getTamanho() >= 2) {
					aeronave = filaPousoEmergencial.removerComRetorno();
					pista1.add(aeronave);
					pousoEmergencial.add(aeronave);
					numPousosEmergenciais++;
					System.out.println("Pista 1 - Pouso Emergencial: " + aeronave);
					
					aeronave2 = filaPousoEmergencial.removerComRetorno();
					pista2.add(aeronave2);
					pousoEmergencial.add(aeronave2);
					numPousosEmergenciais++;
					System.out.println("Pista 2 - Pouso Emergencial: " + aeronave2);
		
				} else if(filaPousoEmergencial.getTamanho() == 1) {
					aeronave = filaPousoEmergencial.removerComRetorno();
				    pista1.add(aeronave);
				    pousoEmergencial.add(aeronave);
				    numPousosEmergenciais++;
					System.out.println("Pista 1 - Pouso Emergencial: " + aeronave);
					
					if(filaTotalDecolagem > filaTotalAterrissagem) {
						if(fila1Decolagem.getTamanho() >= fila2Decolagem.getTamanho()) {
							aeronave = fila1Decolagem.removerComRetorno();	
							pista1.add(aeronave);
							decolagensCiclo += 1;
							if(!fila1Decolagem.estaVazio()) {
								somaEsperaDecolagensCiclo = somaEsperaDecolagensCiclo + aeronave.getTempoDeEspera();
							}
							System.out.println("Pista 1 - Decolagem        : " + aeronave);						                    
						} else if(fila2Decolagem.getTamanho() > fila1Decolagem.getTamanho()) {
							aeronave = fila2Decolagem.removerComRetorno();	
							pista2.add(aeronave);
							decolagensCiclo += 1;
							if(!fila2Decolagem.estaVazio()) {
								somaEsperaDecolagensCiclo = somaEsperaDecolagensCiclo + aeronave.getTempoDeEspera();
							}
							System.out.println("Pista 2 - Decolagem        : " + aeronave);
						}
					} else if(filaTotalAterrissagem >= filaTotalDecolagem) {
							if(fila1Aterrissagem.getTamanho() >= fila2Aterrissagem.getTamanho()) {
								aeronave2 = fila1Aterrissagem.removerComRetorno();	
								pista2.add(aeronave2);
								aterrissagensCiclo += 1;
								if(!fila1Aterrissagem.estaVazio()) {
									somaEsperaAterrissagensCiclo = somaEsperaAterrissagensCiclo + aeronave2.getTempoDeEspera();
								}
								System.out.println("Pista 2 - Pouso            : " + aeronave2);								                     
							} else if(fila2Aterrissagem.getTamanho() > fila1Aterrissagem.getTamanho()) {
								aeronave2 = fila2Aterrissagem.removerComRetorno();	
								pista2.add(aeronave2);
								aterrissagensCiclo += 1;
								if(!fila2Aterrissagem.estaVazio()) {
									somaEsperaAterrissagensCiclo = somaEsperaAterrissagensCiclo + aeronave2.getTempoDeEspera();
								}
								System.out.println("Pista 2 - Pouso            : " + aeronave2);
							}
					    }
				   }
		    } else if (!emergencia){  
				if(filaTotalDecolagem > filaTotalAterrissagem) {
					aeronave = fila1Decolagem.removerComRetorno();	
					pista1.add(aeronave);
					decolagensCiclo += 1;
					if(!fila1Decolagem.estaVazio()) {
						somaEsperaDecolagensCiclo = somaEsperaDecolagensCiclo + aeronave.getTempoDeEspera();
					}
					System.out.println("Pista 1 - Decolagem        : " + aeronave);					            
					
					aeronave2 = fila2Decolagem.removerComRetorno();	
					pista2.add(aeronave2);
					decolagensCiclo += 1;
					if(!fila2Decolagem.estaVazio()) {
						somaEsperaDecolagensCiclo = somaEsperaDecolagensCiclo + aeronave2.getTempoDeEspera();
					}
					System.out.println("Pista 2 - Decolagem        : " + aeronave2);
				} else if(filaTotalAterrissagem > filaTotalDecolagem) {
					aeronave = fila1Aterrissagem.removerComRetorno();	
					pista1.add(aeronave);
					aterrissagensCiclo += 1;
					if(!fila1Aterrissagem.estaVazio()) {
						somaEsperaAterrissagensCiclo = somaEsperaAterrissagensCiclo + aeronave.getTempoDeEspera();
					}
					System.out.println("Pista 1 - Pouso            : " + aeronave);				                   
					
					aeronave2 = fila2Aterrissagem.removerComRetorno();	
					pista2.add(aeronave2);
					aterrissagensCiclo += 1;
					if(!fila2Aterrissagem.estaVazio()) {
						somaEsperaAterrissagensCiclo = somaEsperaAterrissagensCiclo + aeronave2.getTempoDeEspera();
					}
					System.out.println("Pista 2 - Pouso            : " + aeronave2);
				} else {
					aeronave= fila1Decolagem.removerComRetorno();	
					pista1.add(aeronave);
					decolagensCiclo += 1;
					if(!fila1Decolagem.estaVazio()) {
						somaEsperaDecolagensCiclo = somaEsperaDecolagensCiclo + aeronave.getTempoDeEspera();
					}
					System.out.println("Pista 1 - Decolagem        : " + aeronave);
					
					aeronave2 = fila2Aterrissagem.removerComRetorno();	
					pista2.add(aeronave2);
					aterrissagensCiclo += 1;
					if(!fila2Aterrissagem.estaVazio()) {
						somaEsperaAterrissagensCiclo = somaEsperaAterrissagensCiclo + aeronave2.getTempoDeEspera();
					}
					System.out.println("Pista 2 - Pouso            : " + aeronave2);
				}
			}
			
			
			// Atualização do tempo de combustivel - Se prioridade envia para Fila de Pouso emergencial
			
			fila1Aterrissagem.atualizaTempoRestante();     // Atualiza combustivel Fila1 Aterrissagem
			
			fila1Aterrissagem.atualizaFila(filaPousoEmergencial);  // encaminha para fila de pouso emergencial
					
		
			fila2Aterrissagem.atualizaTempoRestante();      // Atualiza combustivel Fila2 Aterrissagem
			
			fila2Aterrissagem.atualizaFila(filaPousoEmergencial);   // encaminha para fila de pouso emergencial
			
			filaPousoEmergencial.atualizaTempoRestante();
			
			
			if(filaPousoEmergencial.estaVazio()) {
				emergencia = false;
			} else {
				emergencia = true;
			}
			
			
			
			// Atualização do tempo de espera dos aviões para pouso e decolagens
		
			fila1Aterrissagem.atualizaTempoEspera();
			
			fila2Aterrissagem.atualizaTempoEspera();
			
			
			fila1Decolagem.atualizaTempoEspera();
			
			fila1Decolagem.atualizaTempoEspera();
			
					
			// Cálculo dos tempos médios
			
            double produtoDecolagensEspera = decolagensCiclo * somaEsperaDecolagensCiclo;
            
            double produtoAterrissagensEspera = aterrissagensCiclo * somaEsperaAterrissagensCiclo;
            
            
            somaProdutoDecolagensEspera = somaProdutoDecolagensEspera + produtoDecolagensEspera;
            
            somaProdutoAterrissagensEspera = somaProdutoAterrissagensEspera + produtoAterrissagensEspera;
            
            somaTotalDecolagens = somaTotalDecolagens + decolagensCiclo;       
            
            somaTotalAterrissagens = somaTotalAterrissagens + aterrissagensCiclo;
            
            
            tempoMedioDecolagens = somaProdutoDecolagensEspera / somaTotalDecolagens;
            
            tempoMedioAterrissagens = somaProdutoAterrissagensEspera / somaTotalAterrissagens;
            

            
			System.out.println("Tempo médio de espera Decolagem: " + String.format("%.2f", tempoMedioDecolagens) + " Ciclos de tempo");
			
			System.out.println("Tempo Médio de espera Aterrissagem: " + String.format("%.2f", tempoMedioAterrissagens) + " Ciclos de Tempo");
					
			System.out.println("Número de Pousos Emergenciais: " + numPousosEmergenciais);
			
			System.out.println("Ciclos de tempo: " + ciclosDeTempo);
			
			ciclosDeTempo++;
						
			System.out.println("_________________________________________________________________________________________________"); 
		    
			if(ciclosDeTempo == ciclosDeSimulacao + 1) {
				simulacaoTerminou = true;			
			}
			
			
			new Thread();
			Thread.sleep(700);  // Timer 1000 = 1 seg
						
	    }  // Final simulação
		
	}
}
