import java.util.*;

class DisJS{
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer>size = new ArrayList<>();
    DisJS(int n){
        for(int i=0;i<=n;i++){
            rank.add(0);
            parent.add(i);
            size.add(1);

        }
    }
    public int findUniqParent(int node){
        if(node==parent.get(node)){
            return node;
        }
        int ultp=findUniqParent(parent.get(node));
        parent.set(node,ultp);
        return parent.get(node);
    }

    public void unionByRank(int u,int v){
        int ultp_u=findUniqParent(u);
        int ultp_v=findUniqParent(v);
        if(ultp_v==ultp_u)return;
        if(rank.get(u)<rank.get(v)){
            parent.set(ultp_u,ultp_v);
        }
        else if(rank.get(u)> rank.get(v)){
            parent.set(ultp_v,ultp_u);
        }
        else{
            parent.set(ultp_v,ultp_u);
            int rankU=rank.get(ultp_u);
            rank.set(ultp_u,rankU+1);
        }
    }
    public void unionBySize(int u,int v){
        int ultp_u=findUniqParent(u);
        int ultp_v=findUniqParent(v);
        if(ultp_v==ultp_u)return;
        if(size.get(ultp_u)<size.get(ultp_v)){
            parent.set(ultp_u,ultp_v);
            int sv=size.get(ultp_v);
            int su=size.get(ultp_u);
            size.set(ultp_v,su+sv);
        }
        else{
            parent.set(ultp_v,ultp_u);
            int sv=size.get(ultp_v);
            int su=size.get(ultp_u);
            size.set(ultp_u,su+sv);
        }
    }

}

public class DisjointSet {
    public static void main(String[] args) {
        DisJS ds = new DisJS(7);
        ds.unionBySize(1,2);
        ds.unionBySize(2,3);
        ds.unionBySize(4,5);
        ds.unionBySize(6,7);
        ds.unionBySize(5,6);
        // before adding 3,7 lets check if 3,7 belong to same component or not
        if(ds.findUniqParent(3)==ds.findUniqParent(7)){
            System.out.println("3,7 are in same component");
        } else if (ds.findUniqParent(3)!=ds.findUniqParent(7)) {
            System.out.println("3,7 doesn't belong to the same component");
        }
        //after adding 3,7
        ds.unionByRank(3,7);
        if(ds.findUniqParent(3)==ds.findUniqParent(7)){
            System.out.println("3,7 are in same component");
        }
    }


}
