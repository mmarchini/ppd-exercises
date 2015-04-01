/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class Node implements Runnable{

    private final List<Thread> joinNodes;
    private final int id;

    public Node(int id) {
        this.joinNodes = new ArrayList();
        this.id = id;
    }
    
    public void addJoin(Thread node){
        joinNodes.add(node);
    }

    public int getId() {
        return id;
    }
    
    @Override
    public void run() {
        for(Thread n : joinNodes){
            try {
                //System.out.println("Esperando!");
                n.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Sou a thread: "+id);
    }
    
}
