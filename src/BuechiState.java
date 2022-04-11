import java.util.ArrayList;
import java.util.List;

public class BuechiState {
    private final int ID;
    private final List<Integer> incoming;
    private final List<LTL> old;
    private final List<LTL> newF;
    private final List<LTL> next;

    public BuechiState(int ID, List<Integer> incoming, List<LTL> old, List<LTL> newF, List<LTL> next) {
        this.ID = ID;
        this.incoming = incoming == null ? new ArrayList<>() : incoming;
        this.old = old == null ? new ArrayList<>() : old;
        this.newF = newF == null ? new ArrayList<>() : newF;
        this.next = next == null ? new ArrayList<>() : next;
    }

    public void addIncoming(Integer incoming){
        this.incoming.add(incoming);
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuechiState that = (BuechiState) o;

        if (ID != that.ID) return false;
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

    public LTL getLTL() {
        return newF.get(0);
    }
}
