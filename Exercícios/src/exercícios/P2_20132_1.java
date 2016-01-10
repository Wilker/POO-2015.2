/*1. [2,5 pontos] Uma grande cidade possui várias linhas de metro, de modo que
 cada estação pode ser visitada por várias linhas. Para identificar cada estação
 utiliza-se um código alfanumérico que determina a região geográfica onde está
 situada a estação. Escreva uma programa Java que leia um arquivo contendo o
 tempo médio de percurso entre duas estações e outro contendo uma sequência
 de estações percorridas em uma viagem e apresente o tempo médio total do percurso.
 O primeiro arquivo possui três colunas contendo respectivamente código
 da estação A, código da estação B e o tempo de percurso entra elas. O segundo
 arquivo possui somente uma coluna contendo os códigos das estações visitadas.
 Existem no máximo 80 estações no sistema. Seu programa deverá utilizar uma
 função com o seguinte cabeçalho.*/
package exercícios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author Wilker
 */
public class P2_20132_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        int tempoTotal = calculaTempoMedioTotal(inicializaEstacoes("C:\\Users\\Wilker\\Desktop\\TempoMédioEstacoes.txt"), "C:\\Users\\Wilker\\Desktop\\EstacoesVisitadass.txt");
        System.out.println(tempoTotal);
    }

    public static String[][] inicializaEstacoes(String tempoMedio) throws FileNotFoundException {
        InputStream input = new FileInputStream(tempoMedio);
        Scanner in = new Scanner(input);
        String[][] origDest = new String[81][81];
        int cont = 1;
        while (in.hasNextLine()) {
            String s = in.nextLine();
            String[] tmp = s.split(" ");
            int orig = buscaEstacao(origDest[0], tmp[0]);
            int dest = buscaEstacao(origDest[0], tmp[1]);
            if (orig == -1) {
                origDest[0][cont] = tmp[0];
                orig = cont;
                cont++;
            }
            if (dest == -1) {
                origDest[0][cont] = tmp[1];
                dest = cont;
                cont++;
            }
            origDest[orig][dest] = tmp[2];
            origDest[dest][orig] = tmp[2];
        }
        in.close();
        return origDest;
    }

    public static int calculaTempoMedioTotal(String[][] origDest, String arqPercurso) throws FileNotFoundException {
        InputStream input = new FileInputStream(arqPercurso);
        Scanner in = new Scanner(input);
        String anterior = null;
        int total = 0;
        while (in.hasNextLine()) {
            String prox = in.nextLine();
            if (anterior == null) {
                anterior = prox;
            } else {
                int orig = buscaEstacao(origDest[0], anterior);
                int dest = buscaEstacao(origDest[0], prox);
                total += Integer.parseInt(origDest[orig][dest]);
                anterior = prox;
            }
        }
        in.close();
        return total;
    }

    public static int buscaEstacao(String[] estacoes, String estacao) {
        for (int i = 1; i < estacoes.length; i++) {
            if (estacoes[i] == null) {
                break;
            } else {
                if (estacoes[i].equals(estacao)) {
                    return i;
                }
            }
        }
        return -1;
    }
}