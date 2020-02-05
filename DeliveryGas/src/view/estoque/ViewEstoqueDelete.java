package view.estoque;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerEstoque;
import model.entities.Estoque;

public class ViewEstoqueDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ControllerEstoque estoqueController = new ControllerEstoque();

	/**
	 * Launch the application.
	 */
	public void NewViewEstoqueDelete() {
		try {
			ViewEstoqueDelete dialog = new ViewEstoqueDelete();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewEstoqueDelete() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCodEstoque = new JLabel("Selecione o código do estoque");
		lblCodEstoque.setBounds(147, 72, 174, 16);
		contentPanel.add(lblCodEstoque);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(183, 135, 98, 26);
		contentPanel.add(btnExcluir);
		
		JComboBox comboBoxCodEstoque = new JComboBox();
		comboBoxCodEstoque.setBounds(117, 100, 239, 25);
		
		atualizaComboBox(comboBoxCodEstoque);
		
		contentPanel.add(comboBoxCodEstoque);
		{
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(estoqueController.deleteById((Integer)comboBoxCodEstoque.getSelectedItem()) == true){
						atualizaComboBox(comboBoxCodEstoque);
						JOptionPane.showMessageDialog(null, "Estoque excluído", "Estoque", JOptionPane.INFORMATION_MESSAGE);
						
					}
					else{
						JOptionPane.showMessageDialog(null, "Estoque não excluído", "Cliente", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
	}
	
	public void atualizaComboBox(JComboBox comboBoxCodEstoque){
		comboBoxCodEstoque.removeAllItems();
		List<Estoque> listEstoque = estoqueController.findAll();
		for(Estoque percorrer : listEstoque){
			comboBoxCodEstoque.addItem(percorrer.getCodEstoque());
		}
	}
}
