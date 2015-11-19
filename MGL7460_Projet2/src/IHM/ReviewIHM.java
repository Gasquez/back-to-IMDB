package IHM;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.SwingConstants;

import Bean.Review;

public class ReviewIHM extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String movieName;
	long release;
	String productor;
	String summery;
	String kind;
	String nationnality;
	List<String> actors = new ArrayList<String>();
	
	JPanel containerParentPanel = new JPanel();
	JPanel containerPanel = new JPanel();	//General container
	JPanel firstRowPanel = new JPanel();	//Panel for name and modify button
	JPanel secondRowPanel = new JPanel();	//Panel for productor and release date
	JPanel secondRowBisPanel = new JPanel(); //Panel for kind and nationality
	JPanel thirdRowPanel = new JPanel();	//Panel for actors
	JPanel fourRowPanel = new JPanel();		//Panel for summery
	
	//Items modifiables
	JTextArea summeryTextArea;
	JTextField productorTextField;
	JTextField releaseTextField;
	JTextField kindTextField;
	JTextField nationnalityTextField;
	JLabel actorsListLabel ;
	JPanel actorsListModifyPanel ;
	List<String> actorsListTemp ;
//	JComboBox<String> allActorsComboBox ;
	JPanel actorsSelectedPanel ;
	JLabel actorsLabel ;
	//JButton addActor ;
	JButton modifyReleaseButton = new JButton("Modify");
	JButton deleateReleaseButton = new JButton("Deleate");

	
	/**
	 * Public constructror
	 * 
	 * @param containerParent
	 * @param movieName
	 * @param release
	 * @param productor
	 * @param summery
	 * @param kind
	 * @param nationnality
	 * @param actors - array of integer
	 */
	public ReviewIHM( JPanel containerParent, Review myReview )
	{
		this.movieName = myReview.getTitle();
		this.release = myReview.getRelease();
		this.productor = myReview.getProducer();
		this.summery = myReview.getSummary();
		this.actors = myReview.getActors();
		this.kind = myReview.getKind();
		this.nationnality = myReview.getNationnality();
		
		this.containerParentPanel = containerParent ;
		
		modifyReleaseButton.setName("Modify");
		deleateReleaseButton.setName("Deleate");

		startPopup(containerParentPanel);
	}
	
	/**
	 * Configuration of popup
	 * @param container
	 */
	public JFrame startPopup(JPanel container){
		//PopupFactory factory = PopupFactory.getSharedInstance();	new FlowLayout()
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS)); 
		//firstRowPanel.setLayout(new BoxLayout(firstRowPanel, BoxLayout.X_AXIS)); 
		GridLayout gridLayoutRow = new GridLayout();
		gridLayoutRow.setColumns(5);
		gridLayoutRow.setRows(0);
	//	gridLayoutRow.setHgap(5);
	//	gridLayoutRow.setVgap(50);
		firstRowPanel.setLayout(gridLayoutRow);
		firstRowPanel.setName("firstRowPanel");
		gridLayoutRow = new GridLayout();
		gridLayoutRow.setColumns(4);
		gridLayoutRow.setRows(0);
		gridLayoutRow.setHgap(10);
		secondRowPanel.setLayout(gridLayoutRow); 
		secondRowBisPanel.setLayout(gridLayoutRow); 
		thirdRowPanel.setLayout(new BoxLayout(thirdRowPanel, BoxLayout.X_AXIS)); 
		fourRowPanel.setLayout(new BoxLayout(fourRowPanel, BoxLayout.X_AXIS));
		
		JFrame frame = null;
	/*
	 * First row	
	 */
		ImageIcon flecheOpp = new ImageIcon(new ImageIcon("pictures/fleche-opp.png").getImage().getScaledInstance(50, 25, Image.SCALE_DEFAULT));
		JLabel flecheOppLabel = new JLabel (flecheOpp);

		ImageIcon fleche = new ImageIcon(new ImageIcon("pictures/fleche.png").getImage().getScaledInstance(50, 25, Image.SCALE_DEFAULT));
		JLabel flecheLabel = new JLabel (fleche);

		JLabel nameOfMovieLabel = new JLabel (this.getMovieName(), SwingConstants.CENTER);
		nameOfMovieLabel.setName("nameOfMovieLabel");
		deleateReleaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO myControleur.deleateReview(movieName);
			}
		});
		
		modifyReleaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modifyReleaseButton.getText() == "Modify"){
					modifyReleaseButton.setText("Validation");
					actorsListTemp = new ArrayList<String>();
					//TODO REFRESH  all actors in combobox
					summeryTextArea.setEditable(true);
					productorTextField.setEditable(true);
					releaseTextField.setEditable(true);
					kindTextField.setEditable(true);
					nationnalityTextField.setEditable(true);
					actorsListLabel.hide() ;
					actorsListModifyPanel.show() ;
					
				} else {
					modifyReleaseButton.setText("Modify");
					actorsLabel.setText("Actors : ");
					summeryTextArea.setEditable(false);
					productorTextField.setEditable(false);
					releaseTextField.setEditable(false);
					kindTextField.setEditable(false);
					nationnalityTextField.setEditable(false);
					actorsListLabel.show() ;
					actorsListModifyPanel.hide() ;
					//TODO LUNCH SAVE TO CONTROLEUR
					//TODO Refresh actors
				}
				

			}
		});
		modifyReleaseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		modifyReleaseButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		nameOfMovieLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
		nameOfMovieLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		firstRowPanel.add(flecheOppLabel);
		firstRowPanel.add(nameOfMovieLabel);
		firstRowPanel.add(flecheLabel);
		firstRowPanel.add(deleateReleaseButton);
		firstRowPanel.add(modifyReleaseButton);

		
	/*
	 * Second row	
	 */	
		JLabel productorLabel = new JLabel ("Productor : ");
		productorTextField = new JTextField(this.getProductor());
		productorTextField.setEditable(false);
		
		JLabel releaseLabel = new JLabel ("Release : ");
		releaseTextField = new JTextField( String.valueOf( this.getRelease() ) );
		releaseTextField.setEditable(false);
		
		
		secondRowPanel.add(productorLabel);
		secondRowPanel.add(productorTextField);
		secondRowPanel.add(releaseLabel);
		secondRowPanel.add(releaseTextField);
	/*
	 * Second row bis
	 */
		JLabel kindLabel = new JLabel ("Kind : ");
		kindTextField = new JTextField(this.getKind());
		kindTextField.setEditable(false);
		
		JLabel nationnalityLabel = new JLabel ("Nationnality : ");
		nationnalityTextField = new JTextField(this.getNationnality());
		nationnalityTextField.setEditable(false);
		
		
		secondRowBisPanel.add(kindLabel);
		secondRowBisPanel.add(kindTextField);
		secondRowBisPanel.add(nationnalityLabel);
		secondRowBisPanel.add(nationnalityTextField);
		
	/*
	 * Third row	
	 */
	
		JPanel actorsPanel = new JPanel();
		actorsPanel.setLayout(new BoxLayout(actorsPanel, BoxLayout.X_AXIS));
		actorsLabel = new JLabel ("Actors : ");
		String listOfActors = "";
		for(int i = 0; i < getActors().size();i++){
			if(i !=0){
				listOfActors = listOfActors.concat(" / ");
			}
			//TODO AVEC LE CONTROLEUR JUSTE DEMANDER LE NOM DE L ACTEUR EN FCT DE I
			listOfActors = listOfActors.concat((getActors().get(i)).toString());
		}
		actorsListTemp = new ArrayList<String>();	//For save selected actors
		actorsListLabel = new JLabel(listOfActors);
		actorsListModifyPanel = new JPanel();
		actorsListModifyPanel.setLayout(new BoxLayout(actorsListModifyPanel, BoxLayout.X_AXIS));
//		allActorsComboBox = new JComboBox<String>();
		actorsSelectedPanel = new JPanel();
		actorsSelectedPanel.setLayout(new BoxLayout(actorsSelectedPanel, BoxLayout.Y_AXIS)); 


//		for(int i = 0 ; i < getAllActors().size(); i++){
//			allActorsComboBox.addItem(getAllActors().get(i));
//		}
//		addActor = new JButton ("Add actor");
//		addActor.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				actorsListTemp.add((String) allActorsComboBox.getSelectedItem());
//				actorsSelectedPanel.add(new JLabel(allActorsComboBox.getSelectedItem().toString()));
//				allActorsComboBox.removeItem(allActorsComboBox.getSelectedItem());
//				if(allActorsComboBox.getItemAt(0) == null){
//					allActorsComboBox.hide();
//					addActor.hide();
//					actorsLabel.setText("No more actors to add.  ");
//				}
//				actorsSelectedPanel.updateUI();
//			}
//		});

//		actorsListModifyPanel.add(allActorsComboBox);
//		actorsListModifyPanel.add(addActor);
		actorsListModifyPanel.add(new JLabel(" Actors selected : "));
		actorsListModifyPanel.add(actorsSelectedPanel);
		actorsListModifyPanel.hide();
		
		actorsPanel.add(actorsLabel);
		actorsPanel.add(actorsListModifyPanel);
		actorsPanel.add(actorsListLabel);

		
		thirdRowPanel.add(actorsPanel);
	/*
	 * Four row	
	 */
		JLabel summeryLabel = new JLabel("Summery : ");
		summeryLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		summeryLabel.setAlignmentX(Component.TOP_ALIGNMENT);

		summeryTextArea = new JTextArea(getSummery());
		summeryTextArea.setEditable(false);
		summeryTextArea.setAlignmentY(Component.CENTER_ALIGNMENT);
		summeryTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		fourRowPanel.add(summeryLabel);
		fourRowPanel.add(summeryTextArea);
	/*
	 * Add all rows panels
	 */
		containerPanel.add(firstRowPanel);
		containerPanel.add(secondRowPanel);
		containerPanel.add(secondRowBisPanel);
		containerPanel.add(thirdRowPanel);
		containerPanel.add(fourRowPanel);
	/*
	 * Launch popup
	 */
		frame = new JFrame(this.getMovieName());
		frame.add(containerPanel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.pack();
		frame.setSize(450, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		return frame;

	}
	public String getMovieName(){
		return movieName;
	}
	
	public long getRelease(){
		return release;
	}
	
	public String getProductor(){
		return productor;
	}

	public String getSummery(){
		return summery;
	}

	public List<String> getActors(){
		return actors;
	}
	
	public String getKind(){
		return kind;
	}
	
	public String getNationnality(){
		return nationnality;
	}
	
	public JButton getButton(){
		return modifyReleaseButton;
	}
	
	public List<String> getAllActors(){
		//TODO MESSAGE TO CONTROLEUR
		//return controleur.getAllActors();
		//TODO FOR TEST :
		ArrayList<String> actors = new ArrayList<String>();
		for(int i = 0 ; i< 5 ; i++)
			actors.add("actors "+ i);
		return actors;
	}
}
