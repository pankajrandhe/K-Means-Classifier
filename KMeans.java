import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.lang.Math;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class KMeans //implements ActionListener
{

	public static int height1=0;
	public static int width1=0;
	public static int[][] current_cluster;   //= new int[][];


	public static void main(String args[])
	{
		
		double[][] new_mean= new double[3][3];	//it will contain the updated mean of the clusters
		int k;
		int bands=3;
		Scanner in = new Scanner(System.in);

		System.out.println("Enter the number of clusters for the classification");
		k = in.nextInt();
      	System.out.println("No. of desired clusters: "+k);


		try{
			File file = new File(args[0]);
		    final BufferedImage image = ImageIO.read(file);
		 	int width = image.getWidth(null);
		    int height = image.getHeight(null);
		    KMeans.width1=width;
		    KMeans.height1=height;
		    current_cluster= new int[height1][width1];

		    //Declare matrix for R,G and B
		    int [ ] [ ] PRed = new int [ height ] [ width ] ;
		    int [ ] [ ] PGreen = new int [ height ] [ width ] ;
		    int [ ] [ ] PBlue = new int [ height ] [ width ] ;

		    //Declare output matrix for R,G and B
		    int [ ] [ ] OPRed = new int [ height ] [ width ] ;
		    int [ ] [ ] OPGreen = new int [ height ] [ width ] ;
		    int [ ] [ ] OPBlue = new int [ height ] [ width ] ;

		    //Declare the clusters 
		    int [ ] [ ] clusters = new int [ k ] [ bands  ] ;
			int [ ] [ ] clusters_old = new int [ k ] [ bands ] ;

		    
		    int temp_count=0;
		   

		    //Calculate the R, G and B matrices
		    for (int i=0;i<height;i++){
		    	for (int j=0;j<width;j++)
		    	{
		    		Color c = new Color(image.getRGB(j,i));
		    		PRed[j][i]=c.getRed();
		    		PGreen[j][i]=c.getGreen();
		    		PBlue[j][i]=c.getBlue();
               }
            }

            //Initialize the clusters
			for(int m=0;m<k;m++)
			{
				for(int n=0;n<bands;n++)
				{
					if(n==0){
						clusters[m][n]=PRed[m][m];
						clusters_old[m][n]=PRed[m][m];
					}
					else if(n==1)
					{
						clusters[m][n]=PGreen[m][m];
						clusters_old[m][n]=PGreen[m][m];
					}
					else
					{
						clusters[m][n]=PBlue[m][m];
						clusters_old[m][n]=PBlue[m][m];
					}
				}
			}
			

            do
            {           	
            	new_mean=binning(PRed,PGreen,PBlue,clusters,width,height,k,bands);

            	for(int c=0;c<k;c++)
            	{
            		for(int b=0;b<bands;b++)
            		{
            			clusters_old[c][b]=clusters[c][b];

            			clusters[c][b]=(int)(new_mean[c][b]);
					
					}	         		
            	}

            	temp_count++;		  
				           	
        	}while((eDistance(clusters[0],clusters_old[0])>=1) || (eDistance(clusters[1],clusters_old[1])>=1) && (eDistance(clusters[2],clusters_old[2])>=1)) ;

        	
				       // Create a Buffered Image.
				       final BufferedImage image1 = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
				       // We need its raster to set the pixels' values.
				       WritableRaster raster = image1.getRaster();
				       // Create the colors.
				       int[] red = new int[]{255,0,0};
				       int[] green = new int[]{0,255,0};
				       int[] blue = new int[]{0,0,255};
				       int[] yellow = new int[]{255,255,0};
				       int[] black = new int[]{0,0,0};
					   int[] cyan = new int[]{0,255,255};
					   int[] magenta = new int[]{255,0,255};
					   int[] white = new int[]{255,255,255};
					   int[] wood = new int[]{222,184,135};
					   int[] indigo = new int[]{75,0,130};
					   int[] lawngreen = new int[]{124,252,0};
					   int[] coral = new int[]{240,128,128};
					   int[] orange = new int[]{255,165,0};
					   int[] tan = new int[]{210,180,140};
					   int[] sienna = new int[]{160,82,45};
					   int[] seagreen = new int[]{46,139,87};
					   int[] brown = new int[]{139,69,19};
					   int[] turquoise = new int[]{175,238,238};
					   int[] navy = new int[]{0,0,128};
					   int[] violet = new int[]{238,130,238};
				       // Put the pixels on the raster, choosing a color depending on its coordinates.
				       for(int h=0;h<height;h++)
				         for(int w=0;w<width;w++)
				           {
				           if (KMeans.current_cluster[w][h]==0) raster.setPixel(w,h,red);      
				           else if (KMeans.current_cluster[w][h]==1) raster.setPixel(w,h,green);
				           else if (KMeans.current_cluster[w][h]==2) raster.setPixel(w,h,blue);
				           else if (KMeans.current_cluster[w][h]==3) raster.setPixel(w,h,cyan);      
						   else if (KMeans.current_cluster[w][h]==4) raster.setPixel(w,h,magenta);      
						   else if (KMeans.current_cluster[w][h]==5) raster.setPixel(w,h,yellow);      
						   else if (KMeans.current_cluster[w][h]==6) raster.setPixel(w,h,wood);      
						   else if (KMeans.current_cluster[w][h]==7) raster.setPixel(w,h,indigo);
						   else if (KMeans.current_cluster[w][h]==8) raster.setPixel(w,h,lawngreen);
						   else if (KMeans.current_cluster[w][h]==9) raster.setPixel(w,h,coral);
						   else if (KMeans.current_cluster[w][h]==10) raster.setPixel(w,h,orange);
						   else if (KMeans.current_cluster[w][h]==11) raster.setPixel(w,h,white);      
						   else if (KMeans.current_cluster[w][h]==12) raster.setPixel(w,h,violet);     
						   else if (KMeans.current_cluster[w][h]==13) raster.setPixel(w,h,tan);     
						   else if (KMeans.current_cluster[w][h]==14) raster.setPixel(w,h,brown);     
						   else if (KMeans.current_cluster[w][h]==15) raster.setPixel(w,h,turquoise);     
						   else if (KMeans.current_cluster[w][h]==16) raster.setPixel(w,h,seagreen);     
						   else if (KMeans.current_cluster[w][h]==17) raster.setPixel(w,h,sienna);     
						   else if (KMeans.current_cluster[w][h]==18) raster.setPixel(w,h,navy);     
						   else  raster.setPixel(w,h,black);    
				           }
				       // Store the image using the PNG format.
				       ImageIO.write(image1,"PNG",new File("rgbpattern.png"));


			
            final JFrame frame = new JFrame();
            frame.setSize(500, 500);
            frame.setTitle("K-Means Classifier");
            final JFrame frame1= new JFrame();
            frame1.setTitle("Original Image");
            final JFrame frame2= new JFrame();
            frame2.setTitle("K-Means Classification Output");
            //final JTextField textField = new JTextField(20);
            final JFileChooser fc = new JFileChooser();
            JButton load = new JButton( "Load");
            JButton classify = new JButton( "Classify");
            JButton browse = new JButton( "Browse");
            JLabel label1= new JLabel("Test");
            label1.setText("<html><br><b>K-Means Classifier</b><br></html>");

            frame1.getContentPane().setLayout(new FlowLayout());
			frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame2.getContentPane().setLayout(new FlowLayout());
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.getContentPane().setLayout(new FlowLayout());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(label1,BorderLayout.NORTH);
			frame.add(load,BorderLayout.SOUTH);
			frame.add(classify,BorderLayout.SOUTH);
			//frame.add(browse,BorderLayout.SOUTH);


			/*browse.addActionListener(new ActionListener()
        	{
            @Override
            public void actionPerformed(ActionEvent event)

	            {	
	            	
	            	int returnVal = fc.showOpenDialog(KMeans.this);
    				//Handle open button action.
    				//if (event.getSource() == browse) {
        			//int returnVal = fc.showOpenDialog(KMeans.this);

       				// if (returnVal == JFileChooser.APPROVE_OPTION) {
            		//File file1 = fc.getSelectedFile();
            		//This is where a real application would open the file.
            		//log.append("Opening: " + file1.getName() + "." + newline);
	        		//} else {
	            	//		log.append("Open command cancelled by user." + newline);
	        		//	}
   					//} 
				}

	            	//String textFieldValue = textField.getText();
	            	//frame.getContentPane().add(new JLabel(new ImageIcon(image)));
	            	//frame.pack();
					//frame.setVisible(true);
				
        	});	*/		


			load.addActionListener(new ActionListener()
        	{
            @Override
            public void actionPerformed(ActionEvent event)
	            {	
	            	//String textFieldValue = textField.getText();
	            	frame1.getContentPane().add(new JLabel(new ImageIcon(image)));
	            	frame1.pack();
					frame1.setVisible(true);
				}
        	});

        	classify.addActionListener(new ActionListener()
        	{
            @Override
            public void actionPerformed(ActionEvent event)
	            {
	            	frame2.getContentPane().add(new JLabel(new ImageIcon(image1)));
	            	frame2.pack();
					frame2.setVisible(true);

	            }
        	});



			//frame.getContentPane().add(new JLabel(new ImageIcon(image)));
			//frame.getContentPane().add(new JLabel(new ImageIcon(image1)));
			//frame.pack();
			frame.setVisible(true);

		}


		catch (Exception e)
        {
          e.printStackTrace();
          System.exit(1);
        } 

	}

	// Calculates the Eucledean distance between the pixel and the cluster centroid 
        public static double eDistance(int[] p , int[] c){
        	double euc_dist=0.0;
        	double dist=0.0;
        	for (int b=0;b<3;b++)
        	{
        		dist=(p[b]-c[b])*(p[b]-c[b]);
        		euc_dist=euc_dist+dist;
        	}
        	return Math.sqrt(euc_dist);
        }


    // Allocate the cluster number a/c to minimum distance criteria to each pixel 
        public static int clusterAllocate(double[] d){
   
        	double min_dist=d[0];
        	int clust=0;
        	for(int m=0;m<d.length;m++)
        	{
        		if(d[m]<min_dist)
        			{
        				min_dist=d[m];
        				clust=m;	
        			}
        	}
        	return clust;
        }

    // Performs the binning iteratively
        public static double[][] binning(int[][] PRed,int[][] PGreen, int[][] PBlue, int[][] clusters, int width, int height , int k, int bands){

        	double[] pix_clust_dist=new double[k];   //dont forget to initialize it to zero here

			int[][] sum = new int[k][3];	//dont forget to initialize to zero here

			double[][] mean= new double [k][3];

			// stores the pixels belonging to the each cluster
			int [] count= new int[k];   //dont forget to initilize to zero at begining here

            for (int i=0;i<height;i++){

		    	for (int j=0;j<width;j++)
		    	{	
		    		int cluster_number=0;
		    		int[] pixel={PRed[j][i],PGreen[j][i],PBlue[j][i]};

		    		//calculate the distance from the pixel to each of the k clusters
		    		for(int z=0;z<k;z++)
		    		{
		    		pix_clust_dist[z]=eDistance(pixel,clusters[z]);

		    		}
		    		//allocate the cluster to the pixel using minimum Euclidean distance criteria
		    		cluster_number=clusterAllocate(pix_clust_dist);
		    		KMeans.current_cluster[j][i]=cluster_number;
	
		    		count[cluster_number]++;
		    		sum[cluster_number][0]=sum[cluster_number][0]+PRed[j][i];
		    		sum[cluster_number][1]=sum[cluster_number][1]+PGreen[j][i];
		    		sum[cluster_number][2]=sum[cluster_number][2]+PBlue[j][i];    		

               }
        	}

        	//Update the mean or centroid feature vector for each cluster

        	for(int c=0;c<k;c++)
        	{
        		for(int b=0;b<bands;b++)
        		{
	        		mean[c][b]=sum[c][b]/(count[c]+1);
	        	}

        	}
        	return (mean);
        	  
		}			                                
}