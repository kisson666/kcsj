package com.wang.tools;

import com.wang.model.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hppc on 2017/4/13.
 */
@Repository
public class ReadExcel {
    //总行数
    private int totalRows = 0;

    //总列数
    private int totalCells = 0;

    //错误信息接收
    private String errmsg;

    //构造方法
    public ReadExcel(){}

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public String getErrmsg() {
        return errmsg;
    }


    /**
     * 读取excel文件 获取信息集合
     */
    public List<Student> getExcelInfo(MultipartFile mfile,String transPath){
        String fileName = mfile.getOriginalFilename();//获取文件名
        try {
            if (!validateExcel(fileName)) { // 验证文件是否合格
                return null;
            }
            boolean isExcel2003 = true;  //判断是xls 还是xlsx
            if (isExcel2007(fileName)) {
                mfile.transferTo(new File(transPath+".xlsx"));
                isExcel2003 = false;
            } else {
                mfile.transferTo(new File(transPath+"xls"));  //转存文件
            }

            List<Student> students = createExcel(mfile.getInputStream(),isExcel2003);
            return students;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据excel格式创建对应的对象
     *
     */
    public List<Student> createExcel(InputStream is, boolean isExcel2003) {
        try {
            Workbook wb = null;
            if (isExcel2003) {
                //当是2003时创建xls
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
            List<Student> students = readExcelValue(wb);
            return students;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 读取excel的信息
     */
    private List<Student> readExcelValue(Workbook wb) {
        //得到第一个sheet 就是excel文件的第一页
        Sheet sheet = wb.getSheetAt(0);
        //得到总行数 不包括标题那些 不包括空的行
        this.totalRows = sheet.getPhysicalNumberOfRows();
        //得到列数(前提是要有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Student> students = new ArrayList<>();

        //循环excel行
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            Student student = new Student();

            //循环excel的列
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    String foo;
                    switch (c) {
                        case 0:
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            foo = cell.getStringCellValue();
                            student.setStuId(foo);
                            break;
                        case 1:
                            foo = cell.getStringCellValue();
                            student.setName(foo);
                            break;
                        case 2:
                            foo = cell.getStringCellValue();
                            student.setMajor(foo);
                            break;
                        case 3:
                            foo = cell.getStringCellValue();
                            student.setAcademy(foo);
                            break;
                        case 4:
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            foo = cell.getStringCellValue();
                            student.setEntry_year(foo);
                            break;
                        case 5:
                            foo = cell.getStringCellValue();
                            student.setHometown(foo);
                            break;
                        case 6:
                            foo = cell.getStringCellValue();
                            student.setSex(foo);
                            break;
                        default:
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            foo = cell.getStringCellValue();
                            student.setPhone(foo);
                    }

                }

            }
            //添加到list
            students.add(student);
        }
        return students;
    }


    /**
     * 验证文件名是否合格
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errmsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
