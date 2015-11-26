package ReviewIHM;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Closeable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import IHM.IHM;

public class ReviewIHM extends JFrame{
	private ReviewIHMController myReviewIHMController = null;
	JFrame frame = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame containerParentFrame = null;
	JPanel containerPanel = new JPanel();	//General container
	JPanel firstRowPanel = new JPanel();	//Panel for name and modify button
	JPanel secondRowPanel = new JPanel();	//Panel for productor and release date
	JPanel secondRowBisPanel = new JPanel(); //Panel for kind and nationality
	JPanel secondRowTerPanel = new JPanel(); //Panel for editable date and created date
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
	JLabel secondDateLabel;
//	JComboBox<String> allActorsComboBox ;
	JPanel actorsSelectedPanel ;
	JLabel actorsLabel ;
	//JButton addActor ;
	JButton modifyButton = new JButton("Modify");
	JButton deleteButton = new JButton("Delete");

	
	public ReviewIHM(Handlers myController, JFrame parentJFrame, Review myReview ) {
		myReviewIHMController = new ReviewIHMController( myController, myReview );
		
		this.containerParentFrame = parentJFrame;
		
		startPopup();
	}
	
	/**
	 * Configuration of popup
	 * @param container
	 */
	public JFrame startPopup(){
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS)); 
		GridLayout gridLayoutRow = new GridLayout();
		gridLayoutRow.setColumns(5);
		gridLayoutRow.setRows(0);
		firstRowPanel.setLayout(gridLayoutRow);
		firstRowPanel.setName("firstRowPanel");
		gridLayoutRow = new GridLayout();
		gridLayoutRow.setColumns(4);
		gridLayoutRow.setRows(0);
		gridLayoutRow.setHgap(10);
		secondRowPanel.setLayout(gridLayoutRow); 
		secondRowBisPanel.setLayout(gridLayoutRow);
		gridLayoutRow = new GridLayout();
		gridLayoutRow.setColumns(2);
		gridLayoutRow.setRows(0);
		gridLayoutRow.setHgap(10);
		secondRowTerPanel.setLayout(gridLayoutRow);
		thirdRowPanel.setLayout(new BoxLayout(thirdRowPanel, BoxLayout.X_AXIS)); 
		fourRowPanel.setLayout(new BoxLayout(fourRowPanel, BoxLayout.X_AXIS));
		
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
				//action to apply
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
					try { //Save in controller
						if(myReviewIHMController.modifyReview(releaseTextField.getText(),productorTextField.getText(),summaryTextArea.getText(), kindTextField.getText(), nationnalityTextField.getText())){
							secondDateLabel.setText("Edited : "+ formatter.format(myReviewIHMController.getReview().getEditionDate()));

						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					};
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
		//"Edited : "+ new Date(myReviewIHMController.getReview().getEditionDate())
		releaseTextField = new JTextField( formatter.format(new Date(( myReviewIHMController.getReview().getRelease()))));
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
	 * Second row Ter
	 */
		JLabel firstDateLabel = new JLabel ("Created : "+ formatter.format(myReviewIHMController.getReview().getCreationDate()));
		
		secondDateLabel = new JLabel ("Edited : "+ formatter.format(myReviewIHMController.getReview().getEditionDate()));
		
		
		secondRowTerPanel.add(firstDateLabel);
		secondRowTerPanel.add(secondDateLabel);
		
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
		containerPanel.add(secondRowTerPanel);
//		containerPanel.add(thirdRowPanel);	actors pannel
		containerPanel.add(fourRowPanel);
	/*
	 * Launch popup
	 */
		frame = new JFrame( myReviewIHMController.getReview().getTitle() );
		frame.add(containerPanel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.setSize(450, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		return frame;

	}
	

	private void actionOnReviewRemoved(boolean removeHasSucceed) {
		if(removeHasSucceed){
			//If the remove was good, we can close this windows and update IHM
			containerParentFrame.repaint();
			closeChildren();
		} else {
			//else we show message error
			 javax.swing.JOptionPane.showMessageDialog(
	                    this,
	                    "You cannot remove this review",
	                    "Error to remove review",
	                    javax.swing.JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/** 
	 * IHM
	**/

	public void closeChildren()
	{
		((IHM) containerParentFrame).closeChildren(frame);
	}	
}
