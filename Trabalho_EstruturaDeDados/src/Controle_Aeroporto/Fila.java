package Controle_Aeroporto;


public class Fila {
	
	private Nodo inicio = null;
	private Nodo fim;
	private int limite = 0;
	private int tamanho = 0;
	
	public Fila (int limite) {
		this.limite = limite;
	}
	
	public Fila() {
		
	}
	
	public boolean estaVazio() {
		return tamanho == 0;
	}
	
	public boolean temEspaco() {
		return limite > tamanho;
	}
	
	public Nodo getInicio() {
		if(!estaVazio()) {
			return inicio;
		} else {
			return null;
		}
	}
		
	public void setInicio(Nodo inicio) {
		this.inicio = inicio;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public void inserir(Aviao aviao) {
		if(temEspaco()) {
			Nodo novo = new Nodo(aviao);
			if(estaVazio()) {
				inicio = novo;
				fim = novo;
			} else {
				fim.setProx(novo);;
				fim = novo;
			}
			tamanho++;
		} else
			System.out.print("");  // Fila cheia
	}
		
			
	public Aviao removerComRetorno() {
		if (estaVazio()) {
			return null;
		} else {
			Aviao aviao = inicio.getAviao();
			inicio = inicio.getProx();
			tamanho--;
			if(estaVazio()) {
				fim = null;
			}
			return aviao;
		}
			
	}      
		
	
	public void  mostraFila() {
		if (estaVazio()) {
			System.out.println("Fila Vazia");
			return;
		}
		Nodo aux = inicio;
		while (aux != null) {
			System.out.print(aux.getAviao().getId() + " ");
			aux = aux.getProx();
		}
		System.out.println();
	}
	
	public void  mostraFilaCompleto() {
		if (estaVazio()) {
			System.out.println("Fila Vazia");
			return;
		}
		Nodo aux = inicio;
		while (aux != null) {
			System.out.println(aux.getAviao().toString()+ " ");
			aux = aux.getProx();
		}
		System.out.println();
	}
		  
	
	public void atualizaTempoRestante() {
		Nodo aux = inicio;
		int tempo;
		while(aux != null) {
			tempo = aux.getAviao().getTempoRestante();
			aux.getAviao().setTempoRestante(tempo - 1);
			if(aux.getAviao().getTempoRestante() <= 3) {
				aux.getAviao().setPrioridade(true);
			}
			aux = aux.getProx();
		}		
	}
	
	
	public void atualizaFila(Fila fila) {   
	    Nodo aux = inicio;
	    Nodo anterior = null;
	    while (aux != null) {
	        if (aux.getAviao().isPrioridade()) {
	            Aviao aviaoRemovido = aux.getAviao();
	            if (anterior == null) { // o elemento a ser removido é o primeiro da fila
	                tamanho = tamanho - 1;
	                inicio = aux.getProx(); 
	                if (inicio == null) { // a fila ficou vazia
	                    fim = null;
	                }
	            } else {
	                tamanho = tamanho - 1;
	                anterior.setProx(aux.getProx());
	                if (aux.getProx() == null) { // o elemento a ser removido é o último da fila
	                    fim = anterior;
	                }
	            }
	            fila.inserir(aviaoRemovido);
	        } else {
	            anterior = aux;
	        }
	        aux = aux.getProx();
	    }
	}
	
	
	public void atualizaTempoEspera() {
		Nodo aux = inicio;
		int tempo;
		while(aux != null) {
			tempo = aux.getAviao().getTempoDeEspera();
			aux.getAviao().setTempoDeEspera(tempo + 1);
			aux = aux.getProx();
			}
		}
	}
	
		    





