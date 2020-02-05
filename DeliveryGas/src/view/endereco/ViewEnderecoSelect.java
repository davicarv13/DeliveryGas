package view.endereco;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerEndereco;
import model.dao.DaoFactory;
import model.dao.EnderecoDao;
import model.entities.Endereco;

public class ViewEnderecoSelect extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JList<Endereco> list = new JList<Endereco>();

	/**
	 * Launch the application.
	 */
	public void NewViewEnderecoSelect() {
		try {
			ViewEnderecoSelect dialog = new ViewEnderecoSelect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewEnderecoSelect() {
		ControllerEndereco enderecoController = new ControllerEndereco();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		list.setBounds(12, 0, 424, 232);
		contentPanel.add(list);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{	
			List<Endereco> listEnderecos = enderecoController.findAll();
			
			DefaultListModel<Endereco> listModel = new DefaultListModel<Endereco>();
	        
			for (Endereco percorrer : listEnderecos) {
				listModel.addElement(percorrer);
			}
			list.setModel(listModel);
		}
		
	}

}
