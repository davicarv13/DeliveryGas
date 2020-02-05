package view.armazem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerArmazem;
import model.dao.ArmazemDao;
import model.dao.DaoFactory;
import model.entities.Armazem;

public class ViewArmazemSelect extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JList<Armazem> list = new JList<Armazem>();
	
	/**
	 * Launch the application.
	 */
	public void NewViewArmazemSelect() {
		try {
			ViewArmazemSelect dialog = new ViewArmazemSelect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewArmazemSelect() {
		ControllerArmazem armazemController = new ControllerArmazem();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		list.setBounds(12, 0, 424, 232);
		contentPanel.add(list);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			List<Armazem> listArmazem = armazemController.findAll();
			
			DefaultListModel<Armazem> listModel = new DefaultListModel<Armazem>();
	        
			for (Armazem percorrer : listArmazem) {
				listModel.addElement(percorrer);
			}
			list.setModel(listModel);
		}
	}

}
