package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelecteMovieName extends JFrame {
	
	JFrame frame;
	JPanel containerPanel;
	JLabel nameLabel;
	JTextField nameTextField;
	JButton validationButton;
	
	public SelecteMovieName(){
		this.startGui();
		
	}
	
	public void startGui(){
		JPanel namePanel = new JPanel();
		containerPanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS)); 
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS)); 
		nameLabel = new JLabel("Name of movie : ");
		nameTextField = new JTextField("Enter here the movie name.");
		validationButton = new JButton("Ok");
		validationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO mycontroleur.addReview(nameTextField.getText());
			}
		});

		
		namePanel.add(nameLabel);
		namePanel.add(nameTextField);
		
		containerPanel.add(namePanel);
		containerPanel.add(validationButton);

		
		frame = new JFrame("Choose movie name");
		frame.add(containerPanel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
		frame.pack();
		frame.setSize(450, 100);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
