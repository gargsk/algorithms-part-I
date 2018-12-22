package coursera.edu.princeton.cs.algs.week1;

public class WeightedQuickUnion extends UF {

    Integer[] size;

    public WeightedQuickUnion(Integer n) {
        super(n);
        size = new Integer[n];
    }

    @Override
    public void union(Integer p, Integer q) {
        Integer i = findRoot(p);
        Integer j = findRoot(q);
        if(i == j){
            return;
        }else{
            if(size[i] > size[j]){
                id[j] = i;
                size[j] += size[i];
            }else{
                id[i] = j;
                size[i] += size[j];
            }
        }
    }

    @Override
    public boolean connected(Integer p, Integer q) {
        return false;
    }
    private Integer findRoot(Integer i){
        while(i != id[i]){
            // we can flaten the tree here further
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
}
