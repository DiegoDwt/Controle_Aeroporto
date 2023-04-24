package Controle_Aeroporto;


public class Aviao {
		
	    int id;
	    int tempoRestante;
	    boolean prioridade;  // apenas para fila de aterrissagem
	    int tempoDeEspera = 0;
	    
	   public Aviao() {
		   
	   }

	    public Aviao(int id) {
	    	this.id = id;
	    }
	    
	    public Aviao(int id, int tempoRestante) {
	    	this.id = id;
	        this.tempoRestante = tempoRestante;
	        this.prioridade = false;
	    }
	    
	    public Aviao(int id, int tempoRestante, boolean prioridade) {
	    	this.id = id;
	        this.tempoRestante = tempoRestante;
	        this.prioridade = prioridade;
	    }
	     
	    public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	
		public int getTempoRestante() {
			return tempoRestante;
		}

		public void setTempoRestante(int tempoRestante) {
			this.tempoRestante = tempoRestante;
		}

		public boolean isPrioridade() {
			return prioridade;
		}

		public void setPrioridade(boolean prioridade) {
			this.prioridade = prioridade;
		}

		public int getTempoDeEspera() {
				return tempoDeEspera;
			}
			

		public void setTempoDeEspera(int tempoDeEspera) {
			this.tempoDeEspera = tempoDeEspera;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Aviao [id=");
			builder.append(id);
			builder.append(", tempoRestante=");
			builder.append(tempoRestante);
			builder.append(", prioridade=");
			builder.append(prioridade);
			builder.append(", TempoDeEspera=");
			builder.append(tempoDeEspera);
			builder.append("]");
			return builder.toString();
		}

		
	    
}
