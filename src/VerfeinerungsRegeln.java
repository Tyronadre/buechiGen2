import java.util.ArrayList;
import java.util.List;

public class VerfeinerungsRegeln implements Verfeinerung {


    @Override
    public List<BuechiState> applyVerfeinerung(BuechiState state) {
        List<BuechiState> stateList = new ArrayList<>();
        stateList.add(state);
        switch (state.getLTL().getOP()){
            case FunctionTypes.AND:
            case OR:
            case UNTIL:
                case GLOBAL:
                    case RELEASE:
                        case Variable:
                            case SOMETIMES:
        }

        return stateList;
    }
}
