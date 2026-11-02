package br.edu.ifs.ed.util;

import java.io.Serializable;

/**
 * Iterador das colecoes da disciplina.
 *
 * <p>Assinatura fixa da biblioteca da disciplina:
 * {@code getFirst()} posiciona no primeiro elemento e {@code getNext()} avanca,
 * os dois devolvendo {@code null} quando nao ha mais elementos.</p>
 *
 * <p>Nao use o for-each do Java sobre as colecoes desta biblioteca: o percurso
 * da disciplina e feito por este iterador.</p>
 *
 * <pre>
 * MyIterator&lt;Conta&gt; it = contas.iterator();
 * for (Conta c = it.getFirst(); c != null; c = it.getNext()) {
 *     System.out.println(c);
 * }
 * </pre>
 */
public interface MyIterator<E> extends Serializable {

    /** Primeiro elemento da colecao, ou null se ela estiver vazia. */
    E getFirst();

    /** Proximo elemento da colecao, ou null quando o percurso termina. */
    E getNext();

    /** Remove da colecao o ultimo elemento devolvido por getFirst/getNext. */
    void remove();
}