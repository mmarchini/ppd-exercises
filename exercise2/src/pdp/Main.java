/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdp;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        exercicio1();
    }

    /**
     * Segunda maneira de implementar o exemplo 1. Esta maneira é melhor que a
     * anterior por ser melhor escalável uma vez que é configurado em cada
     * thread o bloqueio. Contudo é mais difícil de programar.
     */
    public static void exercicio1() {
        Node node1 = new Node(1),
             node2 = new Node(2),
             node3 = new Node(3),
             node4 = new Node(4),
             node5 = new Node(5),
             node6 = new Node(6),
             node7 = new Node(7),
             node8 = new Node(8),
             node9 = new Node(9);

        Thread t1 = new Thread(node1),
               t2 = new Thread(node2),
               t3 = new Thread(node3),
               t4 = new Thread(node4),
               t5 = new Thread(node5),
               t6 = new Thread(node6),
               t7 = new Thread(node7),
               t8 = new Thread(node8),
               t9 = new Thread(node9);


        // ------------------ 

        // ------------------ 
        node2.addJoin(t1);
        node3.addJoin(t1);
        //-------------------
        node4.addJoin(t2); 
        node5.addJoin(t2); 

        node6.addJoin(t3); 
        node7.addJoin(t3); 
        // ------------------ 
        node8.addJoin(t5);
        node8.addJoin(t6);
        // ------------------ 
        node9.addJoin(t4);
        node9.addJoin(t7);
        node9.addJoin(t8);

        System.out.println("Iniciando o exercício 1!");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
    }
}
