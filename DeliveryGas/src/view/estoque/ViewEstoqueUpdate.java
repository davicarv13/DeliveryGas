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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControllerEstoque;
import model.entities.Estoque;

public class ViewEstoqueUpdate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtQtde;
	private final JLabel lblCodEstoque = new JLabel("Selecione o código do estoque");
	private final JComboBox comboBoxCodEstoque = new JComboBox();
	private final JButton btnAtualizar = new JButton("Atualizar");

	/**
	 * Launch the application.
	 */
	public void NewViewEstoqueUpdate() {
		try {
			ViewEstoqueUpdate dialog = new ViewEstoqueUpdate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewEstoqueUpdate() {
		ControllerEstoque estoqueController = new ControllerEstoque();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblQtde = new JLabel("Quantidade de gás");
		lblQtde.setBounds(12, 12, 147, 16);
		contentPanel.add(lblQtde);
		
		txtQtde = new JTextField();
		txtQtde.setBounds(12, 40, 104, 20);
		contentPanel.add(txtQtde);
		txtQtde.setColumns(10);
		lblCodEstoque.setBounds(171, 12, 188, 16);
		
		contentPanel.add(lblCodEstoque);
		comboBoxCodEstoque.setBounds(171, 38, 168, 25);
		
		contentPanel.add(comboBoxCodEstoque);
		btnAtualizar.setBounds(171, 214, 98, 26);
		
		List<Estoque> listEstoque = estoqueController.findAll();
		
		for(Estoque percorrer : listEstoque){
			comboBoxCodEstoque.addItem(percorrer.getCodEstoque());
		}
		
		contentPanel.add(btnAtualizar);
		
		{
			btnAtualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Estoque obj = new Estoque((Integer)comboBoxCodEstoque.getSelectedItem(), Integer.parseInt(txtQtde.getText()), null, null);
					if(estoqueController.update(obj) == true){
						JOptionPane.showMessageDialog(null, "Estoque atualizado", "Estoque", JOptionPane.INFORMATION_MESSAGE);
						
					}
					else{
						JOptionPane.showMessageDialog(null, "Estoque não atualizado", "Estoque", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
	}
}
