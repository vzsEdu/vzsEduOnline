package com.civil.report.reportService;

import com.civil.report.reportBean.WaveTimeHistoryBean;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by leodavinci on 2015/5/16.
 */
public class ExportExcelReport {


    public ByteArrayOutputStream generateWaveExcelReportStream(ArrayList xAngelArray, ArrayList yAngelArray, Integer waveCount) throws Exception {

        Resource resource = new ClassPathResource("template/excel/WaveTimeHistoryTemplate" + waveCount + ".xlsx");
        InputStream is = resource.getInputStream();
        Workbook wb = WorkbookFactory.create(is);
        fillWaveWorkbook(wb, xAngelArray, "X");
        fillWaveWorkbook(wb, yAngelArray, "Y");

        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
        wb.write(baos);
        is.close();

        return baos;
    }


    private void fillWaveWorkbook (Workbook wb, ArrayList angelArray, String sheetName) {

        boolean firstWave = true;
        String lastWaveName = null;
        int rowCount = 0;
        int waveCount = 0;

        Sheet sheetX = wb.getSheet(sheetName);
        Row titleRow = sheetX.getRow(0);
        Cell titleCell = titleRow.getCell(waveCount);
        titleCell.setCellValue(sheetName + " Direct/Floor");

        for (int i = 0; i < angelArray.size(); i++) {
            WaveTimeHistoryBean bean = (WaveTimeHistoryBean)angelArray.get(i);
            String wave = bean.getWave();
            Integer floor = bean.getFloor();
            BigDecimal shear = bean.getShear();

            // when change to new wave name
            if (!wave.equals(lastWaveName)) {
                waveCount++;
                Cell cell = titleRow.getCell(waveCount);
                cell.setCellValue(wave);
                if (lastWaveName != null) {
                    firstWave = false;
                }
                lastWaveName = bean.getWave();
                rowCount = 1;
            }

            Row dataRow = sheetX.getRow(rowCount);
            Cell cell = dataRow.getCell(waveCount);
            cell.setCellValue(shear.doubleValue());

            if (firstWave) {
                Cell cellFloor = dataRow.getCell(0);
                cellFloor.setCellValue(floor);
            }
            rowCount++;
        }
    }


    public ByteArrayOutputStream generateExcelReportStream(ArrayList javeBeanArrayList) {

        String jasperFileName = "D:\\workspace\\ireport\\test_javabean.jasper";
        String jrxmlFileName = "D:\\workspace\\ireport\\test_javabean.jrxml";
        Map<String, Object> jrxmlParams = null;

        try {
            JasperReport objJReport = JasperCompileManager.compileReport(jrxmlFileName);


            JRDataSource dataSource = new JRBeanCollectionDataSource(javeBeanArrayList);

            JasperPrint jasperPrint = JasperFillManager.fillReport(objJReport, jrxmlParams, dataSource);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JRXlsxExporter exporter = getCommonXlsxExporter();

            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, baos); // fill byte array output stream

            exporter.exportReport();

            return baos;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    private JRXlsxExporter getCommonXlsxExporter(){
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JExcelApiExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);

        return exporter;
    }


}
