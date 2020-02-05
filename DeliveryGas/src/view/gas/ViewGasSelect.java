package view.gas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerGas;
import model.entities.Armazem;
import model.entities.Gas;

public class ViewGasSelect extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JList<Gas> list = new JList<Gas>();

	/**
	 * Launch the application.
	 */
	public void NewViewGasSelect() {
		try {
			ViewGasSelect dialog = new ViewGasSelect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewGasSelect() {
		ControllerGas gasController = new ControllerGas();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		list.setBounds(12, 0, 424, 232);
		contentPanel.add(list);
		{
			List<Gas> listGas = gasController.findAll();
			
			DefaultListModel<Gas> listModel = new DefaultListModel<Gas>();
	        
			for (Gas percorrer : listGas) {
				listModel.addElement(percorrer);
			}
			list.setModel(listModel);
		}
	}
}
