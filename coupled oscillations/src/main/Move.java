package main;

import javax.print.attribute.standard.NumberOfDocuments;

import shapes.Point;

public class Move implements Runnable {
	private Thread th;
	private double[] q = new double[5];
	private double[] q0 = new double[5];
	private double t = 0.00;
	private double mu = 0.00;
	private double mup = 0.00;
	public Move(){
		t = 0.00;
		q = new double[Mainwindow.numbofmass+1];
		q0 = new double[Mainwindow.numbofmass+1];
		mu = 0.00;
		
		for(int i = 1;i <= Mainwindow.numbofmass;i++){
			if(Mainwindow.Verbtn.isSelected()){
				q0[i] = Mainwindow.mass[i-1].getCenter().y - Mainwindow.Opoint[i-1].y;
				q[i] = q0[i];
			}
			if(Mainwindow.Horbtn.isSelected()){
				q0[i] = Mainwindow.mass[i-1].getCenter().x - Mainwindow.Opoint[i-1].x;
				q[i] = q0[i];
			}
		}   
	}   
	public double MU(int r){
		
		for (int j = 1; j <= Mainwindow.numbofmass; j++) {
			mu += (2.00/(Mainwindow.numbofmass+1))*q0[j]*Math.sin(j*r*Math.PI/(Mainwindow.numbofmass+1));
		}
		return mu;
	}
	
	public double W(int r){
		return 2*Math.sin((r*Math.PI)/(2*(Mainwindow.numbofmass+1)));
	}
	public double A(int r){
		return (double)Mainwindow.amps[r-1].getCenter().y - (3*Mainwindow.mainPanel.getHeight())/4;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				t += (double)Mainwindow.dtspinner.getValue();
					for(int j = 1; j <= Mainwindow.numbofmass; j++){
						q[j] = 0.00;
							for(int r = 1 ; r <= Mainwindow.numbofmass+1 ; r++){
								q[j] += (Math.sin(j*r*Math.PI/(Mainwindow.numbofmass+1))*((MU(r))*Math.cos(W(r)*t)));
								//System.out.println(A(r));
								mu = 0.00;
							}
							//q[j] = 100*Math.sin(2*Math.PI*t/4 + j) ;
					}
				for (int i = 0 ; i < Mainwindow.numbofmass ; i++){
					Mainwindow.amps[i].setCenter(new Point(Mainwindow.amps[i].getCenter().x,(int)((3*Mainwindow.mainPanel.getHeight()/4 + MU(i+1)))));
					mu = 0.00;
				}
				if(Mainwindow.Verbtn.isSelected()){
					for(int i = 0;i<Mainwindow.numbofmass;i++){
						Mainwindow.mass[i].setCenter(new Point(Mainwindow.mass[i].getCenter().x,(int)(q[i+1]+ Mainwindow.Opoint[i].y )));
					}	
				}
				if(Mainwindow.Horbtn.isSelected()){
					for(int i = 0;i<Mainwindow.numbofmass;i++){
						Mainwindow.mass[i].setCenter(new Point((int)(q[i+1]+Mainwindow.Opoint[i].x),(Mainwindow.mass[i].getCenter().y)));
					}
				}
				Mainwindow.mainPanel.repaint();
				th.sleep(5);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
