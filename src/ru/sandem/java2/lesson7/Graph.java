package ru.sandem.java2.lesson7;

import java.util.*;

public class Graph {

    private List<Vertex> vertexes;
    private int[][] adjMatrix;

    private int size;

    public Graph(int maxSize) {
        vertexes = new ArrayList<>(maxSize);
        adjMatrix = new int[maxSize][maxSize];
        size = 0;
    }

    public void addVertex(String label) {
        Vertex vertex = new Vertex(label);
        vertexes.add(vertex);
        size++;
    }

    public boolean addEdge(String labelFrom, String labelTo, int weight) {
        Vertex vertexFrom = findVertex(labelFrom);
        Vertex vertexTo = findVertex(labelTo);
        if (vertexFrom == null || vertexTo == null) {
            return false;
        }

        addEdge(vertexFrom, vertexTo, weight);
        return true;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.println(vertexes.get(i));
        }
    }

    private void addEdge(Vertex vertexFrom, Vertex vertexTo, int weight) {
        int indexFrom = vertexes.indexOf(vertexFrom);
        int indexTo = vertexes.indexOf(vertexTo);
        adjMatrix[indexFrom][indexTo] = weight;
        adjMatrix[indexTo][indexFrom] = weight;
    }

    private Vertex findVertex(String label) {
        for (Vertex vertex : vertexes) {
            if (vertex.getLabel().equals(label)) {
                return vertex;
            }
        }
        return null;
    }

    public void dfs(String startLabel) {
        Vertex vertex = findVertex(startLabel);
        if (vertex == null) {
            throw new IllegalArgumentException("Invalid startLabel: " + startLabel);
        }

        Stack<Vertex> stack = new Stack();

        visitVertex(vertex, stack);

        while ( !stack.isEmpty() ) {
            vertex = getAdjUnvisitedVertex(stack.peek());
            if (vertex == null) {
                stack.pop();
            }
            else {
                visitVertex(vertex, stack);
            }
        }

        clearVertexes();
    }

    public void bfs(String startLabel) {
        Vertex vertex = findVertex(startLabel);
        if (vertex == null) {
            throw new IllegalArgumentException("Invalid startLabel: " + startLabel);
        }

        Queue<Vertex> queue = new ArrayDeque();

        visitVertex(vertex, queue);

        while ( !queue.isEmpty()) {
            vertex = getAdjUnvisitedVertex(queue.peek());
            if (vertex == null) {
                queue.remove();
            }
            else {
                visitVertex(vertex, queue);
            }
        }

        clearVertexes();
    }

    public Queue<String> findMinStopPathViaBfs (String startLabel, String finishLabel){
        Vertex vertex = findVertex(startLabel);
        if (vertex == null) {
            throw new IllegalArgumentException("Invalid startLabel: " + startLabel);
        }

        Queue<Vertex> queue = new ArrayDeque();

        visitVertex(vertex, queue);

        while ( !queue.isEmpty()) {
            vertex = getAdjUnvisitedVertex(queue.peek());
            if (vertex == null) {
                queue.remove();
            }
            else {
                visitVertex(vertex, queue);
                vertex.setPreviousVertex(queue.peek());
                if (vertex.getLabel().equals(finishLabel)) {
                    return buildPathStops(vertex);
                }
            }
        }
        clearVertexes();
        return null;
    }

    private Queue<String> buildPathStops(Vertex vertex) {
        Queue<String> queue = new ArrayDeque();
        Vertex current = vertex;
        while (current != null) {
            queue.add(current.getLabel());
            current = current.getPreviousVertex();
        }
        clearVertexes();
        return queue;
    }

    public Stack<String> findShortPathViaBfs (String startLabel, String finishLabel){
        Vertex vertex = findVertex(startLabel);
        if (vertex == null) {
            throw new IllegalArgumentException("Invalid startLabel: " + startLabel);
        }

        ArrayDeque<Vertex> queue = new ArrayDeque();
        ArrayList<ArrayList<Vertex>> stackArrayList = new ArrayList();

        int i = 0;
        visitVertex(vertex, queue);

        while ( !queue.isEmpty()) {
            vertex = getAdjUnvisitedVertex(queue.peek());
            if (vertex == null) {
                queue.remove();
            }
            else {
                visitVertex(vertex, queue);
                vertex.setPreviousVertex(queue.peek());
                if (vertex.getLabel().equals(finishLabel)) {
                    stackArrayList.add(new ArrayList<Vertex>());
                    buildPathWeight(vertex, stackArrayList.get(i++));
                    vertex.setWasVisited(false);
                    queue.removeLast();
//                    queue.remove();
                }
            }
        }

        int minWeigth = 2147483647;
        int minIndex = 0;
        for (int k = 0; k < stackArrayList.size(); k++) {
            int tempWeight = 0;
            for (int j = 0; j < stackArrayList.get(k).size() - 1; j++) {
                int indexFrom = vertexes.indexOf(stackArrayList.get(k).get(j));
                int indexTo = vertexes.indexOf(stackArrayList.get(k).get(j + 1));
                tempWeight += adjMatrix[indexFrom][indexTo];
            }
            if (tempWeight < minWeigth) {
                minWeigth = tempWeight;
                minIndex = k;
            }
        }

        Stack<String> returnStack = new Stack<String>();

        for (int j = 0; j < stackArrayList.get(minIndex).size(); j++) {
            returnStack.push(stackArrayList.get(minIndex).get(j).getLabel());
            if (j < stackArrayList.get(minIndex).size() - 1) {
                int indexFrom = vertexes.indexOf(stackArrayList.get(minIndex).get(j));
                int indexTo = vertexes.indexOf(stackArrayList.get(minIndex).get(j + 1));
                returnStack.push("" + adjMatrix[indexFrom][indexTo]);
            }
        }

        clearVertexes();
        return returnStack;
    }

    private void buildPathWeight(Vertex vertex, ArrayList<Vertex> list) {
        Vertex current = vertex;
        while (current != null) {
            list.add(current);
            current = current.getPreviousVertex();
        }
    }

    private void clearVertexes() {
        for (int i = 0; i < size; i++) {
            vertexes.get(i).setWasVisited(false);
            vertexes.get(i).setPreviousVertex(null);
        }
    }

    private void visitVertex(Vertex vertex, Stack<Vertex> stack) {
        vertex.setWasVisited(true);
//        System.out.println(vertex);
        stack.push(vertex);
    }
    private void visitVertex(Vertex vertex, Queue<Vertex> queue) {
        vertex.setWasVisited(true);
//        System.out.println(vertex);
        queue.add(vertex);
    }

    private Vertex getAdjUnvisitedVertex(Vertex vertex) {
        int startIndex = vertexes.indexOf(vertex);
        for (int i = 0; i < size; i++) {
            if (adjMatrix[startIndex][i] != 0 && !vertexes.get(i).isWasVisited() && ( (vertexes.get(i).getPreviousVertex() == null) || (vertexes.get(i).getPreviousVertex() != null && vertexes.get(i).getPreviousVertex().getLabel() != vertex.getLabel()))) {
                return vertexes.get(i);
            }
        }
        return null;
    }
}
