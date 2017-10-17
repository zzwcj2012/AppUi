package appui.utils;

import jxl.*;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * excel读取类，用于读取excel数据
 *
 */
public class ExcelReadUtil {

	private Log log=new Log(this.getClass());
	
	//获取当前sheet的行数
	public static int getExcelRows(String sourcefile) throws Exception
	{
		int rows=0;
		Workbook testcase_data_workbook = null;
		try {
			testcase_data_workbook=Workbook.getWorkbook(new File(sourcefile));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet[] all_sheet=testcase_data_workbook.getSheets();
		for(int i=0;i<all_sheet.length;i++)
		{
			Sheet testcase_sheet=testcase_data_workbook.getSheet(i);
			rows=testcase_sheet.getRows();
			
		}
		return rows;
		
		
		
		

	}
	
	/**
	 *
	 * @param sheet_id  工作表ID从0开始
	 * @param start_row 开始行从0开始
	 * @param end_row  结束行 从0开始
	 * @param start_col 开始列，从0开始
	 * @return  Object[][]
	 */
	public static  Object[][] case_data_excel( int sheet_id,
											   int start_row,
											   int end_row,
											   int start_col,
//											   int end_col,
										       String sourcefile


	)
	{
		String cell_value=null;
		Cell cell=null;
		ArrayList<Object> testcase_data_list=new ArrayList<Object>();
		String[][] testcase_data_array=new String[end_row-start_row+1][100-start_col+1];
		Workbook testcase_data_workbook = null;
		try
		{

			testcase_data_workbook=Workbook.getWorkbook(new File(sourcefile));
			Sheet testcase_data_sheet=testcase_data_workbook.getSheet(sheet_id);
		
			int rows=testcase_data_sheet.getRows();
			int cols=testcase_data_sheet.getColumns();
			/**
			if (end_row-start_row+1>rows) {
				System.out.println("选择的行数超出实际数据范围内");
			}
			if (end_col-start_col+1>cols) {
				System.out.println("选择的数据列数超出实际数据范围内");
			}
			if (end_row>rows-1) {
				System.out.println("行标超出实际行数数");
			}
			if (end_col>cols-1) {
				System.out.println("列标超出实际行数数");
			}
			**/
			//获取每行用例数据
			//for (int row = start_row,i=0; row <rows && i<testcase_data_array.length; row++,i++)
			for (int row = start_row,i=0; row <=end_row||i<testcase_data_array.length; row++,i++)
			{
				//用一个数组，存放每行数据。//每循环一行，初始化一次数组，将原有数组内存释放
				//特别注意，只取一个表里的几列数据的时候，数组的长度一定要初始化正确
				String[] row_array=new String[cols-1-start_col+1];
				for(int col=start_col,j=0;col<cols-1||j<row_array.length;col++,j++)
				{
					cell=testcase_data_sheet.getCell(col, row);
					if(cell.getType() == CellType.DATE){
						DateCell dc = (DateCell)cell;
						Date date = dc.getDate();	//获取单元格的date类型
						cell_value=formatDate(date,"yyyy-MM-dd");
					}
					else {
						cell_value=testcase_data_sheet.getCell(col, row).getContents();
					}

					//将每一行的每一个列值赋值给行数组，循环行数组赋值
					row_array[j]=cell_value;
				}
				//每获得一行数据就将起存入，用例LIST列表中	
				testcase_data_list.add(row_array);

			}

			String[][] testcase_data_array_try=new String[testcase_data_list.size()][cols-1-start_col+1];
			testcase_data_array_try=testcase_data_list.toArray(testcase_data_array_try);
			testcase_data_array=testcase_data_array_try;

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Object[][] testcase_data_object=(Object[][])testcase_data_array;
		return testcase_data_object;

	}

	/**
	 *
	 * @param sheet_id  工作表ID从0开始
	 * @param start_row 开始行从0开始
	 * @param end_row  结束行 从0开始
	 * @param start_col 结束列，从0开始
	 * @param end_col 结束列从0开始
	 * @return 返回 Object[][]
	 */
	public static  Object[][] case_data_excel( int sheet_id,
											   int start_row,
											   int end_row,
											   int start_col,
											   int end_col,
											   InputStream SourceInputStream

	)
	{
		String cell_value=null;
		Cell cell=null;
		ArrayList<Object> testcase_data_list=new ArrayList<Object>();
		String[][] testcase_data_array=new String[end_row-start_row+1][end_col-start_col+1];
		//System.out.println(SourceInputStream);
		try
		{
			Workbook testcase_data_workbook=Workbook.getWorkbook(SourceInputStream);
			Sheet testcase_data_sheet=testcase_data_workbook.getSheet(sheet_id);
			int rows=testcase_data_sheet.getRows();
			int cols=testcase_data_sheet.getColumns();
			if (end_row-start_row+1>rows) {
				System.out.println("选择的行数超出实际数据范围内");
			}
			if (end_col-start_col+1>cols) {
				System.out.println("选择的数据列数超出实际数据范围内");
			}
			if (end_row>rows-1) {
				System.out.println("行标超出实际行数数");
			}
			if (end_col>cols-1) {
				System.out.println("列标超出实际行数数");
			}
			//获取每行用例数据
			for (int row = start_row,i=0; row <=end_row||i<testcase_data_array.length; row++,i++)
			{
				//用一个数组，存放每行数据。//每循环一行，初始化一次数组，将原有数组内存释放
				//特别注意，只取一个表里的几列数据的时候，数组的长度一定要初始化正确
				String[] row_array=new String[end_col-start_col+1];
				for(int col=start_col,j=0;col<=end_col||j<row_array.length;col++,j++)
				{
					cell=testcase_data_sheet.getCell(col, row);
					if(cell.getType() == CellType.DATE){
						DateCell dc = (DateCell)cell;
						Date date = dc.getDate();	//获取单元格的date类型
						cell_value=formatDate(date,"yyyy-MM-dd");
					}
					else {
						cell_value=testcase_data_sheet.getCell(col, row).getContents();
					}

					//将每一行的每一个列值赋值给行数组，循环行数组赋值
					row_array[j]=cell_value;
				}
				//每获得一行数据就将起存入，用例LIST列表中
				testcase_data_list.add(row_array);

			}

			String[][] testcase_data_array_try=new String[testcase_data_list.size()][end_col-start_col+1];
			testcase_data_array_try=testcase_data_list.toArray(testcase_data_array_try);
			testcase_data_array=testcase_data_array_try;

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Object[][] testcase_data_object=(Object[][])testcase_data_array;
		return testcase_data_object;

	}
	private  static String formatDate(Date date,String format)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		System.out.println(formatter.format(date).toString());
		return formatter.format(date).toString();

	}
	
	/**获取element名称**/
	public  String getElementName(String element) throws Exception
	{
		String elementName=null;
		try{
			if(!element.isEmpty())
			{
				String[] names = element.split("\\|");
			    elementName=names[0];
				
			}
			
			else
			{
				log.error("单元格内容为空！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return elementName;
	
		
	}
	
	/**获取element类型**/
	public  String getElementType(String element) throws Exception
	{
		
		String elementType=null;
		try{
			if(!element.isEmpty())
			{
				String[] names = element.split("\\|");
				elementType=names[1];
				
			}
			
			else
			{
				log.error("单元格内容为空！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return elementType;
			 
	}
	
	
	/**获取element值**/
	public  String getElementContent(String element) throws Exception
	{
	 
		String elementContent=null;
		try{
			if(!element.isEmpty())
			{
				String[] names = element.split("\\|");
				elementContent=names[2];
				
			}
			
			else
			{
				log.error("单元格内容为空！");
			}
		}catch(Exception e){
			e.printStackTrace();	
		}
		return elementContent;
			 
	}
	

	
	public  static  void  main(String[] args) throws Exception
	{
		String filePath="D:/bindcard.xls";
		int rows=ExcelReadUtil.getExcelRows(filePath);
		System.out.println("当前sheet行数："+rows);
		Object[][] excel=ExcelReadUtil.case_data_excel(0, 0, 0,1,filePath);
		for (int i=0;i<excel.length;i++)
		{
			Object[] excel2=excel[i];
			for (int j=0;j<excel2.length;j++)
			{
				if(!((String) excel2[j]).isEmpty())
				{
					System.out.println("第"+j+"列：");
					String[] names = ((String) excel2[j]).split("\\=");
					String name=names[0];
					System.out.println(name);
					String type=names[1];
					System.out.println(type);
					String element=names[2];
					System.out.println(element);
					String value = null;
					if(names.length==4)
					{
						  value=names[3];
						 System.out.println(value);
					}
				}
				System.out.println();
			}
			 
	  }
	}
}


	