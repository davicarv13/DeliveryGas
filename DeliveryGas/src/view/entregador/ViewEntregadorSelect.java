package view.entregador;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerEntregador;
import model.dao.EntregadorDao;
import model.dao.DaoFactory;
import model.entities.Entregador;

public class ViewEntregadorSelect extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JList<Entregador> list = new JList<Entregador>();
	
	/**
	 * Launch the application.
	 */
	public void NewViewEntregadorSelect() {
		try {
			ViewEntregadorSelect dialog = new ViewEntregadorSelect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewEntregadorSelect() {
		ControllerEntregador entregadorController = new ControllerEntregador();
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
			
			List<Entregador> listEntregador = entregadorController.findAll();
			
			DefaultListModel<Entregador> listModel = new DefaultListModel<Entregador>();
	        
			for (Entregador percorrer : listEntregador) {
				listModel.addElement(percorrer);
			}
			list.setModel(listModel);
		}
	}

}
