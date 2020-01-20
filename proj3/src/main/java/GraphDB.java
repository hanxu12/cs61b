import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Graph for storing all of the intersection (vertex) and road (edge) information.
 * Uses your GraphBuildingHandler to convert the XML files into a graph. Your
 * code must include the vertices, adjacent, distance, closest, lat, and lon
 * methods. You'll also need to include instance variables and methods for
 * modifying the graph (e.g. addNode and addEdge).
 *
 * @author Alan Yao, Josh Hug
 */
public class GraphDB {
    /** Your instance variables for storing the graph. You should consider
     * creating helper classes, e.g. Node, Edge, etc. */
    private Map<Long, Node> nodeMap;
    private ArrayList<Way>[] adj;
    int adjOccupied;

    static class Node {
        Long id;
        Integer arrIdx;
        double lat;
        double lon;
        String name;
        Node(Long id, double lat, double lon) {
            this.id = id;
            this.lat = lat;
            this.lon = lon;
        }
        Node(Long id, double lat, double lon, String name) {
            this.id = id;
            this.lat = lat;
            this.lon = lon;
            this.name = name;
        }
        void modifyName(String name){
            this.name = name;
        }
        void modifyArrIdx(int arrIdx){
            this.arrIdx = arrIdx;
        }
    }
    static class Way {
        String name;
        Long id;
        Way(String name, Long id) {
            this.name = name;
            this.id = id;
        }
    }
    
    /**
     * Example constructor shows how to create and start an XML parser.
     * You do not need to modify this constructor, but you're welcome to do so.
     * @param dbPath Path to the XML file to be parsed.
     */
    public GraphDB(String dbPath) {
        try {
            File inputFile = new File(dbPath);
            FileInputStream inputStream = new FileInputStream(inputFile);
            // GZIPInputStream stream = new GZIPInputStream(inputStream);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            GraphBuildingHandler gbh = new GraphBuildingHandler(this);
            saxParser.parse(inputStream, gbh);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        adjOccupied = 0;
        nodeMap = new HashMap<>();
        adj = (ArrayList<Way>[]) new ArrayList[2];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<Way>();
        }
        clean();
    }

    public void resize() {
        ArrayList<Way>[] backupAdj = adj;
        adj = new ArrayList[adj.length * 2];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<Way>();
        }
        for (int i = 0; i < backupAdj.length; i++) {
            for (Way way : backupAdj[i]){
                adj[i].add(way);
            }
        }
    }

    public void addNode(Long id, Node n) {
        if (adjOccupied / adj.length >= 0.75){
            resize();
        }
        n.modifyArrIdx(adjOccupied);
        this.nodeMap.put(id, n);
        adjOccupied += 1;
    }

//    public void addNode(int id, double lat, double lon) {
//        Node tempVertex = new Node(id, lat, lon, "");
//        this.cache.put(id, tempVertex);
//    }
//
//    public void addNode(int id, double lat, double lon, String name) {
//        Node tempVertex = new Node(id, lat, lon, name);
//        this.cache.put(id, tempVertex);
//    }

    public void addEdge(String name, ArrayList<Long> connections) {
        int size = connections.size();
        //edge case
        if (size < 2) {
            return;
        }
        //2 nodes in the connection
        else if (size == 2) {
            //add the 2nd element to the 1st
            int arrIdx = nodeMap.get(connections.get(0)).arrIdx;  //to be added index
            Way tempWay = new Way(name, connections.get(1));
            addEdgeHelper(arrIdx, tempWay);
            //add the 1st element to the 2nd
            arrIdx = nodeMap.get(connections.get(1)).arrIdx;
            tempWay = new Way(name, connections.get(0));
            addEdgeHelper(arrIdx, tempWay);
        }
        //there are >= 3 nodes in the connection
        else {
            //add the 2nd element as 1st's neighbor
            int arrIdx = nodeMap.get(connections.get(0)).arrIdx;
            Way tempWay = new Way(name, connections.get(1));
            addEdgeHelper(arrIdx, tempWay);
            //add the 2nd last to the last element
            arrIdx = nodeMap.get(connections.get(size - 1)).arrIdx;
            tempWay = new Way(name, connections.get(size - 2));
            addEdgeHelper(arrIdx, tempWay);
            //add neighbors for 1 ~ 2nd last
            for (int i = 1; i <= size - 2; i++) {
                arrIdx = nodeMap.get(connections.get(i)).arrIdx;
                addEdgeHelper(arrIdx, new Way(name, connections.get(i - 1)));
                addEdgeHelper(arrIdx, new Way(name, connections.get(i + 1)));
            }
        }
    }

    private void addEdgeHelper(int arrIdx, Way newAdj) {
        if (!adj[arrIdx].contains(newAdj)) {
            adj[arrIdx].add(newAdj);
        }
    }

    /**
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    /**
     *  Remove nodes with no connections from the graph.
     *  While this does not guarantee that any two nodes in the remaining graph are connected,
     *  we can reasonably assume this since typically roads are connected.
     */
    private void clean() {
        // Your code here.
        for (Long id: nodeMap.keySet()){
            if (adj[nodeMap.get(id).arrIdx].isEmpty()) {
                nodeMap.remove(id);
                //also remove the array? no need
            }
        }
    }

    /**
     * Returns an iterable of all vertex IDs in the graph.
     * @return An iterable of id's of all vertices in the graph.
     */
    Iterable<Long> vertices() {
        //YOUR CODE HERE, this currently returns only an empty list.
        List<Long> vertices = new ArrayList<Long>();
        for (Long id: nodeMap.keySet()){
            vertices.add(id);
        }
        return vertices;
    }

    /**
     * Returns ids of all vertices adjacent to v.
     * @param v The id of the vertex we are looking adjacent to.
     * @return An iterable of the ids of the neighbors of v.
     */
    Iterable<Long> adjacent(long v) {
        ArrayList<Long> adjList = new ArrayList<>();
        for (Way way : adj[nodeMap.get(v).arrIdx]) {
            adjList.add(way.id);
        }
        return adjList;
    }

    /**
     * Returns the great-circle distance between vertices v and w in miles.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The great-circle distance between the two locations from the graph.
     */
    double distance(long v, long w) {
        return distance(lon(v), lat(v), lon(w), lat(w));
    }

    static double distance(double lonV, double latV, double lonW, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double dphi = Math.toRadians(latW - latV);
        double dlambda = Math.toRadians(lonW - lonV);

        double a = Math.sin(dphi / 2.0) * Math.sin(dphi / 2.0);
        a += Math.cos(phi1) * Math.cos(phi2) * Math.sin(dlambda / 2.0) * Math.sin(dlambda / 2.0);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 3963 * c;
    }

    /**
     * Returns the initial bearing (angle) between vertices v and w in degrees.
     * The initial bearing is the angle that, if followed in a straight line
     * along a great-circle arc from the starting point, would take you to the
     * end point.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The initial bearing between the vertices.
     */
    double bearing(long v, long w) {
        return bearing(lon(v), lat(v), lon(w), lat(w));
    }

    static double bearing(double lonV, double latV, double lonW, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double lambda1 = Math.toRadians(lonV);
        double lambda2 = Math.toRadians(lonW);

        double y = Math.sin(lambda2 - lambda1) * Math.cos(phi2);
        double x = Math.cos(phi1) * Math.sin(phi2);
        x -= Math.sin(phi1) * Math.cos(phi2) * Math.cos(lambda2 - lambda1);
        return Math.toDegrees(Math.atan2(y, x));
    }

    /**
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    long closest(double lon, double lat) {
        double closestDist = Double.MAX_VALUE;
        //Long closestId = (Long)(nodeMap.keySet().toArray()[0]);
        Long closestId = 0L;
        for (Long id : nodeMap.keySet()) {
            double tempLat = nodeMap.get(id).lat;
            double tempLon = nodeMap.get(id).lon;
            closestDist = Math.min(closestDist, distance(tempLon, tempLat, lon, lat));
            closestId = id;
        }
        return closestId;
    }

    /**
     * Gets the longitude of a vertex.
     * @param v The id of the vertex.
     * @return The longitude of the vertex.
     */
    double lon(long v) {
        return nodeMap.get(v).lon;
    }

    /**
     * Gets the latitude of a vertex.
     * @param v The id of the vertex.
     * @return The latitude of the vertex.
     */
    double lat(long v) {
        return nodeMap.get(v).lat;
    }
}
