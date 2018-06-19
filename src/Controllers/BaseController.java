package Controllers;

import Hongroise.MethodeHongroise;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrianina_pc on 16/06/2018.
 */

@Controller
public class BaseController {

    //--------- probleme d'affectation
    public int[][] buildIntMatrixByParams(Map<String,String> allRequestParams, int init){
        int[][] ret = new int[init][];
        for (int i = 0; i < init; i++) {
            int[] temp = new int[init];
            for (int j = 0; j < init; j++) {
                temp[j] = Integer.parseInt(allRequestParams.get("tab"+i+j));
            }
            ret[i]= temp;
        }
        return ret;
    }

    //--------- probleme de transport
    public double[][] buildDoubleMatrixByParams(Map<String,String> allRequestParams, int ligne, int colonne){
        double[][] ret = new double[ligne][];
        for (int i = 0; i < ligne; i++) {
            double[] temp = new double[colonne];
            for (int j = 0; j < colonne; j++) {
                temp[j] = Double.parseDouble(allRequestParams.get("tab"+i+j));
            }
            ret[i]= temp;
        }
        return ret;
    }


    public double[] buildDoubleTabByParams(Map<String,String> allRequestParams, int ligne, String name){
        double[] ret = new double[ligne];
        for (int i = 0; i < ligne; i++) {
            ret[i] = Double.parseDouble(allRequestParams.get(name+i));
        }
        return ret;
    }

    //-------- reseau de neurone liste des entrees
    public List<double[]> buildListDoubleByParams(Map<String,String> allRequestParams,
                                                  int ligne,int colonne, String name){
        List<double[]> allData = new ArrayList<double[]>();
        for (int i = 0; i < ligne; i++) {
            double[] temp = new double[colonne];
            for (int j = 0; j < colonne; j++) {
                temp[j] = Double.parseDouble(allRequestParams.get(name+i+j));
            }
           allData.add(temp);
        }
        return allData;
    }
    //-------- reseau de neurone liste des sorties
    public List<double[][]> buildListDoubleTabTabByParams(Map<String,String> allRequestParams,
                                                  int ligne,int colonne, String name){
        List<double[][]> allData = new ArrayList<double[][]>();
        for (int i = 0; i < ligne; i++) {
            double[][] temp = new double[colonne][1];
            for (int j = 0; j < colonne; j++) {
                temp[j][0] = Double.parseDouble(allRequestParams.get(name+i+j));
            }
            allData.add(temp);
        }
        return allData;
    }

    //------- convert Multipartfile to java.io.File
    public File convertSpringFile(MultipartFile file) throws IOException {
        File newFile = new File("D:/workspaceJEE/GrapheAlgorithme/src/Save/new"+file.getOriginalFilename());
        if(newFile.exists()) newFile.delete();
        file.transferTo(newFile);
        return newFile;
    }
}
