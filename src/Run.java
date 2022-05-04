import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Run implements Runnable {
    BuechiState init;
    List<BuechiState> finished;
    List<BuechiState> allStates;

    public Run(BuechiState init) {
        this.init = init;
        finished = new ArrayList<>();
        allStates = new ArrayList<>();
        allStates.add(init);
    }

    @Override
    public void run() {
        System.out.println(init.getNewF());
        System.out.println(init.getNewF().get(0).getGraphviz());


        //IMPLEMENT ALGORITHEM
        BuechiState workingState = init;
        var initPointer = init;



        while (workingState != null) {
            System.out.println(getGraphvizFromBuechi(new StringBuilder(),initPointer));
            if (workingState.getNewF().isEmpty()) {
                BuechiState temp;
                //Prüfe ob es einen anderen Zustand gibt der gleiche new oder old felder bestitzt
                BuechiState finalWorkingState = workingState;
                if ((temp = finished.stream().filter(buechiState -> buechiState.getNewF().equals(finalWorkingState.getNewF()) && buechiState.getOld().equals(finalWorkingState.getOld())).findFirst().orElse(null)) != null) {
                    temp.addIncoming(workingState.getIncoming());
                    allStates.remove(workingState);
                } else {
                    finished.add(workingState);
                    workingState = new BuechiState(workingState.getID(), workingState.getNext());
                    allStates.add(workingState);
                }
            } else {
                LTL ltl = workingState.getNewF().get(0);
                if (workingState.getOld().contains(ltl))
                    workingState.getNewF().remove(0);
                else {
                    workingState.nextStates.addAll(VerfeinerungsRegeln.applyVerfeinerung(workingState));
                    if (workingState.getNewF().isEmpty()) {
                        finished.add(workingState);
                        workingState = null;
                    }
                }
            }

            if (allStates.isEmpty())
                break;
            if (workingState == null)
                workingState = allStates.stream().filter(state -> !finished.contains(state)).findFirst().orElse(null);
        }
        System.out.println(getGraphvizFromBuechi(new StringBuilder(), Objects.requireNonNull(finished.stream().filter(state -> state.getID() == 0).findFirst().orElse(null))));
    }


    private String getGraphvizFromBuechi(StringBuilder stringBuilder,BuechiState state) {
        if (state.getID() == 0)
            stringBuilder.append("digraph g{\n");
        for (var tmpState: state.nextStates) {
            if (tmpState != state)
            stringBuilder.append("\t\"").append(state.graphString()).append("\" -> \"").append(tmpState.graphString()).append("\"\n");
        }
        for (var tmpState: state.nextStates) {
            if (tmpState != state)
            getGraphvizFromBuechi(stringBuilder, tmpState);
        }
        if (state.getID() == 0)
            stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

}



