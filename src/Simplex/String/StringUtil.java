package Simplex.String;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class StringUtil {
    public String formaterEquationLineaire(String input){
        String res =  input.replaceAll(" ", "");
        res = res.replace("+", " +");
        return res.replace("-", " -");
    }

    public String[] separerNbrVar(String s){
        String ret[] = new String[2];
        ret[0] = "";
        ret[1] = "";

        for(int i=0;i < s.length();i++){
            if((s.charAt(i) == '+') || (s.charAt(i) == '-') || (s.charAt(i) == '.') || (s.charAt(i) == ',') || (Character.isDigit(s.charAt(i)))){
                ret[0] += s.charAt(i);
            }
            else{
                ret[1] += s.charAt(i);
            }
        }

        if(("".equals(ret[0])||("+".equals(ret[0]))||("-".equals(ret[0])))){
            ret[0] = "1";
        }

        return ret;
    }

    public String[] separerContrainte(String contrainte){
        String ret[] = new String[3];

        String split[] = contrainte.split("<=");

        if(split.length!=1){
            ret[0] = split[0];
            ret[1] = split[1];
            ret[2] = "<=";
        }else{
            split = contrainte.split(">=");
            ret[0] = split[0];
            ret[1] = split[1];
            ret[2] = ">=";
        }
        return ret;
    }
}
