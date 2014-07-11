/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphmst;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator; 
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
     * Total weight of the graph (sum of all edge weights)
     */
    private BigInteger _totalWeight;

    /**
     * _totalWeight getter
     * @return Integer
     */
    public BigInteger getTotalWeight() {
        return _totalWeight;
    }

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
        this._totalWeight = null;
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
    
    public Integer getEdgeWeight(int n1, int n2)
    {
        if (this.edgeExists(n1, n2)){
            return _edges.get(n1).get(n2);
        }
        return null;
    }

    public void addEdge(int e1, int e2, int w) {
        if (this.edgeExists(e1, e2)){
            throw new IllegalArgumentException("Node is already present!");
        }
        _registerEdgeFromTo(e1, e2, w);
        _registerEdgeFromTo(e2, e1, w);
        _increaseTotalWeight(w);
        this._edgeNumber++;
        this.addNode(e1);
        this.addNode(e2);
    }
    
    /**
     * If _totalWeight is set, increases it by given value, otherwise set it to 
     * that value.
     */
    private void _increaseTotalWeight(int w)
    {
        if (this._totalWeight == null){
            this._totalWeight = new BigInteger(Integer.toString(w));
        } else {
            BigInteger newWeight = this._totalWeight.add(new BigInteger(Integer.toString(w)));
            this._totalWeight = newWeight;
        }
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
     * returns graph edges one with one end inside given array and the other - outside.
     * @param seed
     * @return GraphMST
     */
    public GraphMST getExternal(List<Integer> seed){
        GraphMST g = new GraphMST();
        HashMap<Integer, Integer> nodes;
        for (int n : seed){
            if (_edges.containsKey(n)){
                nodes = _edges.get(n);
                for (int m : nodes.keySet()){
                    if (!seed.contains(m)){
                        g.addEdge(n, m, nodes.get(m));
                    }
                }
            }
        
        }
        return g;
    }

    public GraphMST cheapestEdge(){
        GraphMST g = new GraphMST();
        Integer e1 = null, e2 = null, weightMin = null, weightCurrent;
        for (int end1 : _edges.keySet()){
            HashMap<Integer, Integer> nodes = _edges.get(end1);
            for (int end2 : nodes.keySet()){
                if (end1 < end2) {
                    weightCurrent = nodes.get(end2);
                    if (e1 == null) {
                        e1 = end1;
                        e2 = end2;
                        weightMin = weightCurrent;
                    } else {
                        if (weightCurrent < weightMin) {
                            e1 = end1;
                            e2 = end2;
                            weightMin = weightCurrent;
                        }
                    }
                }
            }
        }
        if (e1 != null){
            g.addEdge(e1, e2, weightMin);
        }
        return g;
    }
    
    /**
     * Returns three-element array that contains information about the first
     * edge present in the graph: first node number, second node number
     * and edge weight
     * @return int[3]
     */
    public Integer[] getFirstEdge(){
        Integer[] result = new Integer[3];
        Set<Integer> keys = _edges.keySet();
        Iterator<Integer> iter = keys.iterator();
        if (iter.hasNext()){
            int e1 = iter.next();
            Iterator<Integer> iter2 = _edges.get(e1).keySet().iterator();
            int e2 = iter2.next();
            result[0] = e1;
            result[1] = e2;
            result[2] = _edges.get(e1).get(e2);
        }
        return result;
    }
    
    /**
     * Return MST of the graph
     * @return GraphMST
     */
    public GraphMST getMST()
    {
        GraphMST gResult = new GraphMST(),
                gExt, gCheapest;
        Integer[] edgeInfo;
        int e1, e2, w;
        Iterator<Integer> it = _nodes.iterator();
        if (it.hasNext()){
            int node = it.next();
            List<Integer> seed = new ArrayList();
            seed.add(node);
            gExt = this.getExternal(seed);
            while (seed.size() < this._nodeNumber || gExt.getNodeNumber() != 0) {
                gCheapest = gExt.cheapestEdge();
                edgeInfo = gCheapest.getFirstEdge();
                e1 = edgeInfo[0];
                e2 = edgeInfo[1];
                w = edgeInfo[2];
                gResult.addEdge(e1, e2, w);
                seed.add(seed.contains(e1) ? e2 : e1);
                gExt = this.getExternal(seed);
            }
        }
        return gResult;
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
        DateFormat df;
        df = new SimpleDateFormat("HH:mm:ss");
        System.out.println(df.format(new Date()) + ": loading graph" );
        BufferedReader br = null;
        String fileName = "c:\\Users\\Andrea\\Documents\\courses\\algo2\\graphMST\\edges.txt";
        GraphMST g = new GraphMST();
        try {
            Integer counter = 0;
            Integer weight, e1, e2;
            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));
//            int total = Integer.parseInt();
            br.readLine();
            String[] data;
            while ((sCurrentLine = br.readLine()) != null) {
                counter++;
                data = sCurrentLine.trim().split(" ");
                if (data.length != 3) {
                    throw new IllegalArgumentException("Line must contain exactly three numbers, not " + data.length + "!");
                }
                e1 = Integer.parseInt(data[0]);
                e2 = Integer.parseInt(data[1]);
                weight = Integer.parseInt(data[2]);
                g.addEdge(e1, e2, weight);
                
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println( df.format(new Date()) + ": graph contains " + g.getNodeNumber() + " nodes and " 
                + g.getEdgeNumber() + " edges");
        
        
        System.out.println(g.getMST().getTotalWeight());

    }
    
    
}
