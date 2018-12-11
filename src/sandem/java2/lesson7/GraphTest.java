package sandem.java2.lesson7;

public class GraphTest {
    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addVertex("Москва");
        graph.addVertex("Тула");
        graph.addVertex("Липецк");
        graph.addVertex("Воронеж");
        graph.addVertex("Рязань");
        graph.addVertex("Тамбов");
        graph.addVertex("Саратов");
        graph.addVertex("Калуга");
        graph.addVertex("Орел");
        graph.addVertex("Курск");
        graph.addEdge("Москва", "Тула",150);
        graph.addEdge("Москва", "Рязань",80);
        graph.addEdge("Москва", "Калуга",150);
        graph.addEdge("Тула", "Липецк",350);
        graph.addEdge("Рязань", "Тамбов",200);
        graph.addEdge("Калуга", "Орел",100);
        graph.addEdge("Липецк", "Воронеж",150);
        graph.addEdge("Тамбов", "Саратов",100);
        graph.addEdge("Орел", "Курск", 200);
        graph.addEdge("Саратов", "Воронеж",300);
        graph.addEdge("Курск", "Воронеж",150);

        System.out.println("Minimal stop's path: " + String.join(" -> ", graph.findMinStopPathViaBfs("Москва", "Воронеж")));
        System.out.println("Minimal lenght path: " + String.join(" -> ", graph.findShortPathViaBfs("Москва", "Воронеж")));

    }
}
