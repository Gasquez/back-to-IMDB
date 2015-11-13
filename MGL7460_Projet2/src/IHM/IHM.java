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

public class IHM extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel container = new JPanel();
	ImageIcon logoProject = new ImageIcon(new ImageIcon("pictures/logo-project.png").getImage().getScaledInstance(600, 150, Image.SCALE_DEFAULT));
	JPanel panelTOP = new JPanel();
	JPanel panelLOW = new JPanel();
	JButton boutonAddReview;
	List<String> listMovies;

	public IHM(){
		//TODO RECOLTER LA LISTE DES FILMS PAR LE CONTROLEUR
		//TODO FOR TESTS :
		listMovies = new ArrayList<String>();
		listMovies.add("Le retour du caméléon sanglant");
		listMovies.add("3 petits chats et un chien");
		for(int i = 0; i < 15;i++){
			listMovies.add("Les WaTuGa");
		}
	}
 
	public void startGui(){
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
				new SelecteMovieName();
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
					//TODO OPEN POPUP SEE A REVIEW
					//getControleur().openReview(buttonOneMovie.getText());
					//TODO FOR THE TEST
					ArrayList<Integer> listTrash = new ArrayList<Integer>();
					listTrash.add(1);
					listTrash.add(10);
					new ReviewIHM(panelLOW,buttonOneMovie.getText(),"2011","The prod", "summery badass","horror","french", listTrash);
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
		pack();
		setTitle("Back to the IMDb");
		setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(800,600);
	    setVisible(true);
	}
    public static void main(String[] args) {
    	IHM ihm = new IHM();
    	ihm.startGui();
    }
    
	public void cleanUP() {
		container = new JPanel();
		logoProject = new ImageIcon(new ImageIcon("pictures/logo-project.png").getImage().getScaledInstance(600, 150, Image.SCALE_DEFAULT));
		panelTOP = new JPanel();
		panelLOW = new JPanel();
		boutonAddReview = null;
		listMovies = null;		
	}
}