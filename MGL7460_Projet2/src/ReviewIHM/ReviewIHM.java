package ReviewIHM;

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

import controllers.Handlers;
import Bean.Review;

public class ReviewIHM extends JFrame{
	private ReviewIHMController myReviewIHMController = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame containerParentFrame = null;
	JPanel containerPanel = new JPanel();	//General container
	JPanel firstRowPanel = new JPanel();	//Panel for name and modify button
	JPanel secondRowPanel = new JPanel();	//Panel for productor and release date
	JPanel secondRowBisPanel = new JPanel(); //Panel for kind and nationality
	JPanel thirdRowPanel = new JPanel();	//Panel for actors
	JPanel fourRowPanel = new JPanel();		//Panel for summery
	
	//Items modifiables
	JTextArea summaryTextArea;
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
	JButton modifyButton = new JButton("Modify");
	JButton deleteButton = new JButton("Delete");

	
	public ReviewIHM(Handlers myController, JFrame parentJFrame, Review myReview ) {
		myReviewIHMController = new ReviewIHMController( myController, myReview );
		
		this.containerParentFrame = parentJFrame;
		
		modifyButton.setName("Modify");
		deleteButton.setName("Delete");

		startPopup();
	}
	
	/**
	 * Configuration of popup
	 * @param container
	 */
	public JFrame startPopup(){
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

		JLabel nameOfMovieLabel = new JLabel (myReviewIHMController.getReview().getTitle(), SwingConstants.CENTER);
		nameOfMovieLabel.setName("nameOfMovieLabel");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean removeHasSucceed = myReviewIHMController.removeReview(nameOfMovieLabel.getText());
				// action a effectuer
				actionOnReviewRemoved(removeHasSucceed);	
			}	
		});
		
		
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modifyButton.getText() == "Modify"){
					modifyButton.setText("Validation");
					actorsListTemp = new ArrayList<String>();
					//TODO REFRESH  all actors in combobox
					summaryTextArea.setEditable(true);
					productorTextField.setEditable(true);
					releaseTextField.setEditable(true);
					kindTextField.setEditable(true);
					nationnalityTextField.setEditable(true);
					actorsListLabel.hide() ;
					actorsListModifyPanel.show() ;
					
				} else {
					modifyButton.setText("Modify");
					actorsLabel.setText("Actors : ");
					summaryTextArea.setEditable(false);
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
		modifyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		modifyButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		nameOfMovieLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
		nameOfMovieLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		firstRowPanel.add(flecheOppLabel);
		firstRowPanel.add(nameOfMovieLabel);
		firstRowPanel.add(flecheLabel);
		firstRowPanel.add(deleteButton);
		firstRowPanel.add(modifyButton);

		
	/*
	 * Second row	
	 */	
		JLabel productorLabel = new JLabel ("Productor : ");
		productorTextField = new JTextField( myReviewIHMController.getReview().getProducer() );
		productorTextField.setEditable(false);
		
		JLabel releaseLabel = new JLabel ("Release : ");
		releaseTextField = new JTextField( String.valueOf( myReviewIHMController.getReview().getRelease() ) );
		releaseTextField.setEditable(false);
		
		
		secondRowPanel.add(productorLabel);
		secondRowPanel.add(productorTextField);
		secondRowPanel.add(releaseLabel);
		secondRowPanel.add(releaseTextField);
	/*
	 * Second row bis
	 */
		JLabel kindLabel = new JLabel ("Kind : ");
		kindTextField = new JTextField( myReviewIHMController.getReview().getKind() );
		kindTextField.setEditable(false);
		
		JLabel nationnalityLabel = new JLabel ("Nationnality : ");
		nationnalityTextField = new JTextField( myReviewIHMController.getReview().getNationnality() );
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
		for(int i = 0; i < myReviewIHMController.getReview().getActors().size();i++){
			if(i !=0){
				listOfActors = listOfActors.concat(" / ");
			}
			//TODO AVEC LE CONTROLEUR JUSTE DEMANDER LE NOM DE L ACTEUR EN FCT DE I
			listOfActors = listOfActors.concat((myReviewIHMController.getReview().getActors().get(i)).toString());
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
		JLabel summaryLabel = new JLabel("Summery : ");
		summaryLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		summaryLabel.setAlignmentX(Component.TOP_ALIGNMENT);

		summaryTextArea = new JTextArea( myReviewIHMController.getReview().getSummary() );
		summaryTextArea.setEditable(false);
		summaryTextArea.setAlignmentY(Component.CENTER_ALIGNMENT);
		summaryTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		fourRowPanel.add(summaryLabel);
		fourRowPanel.add(summaryTextArea);
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
		frame = new JFrame( myReviewIHMController.getReview().getTitle() );
		frame.add(containerPanel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.pack();
		frame.setSize(450, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		return frame;

	}
	

	private void actionOnReviewRemoved(boolean removeHasSucceed) {
		if(removeHasSucceed)
			closeChildren();
		// TODO afficher à l'utilisateur qu'il y a un problème
	}
	
	/** 
	 * IHM
	**/

	public void closeChildren()
	{
		containerParentFrame.dispose();
	}	
}
