package help;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.List;

public class testTxt {

    private static final Integer ONE = 1;

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<String, Integer> mapcheck=new HashMap<>();
//        String[] checkNames = new String[]{"initialize", "bwa_aln",
//                "bwa_samse", "sam2ext", "cnv_rmdup", "cnv_uniform",
//                "cnv_prebaseline", "cnv_baseline", "cnv_segment", "cnv_esta",
//                "cnv_disease", "cnv_graph", "cnv_filter", "cnv_graph_md",
//                "cnv_preresult", "cnv_result", "cnv_cp_pngs", "niflm",
//                "nifty_preparation", "nifty_prebaseline", "nifty_baseline",
//                "nifty_baseline_98", "nifty_baseline_99", "nifty_normalization",
//                "nifty_exter", "nifty_result", "qc", "result"};

        String[] checkNames = new String[]{"initialize",
                "bwa_aln",
                "bwa_samse",
                "sam2ext",
                "cnv_rmdup",
                "cnv_uniform",
                "cnv_prebaseline",
                "cnv_baseline",
                "cnv_segment",
                "cnv_graph",
                "cnv_esta",
                "cnv_filter",
                "cnv_graph_md",
                "cnv_preresult",
                "cnv_result",
                "cnv_cp_pngs",
                "niflm",
                "nifty_preparation",
                "nifty_prebaseline",
                "nifty_baseline",
                "nifty_baseline_99",
                "nifty_baseline_98",
                "nifty_normalization",
                "nifty_exter",
                "nifty_result",
                "qc",
                "result"};

        for (int i =0;i<checkNames.length;i++){
            mapcheck.put("finish"+" "+checkNames[i],1);
            mapcheck.put("call"+" "+checkNames[i],1);
        }


        /* 读取数据 */
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:/Users/yxx/Desktop/流程实验/nifty/.log")),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                String[] names = lineTxt.split(",");
                for (String name : names) {
                    if (map.keySet().contains(name)) {
                        map.put(name, (map.get(name) + ONE));
                    } else {
                        map.put(name, ONE);
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }

        /* 输出数据 */
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("C:/Users/yxx/Desktop/流程实验/value_map.txt")),
                    "UTF-8"));

            for (String name : map.keySet()) {
                bw.write(name + " " + map.get(name));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.err.println("write errors :" + e);
        }
    }
}