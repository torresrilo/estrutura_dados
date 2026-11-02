package br.edu.ifs.ed.demo;

import br.edu.ifs.ed.listas.Vetor;
import br.edu.ifs.ed.util.MyIterator;

public class VetorDemo {
    public static void main(String[] args) {
        System.out.println("----- DEMONSTRAÇÃO DA CLASSE VETOR -----\n");

        Vetor<String> vetor = new Vetor<>(4, 2);
        System.out.println("1. Vetor de String criado com capacidade 4 e incremento de 2.");
        System.out.println("   Está vazio? .isEmpty(): " + (vetor.isEmpty() ? "SIM" : "NÃO"));
        System.out.println("   Quantidade de itens .size(): " + vetor.size());
        System.out.println("   Capacidade .capacidade(): " + vetor.capacidade());
        System.out.println("   Vetor inicial: " + vetor);
        System.out.println("--------------------------------------------------");

        System.out.println("2. Fazendo inserções:");
        vetor.insertAtEnd("Estrutura");
        System.out.println("   .insertAtEnd(\"Estrutura\"): " + vetor);
        vetor.insertAtEnd("de");
        System.out.println("   .insertAtEnd(\"de\"): " + vetor);
        vetor.insertAtEnd("Dados");
        System.out.println("   .insertAtEnd(\"Dados\"): " + vetor);
        vetor.insertAtBegin("Aula");
        System.out.println("   .insertAtBegin(\"Aula\"): " + vetor);
        vetor.insertAt(1, "de");
        System.out.println("   .insertAt(1, \"de\"): " + vetor);
        System.out.println("--------------------------------------------------");
        
        System.out.println("3. Após inserções:");
        System.out.println("   Vetor: " + vetor);
        System.out.println("   Quantidade de itens .size(): " + vetor.size());
        System.out.println("   Capacidade do vetor redimensionado .capacidade(): " + vetor.capacidade());
        System.out.println("--------------------------------------------------");

        System.out.println("4. Acessando os elementos usando elementAt():");
        System.out.println("   Elemento no índice 1: " + vetor.elementAt(1));
        System.out.println("   Elemento em índice inválido (10): " + vetor.elementAt(10));
        System.out.println("--------------------------------------------------");

        System.out.println("5. Testando indexOf():");
        System.out.println("   Índice da palavra \"Aula\": " + vetor.indexOf("Aula"));
        System.out.println("   Índice de palavra inexistente \"Professor\": " + vetor.indexOf("Professor")); 
        System.out.println("--------------------------------------------------");

        System.out.println("6. Testando replace() para substituir índice 1 por sobre:");
        boolean substituido = vetor.replace(1, "sobre");
        System.out.println("   Substituiu índice 1 com sucesso? " + (substituido ? "SIM" : "NÃO"));
        System.out.println("   Vetor atualizado: " + vetor);
        System.out.println("--------------------------------------------------");

        System.out.println("7. Utilizando o MyIterator para percorrer o vetor e imprimir:");
        MyIterator<String> it = vetor.iterator();
        System.out.print("   Percorrendo com iterador: [ ");
        for (String item = it.getFirst(); item != null; item = it.getNext()) {
            System.out.print(item + " ");
        }
        System.out.println("]");
        System.out.println("--------------------------------------------------");

        System.out.println("8. Testando Remoções:");
        System.out.println("   Removendo do início .removeFromBegin(): " + vetor.removeFromBegin());
        System.out.println("   Removendo do fim .removeFromEnd(): " + vetor.removeFromEnd());
        System.out.println("   Removendo no índice 1 .removeAt(1): " + vetor.removeAt(1));
        System.out.println("   Vetor após remoções: " + vetor);
        MyIterator<String> itRemover = vetor.iterator();
        itRemover.getFirst(); 
        itRemover.remove();
        System.out.println("   Vetor após remover o primeiro item via iterador: " + vetor);
        System.out.println("--------------------------------------------------");

        System.out.println("9. Testando clear():");
        vetor.clear();
        System.out.println("   Vetor limpo: " + vetor);
        System.out.println("   Está vazio agora? " + (vetor.isEmpty() ? "SIM" : "NÃO"));
        System.out.println("   Capacidade após clear (volta à capacidade inicial): " + vetor.capacidade());
        System.out.println("--------------------------------------------------");

        System.out.println("10. Testando Política de Dobro de Capacidade (incremento <= 0):");
        Vetor<Integer> vetorDobro = new Vetor<>(2, 0); // Capacidade inicial 2, incremento 0 (vai dobrar)
        System.out.println("   Capacidade inicial: " + vetorDobro.capacidade());
        vetorDobro.insertAtEnd(10);
        vetorDobro.insertAtEnd(20);
        System.out.println("   Inseriu 2 itens. Vetor: " + vetorDobro);
        System.out.println("   Capacidade: " + vetorDobro.capacidade());
        vetorDobro.insertAtEnd(30); 
        System.out.println("   Inseriu o 3º item (extrapolou capacidade). Vetor: " + vetorDobro);
        System.out.println("   Nova capacidade (dobrou): " + vetorDobro.capacidade());
        System.out.println("\n----- FIM -----");
    }
}