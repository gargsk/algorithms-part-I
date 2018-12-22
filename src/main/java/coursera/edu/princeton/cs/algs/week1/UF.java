package coursera.edu.princeton.cs.algs.week1;

public abstract class UF {

    protected Integer[] id;

    public UF(Integer n) {
        id = new Integer[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    public abstract void union(Integer p, Integer q);

    public abstract boolean connected(Integer p, Integer q);

}
