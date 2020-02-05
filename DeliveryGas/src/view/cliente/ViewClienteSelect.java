package view.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerCliente;
import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.entities.Cliente;

public class ViewClienteSelect extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JList<Cliente> list = new JList<Cliente>();
	
	/**
	 * Launch the application.
	 */
	public void NewViewClienteSelect() {
		try {
			ViewClienteSelect dialog = new ViewClienteSelect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewClienteSelect() {
		ControllerCliente ClienteController = new ControllerCliente();
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
			
			List<Cliente> listCliente = ClienteController.findAll();
			
			DefaultListModel<Cliente> listModel = new DefaultListModel<Cliente>();
	        
			for (Cliente percorrer : listCliente) {
				listModel.addElement(percorrer);
			}
			list.setModel(listModel);
		}
	}

}
