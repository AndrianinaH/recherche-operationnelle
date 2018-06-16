package Controllers;

import Hongroise.MethodeHongroise;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * Created by Andrianina_pc on 16/06/2018.
 */

@Controller
public class BaseController {

    public int[][] buildMatrixByParams(Map<String,String> allRequestParams, int init){
        int[][] ret = new int[init][];
        for (int i = 0; i < init; i++) {
            int[] temp = new int[init];
            for (int j = 0; j < init; j++) {
//                System.out.println("tab"+i+j+" = "+allRequestParams.get("tab"+i+j));
                temp[j] = Integer.parseInt(allRequestParams.get("tab"+i+j));
            }
            ret[i]= temp;
        }
        return ret;
    }
}
