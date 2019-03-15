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
	JLabel lblIncome = new JLabel("�� ����");
	JTextField txtIncome = new JTextField(10);
	JLabel lblExpense = new JLabel("�� ����");
	JTextField txtExpense = new JTextField(10);
	
	JTextArea txaIncome = new JTextArea();
	JTextArea txaExpense = new JTextArea();
	String message = null;
	
	
	
	LegerDao dao = LegerDao.getInstance();
	SearchDto dto = new SearchDto();
	
	public StatsPanel() {
		this.setLayout(new BorderLayout());
		
		// North �ѱݾ� ��ºκ�
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
		
		// Center �����Ʈ panel
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
	} // ������
	
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
				
				// ��Ʈ�� �� �ڷ����
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
				} // �ڷ���� for��
				
				// ��Ʈ����
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				//������ �Է�
				for (int i = 0; i < statsName.size(); i++ ) {
					int nums = Integer.parseInt(statsNum.get(i));
					String category = statsName.get(i);
//					if () {
//						
//					}
					dataset.addValue(nums, "����", category);
				} // ������ �Է�for��
				
				// ������ ������ ����
				
				// ������ ����
				final BarRenderer renderer = new BarRenderer();
				// �ɼ� ����
				final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
				final ItemLabelPosition p_center = new ItemLabelPosition(
						ItemLabelAnchor.CENTER, TextAnchor.CENTER);
				Font f = new Font("���� ���", Font.BOLD, 14);
				Font axisF = new Font("���� ���", Font.BOLD, 14);
				// ������ ����
				renderer.setBaseItemLabelGenerator(generator);
				renderer.setBaseItemLabelsVisible(true);
				renderer.setBasePositiveItemLabelPosition(p_center);
				renderer.setBaseItemLabelFont(f);
				renderer.setSeriesPaint(0, new Color(232, 168, 255));
				// plot����
				final CategoryPlot plot = new CategoryPlot();
				// plot�� ������ ����
				plot.setDataset(dataset);
				plot.setRenderer(renderer);
				// plot �⺻����
				plot.setOrientation(PlotOrientation.VERTICAL); 		// �׷��� ǥ�ù���
				plot.setRangeGridlinesVisible(true);				// X�� ���̵���� ǥ�ÿ���
				plot.setDomainGridlinesVisible(true);				// Y�� ���̵���� ǥ�ÿ���
				// ������ ���� ���� : dataset ��� ������� ������ ( ���� ����Ѱ� �Ʒ��� �� )
				plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
				// x�� ����
				plot.setDomainAxis(new CategoryAxis());				// x�� ��������
				plot.getDomainAxis().setTickLabelFont(axisF);		// x�� ���ݶ� ��Ʈ ����
				plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // ī�װ� �� ��ġ ����
				// y�� ����
				plot.setRangeAxis(new NumberAxis()); 
				plot.getRangeAxis().setTickLabelFont(axisF);
				
				final JFreeChart chart = new JFreeChart(plot);
//				if () {
//					
//				}
				chart.setTitle("�� ���� ��Ʈ"); // ��ƮŸ��Ʋ
				TextTitle copyright = new TextTitle("JFreeChart", new Font("SansSerif", Font.PLAIN, 9));
				copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
				chart.addSubtitle(copyright);
				chart.setBackgroundPaint(new Color(255, 255, 162));
				return chart;
			}

		}
public class StatsChartPanelExpense {
		
		public JFreeChart getChart(Vector<StatsVo> vec) {
				
				// ��Ʈ�� �� �ڷ����
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
				} // �ڷ���� for��
				
				// ��Ʈ����
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				//������ �Է�
				for (int i = 0; i < statsName.size(); i++ ) {
					int nums = Integer.parseInt(statsNum.get(i));
					String category = statsName.get(i);
//					if () {
//						
//					}
					dataset.addValue(nums, "����", category);
				} // ������ �Է�for��
				
				// ������ ������ ����
				
				// ������ ����
				final BarRenderer renderer = new BarRenderer();
				// �ɼ� ����
				final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
				final ItemLabelPosition p_center = new ItemLabelPosition(
						ItemLabelAnchor.CENTER, TextAnchor.CENTER);
				Font f = new Font("���� ���", Font.BOLD, 10);
				Font axisF = new Font("���� ���", Font.BOLD, 10);
				// ������ ����
				renderer.setBaseItemLabelGenerator(generator);
				renderer.setBaseItemLabelsVisible(true);
				renderer.setBasePositiveItemLabelPosition(p_center);
				renderer.setBaseItemLabelFont(f);
				renderer.setSeriesPaint(0, new Color(232, 168, 255));
				// plot����
				final CategoryPlot plot = new CategoryPlot();
				// plot�� ������ ����
				plot.setDataset(dataset);
				plot.setRenderer(renderer);
				// plot �⺻����
				plot.setOrientation(PlotOrientation.VERTICAL); 		// �׷��� ǥ�ù���
				plot.setRangeGridlinesVisible(true);				// X�� ���̵���� ǥ�ÿ���
				plot.setDomainGridlinesVisible(true);				// Y�� ���̵���� ǥ�ÿ���
				// ������ ���� ���� : dataset ��� ������� ������ ( ���� ����Ѱ� �Ʒ��� �� )
				plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
				// x�� ����
				plot.setDomainAxis(new CategoryAxis());				// x�� ��������
				plot.getDomainAxis().setTickLabelFont(axisF);		// x�� ���ݶ� ��Ʈ ����
				plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // ī�װ� �� ��ġ ����
				// y�� ����
				plot.setRangeAxis(new NumberAxis()); 
				plot.getRangeAxis().setTickLabelFont(axisF);
				
				final JFreeChart chart = new JFreeChart(plot);
				
				chart.setTitle("�� ���� ��Ʈ"); // ��ƮŸ��Ʋ
				TextTitle copyright = new TextTitle("JFreeChart", new Font("SansSerif", Font.PLAIN, 9));
				copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
				chart.addSubtitle(copyright);
				chart.setBackgroundPaint(new Color(255, 217, 236));
				
				return chart;
			}

		}
} // class
