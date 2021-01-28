package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import shapes.*;

public class PaintPanel extends JPanel 
{
	private static final long serialVersionUID = 5898156464924142361L;
	ListenersCollection listener = new ListenersCollection();
	public static int mousex,mousey;
	public PaintPanel()
	{
		addMouseListener(listener);
		addMouseMotionListener(listener);
		setFocusable(true);
		addKeyListener(listener);
		addFocusListener(listener);
	}
	void render(Graphics G)
	{// draw panel
		G.setColor(Color.white);
		G.fillRect(0, 0, this.getWidth(), this.getHeight());
		G.setColor(Color.black);
		G.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
		G.drawLine(0, (int)3*this.getHeight()/4, this.getWidth(), (int)3*this.getHeight()/4);
		G.drawLine(0, (int)this.getHeight()/4, this.getWidth(), (int)this.getHeight()/4);
		Graphics2D g2 = (Graphics2D) G;
		g2.setStroke(new BasicStroke(4));
		g2.drawLine(0, (int)this.getHeight()/2, this.getWidth(), (int)this.getHeight()/2);
		G.setFont(new Font("Arial",Font.BOLD,15));
		G.drawString("Simulation :", 10, 20);
		G.drawString("Amplitudes :", 10, this.getHeight()/2 + 20);
		Iterator<Shape> it = shapes.Shape.Allshapes.iterator();
		while (it.hasNext()) {
			it.next().Render(G);
		}	 
		
	}

	class ListenersCollection implements MouseListener, MouseMotionListener, KeyListener, FocusListener
	{

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			mousex = arg0.getPoint().x;
			mousey = arg0.getPoint().y;
			Iterator<Shape> it = shapes.Shape.Allshapes.iterator();
			while (it.hasNext()) {
				Shape currentshape = it.next();
				if(currentshape.isIn(new Point(arg0.getPoint().x , arg0.getPoint().y))){
					if(currentshape.getType().equals("Mass")){
						if(Mainwindow.Horbtn.isSelected()){
							currentshape.setCenter(new Point(PaintPanel.mousex , currentshape.getCenter().y));
						}
						if(Mainwindow.Verbtn.isSelected()){
							currentshape.setCenter(new Point(currentshape.getCenter().x , PaintPanel.mousey));
						}
						Mainwindow.mainPanel.repaint();
					}
					//if(currentshape.getType().equals("AMP")){
						//	currentshape.setCenter(new Point(currentshape.getCenter().x , PaintPanel.mousey));
							//Mainwindow.mainPanel.repaint();
					//}
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			//if(arg0.getButton() == MouseEvent.BUTTON1){
				//Iterator<Shape> it = shapes.Shape.Allshapes.iterator();
				
			//}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void keyPressed(KeyEvent arg0){
			// TODO Auto-generated method stub
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}	
	}
	public void paintComponent(Graphics G)
	{
		super.paintComponent(G);
		render(G);
	}
}
