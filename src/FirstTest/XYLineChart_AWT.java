package FirstTest;


import java.awt.BorderLayout;
import java.awt.Color; 
import java.awt.BasicStroke; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class XYLineChart_AWT extends ApplicationFrame {
	
	  File file;
	  JFreeChart xylineChart;
	  ChartPanel chartPanel;
    
   public XYLineChart_AWT( String applicationTitle, String chartTitle,double a ) 
   {
      super(applicationTitle);
       xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Category" ,
         "Score" ,
         createDataset(a) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
     
         
      chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 500 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesPaint( 2 , Color.YELLOW );
      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
      renderer.setSeriesShapesVisible(0, false);
      plot.setRenderer( renderer ); 

	
		 setContentPane( chartPanel ); 
		 
		 
   }
   

    public   ChartPanel getChart(){
	   return chartPanel;
   }
    
   private XYDataset createDataset(double a)
   {
	   
      final XYSeries Calculation = new XYSeries( "test" );          
      double  y ;
 
      for(double x=0 ; x<=10 ;x=x+0.1){
    	  
    	  y = Math.exp(a*x);
    	  Calculation.add( x,y);
      }
    
              
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries( Calculation);
      return dataset;
   }
   
   public void saveFile(String name){
	   try {

	   ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
	   file = new File(name+getDate()+".png");
	 
		ChartUtilities.saveChartAsPNG(file, xylineChart, 500, 500,info);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
   }
   
	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("-MM-dd-yyyy--HHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	

  
}