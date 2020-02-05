package view.estoque;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerEstoque;
import model.entities.Endereco;
import model.entities.Estoque;

public class ViewEstoqueSelect extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JList<Estoque> list = new JList<Estoque>();

	/**
	 * Launch the application.
	 */
	public void NewViewEstoqueSelect(){
		try {
			ViewEstoqueSelect dialog = new ViewEstoqueSelect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewEstoqueSelect(){ 
		ControllerEstoque estoqueController = new ControllerEstoque();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		list.setBounds(12, 0, 424, 232);
		contentPanel.add(list);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			List<Estoque> listEstoque = estoqueController.findAll();
			
			DefaultListModel<Estoque> listModel = new DefaultListModel<Estoque>();
	        
			for (Estoque percorrer : listEstoque) {
				listModel.addElement(percorrer);
			}
			list.setModel(listModel);
		}
	}

}
