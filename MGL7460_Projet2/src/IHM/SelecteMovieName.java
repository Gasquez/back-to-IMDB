package IHM;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ReviewIHM.ReviewIHMController;
import controllers.Handlers;

public class SelecteMovieName extends JFrame {
	
	JFrame frame;
	JPanel containerPanel;
	JLabel nameLabel, dateLabel;
	JTextField nameTextField, dateTextField;
	JButton validationButton;
	Handlers controller;
	
	public SelecteMovieName(Handlers controller){
		this.controller = controller;
		this.startGui();
	}
	
	public void startGui(){
		JPanel namePanel = new JPanel();
		containerPanel = new JPanel();
		
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS)); 
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		
		nameLabel = new JLabel("Name of movie : ");
		nameTextField = new JTextField("Enter here the movie name.");
		
		dateLabel = new JLabel("date of movie : ");
		dateTextField = new JTextField("Enter here the movie release date.");
		
		validationButton = new JButton("Ok");
		validationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat format = new SimpleDateFormat("MMM d, yyyy", Locale.CANADA);
				Date date;
				try {
					date = format.parse(dateTextField.getText());
					controller.addReview(nameTextField.getText(), date.getTime());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		namePanel.add(nameLabel);
		namePanel.add(nameTextField);
		namePanel.add(dateLabel);
		namePanel.add(dateTextField);
		
		containerPanel.add(namePanel);
		containerPanel.add(validationButton);

		
		frame = new JFrame("Choose movie name");
		frame.add(containerPanel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
