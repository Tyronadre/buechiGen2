import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BuechiState {
    private static int nextID = 0;

    private final int ID;
    private List<Integer> incoming;
    private List<LTL> old;
    private List<LTL> newF;
    private List<LTL> next;

    public BuechiState(List<Integer> incoming, List<LTL> old, LTL newF, List<LTL> next) {
        this.ID = nextID++;
        this.incoming = incoming == null ? new ArrayList<>() : incoming;
        this.old = old == null ? new ArrayList<>() : old;
        this.newF = new ArrayList<>();
        if(newF != null)
            this.newF.add(newF);
        this.next = next == null ? new ArrayList<>() : next;
    }

    public BuechiState(LTL initalLTL) {
        this.ID = -1;
        this.newF = new ArrayList<>();
        this.newF.add(initalLTL);
    }

    public void addIncoming(Integer incoming){
        this.incoming.add(incoming);
    }

    public void addIncoming (List<Integer> incoming) {
        this.incoming.addAll(incoming);
    }

    public void addOld(LTL old){
        this.old.add(old);
    }

    public void addNewF(LTL newF){
        this.newF.add(newF);
    }

    public void addNext(LTL next){
        this.next.add(next);
    }

    public int getID() {
        return ID;
    }

    public List<Integer> getIncoming() {
        return incoming;
    }

    public List<LTL> getOld() {
        return old;
    }

    public List<LTL> getNewF() {
        return newF;
    }

    public List<LTL> getNext() {
        return next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuechiState that = (BuechiState) o;

        if (!incoming.equals(that.incoming)) return false;
        if (!old.equals(that.old)) return false;
        if (!newF.equals(that.newF)) return false;
        return next.equals(that.next);
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + incoming.hashCode();
        result = 31 * result + old.hashCode();
        result = 31 * result + newF.hashCode();
        result = 31 * result + next.hashCode();
        return result;
    }

    public BuechiState removeLTLNew(LTL ltl){
        newF.remove(ltl);
        return this;
    }

    public BuechiState addLTLOld(LTL ltl){
        old.add(ltl);
        return this;
    }

    public BuechiState addLTLNew(LTL... ltl) {
        newF.addAll(Arrays.stream(ltl).collect(Collectors.toList()));
        return this;
    }

    public BuechiState addLTLNext(LTL ltl) {
        next.add(ltl);
        return this;
    }
}
