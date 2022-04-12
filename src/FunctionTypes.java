public enum FunctionTypes {
    Variable,
    AND,
    OR,
    UNTIL,
    GLOBAL,
    SOMETIMES,
    RELEASE,
    NEXT;


    public static String toString(FunctionTypes functionTypes) {
        return switch (functionTypes) {
            case OR -> "|";
            case AND -> "&";
            case UNTIL -> "U";
            case RELEASE -> "R";
            case Variable -> "variable";
            case NEXT -> "X";
            default -> "Should not happen UwU";
        };
    }

}
