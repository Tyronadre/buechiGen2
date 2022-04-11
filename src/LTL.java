import java.util.List;

public class LTL {
    String name;
    FunctionTypes op;
    LTL right;
    LTL left;

    public LTL(String name, FunctionTypes op, LTL right, LTL left) {
        this.name = name;
        this.op = op;
        this.right = right;
        this.left = left;
    }

    public FunctionTypes getOP() {
        return op;
    }
}
