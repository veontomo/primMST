/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphmst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; 
import java.util.Set;
/**
 *
 * @author Andrea
 */
public class GraphMST {

    /**
     * list of edges along with their weigths
     * 
     * The edges are stored in the following format: 
     * 1 => [2 => 6, ...]
     * 2 => [1 => -2, 4 => 31, ...]
     * 4 => [2 => 31, ...]
     * 
     */
    private HashMap<Integer, HashMap<Integer, Integer>> _edges;

    public HashMap<Integer, HashMap<Integer, Integer>> getEdges() {
        return _edges;
    }

    public List<Integer> getNodes() {
        return _nodes;
    }

    /**
     * List of graph nodes
     */
    private List<Integer> _nodes;

    /**
     * number of nodes in the graph
     */
    private int _nodeNumber;
    
    /**
     * number of edges in the graph
     */
    private int _edgeNumber;

    /**
     * number of edges in the graph
     */
    public int getEdgeNumber() {
        return _edgeNumber;
    }

    /**
     * Constructor
     */
    public GraphMST() {
        this._edges = new HashMap();
        this._nodes = new ArrayList();
        this._nodeNumber = 0;
    }
    
    /**
     * _nodeNumber getter
     *
     * @return
     */
    public int getNodeNumber() {
        return _nodeNumber;
    }
    
    /**
     * adds info into _edges. It assumes that the edge is not present in the graph.
     * It is supposed that this method is called twice: to register 
     * the edge as n1 -> n2 and as n2 -> n1
     * @param n1
     * @param n2 
     * @param w
     */
    private void _registerEdgeFromTo(int n1, int n2, int w){
        HashMap<Integer, Integer> nodes;
        if (_edges.containsKey(n1)) {
            nodes = _edges.get(n1);
        } else {
            nodes = new HashMap();
        }
        nodes.put(n2, w);
        _edges.put(n1, nodes);
    }
    
    /**
     * Returns true, if there exists an edge connecting nodes n1 and n2
     * @param n1
     * @param n2
     * @return boolean
     */
    public boolean edgeExists(int n1, int n2)
    {
        return _edges.containsKey(n1) && _edges.get(n1).containsKey(n2);
    }

    public void addEdge(int e1, int e2, int w) {
        if (this.edgeExists(e1, e2)){
            throw new IllegalArgumentException("Node is already present!");
        }
        _registerEdgeFromTo(e1, e2, w);
        _registerEdgeFromTo(e2, e1, w);
        this._edgeNumber++;
        this.addNode(e1);
        this.addNode(e2);
    }

    /**
     * Inserts node number into list of nodes if it is not there.
     *
     * @param n
     */
    private void addNode(int n) {
        if (!_nodes.contains(n)) {
            _nodes.add(n);
            this._nodeNumber++;
        }
    }

    /**
     * Returns list of edges that form MST of the graph.
     *
     * @return List<Edge>
     */
//    public List<Edge> mst()
//    {
//        
//        List<Integer> visitedNodes = new ArrayList();
//        visitedNodes.add(this._nodes.get(0));
//        while(visitedNodes.size() < this._nodeNumber ){
//          List<Edge> cutEdges = this.getCutEdges(visitedNodes);
//          Edge e = this.getCheapestEdge(cutEdges);//          
//}
//    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    
}
