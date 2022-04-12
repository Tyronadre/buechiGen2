import java.util.ArrayList;
import java.util.List;

public class run implements Runnable {
    BuechiState init;
    List<BuechiState> finished;

    public run(BuechiState init) {
        this.init = init;
    }

    @Override
    public void run() {
        System.out.println(init.getNewF());
        System.out.println(init.getNewF().get(0).getGraphviz());


        //IMPLEMENT ALGORITHEM
        this.finished = new ArrayList<>();
        BuechiState workingState = init;
        while (true) {
            if (workingState.getNewF().isEmpty()) {
                BuechiState temp;
                if ((temp = finished.stream().filter(buechiState -> buechiState.equals(workingState)).findFirst().orElse(null)) != null) {
                    temp.addIncoming(workingState.getIncoming());
                }
            }
        }
    }
}


}
