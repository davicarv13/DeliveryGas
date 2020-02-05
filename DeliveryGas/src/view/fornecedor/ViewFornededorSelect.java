package view.fornecedor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerFornecedor;
import model.dao.FornecedorDao;
import model.dao.DaoFactory;
import model.entities.Fornecedor;

public class ViewFornededorSelect extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JList<Fornecedor> list = new JList<Fornecedor>();
	
	/**
	 * Launch the application.
	 */
	public void NewViewFornecedorSelect() {
		try {
			ViewFornededorSelect dialog = new ViewFornededorSelect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewFornededorSelect() {
		ControllerFornecedor FornecedorController = new ControllerFornecedor();
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
			
			List<Fornecedor> listFornecedor = FornecedorController.findAll();
			
			DefaultListModel<Fornecedor> listModel = new DefaultListModel<Fornecedor>();
	        
			for (Fornecedor percorrer : listFornecedor) {
				listModel.addElement(percorrer);
			}
			list.setModel(listModel);
		}
	}

}
