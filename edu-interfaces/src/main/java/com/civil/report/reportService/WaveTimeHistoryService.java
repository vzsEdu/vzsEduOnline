package com.civil.report.reportService;

import com.civil.report.reportBean.WaveTimeHistoryBean;
import lombok.extern.slf4j.Slf4j;
import org.jfree.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by leodavinci on 2015/5/17.
 */
@Slf4j
public class WaveTimeHistoryService {

    public final static String WAVE = "Wave";
    public final static String ANGEL = "Angel";
    public final static String WAVE_COUNT = "Wave_Count";

    public ByteArrayOutputStream processWaveTimeHistoryLoading(BufferedReader br) throws Exception{
        StateMachine stateMachine = new StateMachine(State.OFF);
        ArrayList<WaveTimeHistoryBean> xAngelWaveTimeHistoryArray = new ArrayList<WaveTimeHistoryBean>();
        ArrayList<WaveTimeHistoryBean> yAngelWaveTimeHistoryArray = new ArrayList<WaveTimeHistoryBean>();
        Map<String, Integer> waveCountMap = new HashMap<String, Integer>();
        waveCountMap.put(WAVE_COUNT, 0);
        DetailLineProcessAction action = new DetailLineProcessAction(null, null, waveCountMap, xAngelWaveTimeHistoryArray, yAngelWaveTimeHistoryArray);
        stateMachine.addAction(State.OFF, action);
        stateMachine.addAction(State.TITLE, action);
        stateMachine.addAction(State.SUBTITLE, action);
        stateMachine.addAction(State.DETAIL, action);
        stateMachine.addAction(State.VALUE, action);
        stateMachine.addAction(State.END, action);
        stateMachine.addAction(State.PAUSE, action);
        stateMachine.processStream(br);

        log.info("Wave Count: " + waveCountMap.get(WAVE_COUNT));

        ExportExcelReport eer = new ExportExcelReport();
        return eer.generateWaveExcelReportStream(xAngelWaveTimeHistoryArray, yAngelWaveTimeHistoryArray, waveCountMap.get(WAVE_COUNT));

    }
//
//    private static WaveTimeHistoryBean[] data = {
//            new WaveTimeHistoryBean("USER1",0,16,new BigDecimal(101)),
//            new WaveTimeHistoryBean("USER1",0,15,new BigDecimal(103)),
//            new WaveTimeHistoryBean("USER1",0,14,new BigDecimal(123)),
//            new WaveTimeHistoryBean("USER1",0,13,new BigDecimal(156)),
//            new WaveTimeHistoryBean("USER1",0,12,new BigDecimal(165)),
//            new WaveTimeHistoryBean("USER1",0,11,new BigDecimal(177)),
//            new WaveTimeHistoryBean("USER1",0,10,new BigDecimal(183)),
//            new WaveTimeHistoryBean("USER1",0,9,new BigDecimal(196)),
//
//            new WaveTimeHistoryBean("USER2",0,16,new BigDecimal(105)),
//            new WaveTimeHistoryBean("USER2",0,15,new BigDecimal(107)),
//            new WaveTimeHistoryBean("USER2",0,14,new BigDecimal(123)),
//            new WaveTimeHistoryBean("USER2",0,13,new BigDecimal(156)),
//            new WaveTimeHistoryBean("USER2",0,12,new BigDecimal(165)),
//            new WaveTimeHistoryBean("USER2",0,11,new BigDecimal(177)),
//            new WaveTimeHistoryBean("USER2",0,10,new BigDecimal(183)),
//            new WaveTimeHistoryBean("USER2",0,9,new BigDecimal(196)),
//
//            new WaveTimeHistoryBean("USER7",0,16,new BigDecimal(105)),
//            new WaveTimeHistoryBean("USER7",0,15,new BigDecimal(107)),
//            new WaveTimeHistoryBean("USER7",0,14,new BigDecimal(123)),
//            new WaveTimeHistoryBean("USER7",0,13,new BigDecimal(156)),
//            new WaveTimeHistoryBean("USER7",0,12,new BigDecimal(165)),
//            new WaveTimeHistoryBean("USER7",0,11,new BigDecimal(177)),
//            new WaveTimeHistoryBean("USER7",0,10,new BigDecimal(183)),
//            new WaveTimeHistoryBean("USER7",0,9,new BigDecimal(196))
//    };
//    public static Object[] getBeanArray() {
//        return data;
//    }
//    public static Collection<?> getBeanCollection() {
//        return Arrays.asList(data);
//    }

}

class DetailLineProcessAction extends AbstractAction {

    HashMap<String, Object> lastMap;
    ArrayList<WaveTimeHistoryBean> xAngelWaveTimeHistoryArray;
    ArrayList<WaveTimeHistoryBean> yAngelWaveTimeHistoryArray;
    Map<String, Integer> waveCountMap;


    public DetailLineProcessAction(AbstractAction nextAction, Map<String, Object> output, Map<String, Integer> waveCountMap,
                                   ArrayList<WaveTimeHistoryBean> xAngelWaveTimeHistoryArray, ArrayList<WaveTimeHistoryBean> yAngelWaveTimeHistoryArray) {
        super(nextAction, output);
        this.waveCountMap = waveCountMap;
        this.xAngelWaveTimeHistoryArray = xAngelWaveTimeHistoryArray;
        this.yAngelWaveTimeHistoryArray = yAngelWaveTimeHistoryArray;
    }

    public State processLine(String line, State currentState) throws Exception {
        if (line != null && line.matches("^ ================== \\[.*\\]地震波各时刻最大反应==========================$")) {
            lastMap = new HashMap<String, Object>();
            lastMap.put(WaveTimeHistoryService.WAVE, line.substring(21, 31).trim());
            waveCountMap.put(WaveTimeHistoryService.WAVE_COUNT, waveCountMap.get(WaveTimeHistoryService.WAVE_COUNT)+1);
            return State.TITLE;
        }

        if (State.END.equals(currentState)) {
            return currentState;
        }

        if ((State.TITLE.equals(currentState) || State.PAUSE.equals(currentState))
            && line.matches("^     主方向作用方向角 :.*$")) {
            lastMap.put(WaveTimeHistoryService.ANGEL, Integer.parseInt(new DecimalFormat("0").format(Double.parseDouble(line.substring(15, 25).trim()))));
            return State.SUBTITLE;
        }


        if (State.SUBTITLE.equals(currentState)
                &&  line.matches("^                  Shear      Ratio        Moment       Velocity   Acceleration.*$")) {
            return State.DETAIL;
        }

        if (State.DETAIL.equals(currentState) &&  line.matches("^ ------------------------------------------------------------------------------.*$"))  {
            return State.VALUE;
        }

        if (State.VALUE.equals(currentState) && line.matches("^\\s*$")) {
            if ((Integer)lastMap.get(WaveTimeHistoryService.ANGEL) == 0) {
                return State.PAUSE;
            } else if ((Integer)lastMap.get(WaveTimeHistoryService.ANGEL) == 90) {
                return State.END;
            }
        }

        if (State.VALUE.equals(currentState)) {
            String wave = (String)lastMap.get(WaveTimeHistoryService.WAVE);
            Integer angel = (Integer)lastMap.get(WaveTimeHistoryService.ANGEL);
            Integer floor = Integer.parseInt(line.substring(1, 5).trim());
            BigDecimal shear  = BigDecimal.valueOf(Double.parseDouble(line.substring(14, 23).trim()));
            WaveTimeHistoryBean bean = new WaveTimeHistoryBean(wave, angel, floor, shear);
            Log.info(bean);
            if (lastMap.get(WaveTimeHistoryService.ANGEL) == 0) {
                xAngelWaveTimeHistoryArray.add(bean);
            } else if (lastMap.get(WaveTimeHistoryService.ANGEL) == 90) {
                yAngelWaveTimeHistoryArray.add(bean);
            }
            return currentState;
        }

        return currentState;
    }
}