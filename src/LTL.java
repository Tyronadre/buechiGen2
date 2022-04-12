public class LTL {
    String name;
    FunctionTypes op;
    LTL right;
    LTL left;

    public LTL() {
    }

    public LTL(String name, FunctionTypes op, LTL right, LTL left) {
        this.name = name;
        this.op = op;
        this.right = right;
        this.left = left;
    }

    public LTL parse(String ltl) {
        /* (aUb)
         *  ((aUb)&c)
         *
         *
         * */
        if (ltl.charAt(0) == '('){

            //get inner formula
            int klammer= 1,pos = 0;
            while (klammer != 0){
                pos++;
                if (ltl.charAt(pos) == '(')
                    klammer++;
                else if (ltl.charAt(pos) == ')')
                    klammer--;
            }
            this.name = ltl.substring(0,pos+1);
            this.left = new LTL().parse(ltl.substring(1,pos));
            if (this.left == null)
                ltl = ltl.substring(1);
            else
                ltl = ltl.substring(this.left.name.length() + 1);
                this.op = switch (ltl.charAt(0)){
                    case ('&') -> FunctionTypes.AND;
                    case ('|') -> FunctionTypes.OR;
                    case ('X') -> FunctionTypes.NEXT;
                    case ('U') -> FunctionTypes.UNTIL;
                    case ('R') -> FunctionTypes.RELEASE;
                    default -> throw new IllegalStateException("Unexpected value: " + ltl.substring(1 + this.left.name.length()).charAt(0));
                };
            this.right = new LTL().parse(ltl.substring(1,ltl.length()-1));

        }
        else if (ltl.charAt(0) == 'X') {
            return null;
        }
         else if (Character.isLowerCase(ltl.charAt(0))) {
            this.op = FunctionTypes.Variable;
            this.name = ltl.substring(0, 1);
        }

        return this;
    }

    public FunctionTypes getOP() {
        return op;
    }

    @Override
    public String toString() {
        return "LTL{" + "name='" + name + '\'' + ", op=" + op + ", right=" + right + ", left=" + left + '}';
    }

    public String getGraphviz() {
        StringBuilder output = new StringBuilder();
        output.append("digraph g{\n");
        output = (getGraphvizHelper(output, this));
        output.append("}");
        return output.toString();
    }

    private StringBuilder getGraphvizHelper(StringBuilder stringBuilder, LTL ltl) {
        if (ltl.left != null) {
            stringBuilder.append("\t\"").append(ltl.name).append("\" -> \"").append(ltl.left.name).append("\" [ label = \"").append(ltl.op).append(" \" ];\n");
            stringBuilder = (getGraphvizHelper(stringBuilder, ltl.left));
        }
        if (ltl.right != null) {
            stringBuilder.append("\t\"").append(ltl.name).append("\" -> \"").append(ltl.right.name).append("\" [ label = \"").append(ltl.op).append("\" ];\n");
            stringBuilder = (getGraphvizHelper(stringBuilder, ltl.right));
        }
        return stringBuilder;


    }


}

