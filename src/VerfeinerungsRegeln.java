import java.util.ArrayList;
import java.util.List;

public class VerfeinerungsRegeln implements Verfeinerung {


    @Override
    public List<BuechiState> applyVerfeinerung(BuechiState state) {
        List<BuechiState> stateList = new ArrayList<>();
        LTL ltl = state.getNewF().get(0);
        switch (ltl.getOP()) {
            case Variable:
                stateList.add(state.addLTLOld(ltl).removeLTLNew(ltl));
                break;
            case AND:
                stateList.add(state.removeLTLNew(ltl).addLTLOld(ltl).addLTLNew(ltl.left, ltl.right));
                break;
            case OR:
                stateList.add(state.removeLTLNew(ltl).addLTLOld(ltl).addLTLNew(ltl.left));
                stateList.add(new BuechiState(state.getIncoming(), state.getOld(), ltl, null));
                break;
            case UNTIL:
                stateList.add(state.removeLTLNew(ltl).addLTLOld(ltl).addLTLNew(ltl.right));
                stateList.add(new BuechiState(state.getIncoming(), state.getOld(), ltl, state.getNext()).addLTLNext(ltl));
                break;
            case GLOBAL:
                System.err.println("I DONT KNOW");
                break;
            case RELEASE:
                stateList.add(state.removeLTLNew(ltl).addLTLOld(ltl).addLTLNew(ltl.right, ltl.left));
                stateList.add(new BuechiState(state.getIncoming(), state.getOld(), ltl, state.getNext()).addLTLNext(ltl));
                break;
            case SOMETIMES:
                System.err.println("I DONT KNOW");
                break;
        }

        return stateList;
    }
}
