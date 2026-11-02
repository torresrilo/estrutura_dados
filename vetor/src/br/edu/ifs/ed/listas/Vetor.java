/*
 * ESTRUTURA DE DADOS I - IFS Campus Aracaju
 * Prof. Glauco de Carvalho
 *
 * Lista 05 - Classe Vetor
 *
 * ESQUELETO: implemente os metodos marcados com TODO.
 * Nao altere as assinaturas nem os nomes dos metodos: os testes da correcao
 * chamam exatamente estes nomes.
 */
package br.edu.ifs.ed.listas;

import br.edu.ifs.ed.util.MyIterator;
import java.io.Serializable;

/**
 * Array que cresce sozinho - a classe Vetor da disciplina.
 *
 * <p>Regras que a sua implementacao precisa respeitar:</p>
 * <ul>
 *   <li>o array cresce somando o {@code incremento} ao numero de itens -
 *       {@code redimensione()} e chamado antes de toda insercao;</li>
 *   <li>toda posicao liberada recebe null;</li>
 *   <li>indice fora da faixa devolve {@code null} (ou {@code false}),
 *       NAO lanca excecao - este e o contrato da classe.</li>
 * </ul>
 *
 * <p>Item extra da lista: faca {@code incremento} menor ou igual a zero
 * significar "dobrar a capacidade", e compare o numero de redimensionamentos
 * das duas politicas para 10.000 insercoes.</p>
 */
public class Vetor<E> implements Serializable {

    private static final long serialVersionUID = 1L;
    protected static final int CAPACIDADE_DEFAULT = 100;

    protected E[] lista;
    protected int incremento, capacidadeInicial;
    protected int numItens;

    public Vetor() {
        this(CAPACIDADE_DEFAULT, 10);
    }

    public Vetor(int capacidadeInicial) {
        this(capacidadeInicial, 10);
    }

    @SuppressWarnings("unchecked")
    public Vetor(int capacidadeInicial, int incremento) {
        if (capacidadeInicial <= 0) {
            capacidadeInicial = CAPACIDADE_DEFAULT;
        }
        this.capacidadeInicial = capacidadeInicial;
        this.incremento = incremento;
        // Genericos sao apagados em tempo de execucao: nao existe new E[n].
        this.lista = (E[]) new Object[capacidadeInicial];
    }

    /** Capacidade atual do array interno. Ja implementado. */
    public int capacidade() {
        return lista.length;
    }

    public boolean isEmpty() {
        return numItens == 0;
    }

    public int size() {
        return numItens;
    }

    /**
     * Cresce o array quando ele esta cheio: o novo tamanho e
     * {@code numItens + incremento} (ou o dobro, se o incremento nao for positivo).
     * Use System.arraycopy para transferir os elementos.
     */
    protected void redimensione() {
        if(this.numItens >= this.lista.length) {
        	int novaCapacidade;
        	
        	if(this.incremento > 0) {
        		novaCapacidade = this.numItens + this.incremento;
        	}
        	else {
        		novaCapacidade = this.lista.length * 2;
        	}
        	
        	E[] novoVetor = (E[]) new Object[novaCapacidade];
        	
        	System.arraycopy(this.lista,  0,  novoVetor, 0, this.numItens);
        	
        	this.lista = novoVetor;
        }
    }

    /** Esvazia o vetor e devolve o array ao tamanho original. */
    public void clear() {
    	this.lista = (E[]) new Object[capacidadeInicial];
    	this.numItens = 0;
    }

    /** Objeto da posicao informada, ou null se o indice estiver fora da faixa. */
    public E elementAt(int indice) {
    	if (indice < 0 || indice >= numItens) {
    		return null;
    	}
    	else {
    		return lista[indice];
    	}
    }

    /** Armazena no final, chamando redimensione() antes. */
    public void insertAtEnd(E obj) {
    	redimensione();
        this.lista[this.numItens] = obj;
        this.numItens++;
    }

    /** Insere no inicio. Equivale a insertAt(0, obj). Custo O(n). */
    public void insertAtBegin(E obj) {
    	redimensione();
    	
    	for(int i = this.numItens - 1; i >=0; i--) {
    		this.lista[i + 1] = this.lista[i];
    	}
    	
    	this.lista[0] = obj;
    	
    	this.numItens++;
    }

    /**
     * Insere na posicao informada, deslocando os seguintes para a direita.
     * Devolve false se o indice estiver fora da faixa 0..size().
     * ATENCAO: o laco de deslocamento anda de TRAS PARA FRENTE. Do outro jeito,
     * cada valor sobrescreve o seguinte antes de ser copiado.
     */
    public boolean insertAt(int indice, E obj) {
    	if (indice < 0 || indice > numItens) {
    		return false;
    	}
    	
    	redimensione();
    	
    	for(int i = this.numItens - 1; i >=indice; i--) {
    		this.lista[i + 1] = this.lista[i];
    	}
    	
    	this.lista[indice] = obj;
    	
    	this.numItens++;
    	
    	return true;
    }

    /** Remove o primeiro elemento e o devolve. Equivale a removeAt(0). */
    public E removeFromBegin() {
        if (numItens == 0) {
        	return null;
        }
        
        E removido = this.lista[0];
        
        for(int i=0; i<this.numItens-1; i++) {
        	this.lista[i] = this.lista[i+1];
        }
        
        this.lista[this.numItens - 1] = null;
        
        this.numItens--;
        
        return removido;
    }

    /** Remove o ultimo elemento e o devolve, ou null se o vetor estiver vazio. */
    public E removeFromEnd() {
        if(numItens == 0) {
        	return null;
        }
        
        E removido = this.lista[this.numItens - 1];
        
        this.lista[this.numItens - 1] = null;
        
        this.numItens--;
        
        return removido;
    }

    /**
     * Remove o objeto da posicao informada, deslocando os seguintes, e o devolve.
     * Devolve null se o indice estiver fora da faixa.
     * ATENCAO: o limite do laco e numItens - 1. Com numItens, a ultima volta
     * le lista[numItens] e estoura quando o vetor esta cheio - foi exatamente
     * esse o defeito da versao original da classe.
     */
    public E removeAt(int indice) {
        if(indice < 0 || indice >= numItens) {
        	return null;
        }
        
        E removido = this.lista[indice];
        
        for(int i = indice; i < this.numItens - 1; i++) {
        	this.lista[i] = this.lista[i+1];
        }
        
        this.lista[this.numItens - 1] = null;
        
        this.numItens--;
        
        return removido;
    }

    /** Substitui o objeto da posicao informada. False se o indice for invalido. */
    public boolean replace(int indice, E obj) {
        if(indice < 0 || indice >= numItens) {
        	return false;
        }
        
        this.lista[indice] = obj;
        return true;
    }

    /** Posicao do primeiro elemento igual ao informado, ou -1. Use equals. */
    public int indexOf(E obj) {
        for(int i = 0; i < this.numItens; i++) {
        	
        	if(obj == null) {
        		if(this.lista[i] == null) {
        			return i;
        		}
        	}
        	else {
        		if (obj.equals(this.lista[i])) {
        			return i;
        		}
        	}
        }
        
        return -1;
    }

    /** Iterador que percorre do primeiro ao ultimo elemento. */
    public MyIterator<E> iterator() {
        return new VetorIterador();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < numItens; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(lista[i]);
        }
        return sb.append(']').toString();
    }

    /**
     * Classe interna do iterador.
     * getFirst() posiciona no primeiro; getNext() avanca; os dois devolvem null
     * quando nao ha mais elementos. remove() tira da colecao o ultimo devolvido.
     */
    private class VetorIterador implements MyIterator<E> {

        private static final long serialVersionUID = 1L;
        private int indiceCorrente = -1;

        @Override
        public E getFirst() {
        	if (numItens == 0) {
        		return null;
        	}
        	indiceCorrente = 0;
        	return lista[indiceCorrente];
        }

        @Override
        public E getNext() {
        	if (indiceCorrente + 1 < numItens) {
        		indiceCorrente ++;
        		return lista[indiceCorrente];
        	}
        	return null;
        }

        @Override
        public void remove() {
        	if(indiceCorrente < 0 || indiceCorrente >= numItens) {
        		throw new IllegalStateException("Nenhum elemento válido selecionado para remoção.");
        	}
        	
        	for(int i = indiceCorrente; i<numItens - 1; i++) {
        		lista[i] = lista [i+1];
        	}
        	
        	lista[numItens - 1] = null;
        	
        	numItens--;
        	
        	indiceCorrente--;
        }
    }
}
