import java.util.ArrayList;
import java.util.List;

public class VerfeinerungsRegeln {



    public static void applyVerfeinerung(BuechiState state) {
        List<BuechiState> stateList = new ArrayList<>();
        LTL ltl = state.getNewF().get(0);
        switch (ltl.getOP()) {
            case Variable -> state.addLTLOld(ltl).removeLTLNew(ltl);
            case AND -> stateList.add(state.removeLTLNew(ltl).addLTLOld(ltl).addLTLNew(ltl.left, ltl.right));
            case OR -> {
                stateList.add(state.removeLTLNew(ltl).addLTLOld(ltl).addLTLNew(ltl.left));
                BuechiState newBuechiState = new BuechiState();
                state.nextStates.add(newBuechiState);
                stateList.add(newBuechiState);

            }
            case UNTIL -> {
                stateList.add(state.removeLTLNew(ltl).addLTLOld(ltl).addLTLNew(ltl.right));
                BuechiState newBuechiState = new BuechiState();
                state.getIncoming().forEach(newBuechiState::addIncoming);
                state.getOld().forEach(newBuechiState::addOld);
                state.getNext().forEach(newBuechiState::addNext);
                newBuechiState.addLTLNext(ltl);
                newBuechiState.addLTLNew(ltl.left);
                stateList.add(newBuechiState);
            }
            case GLOBAL, SOMETIMES -> System.err.println("I DONT KNOW");
            case RELEASE -> {
                stateList.add(state.removeLTLNew(ltl).addLTLOld(ltl).addLTLNew(ltl.right, ltl.left));
                stateList.add(new BuechiState(state.getIncoming(), state.getOld(), ltl, state.getNext()).addLTLNext(ltl));
            }
        }

        return stateList;
    }
}
