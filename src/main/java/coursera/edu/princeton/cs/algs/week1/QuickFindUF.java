package coursera.edu.princeton.cs.algs.week1;

public class QuickFindUF extends UF {

    public QuickFindUF(Integer n) {
        super(n);
    }

    @Override
    public void union(Integer p, Integer q) {
        Integer pid = id[p];
        Integer qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid)
                id[i] = qid;
        }
    }

    @Override
    public boolean connected(Integer p, Integer q) {
        return id[p]==id[q];
    }

}
