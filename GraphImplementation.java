import java.util.LinkedList;
import java.util.List;

public class GraphImplementation implements Graph {



    int[][] adjmatrix;
    int vertices;


    public GraphImplementation(int verticies){
        this.vertices=verticies;
        adjmatrix = new int[verticies][verticies];
    }

    @Override
    public void addEdge(int v1, int v2) {
        adjmatrix[v1][v2] = 1;
    }



    @Override
    public List<Integer> topologicalSort() {
        int[] temp = new int[vertices];
        for(int i = 0; i < vertices; i++){
            temp[i] = 0;
        }

        for(int j = 0; j < adjmatrix.length; j++){
            for(int k = 0; k < adjmatrix.length;k++){
                temp[k] += adjmatrix[j][k];
            }
        }


        List<Integer> it = new LinkedList<>();
        for(int j = 0 ; j < vertices; j++){
            for(int a = 0; a < temp.length; a++){
                if(temp[a] == 0){
                    int[] neighbors = this.neighbors(a);
                    for(int i = 0; i < neighbors.length;i++){
                        temp[neighbors[i]] -= 1;
                    }
                    it.add(a);
                    temp[a] = -1;
                }
            }


        }
        return it;
    }


    @Override
    public int[] neighbors(int vertex) {
        int count = 0;
        for(int i = 0; i < vertices; i++){
            if(adjmatrix[vertex][i] > 0){
                count++;
            }
        }
        int[] neighbor = new int[count];
        for(int i = 0, j = 0; i < vertices;i++){
            if(adjmatrix[vertex][i] > 0){
                neighbor[j++] = i;
            }
        }
        return neighbor;
    }

}