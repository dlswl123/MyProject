package myproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.TextAnchor;
import org.jfree.chart.axis.NumberAxis;


@SuppressWarnings("serial")
public class StatsPanel extends JPanel {
	
	JPanel pnlText = new JPanel();
	JPanel pnlStats = new JPanel();
	JPanel pnlIncome = new JPanel();
	JPanel pnlExpense = new JPanel();
	JPanel chartPnlIncome = new JPanel();
	JLabel lblIncome = new JLabel("총 수입");
	JTextField txtIncome = new JTextField(10);
	JLabel lblExpense = new JLabel("총 지출");
	JTextField txtExpense = new JTextField(10);
	
	JTextArea txaIncome = new JTextArea();
	JTextArea txaExpense = new JTextArea();
	String message = null;
	
	
	
	LegerDao dao = LegerDao.getInstance();
	SearchDto dto = new SearchDto();
	
	public StatsPanel() {
		this.setLayout(new BorderLayout());
		
		// North 총금액 출력부분
		pnlText.setBackground(new Color(206, 251, 201));
		pnlText.add(lblIncome);
		txtIncome.setEditable(false);
		pnlText.add(txtIncome);
		pnlText.add(lblExpense);
		txtExpense.setEditable(false);
		pnlText.add(txtExpense);
		this.add(pnlText, BorderLayout.NORTH);
		txaExpense.setEditable(false);
		txaIncome.setEditable(false);
		
		// Center 통계차트 panel
		pnlStats.setLayout(new GridLayout(1, 2));
		
		Vector<StatsVo> income = dao.statsPieIncome();
		Vector<StatsVo> expense = dao.statsPieExpense();
		printMessage(income, expense);
		
		this.setBackground(new Color(255, 217, 236));
		StatsChartPanelIncome incomeCha = new StatsChartPanelIncome();
			JFreeChart chartIncome = incomeCha.getChart(income);
			ChartPanel incomePanel = new ChartPanel(chartIncome);
		pnlStats.add(incomePanel);
		
		StatsChartPanelExpense expenseCha = new StatsChartPanelExpense();
			JFreeChart chartExpense = expenseCha.getChart(expense);
			ChartPanel ecpensePanel = new ChartPanel(chartExpense);
		pnlStats.add(ecpensePanel);
		this.add(pnlStats, BorderLayout.CENTER);
	} // 생성자
	
	public void printMessage(Vector<StatsVo> income, Vector<StatsVo> expense) {
		int sumI = 0;
		int sumE = 0;
		
		for (StatsVo vo : income) {
			String ledname = vo.getLedname();
			int ledmoney = vo.getLedmoney();
			
			message = ledname  + "\t|"
					+ ledmoney + "\n";
			txaIncome.append(message);
			sumI += ledmoney;
		}
		
		for (StatsVo vo : expense) {
			String ledname = vo.getLedname();
			int ledmoney = vo.getLedmoney();
			
			message = ledname  + "\t|"
					+ ledmoney + "\n";
			txaExpense.append(message);
			sumE += ledmoney;
		}
		txtIncome.setText(String.valueOf(sumI));
		txtExpense.setText(String.valueOf(sumE));
		
			
	} // printMessage()
	
	public class StatsChartPanelIncome {
		
		public JFreeChart getChart(Vector<StatsVo> vec) {
				
				// 차트에 들어갈 자료생성
				Vector<String> statsName = new Vector<>();
				Vector<String> statsNum = new Vector<>();
				int index = 0;
				
				for (StatsVo vo : vec) {
					String ledname = vo.getLedname();
					String ledmoney = String.valueOf(vo.getLedmoney());
					statsName.add(ledname);
					statsNum.add(ledmoney);
//					System.out.println(statsName);
//					System.out.println(statsNum);
					index++;
				} // 자료생성 for문
				
				// 차트생성
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				//데이터 입력
				for (int i = 0; i < statsName.size(); i++ ) {
					int nums = Integer.parseInt(statsNum.get(i));
					String category = statsName.get(i);
//					if () {
//						
//					}
					dataset.addValue(nums, "수입", category);
				} // 데이터 입력for문
				
				// 렌더링 생성및 세팅
				
				// 렌더링 생성
				final BarRenderer renderer = new BarRenderer();
				// 옵션 정의
				final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
				final ItemLabelPosition p_center = new ItemLabelPosition(
						ItemLabelAnchor.CENTER, TextAnchor.CENTER);
				Font f = new Font("맑은 고딕", Font.BOLD, 14);
				Font axisF = new Font("맑은 고딕", Font.BOLD, 14);
				// 렌더링 세팅
				renderer.setBaseItemLabelGenerator(generator);
				renderer.setBaseItemLabelsVisible(true);
				renderer.setBasePositiveItemLabelPosition(p_center);
				renderer.setBaseItemLabelFont(f);
				renderer.setSeriesPaint(0, new Color(232, 168, 255));
				// plot생성
				final CategoryPlot plot = new CategoryPlot();
				// plot에 데이터 적재
				plot.setDataset(dataset);
				plot.setRenderer(renderer);
				// plot 기본설정
				plot.setOrientation(PlotOrientation.VERTICAL); 		// 그래프 표시방향
				plot.setRangeGridlinesVisible(true);				// X축 가이드라인 표시여부
				plot.setDomainGridlinesVisible(true);				// Y축 가이드라인 표시여부
				// 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 먼저 등록한게 아래로 깔림 )
				plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
				// x축 세팅
				plot.setDomainAxis(new CategoryAxis());				// x축 종류설정
				plot.getDomainAxis().setTickLabelFont(axisF);		// x축 눈금라벨 폰트 조정
				plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 카테고리 라벨 위치 조정
				// y축 세팅
				plot.setRangeAxis(new NumberAxis()); 
				plot.getRangeAxis().setTickLabelFont(axisF);
				
				final JFreeChart chart = new JFreeChart(plot);
//				if () {
//					
//				}
				chart.setTitle("총 수입 차트"); // 차트타이틀
				TextTitle copyright = new TextTitle("JFreeChart", new Font("SansSerif", Font.PLAIN, 9));
				copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
				chart.addSubtitle(copyright);
				chart.setBackgroundPaint(new Color(255, 255, 162));
				return chart;
			}

		}
public class StatsChartPanelExpense {
		
		public JFreeChart getChart(Vector<StatsVo> vec) {
				
				// 차트에 들어갈 자료생성
				Vector<String> statsName = new Vector<>();
				Vector<String> statsNum = new Vector<>();
				int index = 0;
				
				for (StatsVo vo : vec) {
					String ledname = vo.getLedname();
					String ledmoney = String.valueOf(vo.getLedmoney());
					statsName.add(ledname);
					statsNum.add(ledmoney);
//					System.out.println(statsName);
//					System.out.println(statsNum);
					index++;
				} // 자료생성 for문
				
				// 차트생성
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				//데이터 입력
				for (int i = 0; i < statsName.size(); i++ ) {
					int nums = Integer.parseInt(statsNum.get(i));
					String category = statsName.get(i);
//					if () {
//						
//					}
					dataset.addValue(nums, "지출", category);
				} // 데이터 입력for문
				
				// 렌더링 생성및 세팅
				
				// 렌더링 생성
				final BarRenderer renderer = new BarRenderer();
				// 옵션 정의
				final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
				final ItemLabelPosition p_center = new ItemLabelPosition(
						ItemLabelAnchor.CENTER, TextAnchor.CENTER);
				Font f = new Font("맑은 고딕", Font.BOLD, 10);
				Font axisF = new Font("맑은 고딕", Font.BOLD, 10);
				// 렌더링 세팅
				renderer.setBaseItemLabelGenerator(generator);
				renderer.setBaseItemLabelsVisible(true);
				renderer.setBasePositiveItemLabelPosition(p_center);
				renderer.setBaseItemLabelFont(f);
				renderer.setSeriesPaint(0, new Color(232, 168, 255));
				// plot생성
				final CategoryPlot plot = new CategoryPlot();
				// plot에 데이터 적재
				plot.setDataset(dataset);
				plot.setRenderer(renderer);
				// plot 기본설정
				plot.setOrientation(PlotOrientation.VERTICAL); 		// 그래프 표시방향
				plot.setRangeGridlinesVisible(true);				// X축 가이드라인 표시여부
				plot.setDomainGridlinesVisible(true);				// Y축 가이드라인 표시여부
				// 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 먼저 등록한게 아래로 깔림 )
				plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
				// x축 세팅
				plot.setDomainAxis(new CategoryAxis());				// x축 종류설정
				plot.getDomainAxis().setTickLabelFont(axisF);		// x축 눈금라벨 폰트 조정
				plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 카테고리 라벨 위치 조정
				// y축 세팅
				plot.setRangeAxis(new NumberAxis()); 
				plot.getRangeAxis().setTickLabelFont(axisF);
				
				final JFreeChart chart = new JFreeChart(plot);
				
				chart.setTitle("총 지출 차트"); // 차트타이틀
				TextTitle copyright = new TextTitle("JFreeChart", new Font("SansSerif", Font.PLAIN, 9));
				copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
				chart.addSubtitle(copyright);
				chart.setBackgroundPaint(new Color(255, 217, 236));
				
				return chart;
			}

		}
} // class
