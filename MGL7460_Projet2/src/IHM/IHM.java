package IHM;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Bean.Review;
import ReviewIHM.ReviewIHM;
import controllers.Handlers;

public class IHM extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Handlers controller = null;
	
	private JPanel container = new JPanel();
	private ImageIcon logoProject = new ImageIcon(new ImageIcon("pictures/logo-project.png").getImage().getScaledInstance(600, 150, Image.SCALE_DEFAULT));
	private JPanel panelTOP = new JPanel();
	private JPanel panelLOW = new JPanel();
	private JButton boutonAddReview;
	private List<String> listMovies;

	public IHM(Handlers controller){
		this.controller = controller;
		listMovies = controller.getAllReviews();
		startGui();
	}
 
	private void startGui(){
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); 
		panelTOP.setLayout(new BoxLayout(panelTOP, BoxLayout.X_AXIS)); 
		panelLOW.setLayout(new BoxLayout(panelLOW, BoxLayout.Y_AXIS)); 
	/*
	 *	Panel TOP 
	 */
		JPanel temporaire = new JPanel();
		temporaire.setLayout(new FlowLayout(FlowLayout.CENTER));
		temporaire.add(new JLabel(logoProject));
		panelTOP.add(temporaire);

	/*
	 *	Panel LOW 
	 */
	//Button add review
		boutonAddReview = new JButton("Add Review");
		boutonAddReview.setToolTipText("Click for add a new review");
		boutonAddReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SelecteMovieName(controller);
			}
		});
		boutonAddReview.setAlignmentX(Component.CENTER_ALIGNMENT);
	//Panel view all movies
		temporaire = new JPanel();
		temporaire.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel list = new JPanel();
		list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS)); 
		JScrollPane scrollabelPanel  = new JScrollPane(temporaire);
		
		for(int i=0; i<listMovies.size(); i++){
			final JButton buttonOneMovie = new JButton(listMovies.get(i));
			buttonOneMovie.setToolTipText("Click for see review");
			buttonOneMovie.setFont(new java.awt.Font("Serif",1,14));
			buttonOneMovie.setBackground(Color.GREEN);
			buttonOneMovie.setCursor( Cursor.getPredefinedCursor(12) );
			buttonOneMovie.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String selectedReviewName = buttonOneMovie.getText();
					Review selectedReview = controller.getReview(selectedReviewName);
					new ReviewIHM( controller, IHM.this, selectedReview );
				}
			});
			list.add(buttonOneMovie);
		}
		temporaire.add(list);		
		
		panelLOW.add(scrollabelPanel);
		panelLOW.add(boutonAddReview);
		
	/*
	 * Add all panels in container
	 */
		container.add(panelTOP);
		container.add(panelLOW);
		add(container);
		//pack();
		setTitle("Back to the IMDb");
		setSize(800,600);
		setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	}
}