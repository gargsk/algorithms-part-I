package coursera.edu.princeton.cs.algs.week1;

public class QuickUnionUF extends UF {

    public QuickUnionUF(Integer n) {
        super(n);
    }

    @Override
    public void union(Integer p, Integer q) {
        Integer i = findRoot(p);
        Integer j = findRoot(q);
        id[i] = j;
    }

    @Override
    public boolean connected(Integer p, Integer q) {
        return findRoot(p) == findRoot(q);
    }

    private Integer findRoot(Integer i){
        while(i != id[i]){
            i = id[i];
        }
        return i;
    }
}
